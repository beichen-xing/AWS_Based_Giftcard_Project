package http;

/*=======================Delete with statusCode and error message==========================*/
public class DeleteCardResponse {
	public final String id;
	public final int statusCode;
	public final String error;
	
	public DeleteCardResponse (String id, int statusCode) {
		this.id = id;
		this.statusCode = statusCode;
		this.error = "";
	}
	
	public DeleteCardResponse (String id, int statusCode, String errorMessage) {
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
