package spring.boot.bankaccount.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import spring.boot.bankaccount.dto.AccountCreateDTO;
import spring.boot.bankaccount.dto.AccountDTO;
import spring.boot.bankaccount.exception.AccountDoesntExistsException;
import spring.boot.bankaccount.exception.BalanceLowerThanWithdrawalException;
import spring.boot.bankaccount.exception.CannotRequestNullValueException;
import spring.boot.bankaccount.model.Account;
import spring.boot.bankaccount.repository.AccountHolderRepository;
import spring.boot.bankaccount.repository.AccountRepository;

@Service
public class AccountService {

	AccountRepository accountRepository;
	AccountDTO accountDTO;
	Account account;
	AccountHolderRepository accountHolderRepository;

	@Autowired
	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Autowired
	public void setAccountDTO(AccountDTO accountDTO) {
		this.accountDTO = accountDTO;
	}

	@Autowired
	public void setAccount(Account account) {
		this.account = account;
	}

	@Autowired
	public void setAccountHolderRepository(AccountHolderRepository accountHolderRepository) {
		this.accountHolderRepository = accountHolderRepository;
	}

	/* from account to accountDTO */
	private AccountDTO setAccountDTO(Account settingAccountToDTO) {
		accountDTO.setId(settingAccountToDTO.getId());
		accountDTO.setBalance(settingAccountToDTO.getBalance());
		accountDTO.setAccountHolder(settingAccountToDTO.getAccountHolder());
		return accountDTO;
	}

	/* from accountDTO to account */
	private Account setAccount(AccountDTO dto) {
		account.setId(dto.getId());
		account.setBalance(dto.getBalance());
		account.setAccountHolder(dto.getAccountHolder());
		return account;
	}

	/* from account List to an accountDTO List */
	private List<AccountDTO> setAccountDTOList(List<Account> accountList) {
		return accountList.stream()
				.map(account -> new AccountDTO(account.getId(), account.getBalance(), account.getAccountHolder()))
				.collect(Collectors.toList());
	}

	public AccountDTO getAccountDTO(Integer accountId) {
		ifDoesntExistsThrowException(accountId);
		Optional<Account> accountEntity = accountRepository.findById(accountId);
		Account account = accountEntity.get();
		AccountDTO accountDTO = setAccountDTO(account);
		return accountDTO;
	}

	public List<AccountDTO> getAllAccountsDTO() {
		List<Account> accountList = accountRepository.findAll();
		return setAccountDTOList(accountList);
	}

	public AccountDTO withdraw(Integer accountId, Double value) {
		ifDoesntExistsThrowException(accountId);
		Account withdrawalAccount = accountRepository.findById(accountId).get();

		if (withdrawalAccount.getBalance() < value) {
			throw new BalanceLowerThanWithdrawalException(
					"The account with id " + accountId + " has balance lower than withdrawal: " + value);
		}
		Double valueAfterWithdraw = withdrawalAccount.getBalance() - value;
		withdrawalAccount.setBalance(valueAfterWithdraw);
		accountRepository.save(withdrawalAccount);

		return setAccountDTO(withdrawalAccount);
	}

	public AccountDTO deposit(Integer accountId, Double value) {
		ifDoesntExistsThrowException(accountId);
		Account depositAccount = accountRepository.findById(accountId).get();

		Double valueAfterDeposit = depositAccount.getBalance() + value;
		depositAccount.setBalance(valueAfterDeposit);
		accountRepository.save(depositAccount);

		return setAccountDTO(depositAccount);
	}

	@Transactional
	public AccountDTO transfer(Integer originAccountId, Integer destinationAccountId, Double value) {
		deposit(destinationAccountId, value);
		AccountDTO originAccountDTO = withdraw(originAccountId, value);

		return originAccountDTO;
	}

	private boolean ifDoesntExistsThrowException(Integer accountId) {
		boolean itExists = accountRepository.existsById(accountId);
		if (!itExists) {
			throw new AccountDoesntExistsException("The account with id " + accountId + " doesn't exist.");
		}
		return itExists;
	}

	public AccountDTO create(AccountCreateDTO createDTO) {
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

	public void delete(Integer accountId) {
		ifDoesntExistsThrowException(accountId);
		accountRepository.deleteById(accountId);
	}

	public AccountDTO deleteAndShowDeletedAccount(Integer accountId) {
		ifDoesntExistsThrowException(accountId);
		Account accountToBeDeleted = accountRepository.findById(accountId).get();
		accountRepository.delete(accountToBeDeleted);
		AccountDTO deletedAccountDTO = setAccountDTO(accountToBeDeleted);
		return deletedAccountDTO;
	}

}
