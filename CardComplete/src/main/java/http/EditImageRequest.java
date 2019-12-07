package http;

public class EditImageRequest {
	public final String id;
	public final String bounds;
	public final String image_id;
		
	public EditImageRequest(String id, String bounds, String image_id) {
		this.id = id;
		this.bounds = bounds;
		this.image_id = image_id;
		
	}
	
	public String toString() {
		return "EditImage";
	}
}
