package ro.nymphis.service.cart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;
import ro.nymphis.dto.cart.CartItemRequestDTO;
import ro.nymphis.model.cart.Cart;
import ro.nymphis.model.cart.CartItem;
import ro.nymphis.model.product.Product;
import ro.nymphis.model.user.User;
import ro.nymphis.repository.CartItemRepository;
import ro.nymphis.repository.CartRepository;
import ro.nymphis.repository.UserRepository;
import ro.nymphis.repository.ProductRepository;
import ro.nymphis.utils.SecurityUtils;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
@Getter
public class CartServiceImpl implements CartService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final CartItemRepository cartItemRepository;
    private final CartRepository cartRepository;

    @Override
    public void addProduct(Long id, int quantity) {
        Cart cart = getCart();

        Product product = getProductRepository().getById(id);

        if (checkIfCartContainsProduct(product, cart)) {
            cart.getCartItems()
                    .stream()
                    .filter(cartItem -> cartItem.getProduct().getId().equals(product.getId()))
                    .forEach(cartItem -> cartItem.setQuantity(cartItem.getQuantity() + quantity));

        } else {
            CartItem cartItem = new CartItem();
            cartItem.setProduct(product);
            cartItem.setQuantity(quantity);
            cartItem.setCart(cart);

            cart.getCartItems().add(cartItem);

            getCartRepository().save(cart);
        }

    }

    public void removeProduct(Long id) {
        Cart cart = getCart();
        cart.getCartItems().removeIf(cartItem -> cartItem.getProduct().getId().equals(id));
        getCartRepository().save(cart);
    }

    public void updateCart(CartItemRequestDTO cartItemRequestDTO) {
        Cart cart = getCart();

        cart.getCartItems().stream()
                .filter(cartItem -> cartItem.getProduct().getId().equals(cartItemRequestDTO.getProductId()))
                .forEach(cartItem -> cartItem.setQuantity(cartItemRequestDTO.getQuantity()));

       getCartRepository().save(cart);
    }

    public void updateCart(Cart cart) {
        if(cart == null)
            throw new IllegalArgumentException("Argument 'cart' must not be null!");

        getCartRepository().findById(cart.getId()).orElseThrow(() -> new RuntimeException("Cart not found!"));

        getCartRepository().save(cart);
    }

    @Override
    public int getCartItemsCount() {
        return getCart().getCartItems()
                .stream()
                .map(CartItem::getQuantity)
                .reduce(0, Integer::sum);
    }

    private boolean checkIfCartContainsProduct(Product product, Cart cart) {
        return cart.getCartItems()
                .stream()
                .anyMatch(cartItem -> cartItem.getProduct().getId()
                        .equals(product.getId()));
    }

    @Override
    public Cart getCart() {
        Cart cart;

        User user = SecurityUtils.getAuthenticatedUser();

        Optional<Cart> cartOptional = getCartRepository().findCartsByUser(user);

        if (cartOptional.isEmpty()) {
            Cart newCart = new Cart();
            newCart.setUser(user);
            cart = getCartRepository().save(newCart);
        } else {
            cart = cartOptional.get();
            Hibernate.initialize(cart.getCartItems());
        }

        return cart;
    }
}
