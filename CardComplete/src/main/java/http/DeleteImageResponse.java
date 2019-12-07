package http;

public class DeleteImageResponse {
	public final String id;
	public final int statusCode;
	public final String error;
	
	public DeleteImageResponse (String id, int statusCode) {
		this.id = id;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public DeleteImageResponse (String id, int statusCode, String errorMessage) {
		this.id = id;
		this.statusCode = statusCode;
		this.error = errorMessage;
	}

/*=====================For log======================*/
	public String toString() {
		if (statusCode / 100 == 2) {  
			return "DeleteResponse(" + id + ")";
		} else {
			return "ErrorResult(" + id + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}
}
