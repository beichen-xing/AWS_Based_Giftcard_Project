package http;

public class AddTextRequest {
	public final String id;
	public final String content;
	public final String font;
	public final String bounds;
	public final String color;
	public final String size;
	public final String text_id;
	public final String page;
	
	
	public AddTextRequest(String id, String content, String font, String bounds, String color, String size, String text_id, String page) {
		this.id = id;
		this.content = content;
		this.font = font;
		this.bounds = bounds;
		this.color = color;
		this.size = size;
		this.text_id = text_id;
		this.page = page;
		
	}
	
	public String toString() {
		return "AddText";
	}
}
