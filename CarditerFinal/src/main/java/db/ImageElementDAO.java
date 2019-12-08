package db;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import model.Card;
import model.ImageElement;

public class ImageElementDAO {
	java.sql.Connection conn;
	
	 public ImageElementDAO() {
	    	try  {
	    		conn = DatabaseUtil.connect();
	    	} catch (Exception e) {
	    		conn = null;
	    	}
	    }
	
    public ImageElement getImage(String id) throws Exception {
        
        try {
            ImageElement Image = new ImageElement("", "", "", "", "");
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Images WHERE Image_id=?;");
            ps.setString(1, id);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                Image = generateImage(resultSet);
            }
            resultSet.close();
            ps.close();
            
            return Image;

        } catch (Exception e) {
        	e.printStackTrace();
            throw new Exception("Failed in getting the Image element: " + e.getMessage());
        }
    }
    
    
    private ImageElement generateImage(ResultSet resultSet) throws Exception {
    	String image_id  = resultSet.getString("image_id");
    	String image_path  = resultSet.getString("image_path");
    	String bounds  = resultSet.getString("bounds");
    	String card_id = resultSet.getString("card_id");
    	String page = resultSet.getString("page");
       
        return new ImageElement(image_id, image_path, bounds, page, card_id);
    }

	public List<ImageElement> getAllImages(String id) throws Exception {
		 List<ImageElement> allImages = new ArrayList<>();
	        try {
	            Statement statement = conn.createStatement();
	            PreparedStatement ps = conn.prepareStatement("SELECT * FROM Images WHERE card_id=?;");
	            ps.setString(1, id);
	            ResultSet resultSet = ps.executeQuery();

	            while (resultSet.next()) {
	                ImageElement t = generateImage(resultSet);
	                allImages.add(t);
	            }
	            resultSet.close();
	            statement.close();
	            return allImages;

	        } catch (Exception e) {
	            throw new Exception("Failed in getting Images: " + e.getMessage());
	        }
	}
	
	public List<ImageElement> ListImages() throws Exception {
		 List<ImageElement> allImages = new ArrayList<>();
	        try {
	            Statement statement = conn.createStatement();
	            String query = "SELECT * FROM Images;";
	            ResultSet resultSet = statement.executeQuery(query);

	            while (resultSet.next()) {
	            	ImageElement c = generateImage(resultSet);
	                allImages.add(c);
	            }
	            resultSet.close();
	            statement.close();
	            return allImages;

	        } catch (Exception e) {
	            throw new Exception("Failed in getting images: " + e.getMessage());
	        }
	}



}
