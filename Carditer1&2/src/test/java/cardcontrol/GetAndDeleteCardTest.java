package cardcontrol;

import java.io.*;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import http.*;


public class GetAndDeleteCardTest extends LambdaTest {

    @Test
    public void testDeleteCard() throws IOException {
    	
    	// get card      
        GetCardRequest gcr = new GetCardRequest("2");
        
        String ccRequest = new Gson().toJson(gcr);
        String jsonRequest = new Gson().toJson(new PostRequest(ccRequest));
        
        InputStream input = new ByteArrayInputStream(jsonRequest.getBytes());
        OutputStream output = new ByteArrayOutputStream();

        new GetCard().handleRequest(input, output, createContext("create"));

        PostResponse post = new Gson().fromJson(output.toString(), PostResponse.class);
        GetCardResponse resp = new Gson().fromJson(post.body, GetCardResponse.class);
        System.out.println(resp);
        
        Assert.assertEquals("2", resp.id);
        
        // now delete
        DeleteCardRequest dcr = new DeleteCardRequest("2");
        
        ccRequest = new Gson().toJson(dcr);
        jsonRequest = new Gson().toJson(new PostRequest(ccRequest));
        
        input = new ByteArrayInputStream(jsonRequest.getBytes());
        output = new ByteArrayOutputStream();

        new DeleteCard().handleRequest(input, output, createContext("delete"));

        post = new Gson().fromJson(output.toString(), PostResponse.class);
        DeleteCardResponse d_resp = new Gson().fromJson(post.body, DeleteCardResponse.class);
        Assert.assertEquals("2", d_resp.id);
        
        // try again and this should fail
        input = new ByteArrayInputStream(jsonRequest.getBytes());
        output = new ByteArrayOutputStream();

        new DeleteCard().handleRequest(input, output, createContext("delete"));
        post = new Gson().fromJson(output.toString(), PostResponse.class);
        d_resp = new Gson().fromJson(post.body, DeleteCardResponse.class);
        
        Assert.assertEquals(422, d_resp.statusCode);
    }
}