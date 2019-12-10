package cardcontrol;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import http.ListImageofACardRequest;
import http.ListImageofACardResponse;
import http.PostRequest;
import http.PostResponse;

public class ListImagesofACardTest extends LambdaTest {
	 @Test
	    public void testGetTexts() throws IOException {
	    	
	    	// get texts      
	        ListImageofACardRequest gcr = new ListImageofACardRequest("1");
	        
	        String ccRequest = new Gson().toJson(gcr);
	        String jsonRequest = new Gson().toJson(new PostRequest(ccRequest));
	        
	        InputStream input = new ByteArrayInputStream(jsonRequest.getBytes());
	        OutputStream output = new ByteArrayOutputStream();

	        new ListImageofACard().handleRequest(input, output, createContext("get"));

	        PostResponse post = new Gson().fromJson(output.toString(), PostResponse.class);
	        ListImageofACardResponse resp = new Gson().fromJson(post.body, ListImageofACardResponse.class);
	        
	        Assert.assertEquals("200", resp.statusCode);
	 }

}