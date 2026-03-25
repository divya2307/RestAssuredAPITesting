package serializationUsingPOJO;

import org.testng.annotations.Test;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class POSTAPITest {

	static String response;
	
  @Test
  public void  postAPI() throws IOException {
	  
	  //given ---- all Input Details, like payload, URI, query parameters, any auth, headers required to create the input structure
	  //when ---- submit the API with HTTP methods like GET, PUT, POST  etc
	  //then ---- validate the response received, like res code, body, headers etc
	   
	  
	  List<String> typeList = Arrays.asList("Shopping Mart" , "Super Market");
	  GoogleMapAddPlacePOJO map = new GoogleMapAddPlacePOJO();
	  LocationPOJO location = new LocationPOJO();
	  
	  location.setLat(-78.376784);
	  location.setLng(76.875468);
	  
	  map.setAccuracy(50);
	  map.setAddress("13245, Phoenix , AZ");
	  map.setLocation(location);
	  map.setName("Walmart");
	  map.setPhone_number("+1 (654) 789-6578");
	  map.setTypes(typeList);
	  map.setWebsite("http://google.com");
	  map.setLanguage("English");
	  
	  //We can use Request SPEC Builder and Response SPEC Builder for the common parameters which we are sending in API requests
	  
	  RequestSpecification requestSpecification = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/")
			  .addQueryParam("key", "qaclick123").setContentType(ContentType.JSON). build();
	  
	  ResponseSpecification responseSpecification = new ResponseSpecBuilder().expectStatusCode(200).expectHeader("Server", "Apache/2.4.52 (Ubuntu)") .build();
	
	  
	 RequestSpecification req =	given().spec(requestSpecification); // here we can seperate the given when then
	   
	response =  req.when()
	  .log().all()
	  	.body(map)
	  	.post("maps/api/place/add/json")
	  .then().spec(responseSpecification)
	  	.log().all()
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
