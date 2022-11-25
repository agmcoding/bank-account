package spring.boot.bankaccount.exception;

public class BalanceLowerThanWithdrawalException extends RuntimeException {

	public BalanceLowerThanWithdrawalException() {
		super();
	}
	
	public BalanceLowerThanWithdrawalException(String message) {
		super(message);
	}
	
}
