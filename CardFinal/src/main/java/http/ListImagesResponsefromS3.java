package http;

import java.util.ArrayList;
import java.util.List;

import model.ImageElement;

public class ListImagesResponsefromS3 {
	public final List<String> list;
	public final int statusCode;
	public final String error;
	
	public ListImagesResponsefromS3(List<String> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public ListImagesResponsefromS3 (int code, String errorMessage) {
		this.list = new ArrayList<String>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "Empty"; }
		return "AllImages(" + list.size() + ")";
	}
}