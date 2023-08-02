package ro.nymphis.service.user.impl;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ro.nymphis.service.email.EmailService;
import ro.nymphis.exception.TokenExpirationException;
import ro.nymphis.model.ConfirmationToken;
import ro.nymphis.model.user.Role;
import ro.nymphis.model.user.User;
import ro.nymphis.repository.ConfirmationTokenRepository;
import ro.nymphis.repository.RoleRepository;
import ro.nymphis.repository.UserRepository;
import ro.nymphis.service.user.UserService;

import javax.transaction.Transactional;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Optional;

@Data
@Getter
@RequiredArgsConstructor
@Slf4j
@Service("userService")
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final ConfirmationTokenRepository confirmationTokenRepository;
    private final EmailService emailService;


    @Override
    public User save(User user) {
        Optional<User> count = userRepository.findUserByEmail(user.getEmail());
        if (count.isPresent()) {
            throw new RuntimeException("Email already in use!");
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        Role role = getRoleRepository().findRoleByRoleName("ROLE_USER")
                .orElseThrow(() -> new IllegalArgumentException("Role not found!"));
        user.setRoles(Arrays.asList(role));
        user.setIsActive(false);

        user = userRepository.save(user);

        log.info("User is being saved!");
        return user;
    }

    @Transactional
    @Override
    public void register(User user, String baseUrl) {
        user = save(user);
        ConfirmationToken confirmationToken = new ConfirmationToken(user);
        confirmationTokenRepository.save(confirmationToken);
        emailService.send(user.getEmail(),"Please click this link to activate your account: " +
                generateActivationLink(baseUrl, user.getEmail(), confirmationToken.getToken()), "Confirm your email!");
    }

    @Override
    public void activateUser(String email, String token) {
        Optional<User> optionalUser = userRepository.findUserByEmail(email);
        if (!optionalUser.isPresent())
            return;

        User user = optionalUser.get();
        if (user.getIsActive())
            return;

        ConfirmationToken confirmationToken = confirmationTokenRepository.findByUser(user);
        if (!passwordEncoder.matches(confirmationToken.getToken(), token))
            return;

        Timestamp expirationDate = Timestamp.valueOf(Timestamp.valueOf(confirmationToken.getToken()).toLocalDateTime().plusDays(1));
        if (expirationDate.compareTo(Timestamp.valueOf(LocalDateTime.now())) < 0) {
            userRepository.delete(user);
            throw new TokenExpirationException();
        }
        user.setIsActive(true);
        userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        log.info("Fetching user with email : {} from database!", email);
        Optional<User> user = getUserRepository().findUserByEmail(email);
        return user
                .orElseThrow(() -> new UsernameNotFoundException(
                        String.format("User with email : %s not found!", email)));
    }


    private String generateActivationLink(String baseUrl, String email, String token) {
        return baseUrl + "/registration/confirm?token=" + passwordEncoder.encode(token) + "&email=" + email;
    }

    @Override
    public void update(User user) {
        userRepository.save(user);
    }
}

