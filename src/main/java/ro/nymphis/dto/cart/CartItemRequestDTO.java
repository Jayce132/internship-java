package ro.nymphis.dto.cart;

import lombok.Data;

@Data
public class CartItemRequestDTO {
    Long productId;
    int quantity;
}
