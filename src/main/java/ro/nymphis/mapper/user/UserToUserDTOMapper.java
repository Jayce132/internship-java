package ro.nymphis.mapper.user;

import org.springframework.stereotype.Component;
import ro.nymphis.dto.user.UserRegisterDTO;
import ro.nymphis.model.user.User;
import ro.nymphis.mapper.Mapper;

@Component
public class UserToUserDTOMapper implements Mapper<User,UserRegisterDTO> {
    @Override
    public UserRegisterDTO map(User user) {
        UserRegisterDTO userRegisterDTO = new UserRegisterDTO();
        userRegisterDTO.setFirstName(user.getFirstName());
        userRegisterDTO.setLastName(user.getLastName());
        userRegisterDTO.setEmail(user.getEmail());
        userRegisterDTO.setPassword(user.getPassword());
        userRegisterDTO.setConfirmPassword(user.getPassword());
        userRegisterDTO.setAcceptedTermsAndConditions(user.getIsActive());

        return userRegisterDTO;
    }
}
