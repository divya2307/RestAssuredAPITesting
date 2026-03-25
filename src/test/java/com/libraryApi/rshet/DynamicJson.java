package com.libraryApi.rshet;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;


public class DynamicJson {
	
	static Response response;
	static ArrayList<String> bookID = new ArrayList<String>();
	
	//add book using parameters sent to method manually
	@Test(priority = 1)
	public void addBook() {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		response =  given()
			.contentType(ContentType.JSON)
			.body(PayloadJson.addBookPayload("comedy", "826352")) // Dynamically sending this input to json 
		.when()
			.post("Library/Addbook.php")
		.then()
			.assertThat()
			.statusCode(200)
			.log().all()
			.extract().response();

		Assert.assertEquals(response.jsonPath().getString("Msg"),"successfully added");
		bookID.add(response.jsonPath().getString("ID"));
	}


	//Add Book using Data Provider Parameters
	@Test (dataProvider = "bookDetails" , priority = 2)
	public void addBookUsingDataProvider(String  aisleString , String isbn) {
		
		RestAssured.baseURI = "http://216.10.245.166";
		 
		response = given()
			.contentType(ContentType.JSON)
			.body(PayloadJson.addBookPayload(aisleString, isbn)) //data provider input dynamic
		.when()
			.post("Library/Addbook.php")
		.then()
			.assertThat()
			.statusCode(200)
			.log().all()
			.extract().response();

		Assert.assertEquals(response.jsonPath().getString("Msg"),"successfully added");
		bookID.add(response.jsonPath().getString("ID"));
	
	}
		
	
	@DataProvider (name = "bookDetails")
	public Object[][] getData() {
		
		Object[][] obj = new Object[3][2];
		
		obj[0][0] = "comedy";
		obj[0][1] = "324522";
		
		obj[1][0] = "horror";
		obj[1][1] = "452232";
		
		obj[2][0] = "thriller";
		obj[2][1] = "232432";
		
		
		// you can also send it as 
		// return new Object[][] {{"abc","123"},{"hjk","987"},{"gffg","987"}}
		
		return obj;

	}
	
	@Test(priority = 3) // this api call fails via postman too
	public void getBookByID() {
		
		RestAssured.baseURI = "http://216.10.245.166";
		System.out.println("Book ID : " +bookID.get(0));
		given()
			.queryParam("ID", bookID.get(0))
			.log().all()
		.when()
			.get("Library/GetBook.php")
		.then()
			.log().all()
			.assertThat()
			.statusCode(200);
		
		
	}
	
	@Test(priority = 4)
	public void getBookByAUthor() {
		
		RestAssured.baseURI = "http://216.10.245.166";
		
		given()
			.queryParam("AuthorName", "Divya")
		.when()
			.get("Library/GetBook.php")
		.then()
			.log().all()
			.assertThat()
			.statusCode(200);
	}
	
	@Test (priority = 5)
	public void deleteBook() {
		RestAssured.baseURI = "http://216.10.245.166";
		
		for(String id:bookID) {
		
			System.out.println("Book to be deleted  : " +id);
			String delResponse =
			given()
				.contentType(ContentType.JSON)
				.body(PayloadJson.deletePayload(id))
			.when()
				.post("Library/Deletebook.php")
			.then()
				.assertThat()
				.statusCode(200)
				.log().all()
				.extract().response().jsonPath().getString("msg");
			
			Assert.assertEquals(delResponse, "book is successfully deleted");
		}
		
	}
	


}
