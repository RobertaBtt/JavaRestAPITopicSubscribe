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
import com.crvl.restapi.server.container.ServerResourceContainer;
import com.crvl.restapi.server.container.ServerResourceContainer;

public class SurveysResource extends ServerResource {
	
	public final static MediaType MEDIA_TYPE = MediaType.APPLICATION_JSON;
	
    @Get 
    public Representation getSurveys() {
    	String surveys = "";
    	Status status = Status.SUCCESS_OK;
    	
    	try {
    		surveys = new JSONParser().parse(new FileReader("SurveyList.json")).toString();
		}  	catch (FileNotFoundException e1) { status = Status.SERVER_ERROR_INTERNAL;} 
    		catch (IOException e1) { status = Status.SERVER_ERROR_INTERNAL;} 
    		catch (ParseException e1) {status = Status.SERVER_ERROR_INTERNAL;	}
    	
    	ServerResourceContainer serverResourceContainer = ServerResourceContainer.getInstance();
    	JSONObject data  = new JSONObject();    	
    	data.put("surveys", surveys);/*Data: version requested. does not mean it exists.*/    	
    	return serverResourceContainer.packResult(status, data, MEDIA_TYPE);
    	    	
    }
}