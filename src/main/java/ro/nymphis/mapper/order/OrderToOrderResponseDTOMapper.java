package ro.nymphis.mapper.order;

import org.springframework.stereotype.Component;
import ro.nymphis.dto.order.OrderResponseDTO;
import ro.nymphis.mapper.Mapper;
import ro.nymphis.model.order.Order;

@Component
public class OrderToOrderResponseDTOMapper implements Mapper<Order, OrderResponseDTO> {

    @Override
    public OrderResponseDTO map(final Order order) {
        return OrderResponseDTO.builder()
                .id(order.getId())
                .orderNumber(order.getOrderNumber())
                .totalSum(order.getTotalSum())
                .orderDate(order.getOrderDate())
                .shippingAddress(order.getShippingAddress())
                .billingAddress(order.getBillingAddress())
                .build();
    }
}
