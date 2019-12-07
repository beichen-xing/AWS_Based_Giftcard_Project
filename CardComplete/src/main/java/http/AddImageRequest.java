package http;

public class AddImageRequest {
	public String image_id;
	public String image_path;
	public String bounds;
	public String card_id;
	public String page;
	public String content;
	
	
	public AddImageRequest(String image_id, String image_path, String bounds, String page, String card_id, String content) {
		this.image_id = image_id;
		this.image_path = image_path;
		this.bounds = bounds;
		this.page = page;
		this.card_id = card_id;
		this.content = content;
	}
	
	public String toString() {
		return "AddImage";
	}
}
