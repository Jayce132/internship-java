package ro.nymphis.facades;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;
import ro.nymphis.exception.checkout.CheckoutException;
import ro.nymphis.exception.checkout.CheckoutExceptionMessages;
import ro.nymphis.model.cart.Cart;
import ro.nymphis.model.order.Order;
import ro.nymphis.model.order.OrderStatus;
import ro.nymphis.model.product.Product;
import ro.nymphis.model.user.User;
import ro.nymphis.service.cart.CartService;
import ro.nymphis.service.email.EmailService;
import ro.nymphis.service.order.OrderService;
import ro.nymphis.service.product.ProductService;
import ro.nymphis.utils.SecurityUtils;

@Component
@RequiredArgsConstructor
@Getter
public class PlaceOrderFacadeImpl implements PlaceOrderFacade {

	private final OrderService orderService;
	private final CartService cartService;
	private final ProductService productService;
	private final EmailService emailService;

	@Override
	@Transactional
	public Order placeOrder() throws CheckoutException {

		final User user = SecurityUtils.getAuthenticatedUser();

		final Order order = getOrderService().getPendingOrder(user)
				.orElseThrow(() -> new CheckoutException(CheckoutExceptionMessages.NO_PENDING_ORDER.getMessage()));

		if (!order.getOrderStatus().equals(OrderStatus.FILLED.toString())) {
			throw new CheckoutException(CheckoutExceptionMessages.ORDER_NOT_FILLED.getMessage());
		}
		order.setOrderStatus(OrderStatus.PLACED.toString());
		getOrderService().upsertOrder(order);
		final Cart cart = getCartService().getCart();
		cart.getCartItems().clear();
		order.getOrderItems().forEach(orderItem -> {
			Product product = orderItem.getProduct();
			product.setStock(product.getStock() - orderItem.getQuantity());
			getProductService().upsertProduct(product);
		});

		getEmailService().send(order.getUser().getEmail(), "Your order has been placed!", getEmailService().getEmailBody(order));
        return order;
	}
}
