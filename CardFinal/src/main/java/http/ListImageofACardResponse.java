package http;

import java.util.ArrayList;
import java.util.List;

import model.ImageElement;


public class ListImageofACardResponse {
	public final List<ImageElement> images;
	public final int statusCode;
	public final String error;
	
	public ListImageofACardResponse(List<ImageElement> images, int code) {
		this.images = images;
		this.statusCode = code;
		this.error = "";
	}
	
	public ListImageofACardResponse (int code, String errorMessage) {
		this.images = new ArrayList<ImageElement>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (images == null) { return "EmptyCards"; }
		return "AllTexts(" + images.size() + ")";
	}
}