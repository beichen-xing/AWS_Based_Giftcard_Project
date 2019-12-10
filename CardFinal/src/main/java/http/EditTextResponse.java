package http;

public class EditTextResponse {
	public final String response;
	public final int statusCode;
	
	public EditTextResponse (String s, int code) {
		this.response = s;
		this.statusCode = code;
	}
	
	// 200 means success
	public EditTextResponse (int n) {
		this.response = "";
		this.statusCode = n;
	}
	
	public String toString() {
		return "Response(" + response + ")";
	}
}