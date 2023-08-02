package ro.nymphis.facades;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.nymphis.exception.checkout.CheckoutException;
import ro.nymphis.exception.checkout.CheckoutExceptionMessages;
import ro.nymphis.model.cart.Cart;
import ro.nymphis.model.cart.CartItem;
import ro.nymphis.model.order.Order;
import ro.nymphis.model.order.OrderItem;
import ro.nymphis.service.cart.CartService;
import ro.nymphis.service.order.OrderService;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Getter
public class CheckoutFacadeImpl implements CheckoutFacade {

    private final OrderService orderService;
    private final CartService cartService;

    @Transactional
    @Override
    public void checkOut() throws CheckoutException {
        Cart cart = getCartService().getCart();

        checkCartNotEmpty(cart);
        checkCartItemsNotOutOfStock(cart);

        Order order = buildOrderFromCart(cart);

        getOrderService().upsertOrder(order);
    }

    private void checkCartNotEmpty(Cart cart) throws CheckoutException {
        if(cart.getCartItems().size() == 0)
            throw new CheckoutException(CheckoutExceptionMessages.EMPTY_CART.getMessage());
    }

    private void checkCartItemsNotOutOfStock(Cart cart) throws CheckoutException {
        List<CartItem> outOfStockCartItems = cart.getCartItems()
                .stream()
                .filter(cartItem -> cartItem.getProduct().getStock() < cartItem.getQuantity())
                .collect(Collectors.toList());

        if(outOfStockCartItems.size() > 0) {
            cleanUpCart(cart);
            throw new CheckoutException(CheckoutExceptionMessages.ITEMS_OUT_OF_STOCK.getMessage());
        }
    }

    private void cleanUpCart(Cart cart) {
        List<CartItem> cartItems = cart.getCartItems()
                .stream()
                .peek(cartItem -> cartItem.setQuantity(Math.min(cartItem.getQuantity(), cartItem.getProduct().getStock())))
                .filter(cartItem -> cartItem.getQuantity() > 0)
                .collect(Collectors.toList());

        cart.getCartItems().clear();
        cart.setCartItems(cartItems);

        getCartService().updateCart(cart);
    }

    private Order buildOrderFromCart(Cart cart) {
        Order order;

        Optional<Order> orderOptional = getOrderService().getPendingOrder(cart.getUser());

        if (orderOptional.isPresent()) {
            order = orderOptional.get();
            order.getOrderItems().clear();
        } else {
            order = new Order();
            order.setUser(cart.getUser());
        }

        List<OrderItem> orderItems = cart.getCartItems()
                .stream()
                .map(cartItem -> {
                    OrderItem orderItem = new OrderItem();
                    orderItem.setOrder(order);
                    orderItem.setProduct(cartItem.getProduct());
                    orderItem.setPrice(cartItem.getProduct().getPrice());
                    orderItem.setQuantity(cartItem.getQuantity());

                    return orderItem;
                })
                .collect(Collectors.toList());

        order.setTotalSum(orderItems.stream()
                .map(orderItem -> orderItem.getPrice() * orderItem.getQuantity())
                .reduce(0.0, Double::sum));
        order.setOrderItems(orderItems);

        return order;
    }
}
