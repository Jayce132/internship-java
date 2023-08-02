package ro.nymphis.service.order;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import ro.nymphis.model.order.Order;
import ro.nymphis.model.user.User;

import ro.nymphis.service.BaseService;

import java.util.Collection;
import java.util.Optional;


public interface OrderService extends BaseService<Long, Order> {
    Optional<Order> getOrderById(final Long id);

    Collection<Order> getOrders();

    Page<Order> getOrdersByUserIdWithPagination(final Pageable pageable, final Long id);

    Optional<Order> getPendingOrder(final User user);

    void upsertOrder(final Order order);
}
