package cardcontrol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

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
import db.ImageElementDAO;
import db.TextElementDAO;
import http.GetAllTextsResponse;
import http.ListImagePathRequest;
import http.ListImagePathResponse;
import model.Card;
import model.ImageElement;


public class ListImagePathofACard implements RequestStreamHandler {
	
	
/*==================get card from RDS===========================*/ 
	
	LambdaLogger logger;

	List<String> GetImageFromRDS(String id) throws Exception {
		if (logger != null) { logger.log("in loadCard"); }
		ImageElementDAO dao = new ImageElementDAO();
		List<String> imagespath = dao.getAllImagespath(id);
		return imagespath;
	}
	
	public List<String> loadImages(String id) throws Exception {
		List<String> imagespath;
		try {
			imagespath = GetImageFromRDS(id);
			return imagespath;
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

		ListImagePathResponse response = null;

		
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
			response = new ListImagePathResponse(422, "error");  
			responseJson.put("body", new Gson().toJson(response));
			processed = true;
			body = null;
		}

		if (!processed) {
			ListImagePathRequest req = new Gson().fromJson(body, ListImagePathRequest.class);
			logger.log(req.toString());

			boolean fail = false;
			String failMessage = "";
			List<String> imagespath = new ArrayList<>();
			
			try {
				imagespath = loadImages(req.card_id);
			} catch (Exception ex) {
				failMessage = req.card_id + " not-exist";
				fail = true;
			}
			


			if (fail) {
				response = new ListImagePathResponse (400, "error");
			} else {
				response = new ListImagePathResponse(imagespath,200);  
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