package ro.nymphis.controller.auth;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ro.nymphis.mapper.Mapper;
import ro.nymphis.model.user.User;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import ro.nymphis.dto.user.UserRegisterDTO;
import ro.nymphis.exception.ModifiedTokenException;
import ro.nymphis.exception.TokenExpirationException;
import ro.nymphis.service.user.UserService;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Getter
@Controller
@RequiredArgsConstructor
@RequestMapping
public class AuthController {
    private final UserService userService;
    private final Mapper<UserRegisterDTO, User> userRegisterDTOToUserMapper;

    @GetMapping("/login")
    public String loginView() {
        return "/auth/login";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") @Valid final UserRegisterDTO userRegisterDTO, final BindingResult result, final Model model) {
        final Map<String, String> userErrors = new HashMap<>();

        if (result.hasErrors()) {
            for (final FieldError fieldError : result.getFieldErrors()) {
                userErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
            }
            model.addAttribute("errors", userErrors);
            return "auth/register";
        }

        final User user = getUserRegisterDTOToUserMapper().map(userRegisterDTO);
        final String baseUrl = ServletUriComponentsBuilder.fromCurrentContextPath().build().toUriString();

        try {
            getUserService().register(user, baseUrl);
        } catch (RuntimeException e) {
            model.addAttribute("email", e.getMessage());
            return "auth/register";
        }

        return "auth/account-activation-link-sent";
    }

    @GetMapping("/register")
    public ModelAndView register(@ModelAttribute("user") final UserRegisterDTO userRegisterDTO) {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/register");

        return modelAndView;
    }

    @GetMapping("/registration/confirm")
    public ModelAndView confirmUser(@RequestParam("email") String email, @RequestParam("token") String token) {
        final ModelAndView modelAndView = new ModelAndView();

        try {
            getUserService().activateUser(email, token);
        } catch (TokenExpirationException tokenExpirationException) {
            modelAndView.setViewName("auth/email-validation-expired");
            return modelAndView;
        } catch (ModifiedTokenException modifiedTokenException) {
            modelAndView.setViewName("auth/register");
            return modelAndView;
        }

        modelAndView.setViewName("/auth/login");

        return modelAndView;
    }

    @GetMapping("/login-error")
    public ModelAndView loginError() {
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("auth/login");
        modelAndView.addObject("errorMessage", "Incorrect data inserted, please try again!");

        return modelAndView;
    }
}
