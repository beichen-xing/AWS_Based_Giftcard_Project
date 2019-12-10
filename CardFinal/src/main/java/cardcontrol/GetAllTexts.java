package cardcontrol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.amazonaws.services.lambda.runtime.events.S3Event;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.google.gson.Gson;


import db.DatabaseUtil;
import db.TextElementDAO;
import http.GetAllTextsRequest;
import http.GetAllTextsResponse;
import http.GetCardRequest;
import http.GetCardResponse;
import model.Card;
import model.TextElement;


public class GetAllTexts implements RequestStreamHandler {

/*====================Load all cards from RDS========================*/	
	public LambdaLogger logger = null;

	List<TextElement> getAllTexts(String id) throws Exception {
		if (logger != null) { logger.log("in getTexts"); }
		TextElementDAO dao = new TextElementDAO();
		
		return dao.getAllTexts(id);
	}
	
	
/*======================Response a JSON file==================================*/		
	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all texts");

		JSONObject headerJson = new JSONObject();
		headerJson.put("Content-Type", "application/json");  
		headerJson.put("Access-Control-Allow-Methods", "GET,POST,DELETE,OPTIONS");
	    headerJson.put("Access-Control-Allow-Origin",  "*");
	        
		JSONObject responseJson = new JSONObject();
		responseJson.put("headers", headerJson);

		GetAllTextsResponse response = null;
		
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
			response = new GetAllTextsResponse(422,"error");  
			responseJson.put("body", new Gson().toJson(response));
			processed = true;
			body = null;
		}
		
		if (!processed) {
			GetAllTextsRequest req = new Gson().fromJson(body, GetAllTextsRequest.class);
			logger.log(req.toString());

			boolean fail = false;
			String failMessage = "";
			List<TextElement> list = null;
			
			try {
				list = getAllTexts(req.id);
				response = new GetAllTextsResponse(list, 200);
			} catch (Exception e) {
				response = new GetAllTextsResponse(403, e.getMessage());
			}


			if (fail) {
				response = new GetAllTextsResponse(400,"error");
			} else {
				response = new GetAllTextsResponse(list,200);  
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