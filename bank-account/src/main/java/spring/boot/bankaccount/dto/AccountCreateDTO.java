package spring.boot.bankaccount.dto;

import org.springframework.stereotype.Component;

@Component
public final class AccountCreateDTO {

    private Double balance;
    private Integer accountHolderId;

    public AccountCreateDTO(final Double balance, final Integer accountHolderId) {
        this.balance = balance;
        this.accountHolderId = accountHolderId;
    }

    public AccountCreateDTO() {
        super();
    }

//  getters and setters

    public Double getBalance() {
        return balance;
    }

    public Integer getAccountHolderId() {
        return accountHolderId;
    }

    public void setBalance(final Double balance) {
        this.balance = balance;
    }

    public void setAccountHolderId(final Integer accountHolderId) {
        this.accountHolderId = accountHolderId;
    }

}
