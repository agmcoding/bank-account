package spring.boot.bankaccount.exception;

public class AccountAlreadyExistsException extends RuntimeException {

    public AccountAlreadyExistsException() {
        super();
    }

    public AccountAlreadyExistsException(final String message) {
        super(message);
    }

}
