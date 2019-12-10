package cardcontrol;

import java.io.*;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.google.gson.Gson;

import db.GiftCardDAO;
import http.EditImageRequest;
import http.EditImageResponse;
import model.Card;


public class EditImage implements RequestStreamHandler {

	
	
/*==================Add TextElement in RDS===========================*/ 
	
	LambdaLogger logger;

	boolean EditImageInRDS(String image_id, String image_path, String bounds, String page,String card_id, String card_content) throws Exception {
		if (logger != null) { logger.log("in editImage"); }
		
		GiftCardDAO dao = new GiftCardDAO();
		
		return dao.EditImage(image_id, image_path, bounds, page, card_id, card_content);
		
	}
	
	public void EditImage(String image_id, String image_path, String bounds, String page, String card_id, String card_content) throws Exception {
		Card card;
		try {
			EditImageInRDS(image_id, image_path, bounds, page, card_id, card_content);
		} catch (Exception e) {
			System.out.println("Error");
		}
	}
	
/*=====================Response a JSON file==================================*/	

	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler of RequestStreamHandler");

		JSONObject headerJson = new JSONObject();
		headerJson.put("Content-Type",  "application/json");  
		headerJson.put("Access-Control-Allow-Methods", "GET,POST,DELETE,OPTIONS");
		headerJson.put("Access-Control-Allow-Origin",  "*");

		JSONObject responseJson = new JSONObject();
		responseJson.put("headers", headerJson);

		EditImageResponse response = null;

		
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
			response = new EditImageResponse(422);  
			responseJson.put("body", new Gson().toJson(response));
			processed = true;
			body = null;
		}

		if (!processed) {
			EditImageRequest req = new Gson().fromJson(body, EditImageRequest.class);
			logger.log(req.toString());

			boolean fail = false;
			String failMessage = "";
			
			
			try {
				EditImage(req.image_id, req.image_path, req.bounds, req.page, req.card_id, req.card_content);
			} catch (Exception ex) {
				failMessage = req.image_id + "fail";
				fail = true;
			}
			


			if (fail) {
				response = new EditImageResponse(400);
			} else {
				response = new EditImageResponse(200);  
			}
		}

		
		responseJson.put("body", new Gson().toJson(response));  
		responseJson.put("statusCode", response.statusCode);

		logger.log(responseJson.toJSONString());
		OutputStreamWriter writer = new OutputStreamWriter(output, "UTF-8");
		writer.write(responseJson.toJSONString());  
		writer.close();
		
	}

}

