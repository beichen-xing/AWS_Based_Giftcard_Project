package http;

public class EditImageResponse {
	public final String response;
	public final int statusCode;
	
	public EditImageResponse (String s, int code) {
		this.response = s;
		this.statusCode = code;
	}
	
	// 200 means success
	public EditImageResponse (int n) {
		this.response = "";
		this.statusCode = n;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
}
