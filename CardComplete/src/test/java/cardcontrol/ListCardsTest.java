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



/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class ListCardsTest extends LambdaTest {

	
    @Test
    public void testGetList() throws IOException {
    	ListCards handler = new ListCards();

    	
        String inputString = "{}";
        InputStream input = new ByteArrayInputStream(inputString.getBytes());
        OutputStream output = new ByteArrayOutputStream();

        handler.handleRequest(input, output, createContext("list"));

        PostResponse post = new Gson().fromJson(output.toString(), PostResponse.class);
        AllCardResponse resp = new Gson().fromJson(post.body, AllCardResponse.class);
        
        boolean hasCard = false;
        for (Card c : resp.list) {
        	if (c.id.equals("3bf6ab4b-50de-43cf-8899-febeec2f36b3")) { hasCard = true; break; }
        }
        Assert.assertTrue("card Needs to exist in the cards table for this test case to work.", hasCard);
        Assert.assertEquals(200, resp.statusCode);
    }

}