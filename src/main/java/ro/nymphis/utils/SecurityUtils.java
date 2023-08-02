package ro.nymphis.utils;

import lombok.experimental.UtilityClass;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ro.nymphis.model.user.User;

import java.util.Objects;

@UtilityClass
public class SecurityUtils {
    public User getAuthenticatedUser() {
        Authentication authentication = Objects.requireNonNull(SecurityContextHolder.getContext().getAuthentication(), "Authentication must not be null!");

        if(!(authentication.getPrincipal() instanceof User))
            throw new ClassCastException("Authentication principal must be of type User!");

        return (User) authentication.getPrincipal();
    }
}
