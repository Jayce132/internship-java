package ro.nymphis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.nymphis.model.user.User;

import java.util.Optional;


public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findUserByEmail(final String email);



}
