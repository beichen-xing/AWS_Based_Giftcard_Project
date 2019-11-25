package cardcontrol;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import com.amazonaws.services.lambda.runtime.Context;
import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;


import http.GetTextRequest;
import http.GetTextResponse;
import http.PostRequest;
import http.PostResponse;

public class GetTextTest extends LambdaTest  {
	 @Test
	    public void testGetCard() throws IOException {
	    	
	    	// get card      
	        GetTextRequest gcr = new GetTextRequest("888");
	        
	        String ccRequest = new Gson().toJson(gcr);
	        String jsonRequest = new Gson().toJson(new PostRequest(ccRequest));
	        
	        InputStream input = new ByteArrayInputStream(jsonRequest.getBytes());
	        OutputStream output = new ByteArrayOutputStream();

	        new GetTextElement().handleRequest(input, output, createContext("post"));

	        PostResponse post = new Gson().fromJson(output.toString(), PostResponse.class);
	        GetTextResponse resp = new Gson().fromJson(post.body, GetTextResponse.class);
	        System.out.println(resp);
	        System.out.println(resp);
	        
	        Assert.assertEquals("888", resp.text_id);
	 }

}
