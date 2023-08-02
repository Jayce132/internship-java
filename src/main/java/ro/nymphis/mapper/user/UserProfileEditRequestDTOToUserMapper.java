package ro.nymphis.mapper.user;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ro.nymphis.dto.user.UserProfileEditRequestDTO;
import ro.nymphis.mapper.Mapper;
import ro.nymphis.model.user.User;

@Component
public class UserProfileEditRequestDTOToUserMapper implements Mapper<UserProfileEditRequestDTO, User> {
    @Override
    public User map(UserProfileEditRequestDTO userProfileEditRequestDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if(authentication == null)
            throw new NullPointerException("Authentication must not be null!");

        if(!(authentication.getPrincipal() instanceof User))
            throw new ClassCastException("Principal must be of type User!");

        User user = (User) authentication.getPrincipal();

        user.setFirstName(userProfileEditRequestDTO.getFirstName());
        user.setLastName(userProfileEditRequestDTO.getLastName());
        user.setPhoneNumber(userProfileEditRequestDTO.getPhoneNumber());
        user.setAddress(userProfileEditRequestDTO.getAddress());

        return user;
    }
}
