package ro.nymphis.mapper.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ro.nymphis.dto.order.OrderItemDTO;
import ro.nymphis.mapper.Mapper;
import ro.nymphis.mapper.product.ProductToProductResponseDTOMapper;
import ro.nymphis.model.order.OrderItem;

@Component
@RequiredArgsConstructor
@Getter
public class OrderItemToOrderItemDTOMapper implements Mapper<OrderItem, OrderItemDTO> {

    private final ProductToProductResponseDTOMapper mapper;
    @Override
    public OrderItemDTO map(final OrderItem orderItem) {
        return OrderItemDTO.builder()
                .price(orderItem.getPrice())
                .quantity(orderItem.getQuantity())
                .productResponseDTO(mapper.map(orderItem.getProduct()))
                .build();
    }
}
