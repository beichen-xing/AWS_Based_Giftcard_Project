package http;

public class DeleteTextResponse {
	public final String text_id_unique;
	public final int statusCode;
	public final String error;
	
	public DeleteTextResponse (String text_id_unique, int statusCode) {
		this.text_id_unique = text_id_unique;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public DeleteTextResponse (String text_id_unique, int statusCode, String errorMessage) {
		this.text_id_unique = text_id_unique;
		this.statusCode = statusCode;
		this.error = errorMessage;
	}

/*=====================For log======================*/
	public String toString() {
		if (statusCode / 100 == 2) {  
			return "DeleteResponse(" + text_id_unique + ")";
		} else {
			return "ErrorResult(" + text_id_unique + ", statusCode=" + statusCode + ", err=" + error + ")";
		}
	}
}
