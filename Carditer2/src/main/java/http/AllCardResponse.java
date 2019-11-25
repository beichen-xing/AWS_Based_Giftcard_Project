package http;

import java.util.ArrayList;
import java.util.List;

import model.Card;


/*===========Response with status code and error message==================*/

public class AllCardResponse {
	public final List<Card> list;
	public final int statusCode;
	public final String error;
	
	public AllCardResponse (List<Card> list, int code) {
		this.list = list;
		this.statusCode = code;
		this.error = "";
	}
	
	public AllCardResponse (int code, String errorMessage) {
		this.list = new ArrayList<Card>();
		this.statusCode = code;
		this.error = errorMessage;
	}
	
	public String toString() {
		if (list == null) { return "EmptyCards"; }
		return "AllCards(" + list.size() + ")";
	}
	
}
