package spring.boot.bankaccount.dto;

import org.springframework.stereotype.Component;

@Component
public class AccountCreateDTO {

	private Double balance;
	private Integer accountHolderId;

	public AccountCreateDTO(Double balance, Integer accountHolderId) {
		this.balance = balance;
		this.accountHolderId = accountHolderId;
	}

	public AccountCreateDTO() {
		super();
	}

//		getters and setters	

	public Double getBalance() {
		return balance;
	}

	public Integer getAccountHolderId() {
		return accountHolderId;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public void setAccountHolderId(Integer accountHolderId) {
		this.accountHolderId = accountHolderId;
	}

}
