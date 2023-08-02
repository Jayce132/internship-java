package ro.nymphis.dto.product;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ProductResponseDTO {

    private Long id;
    private String title;
    private Double price;
    private String thumbnail;
    private String description;
    private Integer stock;
    private String stockStatus;
}
