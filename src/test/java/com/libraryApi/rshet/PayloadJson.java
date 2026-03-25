package com.libraryApi.rshet;

public class PayloadJson {
	
	public static String addBookPayload(String aisle, String isbn) {
		String payloadString = "{\n"
				+ "\n"
				+ "\"name\":\"Learn Appium Automation with Java\",\n"
				+ "\"isbn\":\"" +isbn+ "\",\n"
				+ "\"aisle\":\""+aisle+"\",\n"
				+ "\"author\":\"Divya\"\n"
				+ "}\n"
				+ "";
		
		return payloadString;
	}

	public static String deletePayload(String bookID) {
		
		return "{\n"
				+ " \n"
				+ "\"ID\" : \""+bookID+"\"\n"
				+ " \n"
				+ "} \n"
				+ "";
		
	}
}
