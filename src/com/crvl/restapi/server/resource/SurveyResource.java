package com.crvl.restapi.server.resource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


import org.restlet.data.MediaType;
import org.restlet.data.Reference;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.json.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.crvl.restapi.main.ServerMain;

import com.crvl.restapi.model.JSONMessageRepresentation;
import com.crvl.restapi.server.container.ServerResourceContainer;
import com.crvl.restapi.test.ServerResourceContainerTest;

public class SurveyResource extends ServerResource {
	
	private static ServerResourceContainer serverResourceContainer;
	
    @Get
    public Representation getSurvey() {
    	getReference();
    	getRequestEntity();
    	Status status = Status.SUCCESS_OK;
    	
    	String survey = "";
    	try {
    		String surveyIdentification = getSurveyIdentificationFromRequest(getReference());
    		if(!surveyIdentification.equals("")){
    			survey = new JSONParser().parse(new FileReader(surveyIdentification +".json")).toString();
    		}
    		else{
    			status = Status.CLIENT_ERROR_NOT_FOUND;
    		}
    		
		} 	catch (FileNotFoundException e1) { status = Status.CLIENT_ERROR_NOT_FOUND; } 
    		catch (IOException e1) { status = Status.SERVER_ERROR_INTERNAL; } 
    		catch (ParseException e1) {	status = Status.SERVER_ERROR_INTERNAL;}
    	    	
    	    	
    	JSONObject data  = new JSONObject();
    	data.put("survey", survey);
    	serverResourceContainer = ServerResourceContainer.getInstance();
    	return serverResourceContainer.packResult(status, data, ServerMain.MEDIA_TYPE);
    	         	
        
    }
    
    public String getSurveyIdentificationFromRequest(Reference reference){
		
		String surveyIdentification = "";
    	if (reference != null){
    		
	    	try{
	    		List<String> segments = reference.getSegments();
	    		if (segments.size() > 0){
	    			for(int s=0; s<segments.size(); s++){
	    				if (segments.get(s).equalsIgnoreCase("survey")){
	    					surveyIdentification = segments.get(s+1);
	    				}
	    			}	    		
	    		}	    			    	
	    	}
	    	catch(Exception e){ }
    	}
    	return surveyIdentification;		
	}
}