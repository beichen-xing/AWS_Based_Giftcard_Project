package cardcontrol;

import static org.junit.Assert.*;

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

import cardcontrol.LambdaTest;
import http.EditImageRequest;


public class EditImageTest extends LambdaTest{

	void testSuccessInput(String incoming) throws IOException {
    	EditImage handler = new EditImage();

        InputStream input = new ByteArrayInputStream(incoming.getBytes());
        OutputStream output = new ByteArrayOutputStream();

        handler.handleRequest(input, output, createContext("edit image"));

        JsonNode outputNode = Jackson.fromJsonString(output.toString(), JsonNode.class);
        Assert.assertEquals("200", outputNode.get("statusCode").asText());
    }
	
    void testFailInput(String incoming, String failureCode) throws IOException {
    	CreateCard handler = new CreateCard();

        InputStream input = new ByteArrayInputStream(incoming.getBytes());
        OutputStream output = new ByteArrayOutputStream();

        handler.handleRequest(input, output, createContext("edit image"));

        JsonNode outputNode = Jackson.fromJsonString(output.toString(), JsonNode.class);
        Assert.assertEquals(failureCode, outputNode.get("statusCode").asText());
    }
    
   
    @Test
    public void testShouldBeOk() {
    	
    	
    	EditImageRequest atr = new EditImageRequest("1","tqltqltql","10,50","b","1","M");
    	
        String SAMPLE_INPUT_STRING = new Gson().toJson(atr);  
        
        try {
        	testSuccessInput(SAMPLE_INPUT_STRING);
        } catch (IOException ioe) {
        	Assert.fail("Invalid:" + ioe.getMessage());
        }
    }
}
