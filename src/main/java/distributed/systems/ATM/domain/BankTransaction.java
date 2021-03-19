package distributed.systems.ATM.domain;

import distributed.systems.ATM.model.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "bank_transactions")
public class BankTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "receiver_account_num")
    @NonNull
    private int receiverAccountNumber;

    @Column(name = "amount")
    @NonNull
    private BigDecimal amount;

    @Column(name = "created_date")
    @CreatedDate
    private Date createdDate;

    @Column(name = "transaction_type")
    private TransactionType transactionType;

    @ManyToOne
    @JoinColumn(name = "sender_account_id", nullable = false, referencedColumnName = "id")
    private BankAccount senderBankAccount;
}
