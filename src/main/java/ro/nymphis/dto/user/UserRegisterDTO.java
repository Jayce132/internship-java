package ro.nymphis.dto.user;


import lombok.Getter;
import lombok.Setter;
import ro.nymphis.validators.annotations.ValidateEqualFields;
import ro.nymphis.validators.annotations.ValidPassword;

import javax.validation.constraints.*;

@Getter
@Setter
@ValidateEqualFields(firstField = "password", secondField = "confirmPassword", message = "Passwords do not match!")
public class UserRegisterDTO {

    @NotEmpty(message = "First name must be longer than 3 characters!")
    @Size(min = 3, max = 256)
    private String firstName;

    @NotEmpty(message = "Last name must be longer than 3 characters!")
    @Size(min = 3, max = 256)
    private String lastName;

    @NotEmpty(message = "E-mail field must not be empty!")
    @Email(message = "Email field should follow the pattern: example@example.com")
    private String email;

    @NotEmpty
    @ValidPassword
    private String password;

    @NotEmpty
    private String confirmPassword;

    @NotNull(message ="The Terms and Conditions' box must be checked for the registration to be successful!")
    private Boolean acceptedTermsAndConditions;

}
