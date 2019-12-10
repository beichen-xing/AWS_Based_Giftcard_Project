package http;


/*===========Response with status code==================*/

public class GetCardResponse {
	public final String id;
	public final int statusCode;
	public final String type;
	public final String name;
	public final String recipient;
	public final String orientation;
	

	public GetCardResponse(int Code, String id,String name,String recipient, String type,String orientation) {
		this.statusCode = Code;
		this.id = id;
		this.name = name;
		this.recipient = recipient;
		this.type = type;
		this.orientation = orientation;
		
	}


	public GetCardResponse(int code) {
		this.statusCode = code;
		this.id = "";
		this.name = "";
		this.recipient = "";
		this.type = "";
		this.orientation = "";
		
	}
}
