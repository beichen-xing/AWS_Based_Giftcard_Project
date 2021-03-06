package cardcontrol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

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

import db.GiftCardDAO;
import http.DeleteTextRequest;
import http.DeleteTextResponse;
import model.Card;

public class DeleteTextElement implements RequestStreamHandler {

/*==============Delete Card By ID===================*/
	public LambdaLogger logger = null;

	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to delete text");

		JSONObject headerJson = new JSONObject();
		headerJson.put("Content-Type",  "application/json");  
		headerJson.put("Access-Control-Allow-Methods", "DELETE,GET,POST,OPTIONS");
	    headerJson.put("Access-Control-Allow-Origin",  "*");
	        
		JSONObject responseJson = new JSONObject();
		responseJson.put("headers", headerJson);

		DeleteTextResponse response = null;
		
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
			response = new DeleteTextResponse("Bad Request:" + pe.getMessage(), 422); 
	        responseJson.put("body", new Gson().toJson(response));
	        processed = true;
	        body = null;
		}

		if (!processed) {
			DeleteTextRequest req = new Gson().fromJson(body, DeleteTextRequest.class);
			logger.log(req.toString());

			GiftCardDAO dao = new GiftCardDAO();
			
			try {
				if (dao.DeleteText(req.id)) {
					response = new DeleteTextResponse(req.id, 200);
				} else {
					response = new DeleteTextResponse(req.id, 422, "Unable to delete text.");
				}
			} catch (Exception e) {
				response = new DeleteTextResponse(req.id, 403, "Unable to delete text: " + req.id + "(" + e.getMessage() + ")");
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