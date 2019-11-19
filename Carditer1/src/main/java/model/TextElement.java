package model;

public class TextElement {
	public String id;
	public String content;
	public String font;
	public String bounds;
	public String page_id;
	
	public TextElement(String id, String content, String font, String bounds, String page_id){
		this.id = id;
		this.content = content;
		this.font = font;
		this.bounds = bounds;
		this.page_id = page_id;
	}
}
