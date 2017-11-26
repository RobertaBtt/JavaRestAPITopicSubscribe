package com.crvl.restapi.server.resource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.crvl.restapi.model.JSONMessageRepresentation;


public class SubjectsResource extends ServerResource {
	
	
    @Get 
    public Representation getSurveys() {
    	String subjects = "";
    	try {
    		subjects = new JSONParser().parse(new FileReader("Subjects.json")).toString();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
    	
    	
    	JSONObject data  = new JSONObject();
    	data.put("subjects", subjects);    	
    	Status status = Status.SUCCESS_OK;    	
    	JSONMessageRepresentation message = new JSONMessageRepresentation(status, data);    
    	
        Representation result = new StringRepresentation(message.getJsonObjectResponse().toString());        
        result.setMediaType(MediaType.APPLICATION_JSON);
        return result;            	
        
    }
}