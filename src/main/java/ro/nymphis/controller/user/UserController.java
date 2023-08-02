package ro.nymphis.controller.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;
import ro.nymphis.dto.user.UserProfileEditRequestDTO;
import ro.nymphis.dto.user.UserProfileResponseDTO;
import ro.nymphis.mapper.Mapper;
import ro.nymphis.model.user.User;
import ro.nymphis.service.user.UserService;
import ro.nymphis.utils.SecurityUtils;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Controller
@RequestMapping("user")
@Getter
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final Mapper<User, UserProfileResponseDTO> userToUserProfileResponseDTOMapper;
    private final Mapper<UserProfileEditRequestDTO, User> userProfileEditRequestDTOToUserMapper;

    @GetMapping("edit-profile")
    public ModelAndView getEditProfilePage() {
        User user = SecurityUtils.getAuthenticatedUser();

        UserProfileResponseDTO userProfileResponseDTO = getUserToUserProfileResponseDTOMapper().map(user);

        ModelAndView modelAndView = new ModelAndView("user/edit-profile");
        modelAndView.addObject("user", userProfileResponseDTO);

        return modelAndView;
    }

    @PutMapping
    @ResponseStatus(HttpStatus.OK)
    public void updateUser(@Valid @RequestBody UserProfileEditRequestDTO userProfileEditRequestDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()) {
            String validationErrors = bindingResult.getFieldErrors()
                    .stream()
                    .map(DefaultMessageSourceResolvable::getDefaultMessage)
                    .collect(Collectors.joining());

            throw new ResponseStatusException(HttpStatus.UNPROCESSABLE_ENTITY, validationErrors);
        }

        User user = getUserProfileEditRequestDTOToUserMapper().map(userProfileEditRequestDTO);
        getUserService().update(user);
    }
}
