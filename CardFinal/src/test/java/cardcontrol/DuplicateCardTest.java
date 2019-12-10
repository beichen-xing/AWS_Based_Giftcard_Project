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

import http.DuplicateCardRequest;



/**
 * A simple test harness for locally invoking your Lambda function handler.
 */
public class DuplicateCardTest extends LambdaTest {

    void testSuccessInput(String incoming) throws IOException {
    	DuplicateCard handler = new DuplicateCard();

        InputStream input = new ByteArrayInputStream(incoming.getBytes());
        OutputStream output = new ByteArrayOutputStream();

        handler.handleRequest(input, output, createContext("DuplicateCard"));

        JsonNode outputNode = Jackson.fromJsonString(output.toString(), JsonNode.class);
        Assert.assertEquals("200", outputNode.get("statusCode").asText());
    }
	
   
    @Test
    public void testShouldBeOk() {
    	
    	
    	DuplicateCardRequest ccr = new DuplicateCardRequest("1","Lily","88888");
    	
        String SAMPLE_INPUT_STRING = new Gson().toJson(ccr);  
        
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
    
}