package http;

public class CreateCardRequest {
	
		public final String id;
		public final String type;
		public final String name;
		public final String recipient;
		
		public CreateCardRequest (String id,String name,String recipient,String type) {
			this.name = name;
			this.id = id;
			this.recipient = recipient;
			this.type = type;
		}
		
		public String toString() {
			return "CreateCard";
		}
	
}
