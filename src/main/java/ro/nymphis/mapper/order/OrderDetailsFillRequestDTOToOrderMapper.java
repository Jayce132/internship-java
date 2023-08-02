package ro.nymphis.mapper.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ro.nymphis.dto.order.OrderDetailsFillRequestDTO;
import ro.nymphis.exception.OrderNotFoundException;
import ro.nymphis.mapper.Mapper;
import ro.nymphis.model.order.Order;
import ro.nymphis.model.order.OrderStatus;
import ro.nymphis.model.order.OrderUserDetails;
import ro.nymphis.model.user.User;
import ro.nymphis.service.order.OrderService;

import java.util.Objects;

@Component
@RequiredArgsConstructor
@Getter
public class OrderDetailsFillRequestDTOToOrderMapper implements Mapper<OrderDetailsFillRequestDTO, Order> {
    private final OrderService orderService;

    @Override
    public Order map(OrderDetailsFillRequestDTO orderDetailsFillRequestDTO) {
        if(orderDetailsFillRequestDTO == null)
            throw new IllegalArgumentException("Argument 'orderDetailsFillRequestDTO' must not be null!");

        Authentication authentication = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication(), "Authentication must not be null!");

        if(!(authentication.getPrincipal() instanceof User))
            throw new ClassCastException("Authentication principal must be of type User!");

        User user = (User) authentication.getPrincipal();

        final Order order = getOrderService().getPendingOrder(user)
                .orElseThrow(() -> new OrderNotFoundException("No pending order was found!"));

        OrderUserDetails orderUserDetails = new OrderUserDetails();
        orderUserDetails.setFirstName(orderDetailsFillRequestDTO.getFirstName());
        orderUserDetails.setLastName(orderDetailsFillRequestDTO.getLastName());
        orderUserDetails.setPhoneNumber(orderDetailsFillRequestDTO.getPhoneNumber());

        order.setOrderUserDetails(orderUserDetails);
        order.setBillingAddress(orderDetailsFillRequestDTO.getBillingAddress());
        order.setShippingAddress(orderDetailsFillRequestDTO.getShippingAddress());
        order.setOrderStatus(OrderStatus.FILLED.toString());

        return order;
    }
}
