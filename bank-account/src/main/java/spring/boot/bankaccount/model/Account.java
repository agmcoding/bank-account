package spring.boot.bankaccount.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Component
@Entity
public class Account {

	@Id()
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "account_id")
	private Integer id;

	@Column(nullable = false)
	private Double balance;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "account_holder")
	private AccountHolder accountHolder;

//	getters and setters	

	public Integer getId() {
		return id;
	}

	public Double getBalance() {
		return balance;
	}

	@JsonBackReference
	public AccountHolder getAccountHolder() {
		return accountHolder;
	}

	public void setId(final Integer id) {
		this.id = id;
	}

	public void setBalance(final Double balance) {
		this.balance = balance;
	}

	public void setAccountHolder(final AccountHolder accountHolder) {
		this.accountHolder = accountHolder;
	}

}
