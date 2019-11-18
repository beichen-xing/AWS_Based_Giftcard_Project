package http;

public class AddTextRequest {
	public final String id;
	public final String text;
	
	public AddTextRequest (String id,String text) {
		this.id = id;
		this.text = text;
	}
	
	public String toString() {
		return "AddText";
	}
}
