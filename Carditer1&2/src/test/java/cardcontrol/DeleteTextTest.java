package cardcontrol;

import java.io.*;

import org.junit.Assert;
import org.junit.Test;

import com.google.gson.Gson;

import http.*;


public class DeleteTextTest extends LambdaTest {

    @Test
    public void testDeleteText() throws IOException {

        
        // delete
        DeleteTextRequest dtr = new DeleteTextRequest("11");
        
        String dtRequest = new Gson().toJson(dtr);
        String jsonRequest = new Gson().toJson(new PostRequest(dtRequest));
        
        InputStream input = new ByteArrayInputStream(jsonRequest.getBytes());
        OutputStream output = new ByteArrayOutputStream();

        new DeleteTextElement().handleRequest(input, output, createContext("delete"));

        PostResponse post = new Gson().fromJson(output.toString(), PostResponse.class);
        DeleteTextResponse d_resp = new Gson().fromJson(post.body, DeleteTextResponse.class);
        Assert.assertEquals("11", d_resp.id);
        
    }
}
