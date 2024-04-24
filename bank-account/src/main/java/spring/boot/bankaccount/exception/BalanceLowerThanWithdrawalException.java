package spring.boot.bankaccount.exception;

public class BalanceLowerThanWithdrawalException extends RuntimeException {

    public BalanceLowerThanWithdrawalException() {
        super();
    }

    public BalanceLowerThanWithdrawalException(final String message) {
        super(message);
    }

}
