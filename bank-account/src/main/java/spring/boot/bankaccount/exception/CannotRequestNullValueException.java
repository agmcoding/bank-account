package spring.boot.bankaccount.exception;

public class CannotRequestNullValueException extends RuntimeException {

    public CannotRequestNullValueException() {
        super();
    }

    public CannotRequestNullValueException(final String message) {
        super(message);
    }

}
