package deserializationUsingPOJO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetCourseDetailsusingPOJO {
	
	static String accessToken;
	
	List<String> webAutomationcourse = Arrays.asList("Selenium Webdriver Java", "Cypress" , "Protractor");

	
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
		
		CourseJSONPOJO courseJSON = 
		RestAssured
		.given()
			.baseUri("https://rahulshettyacademy.com/oauthapi/getCourseDetails")
			.queryParam("access_token", accessToken)
		.when()
			.get()
			.as(CourseJSONPOJO.class);
		
		System.out.println("LinkedIn Link: " + courseJSON.getLinkedIn());
		System.out.println("Instructor Name: " + courseJSON.getInstructor());
		
		//Get CourseDetails and Price of WebAutomation Section
		
		List<WebAutomationPojo> webautomation = courseJSON.getCourses().getWebAutomation();
		ArrayList<String> webCourse = new ArrayList<String>();
		
		for(WebAutomationPojo details :  webautomation) {
			webCourse.add(details.getCourseTitle());
			System.out.println("WebAutomation Course Details : " + details.getCourseTitle());
			System.out.println("WebAutomation Price Details : " + details.getPrice());		
		}
		
		Assert.assertTrue(webCourse.equals(webAutomationcourse));
		
	   List<APIPojo> api = courseJSON.getCourses().getApi();
	   for(APIPojo details :  api  ) {
		   
			System.out.println("API Course Details : " + details.getCourseTitle());
			System.out.println("API Price Details : " + details.getPrice());		
		}
	   
	   List<MobilePojo> mobile = courseJSON.getCourses().getMobile();
	   for(MobilePojo details :  mobile  ) {
			System.out.println("Mobile Course Details : " + details.getCourseTitle());
			System.out.println("Mobile Price Details : " + details.getPrice());		
		}

	}
}
