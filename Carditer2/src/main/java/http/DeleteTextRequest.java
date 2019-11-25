package http;

public class DeleteTextRequest {
	public final String id;
	
	public DeleteTextRequest (String id) {
		this.id = id;
	}
	
	public String toString() {
		return "Delete(" + id + ")";
	}
}
