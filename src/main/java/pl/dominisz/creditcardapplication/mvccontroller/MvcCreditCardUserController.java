package pl.dominisz.creditcardapplication.mvccontroller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import pl.dominisz.creditcardapplication.model.CreditCardUserForm;

/**
 * http://dominisz.pl
 * 15.06.2018
 */
@Controller
@RequestMapping("/mvc/creditCardUsers")
public class MvcCreditCardUserController {

    @GetMapping("/addUser")
    public String addUserForm(Model model) {
        model.addAttribute("creditCardUserForm", new CreditCardUserForm());
        return "addUser";
    }

}
