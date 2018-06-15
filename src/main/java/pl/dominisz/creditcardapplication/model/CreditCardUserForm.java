package pl.dominisz.creditcardapplication.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CreditCardUserForm {

    private String username;
    private String password;
    private String email;
}

