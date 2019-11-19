package model;

public class Card {
	public final String id;
	public final String type;
	public final String name;
	public final String recipient;
	public final String orientation;
	public final String textelement;
	
	
	public Card(String id, String type, String recipient, String name, String orientation,String textelement) {
		this.id = id;
		this.type = type;
		this.name = name;
		this.recipient = recipient;
		this.orientation = orientation;
		this.textelement = textelement;
	}
	
}
