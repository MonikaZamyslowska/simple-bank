package distributed.systems.ATM.repository;

import distributed.systems.ATM.domain.BankAccount;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {
    Optional<BankAccount> findByAccountNumber(int accountNumber);
}
