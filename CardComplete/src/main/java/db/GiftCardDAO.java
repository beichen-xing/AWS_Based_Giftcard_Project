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
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Cards WHERE card_id = ?;");
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            Card c = new Card("","","","","");
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
    
    public boolean CreateCard(String id, String name, String recipient, String type,String orientation) throws Exception {
        try {
            
            PreparedStatement ps = conn.prepareStatement("INSERT INTO Cards (card_id, card_name,recipient,event_type,orientation) values(?,?,?,?,?);");
            ps.setString(1, id);
            ps.setString(2, name);
            ps.setString(3, recipient);
            ps.setString(4, type);
            ps.setString(5, orientation);
            ps.execute();
            return true;
          
        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in creating card: " + e.getMessage());
        }
    }
    
    
    private Card generateCard(ResultSet resultSet) throws Exception {
    	String id = resultSet.getString("card_id");
    	String type  = resultSet.getString("event_type");
    	String recipient  = resultSet.getString("recipient");
    	String name  = resultSet.getString("card_name");
    	String orientation = resultSet.getString("orientation");
       
        return new Card(id, type, recipient,name,orientation);
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

	public boolean AddText(String id, String content, String font, String bounds, String color, String size, String text_id, String page) throws Exception {
		  try {
	            
	            PreparedStatement ps = conn.prepareStatement("Insert into innodb.Texts (card_id, content, font, bounds, color, size,text_id,page) values(?,?, ?, ?, ?, ?, ?, ?);");
	            ps.setString(1, id);
	            ps.setString(2, content);
	            ps.setString(3, font);
	            ps.setString(4, bounds);
	            ps.setString(5, color);
	            ps.setString(6, size);
	            ps.setString(7, text_id);
	            ps.setString(8, page);
	            ps.execute();
	            return true;
	          
	        } catch (Exception e) {
	        	e.printStackTrace();
	            throw new Exception("Failed in adding text: " + e.getMessage());
	        }
	}

	public boolean DeleteText(String id) throws Exception {
		try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM Texts WHERE text_id = ?;");
            ps.setString(1, id);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to delete text: " + e.getMessage());
        }
	}

	public boolean AddImage(String image_id, String image_path, String bounds, String page, String card_id) throws Exception {
		  try {
	            
	            PreparedStatement ps = conn.prepareStatement("Insert into innodb.Images (image_id, image_path, bounds, page, card_id) values(?,?, ?, ?, ?, ?, ?, ?);");
	            ps.setString(1, image_id);
	            ps.setString(2, image_path);
	            ps.setString(3, bounds);
	            ps.setString(4, page);
	            ps.setString(5, card_id);
	            ps.execute();
	            return true;
	          
	        } catch (Exception e) {
	        	e.printStackTrace();
	            throw new Exception("Failed in adding Image: " + e.getMessage());
	        }
	}

	public boolean DeleteImage(String image_id) throws Exception {
		try {
          PreparedStatement ps = conn.prepareStatement("DELETE FROM Images WHERE image_id = ?;");
          ps.setString(1, image_id);
          int numAffected = ps.executeUpdate();
          ps.close();
          
          return (numAffected == 1);

      } catch (Exception e) {
          throw new Exception("Failed to delete image: " + e.getMessage());
      }
	}
	
	public boolean EditImage(String image_id, String bounds) throws Exception {
		try {
	          PreparedStatement ps = conn.prepareStatement("UPDATE Images SET bounds=? WHERE image_id = ?;");
	          ps.setString(1, bounds);
	          ps.setString(2, image_id);
	          int numAffected = ps.executeUpdate();
	          ps.close();
	          
	          return (numAffected == 1);

	      } catch (Exception e) {
	          throw new Exception("Failed to edit image: " + e.getMessage());
	      }
	}
}
