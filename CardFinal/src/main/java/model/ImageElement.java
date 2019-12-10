package model;

public class ImageElement {
	public String image_id;
	public String image_path;
	public String page;
	public String bounds;
	public String card_id;
	public String card_content;
	public String image_id_unique;
	
	public ImageElement(String image_id, String image_path, String bounds, String page, String card_id, String card_content, String image_id_unique) {
		this.image_id = image_id;
		this.image_path = image_path;
		this.page = page;
		this.bounds = bounds;
		this.card_id = card_id;
		this.card_content = card_content;
		this.image_id_unique = image_id_unique;
	}
}