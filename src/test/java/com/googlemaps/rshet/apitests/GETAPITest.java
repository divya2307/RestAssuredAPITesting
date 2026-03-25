package com.googlemaps.rshet.apitests;

import java.io.IOException;


import org.hamcrest.Matchers;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GETAPITest {
	
	@Test
	public void getAPITestResponse() throws IOException {
		
	String newAddressString = "70 Summer walk, USA";
	Response response =
			RestAssured
			.given()
				.baseUri("http://rahulshettyacademy.com/")
				.queryParam("key", "qaclick123")
				.queryParam("place_id", POSTAPITest.placeID())
				.log().all()
			.when()
				.get("maps/api/place/get/json")
			.then()
				.assertThat()
				.statusCode(200)
				.body("location.latitude" , Matchers.equalTo("-38.383494"))
				.body("address", Matchers.equalTo(newAddressString))
				
				.extract().response()
				
				;
		
	System.out.println("Response GET API : " + response.asPrettyString());

	}

}
