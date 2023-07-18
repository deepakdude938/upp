package com.upp.pagemodules.Login;

import static io.restassured.RestAssured.*;
import java.util.*;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import com.upp.odp.utils.Payload;
import com.upp.utils.Property;

public class LoginAPI_ODP {
	public static String OdpauthToken="";

	public static void loginToUpp() throws Exception {


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

				OdpauthToken = "JWT " + token;
}
}