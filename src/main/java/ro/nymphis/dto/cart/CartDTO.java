package ro.nymphis.dto.cart;

import lombok.Data;

import java.util.List;

@Data
public class CartDTO {
    List<CartItemDTO> cartItemDTOs;
    Double totalPrice;

}
