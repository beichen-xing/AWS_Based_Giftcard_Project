package http;

/*================Delete according to ID==================*/

public class DeleteCardRequest {
	public final String text_id;
	
	public DeleteCardRequest (String n) {
		this.text_id = n;
	}
	
	public String toString() {
		return "Delete(" + text_id + ")";
	}

}
