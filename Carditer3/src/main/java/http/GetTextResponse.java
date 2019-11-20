package http;

public class GetTextResponse {
	public final String id;
	public final int statusCode;
	public String content;
	public String font;
	public String bounds;
	public String card_id;
	public String color;
	
	

	public GetTextResponse(int Code, String id, String content, String font, String bounds, String card_id, String color) {
		this.statusCode = Code;
		this.id = id;
		this.content = content;
		this.font = font;
		this.bounds = bounds;
		this.card_id = card_id;
		this.color = color;
	}


	public GetTextResponse(int code) {
		this.statusCode = code;
		this.id = "";
		this.content = "";
		this.font = "";
		this.bounds = "";
		this.card_id = "";
		this.color = "";
		
	}
}
