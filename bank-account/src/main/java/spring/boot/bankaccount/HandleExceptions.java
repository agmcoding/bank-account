package spring.boot.bankaccount;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import spring.boot.bankaccount.exception.AccountAlreadyExistsException;
import spring.boot.bankaccount.exception.AccountDoesntExistsException;
import spring.boot.bankaccount.exception.AccountHolderNotRegisteredException;
import spring.boot.bankaccount.exception.BalanceLowerThanWithdrawalException;
import spring.boot.bankaccount.exception.CannotRequestNullValueException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public final class HandleExceptions {

    @ExceptionHandler(AccountDoesntExistsException.class)
    public ResponseEntity<?> handleAccountDoesntExistsException(final AccountDoesntExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(CannotRequestNullValueException.class)
    public ResponseEntity<?> handleCannotRequestNullValueException(final CannotRequestNullValueException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(AccountAlreadyExistsException.class)
    public ResponseEntity<?> handleAccountAlreadyExistsException(final AccountAlreadyExistsException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(BalanceLowerThanWithdrawalException.class)
    public ResponseEntity<?> handleBalanceLowerThanWithdrawalException(final BalanceLowerThanWithdrawalException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

    @ExceptionHandler(AccountHolderNotRegisteredException.class)
    public ResponseEntity<?> handleAccountHolderNotRegisteredException(final AccountHolderNotRegisteredException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
    }

}
