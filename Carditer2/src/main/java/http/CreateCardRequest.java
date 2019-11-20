package http;

public class CreateCardRequest {
	
		public final String id;
		public final String type;
		public final String name;
		public final String recipient;
		public final String orientation;
		
		public CreateCardRequest (String id,String name,String recipient,String type,String orientation) {
			this.name = name;
			this.id = id;
			this.recipient = recipient;
			this.type = type;
			this.orientation = orientation;
		}
		
		public String toString() {
			return "CreateCard";
		}
	
}
