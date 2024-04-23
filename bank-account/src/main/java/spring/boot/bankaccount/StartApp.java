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
public final class StartApp implements CommandLineRunner {

    private AccountRepository accountRepository;
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    public void setAccountRepository(final AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Autowired
    public void setAccountHolderRepository(final AccountHolderRepository accountHolderRepository) {
        this.accountHolderRepository = accountHolderRepository;
    }



    @Override
    public void run(final String... args) throws Exception {
        final String PERSON1_NAME = "Bob";
        final int PERSON1_BIRTH_YEAR = 1990;
        final int PERSON1_BIRTH_MONTH = 5;
        final int PERSON1_BIRTH_DAY = 10;

        AccountHolder holder1 = new AccountHolder();
        holder1.setName(PERSON1_NAME);
        holder1.setBirthday(LocalDate.of(PERSON1_BIRTH_YEAR, PERSON1_BIRTH_MONTH, PERSON1_BIRTH_DAY));
        accountHolderRepository.save(holder1);

        final double PERSON1_BALANCE = 20.4;

        Account account1 = new Account();
        account1.setBalance(PERSON1_BALANCE);
        account1.setAccountHolder(holder1);
        accountRepository.save(account1);

        final String PERSON2_NAME = "Amy";
        final int PERSON2_BIRTH_YEAR = 1991;
        final int PERSON2_BIRTH_MONTH = 11;
        final int PERSON2_BIRTH_DAY = 2;

        AccountHolder holder2 = new AccountHolder();
        holder2.setName(PERSON2_NAME);
        holder2.setBirthday(LocalDate.of(PERSON2_BIRTH_YEAR, PERSON2_BIRTH_MONTH, PERSON2_BIRTH_DAY));
        accountHolderRepository.save(holder2);

        final double PERSON2_BALANCE = 30.1;

        Account account2 = new Account();
        account2.setBalance(PERSON2_BALANCE);
        account2.setAccountHolder(holder2);
        accountRepository.save(account2);
    }

}
