package cardcontrol;

import java.io.*;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import http.*;


public class DeleteImageTest extends LambdaTest {

    @Test
    public void testDeleteText() throws IOException {

        
        // delete
        DeleteImageRequest dtr = new DeleteImageRequest("10000");
        
        String dtRequest = new Gson().toJson(dtr);
        String jsonRequest = new Gson().toJson(new PostRequest(dtRequest));
        
        InputStream input = new ByteArrayInputStream(jsonRequest.getBytes());
        OutputStream output = new ByteArrayOutputStream();

        new DeleteImageElement().handleRequest(input, output, createContext("delete"));

        PostResponse post = new Gson().fromJson(output.toString(), PostResponse.class);
        DeleteImageResponse d_resp = new Gson().fromJson(post.body, DeleteImageResponse.class);
        Assert.assertEquals("10000", d_resp.id);
        
    }
}