package http;

import java.util.ArrayList;
import java.util.List;

import model.ImageElement;

public class ListImagesResponse {
	public final List<ImageElement> list;
	public final int statusCode;
	public final String error;
	
	public ListImagesResponse(List<ImageElement> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public ListImagesResponse (int code, String errorMessage) {
		this.list = new ArrayList<ImageElement>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "Empty"; }
		return "AllImages(" + list.size() + ")";
	}
}