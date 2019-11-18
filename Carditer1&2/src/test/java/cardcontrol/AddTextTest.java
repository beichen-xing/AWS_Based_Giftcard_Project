package cardcontrol;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.junit.Assert;
import org.junit.Test;

import com.amazonaws.util.json.Jackson;
import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.Gson;

import http.AddTextRequest;




/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class AddTextTest extends LambdaTest {

    void testSuccessInput(String incoming) throws IOException {
    	AddTextElement handler = new AddTextElement();

        InputStream input = new ByteArrayInputStream(incoming.getBytes());
        OutputStream output = new ByteArrayOutputStream();

        handler.handleRequest(input, output, createContext("add text"));

        JsonNode outputNode = Jackson.fromJsonString(output.toString(), JsonNode.class);
        Assert.assertEquals("200", outputNode.get("statusCode").asText());
    }
	
    void testFailInput(String incoming, String failureCode) throws IOException {
    	CreateCard handler = new CreateCard();

        InputStream input = new ByteArrayInputStream(incoming.getBytes());
        OutputStream output = new ByteArrayOutputStream();

        handler.handleRequest(input, output, createContext("add text"));

        JsonNode outputNode = Jackson.fromJsonString(output.toString(), JsonNode.class);
        Assert.assertEquals(failureCode, outputNode.get("statusCode").asText());
    }
    
   
    @Test
    public void testShouldBeOk() {
    	
    	
    	AddTextRequest atr = new AddTextRequest("11","hhh");
    	
        String SAMPLE_INPUT_STRING = new Gson().toJson(atr);  
        
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    
}
