package com.upp.odp.utils;

import static io.restassured.RestAssured.*;
import java.util.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import com.upp.pagemodules.Login.LoginAPI_ODP;
import com.upp.utils.Property;

public class Create_ODP_Virtual_Account_Api {

	public static AccountDetails accDetails;
	public static Stack<AccountDetails> stack1 = new Stack<AccountDetails>();

	public static void createVirtualAccount(String TSID) throws Exception {

		accDetails = new AccountDetails();

		String base_Url = Property.getProperty("Odp_base_uri");

//createAccount api
		RestAssured.useRelaxedHTTPSValidation();
		RestAssured.baseURI = base_Url;
		String response_account = given()
				.header("Content-Type", "application/json")
				.header("Authorization", LoginAPI_ODP.OdpauthToken)
				.body(Payload.createVirtualAccountFromExcelSheet(TSID)).when()
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
