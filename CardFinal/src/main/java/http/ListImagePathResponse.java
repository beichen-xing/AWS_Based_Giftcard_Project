package http;

import java.util.ArrayList;
import java.util.List;



public class ListImagePathResponse {
	public final List<String> imagesPath;
	public final int statusCode;
	public final String error;
	
	public ListImagePathResponse(List<String> imagesPath, int code) {
		this.imagesPath = imagesPath;
		this.statusCode = code;
		this.error = "";
	}
	
	public ListImagePathResponse (int code, String errorMessage) {
		this.imagesPath = new ArrayList<String>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (imagesPath == null) { return "EmptyCards"; }
		return "AllTexts(" + imagesPath.size() + ")";
	}
}