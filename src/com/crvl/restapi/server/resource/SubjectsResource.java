package com.crvl.restapi.server.resource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import org.restlet.data.MediaType;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.crvl.restapi.server.container.ServerResourceContainer;


public class SubjectsResource extends ServerResource {
	public final static MediaType MEDIA_TYPE = MediaType.APPLICATION_JSON;
	
    @Get 
    public Representation getSurveys() {
    	String subjects = "";
    	Status status = Status.SUCCESS_OK;
    	try {
    		subjects = new JSONParser().parse(new FileReader("Subjects.json")).toString();
		} catch (FileNotFoundException e1) { status = Status.SERVER_ERROR_INTERNAL; } 
    	  catch (IOException e1) {status = Status.SERVER_ERROR_INTERNAL;		} 
    	  catch (ParseException e1) {status = Status.SERVER_ERROR_INTERNAL;		}
    	
    	
    	JSONObject data  = new JSONObject();
    	data.put("subjects", subjects);    	    	
    	ServerResourceContainer serverResourceContainer = ServerResourceContainer.getInstance();
    	return serverResourceContainer.packResult(status, data, MEDIA_TYPE);    	
    	          	
        
    }
}