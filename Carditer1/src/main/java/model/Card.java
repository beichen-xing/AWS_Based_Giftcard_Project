package model;

public class Card {
	public final String id;
	public final String type;
	public final String name;
	public final String recipient;
	
	
	public Card(String id, String type, String recipient, String name) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.recipient = recipient;
	}
	
}
