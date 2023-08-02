package ro.nymphis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ro.nymphis.model.ConfirmationToken;
import ro.nymphis.model.user.User;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;



public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {


    public ConfirmationToken findByUser(User user);
}
