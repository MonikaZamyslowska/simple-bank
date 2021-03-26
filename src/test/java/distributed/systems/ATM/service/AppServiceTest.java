package distributed.systems.ATM.service;

import distributed.systems.ATM.domain.BankAccount;
import distributed.systems.ATM.domain.BankTransaction;
import distributed.systems.ATM.model.TransactionType;
import distributed.systems.ATM.repository.BankAccountRepository;
import distributed.systems.ATM.repository.BankTransactionRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;

@Transactional
@RunWith(SpringRunner.class)
@SpringBootTest
public class AppServiceTest {
    @Autowired
    private AppService appService;

    @Autowired
    private BankAccountRepository bankAccountRepository;

    @Autowired
    private BankTransactionRepository bankTransactionRepository;

    @Test
    public void testShouldProcessTransaction() throws Exception {
        //Given
        BankAccount senderBankAccount = new BankAccount(123456789, new BigDecimal(1000));
        BankAccount receiverBankAccount = new BankAccount(987654321, new BigDecimal(BigInteger.ZERO));
        BankTransaction transferBankTransaction = new BankTransaction(new BigDecimal(100), receiverBankAccount.getAccountNumber(), //adding 1000
                TransactionType.TRANSFER, senderBankAccount);
        BankTransaction payoutBankTransaction = new BankTransaction(new BigDecimal(1), receiverBankAccount.getAccountNumber(), //substring 10
                TransactionType.PAYOUT, receiverBankAccount);

        BigDecimal receiverBalanceAfterTest = new BigDecimal(990);

        //When
        bankAccountRepository.save(senderBankAccount);
        bankAccountRepository.save(receiverBankAccount);

        for (int i = 0; i < 10; i++) {
            appService.processTransaction(transferBankTransaction);
            System.out.println(Thread.currentThread().getId());
            appService.processTransaction(payoutBankTransaction);
            System.out.println(Thread.currentThread().getId());
        }

        //Then
        assertEquals(receiverBalanceAfterTest, receiverBankAccount.getBalance());
    }
}
