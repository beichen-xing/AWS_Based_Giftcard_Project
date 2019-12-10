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
	public String text_id_unique;
	
	
	public GetTextResponse(String text_id, String card_id, String contents, String bounds, String font, String color,String page,String size, String text_id_unique) {
		this.statusCode = 200;
		this.text_id = text_id;
		this.content = contents;
		this.font = font;
		this.bounds = bounds;
		this.card_id = card_id;
		this.color = color;
		this.page = page;
		this.size = size;
		this.text_id_unique = text_id_unique;
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