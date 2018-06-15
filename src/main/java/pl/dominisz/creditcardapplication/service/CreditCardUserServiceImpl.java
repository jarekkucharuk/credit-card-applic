package pl.dominisz.creditcardapplication.service;

import org.apache.commons.validator.routines.EmailValidator;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.dominisz.creditcardapplication.model.CreditCardUser;
import pl.dominisz.creditcardapplication.model.CreditCardUserForm;
import pl.dominisz.creditcardapplication.repository.CreditCardUserRepository;

import java.util.Collections;
import java.util.Optional;

/**
 * http://dominisz.pl
 * 14.06.2018
 */
@Service
public class CreditCardUserServiceImpl implements CreditCardUserService, UserDetailsService {

    private final CreditCardUserRepository creditCardUserRepository;

    private final EmailValidator emailValidator = EmailValidator.getInstance();

    public CreditCardUserServiceImpl(CreditCardUserRepository creditCardUserRepository) {
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

    public Optional<String> createUser(CreditCardUserForm creditCardUserForm) {
        //TODO do poprawy ;-)
        Optional<String> errors = validateFormData(creditCardUserForm);

        if (errors.isPresent()) {
            return errors;
        }

        creditCardUserRepository.save(toCreditCardUser(creditCardUserForm));

        return Optional.empty();
    }

    private Optional<String> validateFormData(CreditCardUserForm creditCardUserForm) {
        if (!validUsername(creditCardUserForm.getUsername())) {
            return Optional.of("Invalid username");
        }

        if (!validEmail(creditCardUserForm.getEmail())) {
            return Optional.of("Invalid email");
        }

        if (!validPassword(creditCardUserForm.getPassword())) {
            return Optional.of("Invalid password");
        }

        return Optional.empty();
    }

    private boolean validPassword(String password) {
        return true;
    }

    private boolean validEmail(String email) {
        return emailValidator.isValid(email)
                && !creditCardUserRepository.findByEmail(email).isPresent();
    }

    private boolean validUsername(String username) {
        username = username.trim();
        return !username.equals("")
                && !creditCardUserRepository.findByUsername(username).isPresent();
    }

    private CreditCardUser toCreditCardUser(CreditCardUserForm creditCardUserForm) {
        CreditCardUser creditCardUser = new CreditCardUser();

        creditCardUser.setUsername(creditCardUserForm.getUsername());
        creditCardUser.setPassword(creditCardUserForm.getPassword());
        creditCardUser.setEmail(creditCardUserForm.getEmail());

        return creditCardUser;
    }

}
