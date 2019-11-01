package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.Card;


public class GiftCardDAO {
	java.sql.Connection conn;
	
	  public GiftCardDAO() {
	    	try  {
	    		conn = DatabaseUtil.connect();
	    	} catch (Exception e) {
	    		conn = null;
	    	}
	    }
	
    public Card getCard(String id) throws Exception {
        try {
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Cards WHERE card_id =?");
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            Card c = new Card("","","","");
            while (resultSet.next()) {
                c = generateCard(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return c;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting card: " + e.getMessage());
        }
    }
    
    
    private Card generateCard(ResultSet resultSet) throws Exception {
    	String id = resultSet.getString("card_id");
    	String type  = resultSet.getString("event_type");
    	String recipient  = resultSet.getString("recipient");
    	String name  = resultSet.getString("card_name");
       
        return new Card(id, type, recipient,name);
    }


	public List<Card> getAllCards() throws Exception {
		 List<Card> allCards = new ArrayList<>();
	        try {
	            Statement statement = conn.createStatement();
	            String query = "SELECT * FROM Cards;";
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


	public boolean deleteCard(String id) throws Exception {
		try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Cards WHERE card_id = ?;");
            ps.setString(1, id);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to delete card: " + e.getMessage());
        }
	}
}
