package com.testautomation.apitesting.tests;

import org.hamcrest.Matchers;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.minidev.json.JSONObject;

public class PostAPIRequest {
	
	static Response response ;
	
	@Test	
	public void createBookings() {
		
		
		//prepare the request body
		
		JSONObject payloadRequest = new JSONObject();
		JSONObject bookingDates = new JSONObject();
		
		
		payloadRequest.put("firstname", "Divya");
		payloadRequest.put("lastname", "Prabhu");
		payloadRequest.put("totalprice", 1000);
		payloadRequest.put("depositpaid", true);
		payloadRequest.put("additionalneeds", "Breakfast");
		payloadRequest.put("bookingdates", bookingDates);
		
		bookingDates.put("checkin", "2018-01-01");
		bookingDates.put("checkout","2019-01-01");
		
		
		response = 
		RestAssured 
			.given()
				.contentType(ContentType.JSON)
				.body(payloadRequest.toString())
				.baseUri("https://restful-booker.herokuapp.com/booking")
				//.log().body()  //to log the request body on the console
				//.log().headers() //to print the headers
				//.log().all()  // this will print both request body and headers
				
			.when()
				.post()
			.then()
				.assertThat()  // all the below stmts are the assertions
				//.log().all()  // this will print the response body and headers
				//.log().ifValidationFails() // print the headers and response when validation fails
				.statusCode(200)
				.statusLine("HTTP/1.1 200 OK")
				.body("booking.firstname", Matchers.equalTo("Divya"))
				.body("booking.totalprice",Matchers.equalTo(1000))
				.body("booking.bookingdates.checkin", Matchers.equalTo("2018-01-01"))
				
			.extract()
				.response();
		
		Assert.assertTrue(response.getBody().asString().contains("bookingid"));
		
		// to access the json content we should use jsonPath()
		System.out.println("********************************************");
		System.out.println("Firstname is : " + response.jsonPath().getString("booking.firstname"));
		System.out.println("Lastname is : " + response.jsonPath().getString("booking.lastname"));
		System.out.println("Checkin Date is : " + response.jsonPath().getString("booking.bookingdates.checkin"));
		System.out.println("Checkout Date is : " + response.jsonPath().getString("booking.bookingdates.checkout"));
		
		System.out.println(response.asPrettyString());		
		
	}
}
