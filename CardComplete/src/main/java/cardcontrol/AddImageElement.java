package cardcontrol;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
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
import com.amazonaws.util.IOUtils;

import java.io.File;
import java.io.IOException;

import db.GiftCardDAO;
import http.AddImageRequest;
import http.AddImageResponse;
import model.Card;

public class AddImageElement implements RequestStreamHandler {

/*==================Upload Image to S3===========================*/
	AWSCredentials credentials = new BasicAWSCredentials(
			  "<AWS accesskey>", 
			  "<AWS secretkey>"
			);
	
	AmazonS3 s3client = AmazonS3ClientBuilder
			  .standard()
			  .withCredentials(new AWSStaticCredentialsProvider(credentials))
			  .withRegion(Regions.US_EAST_2)
			  .build();
	
	public void UploadImage(String image_id, InputStream inputStream) {
		String bucketName = "Album";
		String fileName = image_id;
		byte[] contents;
		try {
			contents = IOUtils.toByteArray(inputStream);
			InputStream stream = new ByteArrayInputStream(contents);
			ObjectMetadata meta = new ObjectMetadata();
			meta.setContentLength(contents.length);
			meta.setContentType("image/png");

			s3client.putObject(new PutObjectRequest(
			        bucketName, fileName, stream, meta)
			        .withCannedAcl(CannedAccessControlList.Private));

			inputStream.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
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
				AddImage(req.image_id, req.image_path, req.bounds, req.page, req.card_id);
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

