package currencyrateservice.service.security;

import currencyrateservice.domain.User;
import currencyrateservice.domain.security.SecurityUser;
import currencyrateservice.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static currencyrateservice.config.Constants.ErrorMessage;
import static currencyrateservice.domain.security.Status.ACTIVE;

@AllArgsConstructor
@Service
public class SecurityUserService implements UserDetailsService {

    private final UserRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) {
        User user = repository
                        .findUserByEmail(username)
                        .orElseThrow(() ->
                                new UsernameNotFoundException(
                                        String.format(ErrorMessage.USERNAME_NOT_FOUND_EXCEPTION, username))
                        );
        return retrieveUserDetailsFrom(user);
    }

    private UserDetails retrieveUserDetailsFrom(User user) {
        return new SecurityUser(user.getEmail(), user.getPassword(),
                user.getRole().receiveAuthorities(),
                user.getStatus().equals(ACTIVE)
        );
    }
}
