package http;


/*===========Response with status code==================*/

public class GetCardResponse {
	public final String id;
	public final int statusCode;
	

	public GetCardResponse(int Code, String id) {
		this.statusCode = Code;
		this.id = id;
		
	}


	public GetCardResponse(int code) {
		this.statusCode = code;
		this.id = "";
	}
}
