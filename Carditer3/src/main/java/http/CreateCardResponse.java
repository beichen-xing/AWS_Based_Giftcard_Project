package http;

/**
 * In most cases the response is the name of the constant that was being created.
 * 
 * if an error of some sort, then the response describes that error.
 *  
 */
public class CreateCardResponse {
	public final String response;
	public final int statusCode;
	
	public CreateCardResponse (String s, int code) {
		this.response = s;
		this.statusCode = code;
	}
	
	// 200 means success
	public CreateCardResponse (int n) {
		this.response = "";
		this.statusCode = n;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
}