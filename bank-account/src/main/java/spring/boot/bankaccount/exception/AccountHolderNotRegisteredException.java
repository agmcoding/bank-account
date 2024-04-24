package spring.boot.bankaccount.exception;

public class AccountHolderNotRegisteredException extends RuntimeException {

    public AccountHolderNotRegisteredException() {
        super();
    }

    public AccountHolderNotRegisteredException(final String message) {
        super(message);
    }

}
