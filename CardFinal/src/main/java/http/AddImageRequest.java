package http;

public class AddImageRequest {
	public String image_id;
	public String image_path;
	public String bounds;
	public String card_id;
	public String page;
	public String card_content;
	
	
	public AddImageRequest(String image_id, String image_path, String bounds, String page, String card_id, String card_content) {
		this.image_id = image_id;
		this.image_path = "https://cs509finalinteration.s3.us-east-2.amazonaws.com/cs509finalinteration/"+image_id;
		this.bounds = bounds;
		this.page = page;
		this.card_id = card_id;
		this.card_content = card_content;
	}
	
	public String toString() {
		return "AddImage";
	}
}
