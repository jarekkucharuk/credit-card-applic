package pl.dominisz.creditcardapplication.service;

import pl.dominisz.creditcardapplication.model.CreditCardUserForm;

import java.util.Optional;

/**
 * http://dominisz.pl
 * 15.06.2018
 */
public interface CreditCardUserService {

    Optional<String> createUser(CreditCardUserForm creditCardUserForm);

}
