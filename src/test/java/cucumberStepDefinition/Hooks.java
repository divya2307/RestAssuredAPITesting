package cucumberStepDefinition;

import java.io.IOException;

import cucumberResources.Utils;
import io.cucumber.java.Before;

public class Hooks {
	
	@Before("@DeletePlace")
	public void beforeScenario() throws IOException {
		placeValidationStepDefinition sDefinition = new placeValidationStepDefinition();
		
		if(placeValidationStepDefinition.placeID == null) {
			sDefinition.add_place_payload_with("School","Borim",100,"7896542341","Hindi","g.com");
			sDefinition.user_calls_add_place_api_with_post_http_request("addPlaceAPI", "POST");
			placeValidationStepDefinition.placeID = Utils.getJsonValue(placeValidationStepDefinition.response,"place_id");
		}
		
	}

}
