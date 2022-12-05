package spring.boot.bankaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.boot.bankaccount.model.AccountHolder;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Integer>{

}
