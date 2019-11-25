package http;

public class GetTextResponse {
	public final String text_id;
	public final int statusCode;
	public String content;
	public String font;
	public String bounds;
	public String card_id;
	public String color;
	public String page;
	public String size;
	
	
	public GetTextResponse(int Code, String text_id, String card_id, String contents, String bounds, String font, String color,String page,String size) {
		this.statusCode = Code;
		this.text_id = text_id;
		this.content = content;
		this.font = font;
		this.bounds = bounds;
		this.card_id = card_id;
		this.color = color;
		this.page = page;
		this.size = size;
	}


	public GetTextResponse(int code) {
		this.statusCode = code;
		this.text_id = "";
		this.content = "";
		this.font = "";
		this.bounds = "";
		this.card_id = "";
		this.color = "";
		this.page = "";
		this.size = "";
	}
}