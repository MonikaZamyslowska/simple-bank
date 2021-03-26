package distributed.systems.ATM.repository;

import distributed.systems.ATM.domain.BankTransaction;
import org.springframework.data.repository.CrudRepository;

public interface BankTransactionRepository extends CrudRepository<BankTransaction, Long> {
}
