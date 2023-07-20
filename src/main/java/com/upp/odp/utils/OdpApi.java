package com.upp.odp.utils;

import static io.restassured.RestAssured.*;
import java.util.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import com.upp.utils.Property;

public class OdpApi {

	public static AccountDetails accDetails;
	public static Stack<AccountDetails> stack1 = new Stack<AccountDetails>();

	public static void createAccount(String TSID) throws Exception {

		accDetails = new AccountDetails();

		String base_Url = Property.getProperty("Odp_base_uri");

//login api
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = base_Url;
		String responseLogin = given()
				.header("Content-Type", "application/json")
				.body(Payload.Login()).when()
				.post("api/a/rbac/login").then()
				.assertThat().statusCode(200)
				.extract()
				.response().asString();

		JsonPath js = new JsonPath(responseLogin);

		String token = js.getString("token");

		String authToken = "JWT " + token;

//createAccount api
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = base_Url;
		String response_account = given()
				.header("Content-Type", "application/json")
				.header("Authorization", authToken)
				.body(Payload.createAccount(TSID)).when()
				.post("api/c/acache/accounts").then()
				.assertThat()
				.statusCode(200).extract()
				.response().asString();

		JsonPath js1 = new JsonPath(response_account);

		String accountno = js1.getString("accountNumber");
		String country = js1.getString("country");
		String currency = js1.getString("currency");

		accDetails.setAccno(accountno);
		accDetails.setCountry(country);
		accDetails.setCurrency(currency);

		pushelmnt(stack1, accDetails);

//logout api
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = base_Url;
		String response_LogOut = given()
				.header("Content-Type", "application/json")
				.header("Authorization", authToken)
				.when().delete("api/a/rbac/logout").then()
				.assertThat().statusCode(200)
				.extract().response().asString();

		System.out.println(response_LogOut);

	}

	public static void pushelmnt(Stack<AccountDetails> stk, AccountDetails accDetails) {
		stk.push(accDetails);
		System.out.println("push -> " + accDetails);
		System.out.println("stack: " + stk);
	}

	public static AccountDetails popelmnt(Stack<?> stk) {
		System.out.print("pop -> ");
		AccountDetails accno = (AccountDetails) stk.pop();
		System.out.println(accno);
		System.out.println("stack: " + stk);

		return accno;
	}

}
