package oAuthDemo;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class AuthorizationServer {
	
	static String accessToken;
	
	@Test (priority = 1)
	public static void accessToken() {
		
	Response response =
		RestAssured
		.given()
			.baseUri("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token")
			.formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
			.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
			.formParams("grant_type", "client_credentials")
			.formParams("scope", "trust")
		.when()
			.post()
		.then()
			.assertThat()
			.statusCode(200)
			.log().all()
			.extract().response();
		
	 accessToken = response.jsonPath().getString("access_token");
			
	}
	
	@Test(priority = 2)
	
	public void getCourseDetails() {
		
		RestAssured
		.given()
			.baseUri("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
			.queryParam("access_token", accessToken)
		.when()
			.get()
		.then()
			.assertThat()
			.log().all()
		;
	}
}
