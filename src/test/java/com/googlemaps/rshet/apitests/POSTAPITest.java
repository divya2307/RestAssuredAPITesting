package com.googlemaps.rshet.apitests;

import org.testng.annotations.Test;
import org.hamcrest.Matchers;
import static org.hamcrest.Matchers.*;


import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;


import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;


public class POSTAPITest {

	static String response;
	
  @Test
  public void  postAPI() throws IOException {
	  
	  //given ---- all Input Details, like payload, URI, query parameters, any auth, headers required to create the input structure
	  //when ---- submit the API with HTTP methods like GET, PUT, POST  etc
	  //then ---- validate the response received, like res code, body, headers etc
	   
	String jsonBodyString = Files.readString(Paths.get(System.getProperty("user.dir")+"/JsonInputPayloadRequests/googleMap_AddPlace.json"));
	  RestAssured.baseURI = "https://rahulshettyacademy.com/";
	  
	 response =
		given()
	  	.queryParam("key", "qaclick123")
	  	//.log().all()
	  	.contentType(ContentType.JSON) // or you can also send it using headers("ContentType","application/JSON")
	  	.body(jsonBodyString)
	  .when()
	  	.post("maps/api/place/add/json")
	  .then()
	  	.assertThat()
	  	.statusCode(200)
	  	//.log().all()
	  	.body("scope", Matchers.equalTo("APP"))
	  	.header("Server", equalTo("Apache/2.4.52 (Ubuntu)"))
	  	.extract().response().asPrettyString();
	 
	 System.out.println("Response POST API: " + response); 
	 
  }
  
  public static String placeID() {
	  JsonPath jsonPath = new JsonPath(response);
		 String placeIdString = jsonPath.get("place_id");
		 System.out.println("Inside POST :" + placeIdString);
		 return placeIdString; 
  }
}
