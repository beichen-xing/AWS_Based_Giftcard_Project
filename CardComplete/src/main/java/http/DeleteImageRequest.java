package http;

public class DeleteImageRequest {
	public final String id;
	
	public DeleteImageRequest (String id) {
		this.id = id;
	}
	
	public String toString() {
		return "Delete(" + id + ")";
	}
}
