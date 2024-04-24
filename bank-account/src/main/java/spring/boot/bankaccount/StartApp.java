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
        final String person1Name = "Bob";
        final int person1BirthYear = 1990;
        final int person1BirthMonth = 5;
        final int person1BirthDay = 10;

        AccountHolder holder1 = new AccountHolder();
        holder1.setName(person1Name);
        holder1.setBirthday(LocalDate.of(person1BirthYear, person1BirthMonth, person1BirthDay));
        accountHolderRepository.save(holder1);

        final double person1Balance = 20.4;

        Account account1 = new Account();
        account1.setBalance(person1Balance);
        account1.setAccountHolder(holder1);
        accountRepository.save(account1);

        final String person2Name = "Amy";
        final int person2BirthYear = 1991;
        final int person2BirthMonth = 11;
        final int person2BirthDay = 2;

        AccountHolder holder2 = new AccountHolder();
        holder2.setName(person2Name);
        holder2.setBirthday(LocalDate.of(person2BirthYear, person2BirthMonth, person2BirthDay));
        accountHolderRepository.save(holder2);

        final double person2Balance = 30.1;

        Account account2 = new Account();
        account2.setBalance(person2Balance);
        account2.setAccountHolder(holder2);
        accountRepository.save(account2);
    }

}
