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
public class ListImageFromS3Test extends LambdaTest {

	
    @Test
    public void testGetList() throws IOException {
    	ListImagefromS3 handler = new ListImagefromS3();

    	
        String inputString = "{}";
        InputStream input = new ByteArrayInputStream(inputString.getBytes());
        OutputStream output = new ByteArrayOutputStream();

        handler.handleRequest(input, output, createContext("list"));

        PostResponse post = new Gson().fromJson(output.toString(), PostResponse.class);
        ListImagesResponsefromS3 resp = new Gson().fromJson(post.body, ListImagesResponsefromS3.class);
        
        boolean hasImage = false;
        Assert.assertEquals(200, resp.statusCode);
    }

}