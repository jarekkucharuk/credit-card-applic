package pl.dominisz.creditcardapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.dominisz.creditcardapplication.model.CreditCardUser;

import java.util.Optional;

/**
 * http://dominisz.pl
 * 15.06.2018
 */
public interface CreditCardUserRepository extends JpaRepository<CreditCardUser, Long> {

    Optional<CreditCardUser> findByUsername(String username);
    Optional<CreditCardUser> findByEmail(String email);

}
