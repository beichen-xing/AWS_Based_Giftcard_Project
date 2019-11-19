package model;

public class TextElement {
	public String id;
	public String content;
	public String font;
	public String bounds;
	public String card_id;
	public String color;
	
	public TextElement(String id, String content, String font, String bounds, String card_id, String color){
		this.id = id;
		this.content = content;
		this.font = font;
		this.bounds = bounds;
		this.card_id = card_id;
		this.color = color;
	}
}