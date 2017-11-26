package com.crvl.restapi.server.resource;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
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

public class UnsubscriberResource extends ServerResource {
	
	private static ServerResourceContainer serverResourceContainer;
	private static List<String>subscriptionList = new ArrayList<String>();
	
	
    @Get
    public Representation subscribe() {
    	getReference();
    	getRequestEntity();
    	Status status = Status.SUCCESS_OK;
    	String subscriptionResult = "";
    	String subject = "";
    	
    	try {

    		String subjectIdentification = getSubjectIdentificationFromRequest(getReference());
    		if(!subjectIdentification.equals("")){
    			new JSONParser().parse(new FileReader(subjectIdentification +".json")).toString();
        		/*If topic exists (file readable) I can subscribe */
    			subscriptionResult = "Unsubscription done! See you next time.";
    			List<String> subscriptionList = SubscriberResource.getSubscriptions();
    			subscriptionList.remove(subjectIdentification);
    			
    		}
    		else{
    			subscriptionResult = "Topic not found. Can't perform unsubscription.";
    			status = Status.CLIENT_ERROR_NOT_FOUND;
    		}
    		
		} 	catch (FileNotFoundException e1) { status = Status.CLIENT_ERROR_NOT_FOUND; subscriptionResult = "Topic not found. Can't perform unsubscription.";} 
    		catch (IOException e1) { status = Status.SERVER_ERROR_INTERNAL; } 
    		catch (ParseException e1) {	status = Status.SERVER_ERROR_INTERNAL;}
    	    	
    	    	
    	JSONObject data  = new JSONObject();
    	data.put("result", subscriptionResult);
    	serverResourceContainer = ServerResourceContainer.getInstance();
    	return serverResourceContainer.packResult(status, data, ServerMain.MEDIA_TYPE);
    	         	
        
    }
    
    public String getSubjectIdentificationFromRequest(Reference reference){
		
		String surveyIdentification = "";
    	if (reference != null){
    		
	    	try{
	    		List<String> segments = reference.getSegments();
	    		if (segments.size() > 0){
	    			for(int s=0; s<segments.size(); s++){
	    				if (segments.get(s).equalsIgnoreCase("subject")){
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