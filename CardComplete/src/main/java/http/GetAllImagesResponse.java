package http;

import java.util.ArrayList;
import java.util.List;

import model.ImageElement;

public class GetAllImagesResponse {
	public final List<ImageElement> list;
	public final int statusCode;
	public final String error;
	
	public GetAllImagesResponse(List<ImageElement> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public GetAllImagesResponse (int code, String errorMessage) {
		this.list = new ArrayList<ImageElement>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "EmptyCards"; }
		return "AllImages(" + list.size() + ")";
	}
}
