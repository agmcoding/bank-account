package spring.boot.bankaccount.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.stereotype.Component;

@Component
@Entity
public class Account {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Integer id;

	@Column(nullable = false)
	private Double balance;

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
