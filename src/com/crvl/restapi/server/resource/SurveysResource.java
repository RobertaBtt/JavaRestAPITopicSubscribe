package com.crvl.restapi.server.resource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



public class SurveysResource extends ServerResource {
	
	
    @Get 
    public Representation getSurveys() {
    	String resultJson = "";
    	try {
    		resultJson = new JSONParser().parse(new FileReader("SurveyList.json")).toString();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
    	
    	System.out.println(resultJson);
    	
        JSONObject testJson = new JSONObject();             
       
        testJson.put("status", "ok");
        testJson.put("code", "200");
        testJson.put("message", "List of Surveys");
        testJson.put("result", resultJson );        
        
        Representation result = new StringRepresentation(testJson.toString());
        result.setMediaType(MediaType.APPLICATION_JSON);        
        
        return result;
    }
}