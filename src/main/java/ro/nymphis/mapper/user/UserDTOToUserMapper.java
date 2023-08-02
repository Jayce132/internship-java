package ro.nymphis.mapper.user;

import org.springframework.stereotype.Component;
import ro.nymphis.dto.user.UserRegisterDTO;
import ro.nymphis.model.user.User;
import ro.nymphis.mapper.Mapper;

@Component
public class UserDTOToUserMapper implements Mapper<UserRegisterDTO, User> {
    @Override
    public User map(UserRegisterDTO userRegisterDTO) {
        User user = new User();
        user.setFirstName(userRegisterDTO.getFirstName());
        user.setLastName(userRegisterDTO.getLastName());
        user.setEmail(userRegisterDTO.getEmail());
        user.setPassword(userRegisterDTO.getPassword());
        user.setPassword(userRegisterDTO.getConfirmPassword());
        user.setIsActive(userRegisterDTO.getAcceptedTermsAndConditions());

        return user;
    }
}
