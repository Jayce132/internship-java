package ro.nymphis.dto.cart;


import lombok.Data;
import ro.nymphis.dto.product.ProductResponseDTO;

@Data
public class CartItemDTO {
    private int quantity;
    private ProductResponseDTO productResponseDTO;
}
