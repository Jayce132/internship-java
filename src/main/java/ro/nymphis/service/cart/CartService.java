package ro.nymphis.service.cart;

import ro.nymphis.dto.cart.CartItemRequestDTO;
import ro.nymphis.model.cart.Cart;

public interface CartService {
    void addProduct(Long id, int quantity);

    void removeProduct(Long id);

    void updateCart(CartItemRequestDTO cartItemRequestDTO);

    void updateCart(Cart cart);

    int getCartItemsCount();

    Cart getCart();
}
