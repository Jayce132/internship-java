package ro.nymphis.dto.order;

import lombok.Builder;
import lombok.Data;
import ro.nymphis.dto.product.ProductResponseDTO;

@Data
@Builder
public class OrderItemDTO {

    private Double price;
    private Integer quantity;
    private ProductResponseDTO productResponseDTO;
}
