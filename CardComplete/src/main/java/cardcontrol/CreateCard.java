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
import http.GetCardRequest;
import http.GetCardResponse;
import model.Card;


public class CreateCard implements RequestStreamHandler {

	
	
/*==================Create card in RDS===========================*/ 
	
	LambdaLogger logger;

	boolean CreateCardInRDS(String id,String name,String recipient,String type,String orientation) throws Exception {
		if (logger != null) { logger.log("in createCard"); }
		GiftCardDAO dao = new GiftCardDAO();
		return dao.CreateCard(id,name,recipient,type,orientation);
		
	}
	
	public void CreateCard(String id,String name,String recipient,String type,String orientation) throws Exception {
		Card card;
		try {
			CreateCardInRDS(id,name,recipient,type,orientation);
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

		CreateCardResponse response = null;

		
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
			response = new CreateCardResponse(422);  
			responseJson.put("body", new Gson().toJson(response));
			processed = true;
			body = null;
		}

		if (!processed) {
			CreateCardRequest req = new Gson().fromJson(body, CreateCardRequest.class);
			logger.log(req.toString());

			boolean fail = false;
			String failMessage = "";
			
			
			try {
				CreateCard(req.id,req.name,req.recipient,req.type,req.orientation);
			} catch (Exception ex) {
				failMessage = req.id + "fail";
				fail = true;
			}
			


			if (fail) {
				response = new CreateCardResponse(400);
			} else {
				response = new CreateCardResponse(200);  
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
