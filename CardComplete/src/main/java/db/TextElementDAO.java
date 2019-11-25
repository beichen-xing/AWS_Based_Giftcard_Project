package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Card;
import model.TextElement;

public class TextElementDAO {
	java.sql.Connection conn;
	
	 public TextElementDAO() {
	    	try  {
	    		conn = DatabaseUtil.connect();
	    	} catch (Exception e) {
	    		conn = null;
	    	}
	    }
	
    public TextElement getText(String id) throws Exception {
        
        try {
            TextElement text = new TextElement("", "", "", "", "", "", "", "");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Texts WHERE text_id=?;");
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
            throw new Exception("Failed in getting the text element: " + e.getMessage());
        }
    }
    
    
    private TextElement generateText(ResultSet resultSet) throws Exception {
    	String id  = resultSet.getString("text_id");
    	String content  = resultSet.getString("content");
    	String font  = resultSet.getString("font");
    	String bounds  = resultSet.getString("bounds");
    	String card_id = resultSet.getString("card_id");
    	String color = resultSet.getString("color");
    	String size = resultSet.getString("size");
    	String page = resultSet.getString("page");
       
        return new TextElement(id, content, font, bounds, card_id, color, size, page);
    }

	public List<TextElement> getAllTexts(String id) throws Exception {
		 List<TextElement> alltexts = new ArrayList<>();
	        try {
	            Statement statement = conn.createStatement();
	            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Texts WHERE card_id=?;");
	            ps.setString(1, id);
	            ResultSet resultSet = ps.executeQuery();

	            while (resultSet.next()) {
	                TextElement t = generateText(resultSet);
	                alltexts.add(t);
	            }
	            resultSet.close();
	            statement.close();
	            return alltexts;

	        } catch (Exception e) {
	            throw new Exception("Failed in getting texts: " + e.getMessage());
	        }
	}


}