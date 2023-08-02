package ro.nymphis.mapper.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.nymphis.dto.order.OrderDetailsResponseDTO;
import ro.nymphis.dto.order.OrderItemDTO;
import ro.nymphis.dto.order.OrderUserDetailsDTO;
import ro.nymphis.mapper.Mapper;
import ro.nymphis.mapper.user.UserToUserProfileResponseDTOMapper;
import ro.nymphis.model.order.Order;
import ro.nymphis.model.order.OrderItem;
import ro.nymphis.model.order.OrderUserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
@Getter
public class OrderToOrderDetailsResponseDTOMapper implements Mapper<Order, OrderDetailsResponseDTO> {

    private final OrderItemToOrderItemDTOMapper mapper;
    private final UserToUserProfileResponseDTOMapper userMapper;
    private  final Mapper<OrderUserDetails, OrderUserDetailsDTO> orderUserDetailsToOrderUserDetailsDTOMapper;

    @Override
    public OrderDetailsResponseDTO map(final Order order) {
        final OrderDetailsResponseDTO responseDTO = new OrderDetailsResponseDTO();
        final Collection<OrderItemDTO> orderItemDTOS = getOrderItemDTOS(order);
        responseDTO.setOrderNumber(order.getOrderNumber());
        responseDTO.setOrderDate(order.getOrderDate());
        responseDTO.setTotalSum(order.getTotalSum());
        responseDTO.setBillingAddress(order.getBillingAddress());
        responseDTO.setShippingAddress(order.getShippingAddress());
        responseDTO.setOrderItemDTOS(orderItemDTOS);
        responseDTO.setOrderUserDetailsDTO(getOrderUserDetailsToOrderUserDetailsDTOMapper().map(order.getOrderUserDetails()));
        responseDTO.setUserProfileResponseDTO(getUserMapper().map(order.getUser()));
        return responseDTO;
    }

    private Collection<OrderItemDTO> getOrderItemDTOS(final Order order) {
        final Collection<OrderItem> orders = order.getOrderItems();
        return orders.stream()
                        .map(orderItem -> getMapper()
                                .map(orderItem))
                                .collect(Collectors.toList());
    }
}
