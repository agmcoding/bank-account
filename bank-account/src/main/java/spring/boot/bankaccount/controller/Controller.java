package spring.boot.bankaccount.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import spring.boot.bankaccount.dto.AccountCreateDTO;
import spring.boot.bankaccount.dto.AccountDTO;
import spring.boot.bankaccount.dto.AccountHolderCreateDTO;
import spring.boot.bankaccount.dto.AccountHolderDTO;
import spring.boot.bankaccount.exception.CannotRequestNullValueException;
import spring.boot.bankaccount.service.AccountService;

@RestController("/")
public final class Controller {

    private AccountService accountService;

    @Autowired
    public void setAccountService(final AccountService accountService) {
        this.accountService = accountService;
    }

    private void checkIfNull(final Object o) {
        if (o == null) {
            throw new CannotRequestNullValueException("Cannot get/post/put/delete null values.");
        }
    }

    @GetMapping
    public ResponseEntity<String> helloMessage() {
        String message = " Hi there !";
        return ResponseEntity.status(HttpStatus.OK).body(message);
    }

    @GetMapping("/all-accounts")
    public ResponseEntity<List<AccountDTO>> getAllAccounts() {
        List<AccountDTO> accountDTOList = accountService.getAllAccountsDTO();
        if (accountDTOList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(accountDTOList);
    }

    @GetMapping("/account/{accountId}")
    public ResponseEntity<AccountDTO> getAccount(@PathVariable("accountId") final Integer accountId) {
        checkIfNull(accountId);
        AccountDTO accountDTO = accountService.getAccountDTO(accountId);
        return ResponseEntity.status(HttpStatus.OK).body(accountDTO);
    }

    @PutMapping("/account/{accountId}/withdraw")
    public ResponseEntity<AccountDTO> withdrawAccount(@PathVariable("accountId") final Integer accountId, @RequestParam("value") final Double value) {
        checkIfNull(accountId);
        checkIfNull(value);
        AccountDTO accountDTO = accountService.withdraw(accountId, value);
        return ResponseEntity.status(HttpStatus.OK).body(accountDTO);
    }

    @PutMapping("/account/{accountId}/deposit")
    public ResponseEntity<AccountDTO> depositAccount(@PathVariable("accountId") final Integer accountId, @RequestParam("value") final Double value) {
        checkIfNull(accountId);
        checkIfNull(value);
        AccountDTO accountDTO = accountService.deposit(accountId, value);
        return ResponseEntity.status(HttpStatus.OK).body(accountDTO);
    }

    @PutMapping("/account/{originAccountId}/transfer/{destinationAccountId}")
    public ResponseEntity<AccountDTO> transfer(@PathVariable("originAccountId") final Integer originAccountId, @PathVariable("destinationAccountId") final Integer destinationAccountId, @RequestParam("value") final Double value) {
        checkIfNull(originAccountId);
        checkIfNull(destinationAccountId);
        checkIfNull(value);
        AccountDTO originAccountDTO = accountService.transfer(originAccountId, destinationAccountId, value);
        return ResponseEntity.status(HttpStatus.OK).body(originAccountDTO);
    }

    @PostMapping("/account")
    public ResponseEntity<AccountDTO> createAccount(@RequestBody final AccountCreateDTO createDTO) {
        checkIfNull(createDTO);
        AccountDTO newAccountDTO = accountService.create(createDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(newAccountDTO);
    }

    @DeleteMapping("/account/{accountId}")
    public ResponseEntity<?> deleteAccount(@PathVariable final Integer accountId, @RequestParam final boolean showDeletedAccount) {
        checkIfNull(accountId);
        if (!showDeletedAccount) {
            accountService.delete(accountId);
            return ResponseEntity.noContent().build();
        } else {
            AccountDTO deletedAccountDTO = accountService.deleteAndShowDeletedAccount(accountId);
            return ResponseEntity.status(HttpStatus.OK).body(deletedAccountDTO);
        }
    }

    @GetMapping("/all-account-holders")
    public ResponseEntity<List<AccountHolderDTO>> getAllAccountHolders() {
        List<AccountHolderDTO> accountHolderDTOList = accountService.getAllAccountHoldersDTO();
        if (accountHolderDTOList.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
        return ResponseEntity.status(HttpStatus.OK).body(accountHolderDTOList);
    }

    @GetMapping("/account-holder/{accountHolderId}")
    public ResponseEntity<?> getAccountHolder(@PathVariable final Integer accountHolderId) {
        checkIfNull(accountHolderId);
        AccountHolderDTO accountHolderDTO = accountService.getAccountHolderDTO(accountHolderId);
        return ResponseEntity.status(HttpStatus.OK).body(accountHolderDTO);
    }

    @PostMapping("/account-holder")
    public ResponseEntity<?> registerAccountHolder(@RequestBody final AccountHolderCreateDTO accountHolderDTO) {
        checkIfNull(accountHolderDTO);
        AccountHolderDTO newAccountHolderDTO = accountService.registerAccountHolder(accountHolderDTO);
        return ResponseEntity.status(HttpStatus.OK).body(newAccountHolderDTO);
    }

}
