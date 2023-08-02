package ro.nymphis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.nymphis.model.cart.Cart;
import ro.nymphis.model.user.User;

import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart,Long> {
    Optional<Cart> findCartsByUser(User user);
}
