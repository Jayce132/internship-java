package ro.nymphis.dto.cart;

import lombok.Data;

@Data
public class CartProductDTO {

    private String title;
    private Double price;
    private String thumbnail;
    private String description;
    private int quantity;
    private Long productId;
}
