package spring.boot.bankaccount.exception;

public class CannotRequestNullValueException extends RuntimeException {

	public CannotRequestNullValueException() {
		super();
	}

	public CannotRequestNullValueException(String message) {
		super(message);
	}

}
