package ro.nymphis.dto.order;

import lombok.Data;
import ro.nymphis.dto.user.UserProfileResponseDTO;

import java.time.LocalDate;
import java.util.Collection;

@Data
public class OrderDetailsResponseDTO {

    private String orderNumber;
    private LocalDate orderDate;
    private Double totalSum;
    private String billingAddress;
    private String shippingAddress;
    private Collection<OrderItemDTO> orderItemDTOS;
    private OrderUserDetailsDTO orderUserDetailsDTO;
    private UserProfileResponseDTO userProfileResponseDTO;
}
