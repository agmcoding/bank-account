package spring.boot.bankaccount;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import io.restassured.RestAssured;
import spring.boot.bankaccount.dto.AccountCreateDTO;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ControllerTest {

	@LocalServerPort
	private int randomPort;

	@BeforeEach
	public void setUpTest() {
		RestAssured.port = randomPort;
	}

	@Test
	void createAccount() {

		AccountCreateDTO dto = new AccountCreateDTO();
		dto.setBalance(10.0);
		dto.setAccountHolderId(1);

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(dto).post("/account").then()
				.statusCode(HttpStatus.CREATED.value());

	}

	@Test
	void shouldNotCreateAccountWithoutId() {

		AccountCreateDTO dto = new AccountCreateDTO();
		dto.setBalance(10.0);

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(dto).post("/account").then()
				.statusCode(HttpStatus.BAD_REQUEST.value());

	}
	
	@Test
	void shouldNotCreateAccountWithoutBalance() {

		AccountCreateDTO dto = new AccountCreateDTO();
		dto.setAccountHolderId(1);

		RestAssured.given().contentType(MediaType.APPLICATION_JSON_VALUE).body(dto).post("/account").then()
				.statusCode(HttpStatus.BAD_REQUEST.value());

	}

	@Test
	void validateGetAllAccounts() {

		RestAssured.given().get("/all-accounts").then().statusCode(HttpStatus.OK.value());

	}

}
