package ro.nymphis.controller.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import ro.nymphis.dto.error.ErrorResponseDTO;
import ro.nymphis.dto.order.OrderDetailsFillRequestDTO;
import ro.nymphis.dto.order.OrderDetailsResponseDTO;
import ro.nymphis.dto.order.OrderResponseDTO;
import ro.nymphis.dto.user.UserProfileResponseDTO;
import ro.nymphis.exception.OrderNotFoundException;
import ro.nymphis.exception.checkout.CheckoutException;
import ro.nymphis.exception.checkout.CheckoutExceptionMessages;
import ro.nymphis.facades.CheckoutFacade;
import ro.nymphis.facades.PlaceOrderFacade;
import ro.nymphis.mapper.Mapper;
import ro.nymphis.model.cart.Cart;
import ro.nymphis.model.order.Order;
import ro.nymphis.model.order.OrderStatus;
import ro.nymphis.model.user.User;
import ro.nymphis.service.email.EmailService;
import ro.nymphis.service.order.OrderService;
import ro.nymphis.utils.SecurityUtils;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("checkout")
@Getter
@RequiredArgsConstructor
public class CheckoutController {
    private final OrderService orderService;
    private final EmailService emailService;
    private final CheckoutFacade checkoutFacade;
    private final PlaceOrderFacade placeOrderFacade;
    private final Mapper<Order, OrderResponseDTO> orderToOrderResponseDTOMapper;
    private final Mapper<Order, OrderDetailsResponseDTO> orderToOrderDetailsResponseDTOMapper;
    private final Mapper<OrderDetailsFillRequestDTO, Order> orderDetailsFillRequestDTOToOrderMapper;
    private final Mapper<User, UserProfileResponseDTO> userToUserProfileResponseDTOMapper;

    @GetMapping
    @ResponseBody
    public ResponseEntity checkOut()  {
        try {
            getCheckoutFacade().checkOut();
        } catch (CheckoutException checkoutException) {
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ErrorResponseDTO(checkoutException.getMessage()));
        }

        return new ResponseEntity(HttpStatus.OK);
    }

    @GetMapping("fill-order-details")
    public ModelAndView getFillOrderDetailsPage() {
        User user = SecurityUtils.getAuthenticatedUser();

        final Order order = getOrderService().getPendingOrder(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No pending order was found!"));

        UserProfileResponseDTO userProfileResponseDTO = getUserToUserProfileResponseDTOMapper().map(user);
        OrderResponseDTO orderResponseDTO = getOrderToOrderResponseDTOMapper().map(order);
        final ModelAndView modelAndView = new ModelAndView("order/checkout/fill-order-details");
        modelAndView.addObject("user", userProfileResponseDTO) ;
        modelAndView.addObject("order", orderResponseDTO);

        return modelAndView;
    }

    @PostMapping("fill-order-details")
    @ResponseStatus(HttpStatus.OK)
    public void fillOrderDetails(@Valid @RequestBody OrderDetailsFillRequestDTO orderDetailsFillRequestDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String validationErrors = bindingResult.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining());

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, validationErrors);
        }

        try {
            Order order = getOrderDetailsFillRequestDTOToOrderMapper().map(orderDetailsFillRequestDTO);
            getOrderService().upsertOrder(order);
        } catch (OrderNotFoundException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No pending order was found!");
        }
    }

    @GetMapping("order-summary")
    public ModelAndView getOrderSummaryPage() {
        final User user = SecurityUtils.getAuthenticatedUser();
        final Order order = getOrderService().getPendingOrder(user)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "No pending order was found!"));

        final ModelAndView modelAndView = new ModelAndView();

        if (!order.getOrderStatus().equals(OrderStatus.FILLED.toString())) {
            modelAndView.setViewName("redirect:/checkout/fill-order-details");
            return modelAndView;
        }
        modelAndView.setViewName("order/checkout/order-summary");
        modelAndView.addObject("order", getOrderToOrderDetailsResponseDTOMapper().map(order));
        return modelAndView;
    }

    @GetMapping("order-placed")
    public ModelAndView getOrderConfirmation() {

        final ModelAndView modelAndView = new ModelAndView();
        try {
            final Order order = getPlaceOrderFacade().placeOrder();
            modelAndView.addObject("order", getOrderToOrderResponseDTOMapper().map(order));
            modelAndView.setViewName("order/checkout/order-placed");
        } catch (CheckoutException e) {
            if (e.getMessage().equals(CheckoutExceptionMessages.NO_PENDING_ORDER.getMessage())) {
                modelAndView.setViewName("redirect:/cart/details");
            }
            else if (e.getMessage().equals(CheckoutExceptionMessages.ORDER_NOT_FILLED.getMessage())) {
                modelAndView.setViewName("redirect:/checkout/fill-order-details");
            }
        }
        return modelAndView;
    }
}
