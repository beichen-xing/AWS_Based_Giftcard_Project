package cardcontrol;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.Scanner;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestStreamHandler;
import com.google.gson.Gson;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import com.amazonaws.services.s3.model.S3Object;
import com.amazonaws.services.s3.model.S3ObjectInputStream;
import com.amazonaws.util.IOUtils;

import java.io.File;
import java.io.IOException;

import db.GiftCardDAO;
import http.AddImageRequest;
import http.AddImageResponse;
import model.Card;

public class AddImageElement implements RequestStreamHandler {
	
	
	private AmazonS3 s3 = null;
	
	// Note: this works, but it would be better to move this to environment/configuration mechanisms
	// which you don't have to do for this project.
	public static final String REAL_BUCKET = "cs509finalinteration/";
	public static final String TEST_BUCKET = "testconstants/";

/*==================Upload Image to S3===========================*/
	boolean uploadImages (String image_id, byte[] content) throws Exception {
		if (logger != null) { logger.log("in createSystemConstant"); }
		
		if (s3 == null) {
			logger.log("attach to S3 request");
			s3 = AmazonS3ClientBuilder.standard().withRegion(Regions.US_EAST_2).build();
			logger.log("attach to S3 succeed");
		}

		String bucket = REAL_BUCKET;
		boolean useTestDB = System.getenv("TESTING") != null;
		if (useTestDB) {
			bucket = TEST_BUCKET;
		}

		ByteArrayInputStream bais = new ByteArrayInputStream(content);
		ObjectMetadata omd = new ObjectMetadata();
		omd.setContentLength(content.length);
		
		// makes the object publicly visible
		PutObjectResult res = s3.putObject(new PutObjectRequest("cs509finalinteration", bucket + image_id, bais, omd)
				.withCannedAcl(CannedAccessControlList.PublicRead));
		
		// if we ever get here, then whole thing was stored
		return true;
	}
	//String image_path = "https://[bucket].s3.amazonaws.com/img_name.jpg";
	
/*==================Load Image name from S3===========================*/ 	
	
/*==================Add ImageElement in RDS===========================*/ 
	
	LambdaLogger logger;

	boolean AddImageInRDS(String image_id, String image_path, String bounds, String page,String card_id) throws Exception {
		if (logger != null) { logger.log("in addImage"); }
		
		GiftCardDAO dao = new GiftCardDAO();
		
		return dao.AddImage(image_id, image_path, bounds, page, card_id);
		
	}
	
	public void AddImage(String image_id, String image_path, String bounds, String page, String card_id) throws Exception {
		Card card;
		try {
			AddImageInRDS(image_id, image_path, bounds, page, card_id);
		} catch (Exception e) {
			System.out.println("Error");
		}
	}

	
/*=====================Response a JSON file==================================*/	

	@Override
	public void handleRequest(InputStream input, OutputStream output, Context conImage) throws IOException {
		logger = conImage.getLogger();
		logger.log("Loading Java Lambda handler of RequestStreamHandler");

		JSONObject headerJson = new JSONObject();
		headerJson.put("Content-Type",  "application/json");  
		headerJson.put("Access-Control-Allow-Methods", "GET,POST,DELETE,OPTIONS");
		headerJson.put("Access-Control-Allow-Origin",  "*");

		JSONObject responseJson = new JSONObject();
		responseJson.put("headers", headerJson);

		AddImageResponse response = null;

		
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
			response = new AddImageResponse(422);  
			responseJson.put("body", new Gson().toJson(response));
			processed = true;
			body = null;
		}

		if (!processed) {
			AddImageRequest req = new Gson().fromJson(body, AddImageRequest.class);
			logger.log(req.toString());

			boolean fail = false;
			String failMessage = "";
			
			
			try {
				byte[] encoded = java.util.Base64.getDecoder().decode(req.content);
				if(uploadImages(req.image_id, encoded)) {
					System.out.println(req.bounds);
					AddImage(req.image_id, req.image_path, req.bounds, req.page, req.card_id);
				}
			} catch (Exception ex) {
				failMessage = req.image_id + "fail";
				fail = true;
			}
			

			if (fail) {
				response = new AddImageResponse(400);
			} else {
				response = new AddImageResponse(200);  
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

