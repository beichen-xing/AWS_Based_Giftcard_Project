package model;

public class Card {
	public final String id;
	public final String type;
	public final String name;
	public final String recipient;
	public final String orientation;
	
	
	public Card(String id, String type, String recipient, String name, String orientation) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.recipient = recipient;
		this.orientation = orientation;
		
	}
	
}
