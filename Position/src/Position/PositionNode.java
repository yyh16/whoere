package Position;

public class PositionNode {
	public String id;
	private String longitude;
	private String latitude;
	private String name;
	
	public PositionNode(){
		id=null;
		longitude=null;
		latitude=null;
		name=null;
	}
	
	public PositionNode(String id,String longitude,String latitude,String name){
		this.id=id;
		this.longitude=longitude;
		this.latitude=latitude;
		this.name=name;
	}
	
	public void setPosition(String longitude,String latitude){
		this.longitude=longitude;
		this.latitude=latitude;
	}
	
	public String getLongitude() {
		return longitude;
	}
	public String getLatitude() {
		return latitude;
	}
	public String getName() {
		return name;
	}
}
