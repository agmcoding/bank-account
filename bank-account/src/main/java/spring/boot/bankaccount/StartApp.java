package spring.boot.bankaccount;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import spring.boot.bankaccount.model.Account;
import spring.boot.bankaccount.repository.AccountRepository;

@Component
public class StartApp implements CommandLineRunner{

	private AccountRepository accountRepository;
	
	@Autowired
	public void setAccountRepository(AccountRepository accountRepository) {
		this.accountRepository = accountRepository;
	}



	@Override
	public void run(String... args) throws Exception {
		
		Account account1 = new Account();
		account1.setBalance(20.4);
		accountRepository.save(account1);
		
		Account account2 = new Account();
		account2.setBalance(30.1);
		accountRepository.save(account2);
		
	}

}
