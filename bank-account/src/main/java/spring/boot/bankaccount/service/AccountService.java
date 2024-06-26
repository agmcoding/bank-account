package spring.boot.bankaccount.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.boot.bankaccount.dto.AccountCreateDTO;
import spring.boot.bankaccount.dto.AccountDTO;
import spring.boot.bankaccount.dto.AccountHolderCreateDTO;
import spring.boot.bankaccount.dto.AccountHolderDTO;
import spring.boot.bankaccount.exception.AccountDoesntExistsException;
import spring.boot.bankaccount.exception.AccountHolderNotRegisteredException;
import spring.boot.bankaccount.exception.BalanceLowerThanWithdrawalException;
import spring.boot.bankaccount.exception.CannotRequestNullValueException;
import spring.boot.bankaccount.model.Account;
import spring.boot.bankaccount.model.AccountHolder;
import spring.boot.bankaccount.repository.AccountHolderRepository;
import spring.boot.bankaccount.repository.AccountRepository;

@Service
public class AccountService {

    private AccountRepository accountRepository;
    private AccountDTO accountDTO;
    private Account account;
    private AccountHolderRepository accountHolderRepository;
    private AccountHolderDTO accountHolderDTO;
    private AccountHolder accountHolder;

    @Autowired
    public void setAccountRepository(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Autowired
    public void setAccountDTO(final AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
    }

    @Autowired
    public void setAccount(final Account account) {
        this.account = account;
    }

    @Autowired
    public void setAccountHolderRepository(final AccountHolderRepository accountHolderRepository) {
        this.accountHolderRepository = accountHolderRepository;
    }

    @Autowired
    public void setAccountHolderDTO(final AccountHolderDTO accountHolderDTO) {
        this.accountHolderDTO = accountHolderDTO;
    }

    @Autowired
    public void setAccountHolder(final AccountHolder accountHolder) {
        this.accountHolder = accountHolder;
    }

    /* from account to accountDTO */
    private AccountDTO setAccountDTO(final Account settingAccountToDTO) {
        accountDTO.setId(settingAccountToDTO.getId());
        accountDTO.setBalance(settingAccountToDTO.getBalance());
        accountDTO.setAccountHolder(settingAccountToDTO.getAccountHolder());
        return accountDTO;
    }

    /* from accountDTO to account */
    private Account setAccount(final AccountDTO dto) {
        account.setId(dto.getId());
        account.setBalance(dto.getBalance());
        account.setAccountHolder(dto.getAccountHolder());
        return account;
    }

    /* from account List to an accountDTO List */
    private List<AccountDTO> setAccountDTOList(final List<Account> accountList) {
        return accountList.stream()
            .map(account -> new AccountDTO(account.getId(), account.getBalance(), account.getAccountHolder()))
            .collect(Collectors.toList());
    }

    public AccountDTO getAccountDTO(final Integer accountId) {
        ifAccountDoesntExistsThrowException(accountId);
        Optional<Account> accountEntity = accountRepository.findById(accountId);
        Account account = accountEntity.get();
        AccountDTO accountDTO = setAccountDTO(account);
        return accountDTO;
    }

    public List<AccountDTO> getAllAccountsDTO() {
        List<Account> accountList = accountRepository.findAll();
        return setAccountDTOList(accountList);
    }

    public AccountDTO withdraw(final Integer accountId, final Double value) {
        ifAccountDoesntExistsThrowException(accountId);
        Account withdrawalAccount = accountRepository.findById(accountId).get();

        if (withdrawalAccount.getBalance() < value) {
            throw new BalanceLowerThanWithdrawalException("The account with id " + accountId + " has balance lower than withdrawal: " + value);
        }
        Double valueAfterWithdraw = withdrawalAccount.getBalance() - value;
        withdrawalAccount.setBalance(valueAfterWithdraw);
        accountRepository.save(withdrawalAccount);

        return setAccountDTO(withdrawalAccount);
    }

    public AccountDTO deposit(final Integer accountId, final Double value) {
        ifAccountDoesntExistsThrowException(accountId);
        Account depositAccount = accountRepository.findById(accountId).get();

        Double valueAfterDeposit = depositAccount.getBalance() + value;
        depositAccount.setBalance(valueAfterDeposit);
        accountRepository.save(depositAccount);

        return setAccountDTO(depositAccount);
    }

    @Transactional
    public AccountDTO transfer(final Integer originAccountId, final Integer destinationAccountId, final Double value) {
        deposit(destinationAccountId, value);
        AccountDTO originAccountDTO = withdraw(originAccountId, value);

        return originAccountDTO;
    }

    private boolean ifAccountDoesntExistsThrowException(final Integer accountId) {
        boolean itExists = accountRepository.existsById(accountId);
        if (!itExists) {
            throw new AccountDoesntExistsException("The account with id " + accountId + " doesn't exist.");
        }
        return itExists;
    }

    private boolean ifAccountHolderNotRegisteredThrowException(final Integer accountHolderId) {
        boolean itExists = accountHolderRepository.existsById(accountHolderId);
        if (!itExists) {
            throw new AccountHolderNotRegisteredException("The account holder with id " + accountHolderId + " isn't registered.");
        }
        return itExists;
    }

    public AccountDTO create(final AccountCreateDTO createDTO) {
        if (createDTO.getBalance() == null || createDTO.getAccountHolderId() == null) {
            throw new CannotRequestNullValueException("Cannot get/post/put/delete null values.");
        }

        accountDTO.setId(0);
        accountDTO.setBalance(createDTO.getBalance());
        accountDTO.setAccountHolder(accountHolderRepository.findById(createDTO.getAccountHolderId()).get());
        Account newAccount = setAccount(accountDTO);
        Account savedNewAccount = accountRepository.save(newAccount);
        AccountDTO newDTO = setAccountDTO(savedNewAccount);
        return newDTO;
    }

    public void delete(final Integer accountId) {
        ifAccountDoesntExistsThrowException(accountId);
        accountRepository.deleteById(accountId);
    }

    public AccountDTO deleteAndShowDeletedAccount(final Integer accountId) {
        ifAccountDoesntExistsThrowException(accountId);
        Account accountToBeDeleted = accountRepository.findById(accountId).get();

        accountToBeDeleted.getAccountHolder().removeAccount(accountToBeDeleted);
        accountHolderRepository.save(accountToBeDeleted.getAccountHolder());

        accountRepository.delete(accountToBeDeleted);
        AccountDTO deletedAccountDTO = setAccountDTO(accountToBeDeleted);
        return deletedAccountDTO;
    }

    public AccountHolderDTO registerAccountHolder(final AccountHolderCreateDTO accountHolderCreateDTO) {
        if (accountHolderCreateDTO.getName() == null || accountHolderCreateDTO.getBirthday() == null) {
            throw new CannotRequestNullValueException("Cannot get/post/put/delete null values.");
        }

        accountHolderDTO.setId(0);
        accountHolderDTO.setName(accountHolderCreateDTO.getName());
        accountHolderDTO.setBirthday(accountHolderCreateDTO.getBirthday());
        AccountHolder newAccountHolder = setAccountHolder(accountHolderDTO);
        AccountHolder savedNewAccountHolder = accountHolderRepository.save(newAccountHolder);
        AccountHolderDTO newDTO = setAccountHolderDTO(savedNewAccountHolder);
        return newDTO;
    }

    /* from accountHolderDTO to accountHolder */
    private AccountHolder setAccountHolder(final AccountHolderDTO dto) {
        accountHolder.setId(dto.getId());
        accountHolder.setName(dto.getName());
        accountHolder.setBirthday(dto.getBirthday());
        accountHolder.setAccounts(dto.getAccounts());
        return accountHolder;
    }

    /* from accountHolder to accountHolderDTO */
    private AccountHolderDTO setAccountHolderDTO(final AccountHolder settingAccountHolderToDTO) {
        accountHolderDTO.setId(settingAccountHolderToDTO.getId());
        accountHolderDTO.setName(settingAccountHolderToDTO.getName());
        accountHolderDTO.setBirthday(settingAccountHolderToDTO.getBirthday());
        accountHolderDTO.setAccounts(settingAccountHolderToDTO.getAccounts());
        return accountHolderDTO;
    }

    public List<AccountHolderDTO> getAllAccountHoldersDTO() {
        List<AccountHolder> accountHolderList = accountHolderRepository.findAll();
        return setAccountHolderDTOList(accountHolderList);
    }

    /* from accountHolder List to an accountHolderDTO List */
    private List<AccountHolderDTO> setAccountHolderDTOList(final List<AccountHolder> accountHolderList) {
        return accountHolderList.stream()
        .map(accountHolder -> new AccountHolderDTO(accountHolder.getId(), accountHolder.getName(), accountHolder.getBirthday(), accountHolder.getAccounts()))
        .collect(Collectors.toList());
    }

    public AccountHolderDTO getAccountHolderDTO(final Integer accountHolderId) {
        ifAccountHolderNotRegisteredThrowException(accountHolderId);
        AccountHolder accountHolder = accountHolderRepository.findById(accountHolderId).get();
        AccountHolderDTO accountHolderDTO = setAccountHolderDTO(accountHolder);
        return accountHolderDTO;
    }

}
