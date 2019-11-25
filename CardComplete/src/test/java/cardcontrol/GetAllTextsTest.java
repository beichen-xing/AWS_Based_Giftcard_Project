package cardcontrol;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import http.GetAllTextsRequest;
import http.GetAllTextsResponse;
import http.PostRequest;
import http.PostResponse;

public class GetAllTextsTest extends LambdaTest {
	 @Test
	    public void testGetTexts() throws IOException {
	    	
	    	// get texts      
	        GetAllTextsRequest gcr = new GetAllTextsRequest("1");
	        
	        String ccRequest = new Gson().toJson(gcr);
	        String jsonRequest = new Gson().toJson(new PostRequest(ccRequest));
	        
	        InputStream input = new ByteArrayInputStream(jsonRequest.getBytes());
	        OutputStream output = new ByteArrayOutputStream();

	        new GetAllTexts().handleRequest(input, output, createContext("get"));

	        PostResponse post = new Gson().fromJson(output.toString(), PostResponse.class);
	        GetAllTextsResponse resp = new Gson().fromJson(post.body, GetAllTextsResponse.class);
	        System.out.println(resp.list.get(0).color);
	        
	        Assert.assertEquals("200", resp.statusCode);
	 }

}
