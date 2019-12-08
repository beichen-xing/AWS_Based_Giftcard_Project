package http;

public class AddImageResponse {
	public final String response;
	public final int statusCode;
	
	public AddImageResponse (String s, int code) {
		this.response = s;
		this.statusCode = code;
	}
	
	// 200 means success
	public AddImageResponse (int n) {
		this.response = "";
		this.statusCode = n;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
}
