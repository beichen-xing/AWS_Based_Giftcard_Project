package cardcontrol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import db.GiftCardDAO;
import db.TextElementDAO;
import http.GetTextRequest;
import http.GetTextResponse;
import model.Card;
import model.TextElement;

public class GetTextElement {
	
	
/*==================get card from RDS===========================*/ 
	
	LambdaLogger logger;

	TextElement GetTextFromRDS(String id) throws Exception {
		if (logger != null) { logger.log("in loadText"); }
		TextElementDAO dao = new TextElementDAO();
		TextElement textElement = dao.getText(id);
		return textElement;
	}
	
	public TextElement loadText(String id) throws Exception {
		TextElement textElement;
		try {
			textElement = GetTextFromRDS(id);
			return textElement;
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

		GetTextResponse response = null;

		
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
			response = new GetTextResponse(422);  
			responseJson.put("body", new Gson().toJson(response));
			processed = true;
			body = null;
		}

		if (!processed) {
			GetTextRequest req = new Gson().fromJson(body, GetTextRequest.class);
			logger.log(req.toString());

			boolean fail = false;
			String failMessage = "";
			TextElement textElement = null;
			
			try {
				textElement = loadText(req.id);
			} catch (Exception ex) {
				failMessage = req.id + " not-exist";
				fail = true;
			}
			


			if (fail) {
				response = new GetTextResponse(400);
			} else {
				response = new GetTextResponse(200, textElement.id, textElement.card_id, textElement.content, textElement.bounds, textElement.font, textElement.color);  
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
