package distributed.systems.ATM.controller;

import distributed.systems.ATM.domain.BankAccount;
import distributed.systems.ATM.domain.User;
import distributed.systems.ATM.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AppController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/home")
    public String viewHomePage() {
        return "index";
    }

    @GetMapping("/account")
    public String getUserDetails(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        User user = userRepository.findUserByEmail(email).orElseThrow();
        int bankAccountNumber = user.getAccount().getAccountNumber();
        model.addAttribute("user", user);
        model.addAttribute("bank_account_number", bankAccountNumber);

        return "account";
    }
}
