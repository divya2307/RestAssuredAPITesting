package graphQLTest;

import static org.testng.Assert.assertEquals;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;

public class QueryAndMutation {

	public static void main(String[] args) {
	
		//Query GraphQL
		int locationID = 29967;
		
		String locationString = RestAssured
		.given()
			.baseUri("https://rahulshettyacademy.com/gq/graphql")
			.contentType(ContentType.JSON)
			.log().all()
			//In body we can't directly send graphQL query, body accepts json in string format. so we need to send the graphQL converted into json string
			//we can find it in dev tools under network > payload
			.body("{\"query\":\"query($locationID:Int! ){\\n  location(locationId:$locationID){\\n    name,\\n    id,\\n    type,\\n    residents{\\n      id,\\n      name,\\n      status\\n    }\\n  }\\n  \\n  episodes(filters:{name:\\\"Dhurandar\\\"})\\n  {\\n    info{\\n      count\\n    },\\n    result{\\n    id,\\n    name,\\n    air_date\\n  }\\n  }\\n  \\n}\\n  \\n\",\"variables\":{\"locationID\":"+locationID+"}}")
		.when()
			.post()
		.then()
			.assertThat()
			.statusCode(200)
			.log().all()
			.extract().response().jsonPath().getString("data.location.name");
		
		assertEquals(locationString, "India");

		//Mutation GrapgQL
		
		String episodeName = "Dhurandar";
		RestAssured
		.given()
			.baseUri("https://rahulshettyacademy.com/gq/graphql")
			.contentType(ContentType.JSON)
			.log().all()
			.body("{\"query\":\"mutation($lName:String!,$cName:String!,$eName:String!) {\\n  \\n  createLocation(location : {name:$lName,type:\\\"East\\\",dimension :\\\"6789\\\"}){\\n    id\\n  }\\n  \\n\\tcreateCharacter(character:{name:$cName,type:\\\"Hero\\\",status:\\\"Alive\\\",species:\\\"Spy\\\",gender:\\\"Male\\\",image:\\\"xyz.png\\\",originId:29967,locationId:29967} ){\\n    id\\n  }\\n  \\n  createEpisode(episode:{name:$eName,air_date:\\\"19/03/2026\\\",episode:\\\"2\\\"}){\\n    id\\n  }\\n  \\n  deleteLocations(locationIds:[20100,1999]){\\n    locationsDeleted\\n  }\\n}\\n\\n\",\"variables\":{\"lName\":\"Pune\",\"cName\":\"Yalina\",\"eName\":\" "+episodeName+ "\"}}")
		.when()
			.post()
		.then()
			.assertThat()
			.statusCode(200)
			.log().all();
			
	}

}
