package cardcontrol;

import java.io.*;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.google.gson.Gson;

import db.GiftCardDAO;
import http.DeleteImageRequest;
import http.DeleteImageResponse;
import http.EditImageRequest;
import http.EditImageResponse;
import model.Card;

public class EditImageElement implements RequestHandler {

    public LambdaLogger logger =null;
    
    boolean EditImageInRDS(String image_id, String bounds, String card_id) throw Exception{
		if (logger != null) { logger.log("in editImage"); }		
		GiftCardDAO dao = new GiftCardDAO();		
		return dao.EditImage(image_id, bounds);   	
    }
    
    public void EditImage(String image_id, String bounds, String card_id) throw  Exception {
    	Card card;
    	try {
    		EditImageInRDS(image_id, bounds);
		} catch (Exception e) {
			System.out.println("Error");
		}
    }

    @Override
    public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to edit image");

		JSONObject headerJson = new JSONObject();
		headerJson.put("Content-Type",  "application/json");  
		headerJson.put("Access-Control-Allow-Methods", "DELETE,GET,POST,OPTIONS");
	    headerJson.put("Access-Control-Allow-Origin",  "*");
	        
		JSONObject responseJson = new JSONObject();
		responseJson.put("headers", headerJson);

		EditImageResponse response = null;
		/*================Get Body From file====================*/		
		String body;
		boolean processed = false;
		try {
			BufferedReader reader = new BufferedReader(new InputStreamReader(input));
			JSONParser parser = new JSONParser();
			JSONObject event = (JSONObject) parser.parse(reader);
			logger.log("event:" + event.toJSONString());

			body = (String)event.get("body");
			if (body == null) {
				body = event.toJSONString();  
			}
		} catch (ParseException pe) {
			logger.log(pe.toString());
			response = new EditImageResponse("Bad Request:" + pe.getMessage(), 422); 
	        responseJson.put("body", new Gson().toJson(response));
	        processed = true;
	        body = null;
		}

		if (!processed) {
			EditImageRequest req = new Gson().fromJson(body, EditImageRequest.class);
			logger.log(req.toString());

			
			try {
				if () {
					response = new EditImageResponse(req.id, 200);
				} else {
					response = new EditImageResponse(req.id, 422, "Unable to delete image.");
				}
			} catch (Exception e) {
				response = new EditImageResponse(req.id, 403, "Unable to delete image: " + req.id + "(" + e.getMessage() + ")");
			}
			
		}
		
        responseJson.put("body", new Gson().toJson(response));  
        responseJson.put("statusCode", response.statusCode);
        
        logger.log("end result:" + responseJson.toJSONString());
        logger.log(responseJson.toJSONString());
        OutputStreamWriter writer = new OutputStreamWriter(output, "UTF-8");
        writer.write(responseJson.toJSONString());  
        writer.close();
	}
}