package cardcontrol;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import http.ListImagePathRequest;
import http.ListImagePathResponse;
import http.PostRequest;
import http.PostResponse;

public class ListImagePathTest extends LambdaTest {
	 @Test
	    public void testGetTexts() throws IOException {
	    	
	    	// get texts      
	        ListImagePathRequest gcr = new ListImagePathRequest("1");
	        
	        String ccRequest = new Gson().toJson(gcr);
	        String jsonRequest = new Gson().toJson(new PostRequest(ccRequest));
	        
	        InputStream input = new ByteArrayInputStream(jsonRequest.getBytes());
	        OutputStream output = new ByteArrayOutputStream();

	        new ListImagePathofACard().handleRequest(input, output, createContext("get"));

	        PostResponse post = new Gson().fromJson(output.toString(), PostResponse.class);
	        ListImagePathResponse resp = new Gson().fromJson(post.body, ListImagePathResponse.class);
	        
	        Assert.assertEquals("200", resp.statusCode);
	 }

}