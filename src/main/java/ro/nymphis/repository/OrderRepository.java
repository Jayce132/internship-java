package ro.nymphis.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.nymphis.model.order.Order;
import ro.nymphis.model.user.User;

import java.util.Collection;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order, Long> {

    @Query(value = "SELECT nextval('hibernate_sequence')", nativeQuery = true)
    Long getNextOrderId();

    @Query(value = "SELECT DISTINCT ord FROM Order ord JOIN FETCH ord.orderItems WHERE ord.id = :id")
    Optional<Order> getOrderItemByOrderId(@Param("id") Long id);

    Page<Order> findOrdersByUserIdAndOrderStatusOrderByOrderDateDesc(Pageable pageable, Long id, String orderStatus);

    Optional<Order> getOrderByUserAndOrderStatusIsIn(User user, Collection<String> orderStatus);

}
