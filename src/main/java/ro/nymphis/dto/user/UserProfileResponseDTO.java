package ro.nymphis.dto.user;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class UserProfileResponseDTO {
    private final String firstName;
    private final String lastName;
    private String email;
    private String phoneNumber;
    private String address;
}
