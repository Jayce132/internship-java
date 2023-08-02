package ro.nymphis.service.order;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.Hibernate;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ro.nymphis.service.email.EmailService;
import ro.nymphis.exception.OrderNotFoundException;
import ro.nymphis.model.order.Order;

import ro.nymphis.model.order.OrderStatus;
import ro.nymphis.model.user.User;

import ro.nymphis.repository.OrderRepository;
import ro.nymphis.repository.UserRepository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Getter
@Slf4j
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final UserRepository userRepository;
    private final EmailService emailService;

    @Override
    public Collection<Order> getOrders() {
        log.info("Fetching all orders from database!");
        return getOrderRepository().findAll();
    }

    @Override
    public Page<Order> getOrdersByUserIdWithPagination(final Pageable pageable, final Long id) {
        return getOrderRepository().findOrdersByUserIdAndOrderStatusOrderByOrderDateDesc(pageable, id, OrderStatus.PLACED.toString());
    }

    @Override
    @Transactional
    public Optional<Order> getPendingOrder(final User user) {
        final Optional<Order> orderOptional = getOrderRepository()
                .getOrderByUserAndOrderStatusIsIn(user, List.of(OrderStatus.CREATED.toString(), OrderStatus.FILLED.toString()));
        orderOptional.ifPresent(order -> Hibernate.initialize(order.getOrderItems()));
        return orderOptional;
    }

    @Override
    public void upsertOrder(Order order) {
        if(order == null)
            throw new IllegalArgumentException("Argument 'order' must not be null!");

        if(order.getId() == null) {
            order.setOrderDate(LocalDate.now());
            order.setOrderNumber("#WEB_" + order.getOrderDate().toString() + String.format("%04d",  getOrderRepository().getNextOrderId()));
            order.setOrderStatus(OrderStatus.CREATED.toString());
        } else {
            orderRepository.findById(order.getId())
                    .orElseThrow(() -> new OrderNotFoundException("There is no order matching the giver id!"));
        }

		orderRepository.save(order);
	}

    @Override
    public Optional<Order> getOrderById(final Long id) {
        return getOrderRepository().getOrderItemByOrderId(id);
    }
}
