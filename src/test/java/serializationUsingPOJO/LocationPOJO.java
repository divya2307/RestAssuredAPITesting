package serializationUsingPOJO;

public class LocationPOJO {
	
	/***
	 * {
  "location": {
    "lat": -38.383494,
    "lng": 33.427362
  },
  ***/
	
	private double lat;
	private double lng;
	
	public double getLat() {
		return lat;
	}
	public void setLat(double d) {
		this.lat = d;
	}
	public double getLng() {
		return lng;
	}
	public void setLng(double lng) {
		this.lng = lng;
	}

}
