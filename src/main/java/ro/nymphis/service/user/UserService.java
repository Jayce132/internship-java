package ro.nymphis.service.user;

import org.springframework.security.core.userdetails.UserDetailsService;
import ro.nymphis.model.user.User;
import ro.nymphis.service.BaseService;

public interface UserService extends UserDetailsService, BaseService<Long, User> {
    User save(User user);

    void update(User user);

    void register(User user,String baseUrl);

    void activateUser(String email, String token);
}
