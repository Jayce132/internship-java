package ro.nymphis.dto.order;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class OrderResponseDTO {

    private Long id;
    private String orderNumber;
    private Double totalSum;
    private LocalDate orderDate;
    private String billingAddress;
    private String shippingAddress;
}
