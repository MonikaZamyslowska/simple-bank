package distributed.systems.ATM.controller;

import distributed.systems.ATM.domain.BankAccount;
import distributed.systems.ATM.domain.BankTransaction;
import distributed.systems.ATM.domain.User;
import distributed.systems.ATM.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/account")
    public String getUserDetails(Model model) {
        User user = getUser();
        model.addAttribute("user", user);
        model.addAttribute("bank_account_number", user.getAccount().getAccountNumber());
        model.addAttribute("balance", user.getAccount().getBalance());
        return "account";
    }

    @GetMapping("/transfer")
    public String showTransferForm(Model model) {
        createTransaction(model);
        return "transfer_form";
    }

    @GetMapping("/payment")
    public String showPaymentForm(Model model) {
        createTransaction(model);
        return "payment_form";
    }

    @GetMapping("/payout")
    public String showPayoutForm(Model model) {
        createTransaction(model);
        return "payout_form";
    }

    @PostMapping("/transfer_process")
    public String transferProcess(BankAccount bankAccount) {
        return "transaction_success";
    }

    @PostMapping("/payment_process")
    public String paymentProcess(BankAccount bankAccount) {
        return "transaction_success";
    }

    @PostMapping("/payout_process")
    public String payoutProcess(BankAccount bankAccount) {
        return "transaction_success";
    }

    private User getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String email = auth.getName();
        return userRepository.findUserByEmail(email).orElseThrow();
    }

    private void createTransaction(Model model) {
        BankTransaction bankTransaction = new BankTransaction();
        bankTransaction.setSenderBankAccount(getBankAccount());
        model.addAttribute("bankTransaction", bankTransaction);
    }

    private BankAccount getBankAccount() {
        return getUser().getAccount();
    }
}
