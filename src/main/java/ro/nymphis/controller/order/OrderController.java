package ro.nymphis.controller.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import ro.nymphis.dto.order.OrderDetailsResponseDTO;
import ro.nymphis.dto.order.OrderItemDTO;
import ro.nymphis.dto.order.OrderResponseDTO;
import ro.nymphis.dto.pagination.PaginatedResponseDTO;
import ro.nymphis.mapper.Mapper;
import ro.nymphis.model.order.Order;
import ro.nymphis.model.user.User;
import ro.nymphis.service.order.OrderService;
import ro.nymphis.utils.SecurityUtils;

@Controller
@RequiredArgsConstructor
@Getter
@RequestMapping("/order")
public class OrderController {

	private final OrderService orderService;
	private final Mapper<Order, OrderResponseDTO> orderToOrderResponseDTOMapper;
	private final Mapper<Order, OrderDetailsResponseDTO> orderToOrderDetailsResponseDTOMapper;

	@GetMapping
	public ModelAndView showPaginatedOrders() {
		return new ModelAndView("order/order-history");
	}

    @GetMapping("/api/history")
    @ResponseBody
    public ResponseEntity<PaginatedResponseDTO<OrderResponseDTO>> showOrdersForUser(
            @RequestParam(value = "page", required = false, defaultValue = "0")
            final Integer page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "5")
            final Integer pageSize) {

        User user = SecurityUtils.getAuthenticatedUser();

		final Page<Order> orders = getOrderService()
				.getOrdersByUserIdWithPagination(PageRequest.of(page, pageSize), user.getId());

		return ResponseEntity.ok(PaginatedResponseDTO.<OrderResponseDTO>builder()
				.currentPage(orders.getNumber())
				.totalPages(orders.getTotalPages())
				.totalEntries(orders.getTotalElements())
				.responseDTOs(orders
						.map(getOrderToOrderResponseDTOMapper()::map)
						.getContent())
				.build());
	}

	@GetMapping("/{id}")
	public ModelAndView showOrder(@PathVariable("id") final Long id) {
		final Order order = getOrderService().getOrderById(id)
				.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Order not found!"));

		final ModelAndView modelAndView = new ModelAndView("order/order-details");
		modelAndView.addObject("orderDetails", getOrderToOrderDetailsResponseDTOMapper().map(order));

        return modelAndView;
    }

}
