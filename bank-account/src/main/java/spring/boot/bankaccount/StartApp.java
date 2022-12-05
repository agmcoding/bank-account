package spring.boot.bankaccount;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import spring.boot.bankaccount.model.Account;
import spring.boot.bankaccount.model.AccountHolder;
import spring.boot.bankaccount.repository.AccountHolderRepository;
import spring.boot.bankaccount.repository.AccountRepository;

@Component
public class StartApp implements CommandLineRunner{

	private AccountRepository accountRepository;
	private AccountHolderRepository accountHolderRepository;
	
	@Autowired
	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}

	@Autowired
	public void setAccountHolderRepository(AccountHolderRepository accountHolderRepository) {
		this.accountHolderRepository = accountHolderRepository;
	}



	@Override
	public void run(String... args) throws Exception {
		
		AccountHolder holder1 = new AccountHolder();
		holder1.setName("Bob");
		holder1.setBirthday(LocalDate.of(1990, 5, 10));
		accountHolderRepository.save(holder1);
		
		Account account1 = new Account();
		account1.setBalance(20.4);
		account1.setAccountHolder(holder1);
		accountRepository.save(account1);

		
		AccountHolder holder2 = new AccountHolder();
		holder2.setName("Amy");
		holder2.setBirthday(LocalDate.of(1991, 11, 2));
		accountHolderRepository.save(holder2);
		
		Account account2 = new Account();
		account2.setBalance(30.1);
		account2.setAccountHolder(holder2);
		accountRepository.save(account2);
		
	}

}
