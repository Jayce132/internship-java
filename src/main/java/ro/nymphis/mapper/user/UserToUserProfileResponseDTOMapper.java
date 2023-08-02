package ro.nymphis.mapper.user;

import org.springframework.stereotype.Component;
import ro.nymphis.dto.user.UserProfileResponseDTO;
import ro.nymphis.mapper.Mapper;
import ro.nymphis.model.user.User;

@Component
public class UserToUserProfileResponseDTOMapper implements Mapper<User, UserProfileResponseDTO> {
    @Override
    public UserProfileResponseDTO map(User user) {
        return UserProfileResponseDTO.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .address(user.getAddress())
                .build();
    }
}
