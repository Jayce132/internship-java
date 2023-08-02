package ro.nymphis.mapper.order;

import org.springframework.stereotype.Component;
import ro.nymphis.dto.order.OrderUserDetailsDTO;
import ro.nymphis.mapper.Mapper;
import ro.nymphis.model.order.OrderUserDetails;

@Component
public class OrderUserDetailsToOrderUserDetailsDTOMapper implements Mapper<OrderUserDetails, OrderUserDetailsDTO> {
    @Override
    public OrderUserDetailsDTO map(OrderUserDetails source) {
        OrderUserDetailsDTO orderUserDetailsDTO = new OrderUserDetailsDTO();
        orderUserDetailsDTO.setFirstName(source.getFirstName());
        orderUserDetailsDTO.setLastName(source.getLastName());
        orderUserDetailsDTO.setPhoneNumber(source.getPhoneNumber());

        return orderUserDetailsDTO;
    }
}
