package http;

import java.util.ArrayList;
import java.util.List;


import model.TextElement;

public class GetAllTextsResponse {
	public final List<TextElement> list;
	public final int statusCode;
	public final String error;
	
	public GetAllTextsResponse(List<TextElement> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public GetAllTextsResponse (int code, String errorMessage) {
		this.list = new ArrayList<TextElement>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "EmptyCards"; }
		return "AllTexts(" + list.size() + ")";
	}
}
