package cucumberStepDefinition;

import static io.restassured.RestAssured.*;

import java.io.IOException;

import org.hamcrest.Matchers;

import cucumberResources.APIPathResourceEnum;
import cucumberResources.TestDataBuild;
import cucumberResources.Utils;

import static org.junit.Assert.*;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;


public class placeValidationStepDefinition {

	ResponseSpecification responseSpecification;
	RequestSpecification req;
	static Response response;
	static String placeID;
	TestDataBuild data = new TestDataBuild();
	


	@Given("Add Place Payload with {string} ,{string} , {int} , {string}, {string}, {string}")
	public void add_place_payload_with(String name, String address, Integer accuracy, String mobilenumber, String website, String language) throws IOException {
		req = given().spec(Utils.requestSpecification()).body(data.addPlacePayload(name,address,accuracy, mobilenumber,website,language));		 
	}
	
	@When("user calls {string} with {string} http request")
	public void user_calls_add_place_api_with_post_http_request(String request , String httpMethod) {
		
		String resource = APIPathResourceEnum.valueOf(request).getResource();
		
		if(httpMethod.equalsIgnoreCase("POST")) {
			response = req.when().post(resource);		  
		}
		
		else if(httpMethod.equalsIgnoreCase("GET")) {
			response = req.when().get(resource);		  
		}
		
		else if(httpMethod.equalsIgnoreCase("DELETE")) {
			response = req.when().delete(resource);		  
		}
		
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(Integer statusCode) {
		responseSpecification =  req.then().spec(Utils.responseSpecification()).expect().statusCode(statusCode);
		assertEquals(response.getStatusCode(), 200);
	    
	}
	@Then("{string} in response body is {string}")
	public void validate_response_body(String key , String expectedValue) {
		responseSpecification =  req.then().spec(Utils.responseSpecification()).expect().body("scope", Matchers.equalToIgnoringCase(expectedValue));
		assertEquals(response.jsonPath().getString(key), expectedValue);
	}

	@Then("verify place_id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedName, String request) throws IOException {
		placeID = Utils.getJsonValue(response,"place_id");
	       
	    req = given().spec(Utils.requestSpecification()).queryParam("place_id", placeID);
	    user_calls_add_place_api_with_post_http_request(request , "GET");
	    
	    String actualName = Utils.getJsonValue(response,"name");
	    
	    assertEquals(actualName, expectedName);
	    
	}


	@Given("Place is added successfully")
	public void place_is_added_successfully() throws IOException {
		req = given().spec(Utils.requestSpecification()).body(data.deletePayload(placeID));
	}






}
