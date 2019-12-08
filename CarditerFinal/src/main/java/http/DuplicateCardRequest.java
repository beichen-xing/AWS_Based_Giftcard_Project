package http;

public class DuplicateCardRequest {
	
		public final String id;
		public final String recipient;
		public final String newid;
		
		
		public DuplicateCardRequest (String id, String recipient, String newid) {
			this.id = id;
			this.recipient = recipient;
			this.newid = newid;
		}
		
		public String toString() {
			return "duplicateCard";
		}
	
}