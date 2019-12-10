package http;

public class DeleteImageRequest {
	public final String image_id_unique;
	
	public DeleteImageRequest (String image_id_unique) {
		this.image_id_unique = image_id_unique;
	}
	
	public String toString() {
		return "Delete(" + image_id_unique + ")";
	}
}