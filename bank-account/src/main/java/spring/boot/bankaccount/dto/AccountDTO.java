package spring.boot.bankaccount.dto;

import org.springframework.stereotype.Component;

import spring.boot.bankaccount.model.AccountHolder;

@Component
public class AccountDTO {

	private Integer id;
	private Double balance;
	private AccountHolder accountHolder;

	public AccountDTO(Integer id, Double balance, AccountHolder accountHolder) {
		this.id = id;
		this.balance = balance;
		this.accountHolder = accountHolder;
	}

	public AccountDTO() {
		super();
	}

//	getters and setters	

	public Integer getId() {
		return id;
	}

	public Double getBalance() {
		return balance;
	}

	public AccountHolder getAccountHolder() {
		return accountHolder;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public void setAccountHolder(AccountHolder accountHolder) {
		this.accountHolder = accountHolder;
	}

}
