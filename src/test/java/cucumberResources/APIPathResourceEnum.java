package cucumberResources;

public enum APIPathResourceEnum {
	
	
	addPlaceAPI("maps/api/place/add/json"),
	getPlaceAPI("maps/api/place/get/json"),
	deletePlaceAPI("maps/api/place/delete/json");
	
	private String resource;

	APIPathResourceEnum(String resource) {
		this.resource = resource;
	}
	
	public String getResource() {
		return resource;
	}

}
