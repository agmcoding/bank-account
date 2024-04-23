package spring.boot.bankaccount.model;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Component
@Entity
public class AccountHolder {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@Column(nullable = false, length = 50)
	private String name;

	@Column(nullable = false)
	private LocalDate birthday;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "accountHolder", orphanRemoval = true, fetch = FetchType.EAGER)
	@Column
	private List<Account> accounts;

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	@JsonManagedReference
	public List<Account> getAccounts() {
		return accounts;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setName(final String name) {
		this.name = name;
	}

	public void setBirthday(final LocalDate birthday) {
		this.birthday = birthday;
	}

	public void setAccounts(final List<Account> accounts) {
		this.accounts = accounts;
	}

	public void removeAccount(final Account account) {
		accounts.remove(account);
	}

}
