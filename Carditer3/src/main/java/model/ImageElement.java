package model;

public class ImageElement {
	public String id;
	public String path;
	public String source;
	public String bounds;
	public String page_id;
	
	public ImageElement(String id, String path, String source, String bounds, String page_id) {
		this.id = id;
		this.path = path;
		this.source = source;
		this.bounds = bounds;
		this.page_id = page_id;
	}
}
