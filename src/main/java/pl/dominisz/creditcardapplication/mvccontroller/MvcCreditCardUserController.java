package pl.dominisz.creditcardapplication.mvccontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dominisz.creditcardapplication.model.CreditCardUserForm;
import pl.dominisz.creditcardapplication.service.CreditCardUserService;

import java.util.Optional;

/**
 * http://dominisz.pl
 * 15.06.2018
 */
@Controller
@RequestMapping("/mvc/creditCardUsers")
public class MvcCreditCardUserController {

    private final CreditCardUserService creditCardUserService;

    public MvcCreditCardUserController(CreditCardUserService creditCardUserService) {
        this.creditCardUserService = creditCardUserService;
    }

    @GetMapping("/addUser")
    public String addUserForm(Model model) {
        model.addAttribute("creditCardUserForm", new CreditCardUserForm());
        return "addUser";
    }

    @PostMapping("/addUser")
    public String addNewUser(@ModelAttribute CreditCardUserForm creditCardUserForm,
                             Model model) {
        Optional<String> errorMessage = creditCardUserService.createUser(creditCardUserForm);

        if (errorMessage.isPresent()) {
            model.addAttribute("errorMessage", errorMessage.get());
            return "addUser";
        }

        return "login";
    }
}
