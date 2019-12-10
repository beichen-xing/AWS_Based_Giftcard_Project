package model;

public class TextElement {
	public String text_id;
	public String content;
	public String font;
	public String bounds;
	public String card_id;
	public String color;
	public String size;
	public String page;
	public String text_id_unique;
	
	public TextElement(String text_id, String content, String font, String bounds, String card_id, String color, String size, String page, String text_id_unique){
		this.text_id = text_id;
		this.content = content;
		this.font = font;
		this.bounds = bounds;
		this.card_id = card_id;
		this.color = color;
		this.size = size;
		this.page = page;
		this.text_id_unique = text_id_unique;
	}
}