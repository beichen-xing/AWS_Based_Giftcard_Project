package cardcontrol;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.services.lambda.runtime.Context;
import com.google.gson.Gson;

import http.*;
import model.Card;
import model.ImageElement;



/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class ListImagesTest extends LambdaTest {

	
    @Test
    public void testGetList() throws IOException {
    	ListImages handler = new ListImages();

    	
        String inputString = "{}";
        InputStream input = new ByteArrayInputStream(inputString.getBytes());
        OutputStream output = new ByteArrayOutputStream();

        handler.handleRequest(input, output, createContext("list"));

        PostResponse post = new Gson().fromJson(output.toString(), PostResponse.class);
        ListImagesResponse resp = new Gson().fromJson(post.body, ListImagesResponse.class);
        
        boolean hasImage = false;
        Assert.assertEquals(200, resp.statusCode);
    }

}