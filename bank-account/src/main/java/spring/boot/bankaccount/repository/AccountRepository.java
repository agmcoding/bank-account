package spring.boot.bankaccount.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import spring.boot.bankaccount.model.Account;

/**
 * The SQL methods are imported from the JpaRepository.
 * The Spring will override those methods.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {

}
