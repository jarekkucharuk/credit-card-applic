package pl.dominisz.creditcardapplication.service;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.dominisz.creditcardapplication.model.CreditCardUser;
import pl.dominisz.creditcardapplication.repository.CreditCardUserRepository;

import java.util.Collections;

/**
 * http://dominisz.pl
 * 14.06.2018
 */
@Service
public class CreditCardUserService implements UserDetailsService {

    private final CreditCardUserRepository creditCardUserRepository;

    public CreditCardUserService(CreditCardUserRepository creditCardUserRepository) {
        this.creditCardUserRepository = creditCardUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        CreditCardUser creditCardUser = creditCardUserRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException(username));

        return toUserDetails(creditCardUser);
    }

    private UserDetails toUserDetails(CreditCardUser creditCardUser) {
        return User.builder()
                .username(creditCardUser.getUsername())
                .password(creditCardUser.getPassword())
                .authorities(Collections.EMPTY_LIST)
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .build();
    }

}
