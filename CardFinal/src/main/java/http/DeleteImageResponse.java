package http;

public class DeleteImageResponse {
	public final String image_id_unique;
	public final int statusCode;
	public final String error;
	
	public DeleteImageResponse (String image_id_unique,  int statusCode) {
		this.image_id_unique = image_id_unique;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public DeleteImageResponse (String image_id_unique, int statusCode, String errorMessage) {
		this.image_id_unique = image_id_unique;
		this.statusCode = statusCode;
		this.error = errorMessage;
	}

/*=====================For log======================*/
	public String toString() {
		if (statusCode / 100 == 2) {  
			return "DeleteResponse(" + image_id_unique + ")";
		} else {
			return "ErrorResult(" + image_id_unique + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}
}