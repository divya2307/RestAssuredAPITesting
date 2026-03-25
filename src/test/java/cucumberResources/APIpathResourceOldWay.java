package cucumberResources;

public class APIpathResourceOldWay {
	// declare all the resources path here add delete etc and then add functions to return these resources
	
	//but best way is enum
	
	String addPlaceResource = "maps/api/place/add/json";
	
	public String getAddPlace() {
		return addPlaceResource;
	}

}
