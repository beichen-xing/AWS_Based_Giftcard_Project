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
import http.CreateCardRequest;
import http.CreateCardResponse;
import http.DuplicateCardRequest;
import http.DuplicateCardResponse;
import http.GetCardRequest;
import http.GetCardResponse;
import model.Card;


public class DuplicateCard implements RequestStreamHandler {

	
	
/*==================Create card in RDS===========================*/ 
	
	LambdaLogger logger;

	boolean CreateCardInRDS(String id, String recipient, String newid) throws Exception {
		if (logger != null) { logger.log("in duplicateCard"); }
		GiftCardDAO dao = new GiftCardDAO();
		if(dao.duplicateCard(id, recipient, newid)) {
			dao.duplicateTexts(id, newid);
			dao.duplicateImage(id, newid);
			return true;
		}
		return false;
		
	}
	
	public void CreateCard(String id, String recipient, String newid) throws Exception {
		Card card;
		try {
			CreateCardInRDS(id, recipient, newid);
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

		DuplicateCardResponse response = null;

		
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
			response = new DuplicateCardResponse(422);  
			responseJson.put("body", new Gson().toJson(response));
			processed = true;
			body = null;
		}

		if (!processed) {
			DuplicateCardRequest req = new Gson().fromJson(body, DuplicateCardRequest.class);
			logger.log(req.toString());

			boolean fail = false;
			String failMessage = "";
			
			
			try {
				CreateCard(req.id,req.recipient,req.newid);
				logger.log("success");
			} catch (Exception ex) {
				failMessage = req.id + "fail";
				fail = true;
			}
			


			if (fail) {
				response = new DuplicateCardResponse(400);
			} else {
				response = new DuplicateCardResponse(200);  
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
