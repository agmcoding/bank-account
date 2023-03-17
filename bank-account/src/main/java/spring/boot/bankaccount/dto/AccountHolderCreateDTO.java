package spring.boot.bankaccount.dto;

import java.time.LocalDate;
import org.springframework.stereotype.Component;

@Component
public class AccountHolderCreateDTO {

	private String name;
	private LocalDate birthday;

	public String getName() {
		return name;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

}
