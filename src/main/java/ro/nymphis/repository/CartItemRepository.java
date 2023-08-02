package ro.nymphis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ro.nymphis.model.cart.Cart;
import ro.nymphis.model.cart.CartItem;
import ro.nymphis.model.order.Order;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem,Long> {
}
