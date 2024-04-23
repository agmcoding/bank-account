package spring.boot.bankaccount.dto;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Component;

import spring.boot.bankaccount.model.Account;

@Component
public class AccountHolderDTO {

	private Integer id;
	private String name;
	private LocalDate birthday;
	private List<Account> accounts;

	public AccountHolderDTO(final Integer id, final String name, final LocalDate birthday, final List<Account> accounts) {
		this.id = id;
		this.name = name;
		this.birthday = birthday;
		this.accounts = accounts;
	}

	public AccountHolderDTO() {
	}

	public Integer getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

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

}
