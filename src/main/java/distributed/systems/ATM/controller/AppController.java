package distributed.systems.ATM.controller;

import distributed.systems.ATM.domain.BankAccount;
import distributed.systems.ATM.domain.BankTransaction;
import distributed.systems.ATM.domain.User;
import distributed.systems.ATM.model.TransactionType;
import distributed.systems.ATM.service.AppService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AppController {
    @Autowired
    private AppService service;

    private static final Logger LOGGER = LoggerFactory.getLogger(AppController.class);

    @GetMapping("/account")
    public String getOwnerUserDetails(Model model) {
        User user = service.getOwnerUser();
        BankAccount userAccount = user.getAccount();
        model.addAttribute("user", user);
        model.addAttribute("userAccount", userAccount);
        return "account";
    }

    @GetMapping("/transfer")
    public String showTransferForm(Model model) {
        BankTransaction bankTransaction = service.createBankTransaction();
        bankTransaction.setTransactionType(TransactionType.TRANSFER);
        model.addAttribute("bankTransaction", bankTransaction);
        return "transfer_form";
    }

    @GetMapping("/payment")
    public String showPaymentForm(Model model) {
        BankTransaction bankTransaction = service.createBankTransaction();
        bankTransaction.setTransactionType(TransactionType.PAYMENT);
        model.addAttribute("bankTransaction", bankTransaction);
        return "payment_form";
    }

    @GetMapping("/payout")
    public String showPayoutForm(Model model) {
        BankTransaction bankTransaction = service.createBankTransaction();
        bankTransaction.setTransactionType(TransactionType.PAYOUT);
        model.addAttribute("bankTransaction", bankTransaction);
        return "payout_form";
    }

    @PostMapping("/transaction_process")
    public String transferProcess(BankTransaction bankTransaction) {
        try {
            service.processTransaction(bankTransaction);
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            return "transaction_failed";
        }
        return "transaction_success";
    }
}
