package distributed.systems.ATM.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "bank_accounts")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_number", unique = true)
    private int accountNumber;

    @Column(name = "balance")
    private BigDecimal balance;

    @OneToOne(mappedBy = "account")
    private User user;

    @OneToMany(mappedBy = "senderBankAccount")
    private List<BankTransaction> bankTransactions;
}
