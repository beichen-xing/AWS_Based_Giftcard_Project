package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Card;
import model.TextElement;


public class TextElementDAO {
	java.sql.Connection conn;
	
    public TextElement getText(String id) throws Exception {
        
        try {
            TextElement text = null;
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Texts WHERE id=?;");
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            
            while (resultSet.next()) {
                text = generateText(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return text;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting card: " + e.getMessage());
        }
    }
    
    
    private TextElement generateText(ResultSet resultSet) throws Exception {
    	String id  = resultSet.getString("text_id");
    	String content  = resultSet.getString("content");
    	String font  = resultSet.getString("font");
    	String bounds  = resultSet.getString("bounds");
    	String page_id = resultSet.getString("page_id");
       
        return new TextElement(id, content, font, bounds, page_id);
    }


	public List<TextElement> getAllTexts() throws Exception {
		 List<TextElement> allTexts = new ArrayList<>();
	        try {
	            Statement statement = conn.createStatement();
	            String query = "SELECT * FROM Texts";
	            ResultSet resultSet = statement.executeQuery(query);

	            while (resultSet.next()) {
	                TextElement text = generateText(resultSet);
	                allTexts.add(text);
	            }
	            resultSet.close();
	            statement.close();
	            return allTexts;

	        } catch (Exception e) {
	            throw new Exception("Failed in getting cards: " + e.getMessage());
	        }
	}


	public boolean deleteText(TextElement textElement) throws Exception {
		try {
            PreparedStatement ps = conn.prepareStatement("DELETE FROM constants WHERE name = ?;");
            ps.setString(1, textElement.id);
            int numAffected = ps.executeUpdate();
            ps.close();
            
            return (numAffected == 1);

        } catch (Exception e) {
            throw new Exception("Failed to insert constant: " + e.getMessage());
        }
	}
}
