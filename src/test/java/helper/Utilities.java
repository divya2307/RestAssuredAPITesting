package helper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import io.restassured.path.json.JsonPath;

public class Utilities {
	
	static String jsonFileString ;
	
	
	public static String readFile() throws IOException {
		
		jsonFileString = Files.readString(Paths.get(System.getProperty("user.dir")+"/JsonInputPayloadRequests/courses.json"));	
		return jsonFileString;
	}
	
	
	public static JsonPath parseComplexJson() throws IOException {
		
		JsonPath jsonPath = new JsonPath(readFile());
		
		return jsonPath;
	}

}
