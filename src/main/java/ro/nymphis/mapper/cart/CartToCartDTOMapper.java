package ro.nymphis.mapper.cart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.nymphis.dto.cart.CartDTO;
import ro.nymphis.mapper.Mapper;
import ro.nymphis.model.cart.Cart;

import java.util.Comparator;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Getter
public class CartToCartDTOMapper implements Mapper<Cart, CartDTO> {

    private final CartItemToCartItemDTOMapper cartItemToCartItemDTOMapper;

    public CartDTO map(Cart source) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setCartItemDTOs(
                source.getCartItems()
                        .stream()
                        .map(getCartItemToCartItemDTOMapper()::map)
                        .sorted(Comparator.comparing(a -> a.getProductResponseDTO().getTitle()))
                        .collect(Collectors.toList())
        );

        cartDTO.setTotalPrice(
                source.getCartItems()
                    .stream()
                    .map(cartItem -> cartItem.getQuantity() * cartItem.getProduct().getPrice())
                    .reduce(0.0, Double::sum));

        return cartDTO;
    }
}
