package distributed.systems.ATM.exception;

public class BankAccountNotFound extends Exception {
    public BankAccountNotFound(String message) {
        super(message);
    }
}
