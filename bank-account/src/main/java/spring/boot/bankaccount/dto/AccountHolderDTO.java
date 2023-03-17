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

	public AccountHolderDTO(Integer id, String name, LocalDate birthday, List<Account> accounts) {
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

}
