package http;


public class DuplicateCardResponse {
	public final String response;
	public final int statusCode;
	
	public DuplicateCardResponse (String s, int code) {
		this.response = s;
		this.statusCode = code;
	}
	
	// 200 means success
	public DuplicateCardResponse (int n) {
		this.response = "";
		this.statusCode = n;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
}