package spring.boot.bankaccount.exception;

public class AccountDoesntExistsException extends RuntimeException {

	public AccountDoesntExistsException() {
		super();
	}
	
	public AccountDoesntExistsException(String message) {
		super(message);
	}
	
	
}

