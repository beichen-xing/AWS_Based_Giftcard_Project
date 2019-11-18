package http;

/*================Delete according to ID==================*/

public class DeleteCardRequest {
	public final String id;
	
	public DeleteCardRequest (String n) {
		this.id = n;
	}
	
	public String toString() {
		return "Delete(" + id + ")";
	}

}
