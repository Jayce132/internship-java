package ro.nymphis.mapper.cart;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.nymphis.dto.cart.CartItemDTO;
import ro.nymphis.mapper.Mapper;
import ro.nymphis.mapper.product.ProductToProductResponseDTOMapper;
import ro.nymphis.model.cart.CartItem;

@RequiredArgsConstructor
@Getter
@Component
public class CartItemToCartItemDTOMapper implements Mapper<CartItem, CartItemDTO> {

    private final ProductToProductResponseDTOMapper productToProductResponseDTOMapper;

    @Override
    public CartItemDTO map(CartItem source) {

        final CartItemDTO cartItemDTO = new CartItemDTO();

        cartItemDTO.setQuantity(source.getQuantity());
        cartItemDTO.setProductResponseDTO(getProductToProductResponseDTOMapper().map(source.getProduct()));
        return cartItemDTO;
    }
}
