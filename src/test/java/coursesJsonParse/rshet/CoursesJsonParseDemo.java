package coursesJsonParse.rshet;

import java.io.IOException;

import helper.Utilities;
import io.restassured.path.json.JsonPath;

public class CoursesJsonParseDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		/***
		 * 1. Print No of courses returned by API

			2.Print Purchase Amount
			
			3. Print Title of the first course
			
			4. Print All course titles and their respective Prices
			
			5. Print no of copies sold by RPA Course
			
			6. Verify if Sum of all Course prices matches with Purchase Amount
		 */
		
		try {
			JsonPath courseJson = Utilities.parseComplexJson();
			
			//1.Print No of courses returned by API
			
			int numCourses = courseJson.getInt("courses.size()");
			
			System.out.println("No of courses : " + numCourses);
			
			//2.Print Purchase Amount
			
			System.out.println("Purchase Amount is :" + courseJson.get("dashboard.purchaseAmount"));
			
			//3. Print Title of the first course
			System.out.println("Title of the first course : " + courseJson.getString("courses[0].title"));
			
			//4. Print All course titles and their respective Prices
			
			int sum=0;
			
			for (int i=0; i<numCourses ; i++) {
				System.out.println("Title of "+(i+1)+" book is : " + courseJson.getString("courses["+i+"].title"));
				System.out.println("Price of "+(i+1)+" book is: " + courseJson.getString("courses["+i+"].price"));
				sum = sum + (courseJson.getInt("courses["+i+"].price") *  courseJson.getInt("courses["+i+"].copies"));
				
				//5. Print no of copies sold by RPA Course
				if (courseJson.getString("courses["+i+"].title").equalsIgnoreCase("RPA")) {
					System.out.println("Number of Copies sold by RPA course : " + courseJson.getString("courses["+i+"].copies"));
				}
			}
			
		
			//6. Verify if Sum of all Course prices matches with Purchase Amount
			 if (courseJson.getInt("dashboard.purchaseAmount") == sum ) {
				 System.out.println("Sum of all Course prices matches with Purchase Amount");
			 }
			 else {
				 System.out.println("Sum of all Course prices DOESN't matches with Purchase Amount");
			 }
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
