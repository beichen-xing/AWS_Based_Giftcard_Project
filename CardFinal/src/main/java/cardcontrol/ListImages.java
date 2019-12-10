package cardcontrol;

import java.io.*;
import java.util.List;

import org.json.simple.JSONObject;

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

import http.ListImagesResponse;
import db.DatabaseUtil;
import db.ImageElementDAO;
import model.ImageElement;

public class ListImages implements RequestStreamHandler {

/*====================Load all images from RDS========================*/	
	public LambdaLogger logger = null;

	List<ImageElement> getImages() throws Exception {
		if (logger != null) { logger.log("in getImages"); }
		ImageElementDAO dao = new ImageElementDAO();
		
		return dao.ListImages();
	}
	
	
/*======================Response a JSON file==================================*/		
	@Override
	public void handleRequest(InputStream input, OutputStream output, Context context) throws IOException {
		logger = context.getLogger();
		logger.log("Loading Java Lambda handler to list all images");

		JSONObject headerJson = new JSONObject();
		headerJson.put("Content-Type", "application/json");  
		headerJson.put("Access-Control-Allow-Methods", "GET,POST,DELETE,OPTIONS");
	    headerJson.put("Access-Control-Allow-Origin",  "*");
	        
		JSONObject responseJson = new JSONObject();
		responseJson.put("headers", headerJson);

		ListImagesResponse response;
		try {
			List<ImageElement> list = getImages();
			response = new ListImagesResponse(list, 200);
		} catch (Exception e) {
			response = new ListImagesResponse(403, e.getMessage());
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