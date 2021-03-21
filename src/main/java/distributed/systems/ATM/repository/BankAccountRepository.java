package distributed.systems.ATM.repository;

import distributed.systems.ATM.domain.BankAccount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BankAccountRepository extends CrudRepository<BankAccount, Long> {
    Optional<BankAccount> findByAccountNumber(int accountNumber);
}
