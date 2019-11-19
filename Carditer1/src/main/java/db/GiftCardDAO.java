package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Card;


public class GiftCardDAO {
	java.sql.Connection conn;
	
    public Card getCard(String id) throws Exception {
        
        try {
            Card card = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM card WHERE id=?;");
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                card = generateCard(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return card;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting card: " + e.getMessage());
        }
    }
    
    
    private Card generateCard(ResultSet resultSet) throws Exception {
    	String id  = resultSet.getString("card_id");
    	String type  = resultSet.getString("event_type");
    	String recipient  = resultSet.getString("recipient");
    	String name  = resultSet.getString("card_name");
       
        return new Card(id, type, recipient,name);
    }


	public List<Card> getAllCards() throws Exception {
		 List<Card> allCards = new ArrayList<>();
	        try {
	            Statement statement = conn.createStatement();
	            String query = "SELECT * FROM cards";
	            ResultSet resultSet = statement.executeQuery(query);

	            while (resultSet.next()) {
	                Card c = generateCard(resultSet);
	                allCards.add(c);
	            }
	            resultSet.close();
	            statement.close();
	            return allCards;

	        } catch (Exception e) {
	            throw new Exception("Failed in getting cards: " + e.getMessage());
	        }
	}


	public boolean deleteCard(Card card) throws Exception {
		try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM constants WHERE name = ?;");
            ps.setString(1, card.id);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
	}
}
