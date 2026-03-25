package cucumberResources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	static PrintStream logPrintStream ; 
	static RequestSpecification requestSpecification;
	
	
	public static String getJsonValue(Response response, String key) {
		
		String value = response.jsonPath().getString(key);
		return value;
		
	}
	
	public static String getProperty(String key) throws IOException {
		
		FileInputStream fis = new FileInputStream(new File(System.getProperty("user.dir"))+ "/src/test/java/cucumberResources/global.properties");
		Properties properties = new Properties();
		
		properties.load(fis);
		String value = properties.getProperty(key);
		
		return value;
		}
	
	
	public static RequestSpecification requestSpecification() throws IOException {
		//check if instance is already created , you can create function for it
		
		if (requestSpecification == null) {
		
			logPrintStream = new PrintStream(new FileOutputStream("logging.txt"));
		
			requestSpecification = new RequestSpecBuilder().setBaseUri(getProperty("base_uri"))
				  .addQueryParam("key", "qaclick123")
				  .addFilter(RequestLoggingFilter.logRequestTo(logPrintStream))
				  .addFilter(ResponseLoggingFilter.logResponseTo(logPrintStream))
				  .setContentType(ContentType.JSON).build();
		}
		return requestSpecification;
		 
	}

	public static ResponseSpecification responseSpecification() {
		
		ResponseSpecification  responseSpecification = new ResponseSpecBuilder()	
				.expectContentType(ContentType.JSON).build();
				
		return responseSpecification;
	}
}
