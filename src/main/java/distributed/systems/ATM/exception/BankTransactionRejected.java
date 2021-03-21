package distributed.systems.ATM.exception;

public class BankTransactionRejected extends Exception {
    public BankTransactionRejected(String message) {
        super(message);
    }
}
