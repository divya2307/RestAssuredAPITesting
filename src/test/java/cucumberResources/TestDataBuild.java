package cucumberResources;

import java.util.Arrays;
import java.util.List;

import serializationUsingPOJO.GoogleMapAddPlacePOJO;
import serializationUsingPOJO.LocationPOJO;

public class TestDataBuild {
	
	public GoogleMapAddPlacePOJO addPlacePayload(String name, String address, Integer accuracy, String mobilenumber, String website, String language) {

		  List<String> typeList = Arrays.asList("Shopping Mart" , "Super Market");
		  GoogleMapAddPlacePOJO map = new GoogleMapAddPlacePOJO();
		  LocationPOJO location = new LocationPOJO();
		  
		  location.setLat(-78.376784);
		  location.setLng(76.875468);
		  
		  map.setAccuracy(accuracy);
		  map.setAddress(address);
		  map.setLocation(location);
		  map.setName(name);
		  map.setPhone_number(mobilenumber);
		  map.setTypes(typeList);
		  map.setWebsite(website);
		  map.setLanguage(language);
		  
		  return map;
	}
	
	public String deletePayload(String place_id) {
		
		String response = "{\n"
				+ "    \"place_id\": \"" +place_id+ "\"\n"
				+ "}";
		
		return response;
	}
}
