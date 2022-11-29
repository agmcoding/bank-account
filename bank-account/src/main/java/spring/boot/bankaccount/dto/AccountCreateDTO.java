package spring.boot.bankaccount.dto;

import org.springframework.stereotype.Component;

@Component
public class AccountCreateDTO {

	private Double balance;

	public AccountCreateDTO(Double balance) {
		this.balance = balance;
	}

	public AccountCreateDTO() {
		super();
	}

//		getters and setters	

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
