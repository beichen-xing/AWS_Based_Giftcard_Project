package cardcontrol;

import java.io.*;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;

import http.*;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;



public class GetCardTest extends LambdaTest {
	 @Test
	    public void testGetCard() throws IOException {
	    	
	    	// get card      
	        GetCardRequest gcr = new GetCardRequest("2");
	        
	        String ccRequest = new Gson().toJson(gcr);
	        String jsonRequest = new Gson().toJson(new PostRequest(ccRequest));
	        
	        InputStream input = new ByteArrayInputStream(jsonRequest.getBytes());
	        OutputStream output = new ByteArrayOutputStream();

	        new GetCard().handleRequest(input, output, createContext("get"));

	        PostResponse post = new Gson().fromJson(output.toString(), PostResponse.class);
	        GetCardResponse resp = new Gson().fromJson(post.body, GetCardResponse.class);
	        System.out.println(resp);
	        System.out.println(resp);
	        
	        Assert.assertEquals("2", resp.id);
	 }


}
