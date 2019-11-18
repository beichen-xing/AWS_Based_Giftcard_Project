package http;

public class AddTextResponse {
	public final String response;
	public final int statusCode;
	
	public AddTextResponse (String s, int code) {
		this.response = s;
		this.statusCode = code;
	}
	
	// 200 means success
	public AddTextResponse (int n) {
		this.response = "";
		this.statusCode = n;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
}