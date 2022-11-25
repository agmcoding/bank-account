package spring.boot.bankaccount.dto;

import org.springframework.stereotype.Component;

@Component
public class AccountDTO {

	private Integer id;
	private Double balance;

	public AccountDTO(Integer id, Double balance) {
		this.id = id;
		this.balance = balance;
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

	public void setId(Integer id) {
		this.id = id;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

}
