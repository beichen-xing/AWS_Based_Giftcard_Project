package http;

public class DeleteTextRequest {
	public final String text_id_unique;
	
	public DeleteTextRequest (String text_id_unique) {
		this.text_id_unique = text_id_unique;
	}
	
	public String toString() {
		return "Delete(" + text_id_unique + ")";
	}
}
