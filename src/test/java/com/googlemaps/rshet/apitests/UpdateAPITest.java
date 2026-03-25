package com.googlemaps.rshet.apitests;


import org.testng.annotations.Test;

import java.io.IOException;

import org.hamcrest.Matchers;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import net.minidev.json.JSONObject;

public class UpdateAPITest {
  @Test
  public void updateApi() throws IOException {
	  
	  JSONObject updatepayloadRequest = new JSONObject();
	  
	  updatepayloadRequest.put("place_id",POSTAPITest.placeID());
	  updatepayloadRequest.put("address", "70 Summer walk, USA");
	  updatepayloadRequest.put("key", "qaclick123");
	  
  
	  RestAssured
	  .given()
	  	.baseUri("https://rahulshettyacademy.com/")
	  	.queryParam("key", "qaclick123")
	  	.contentType(ContentType.JSON)
	  	.log().all()
	  	.body(updatepayloadRequest.toString())
	  .when()
	  	.put("maps/api/place/update/json")
	  .then()
	  	.assertThat()
	  	.statusCode(200)
	  	.log().all()
	  	.body("msg", Matchers.equalTo("Address successfully updated"));
	  	
	  
	  	// you can use TESTNG assert here 
	  
  }
}
