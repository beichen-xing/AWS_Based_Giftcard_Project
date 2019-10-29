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
import http.GetCardRequest;
import http.GetCardResponse;
import model.Card;


public class GetCard implements RequestStreamHandler {

	
	
/*==================get card from RDS===========================*/ 
	
	LambdaLogger logger;

	Card GetCardFromRDS(String id) throws Exception {
		if (logger != null) { logger.log("in loadValue"); }
		GiftCardDAO dao = new GiftCardDAO();
		Card card = dao.getCard(id);
		return card;
	}
	
	public Card loadCard(String id) throws Exception {
		Card card;
		try {
			card = GetCardFromRDS(id);
			return card;
		} catch (Exception e) {
			return null;
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

		GetCardResponse response = null;

		
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
			response = new GetCardResponse(422, "unable to process input");  
			responseJson.put("body", new Gson().toJson(response));
			processed = true;
			body = null;
		}

		if (!processed) {
			GetCardRequest req = new Gson().fromJson(body, GetCardRequest.class);
			logger.log(req.toString());

			boolean fail = false;
			String failMessage = "";
			Card card;
			try {
				card = new Card("", "", "", "");
			} catch (NumberFormatException e) {
				try {
					card = loadCard(req.id);
				} catch (Exception ex) {
					failMessage = req.id + " not-exist";
					fail = true;
				}
			}


			if (fail) {
				response = new GetCardResponse(400);
			} else {
				response = new GetCardResponse(200);  
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
