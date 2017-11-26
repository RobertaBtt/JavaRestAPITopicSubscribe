package com.crvl.restapi.server.container;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;
import org.restlet.data.MediaType;
import org.restlet.data.Reference;
import org.restlet.data.Status;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;

import com.crvl.restapi.model.JSONMessageRepresentation;

public  class ServerResourceContainer {
		
   private static ServerResourceContainer  instance = null;
   protected ServerResourceContainer () {
      // Exists only to defeat instantiation.
   }
   public static ServerResourceContainer  getInstance() {
      if(instance == null) {
         instance = new ServerResourceContainer ();
      }
      return instance;
   }

	
	private List<IAPIResource> apiResourceList = new ArrayList<IAPIResource>();
	
	
	private String getNewestAPIVersion(){
		return "1";
	}
	
	public void addAPIResource(IAPIResource apiResourceClass){
		apiResourceList.add(apiResourceClass);
	}
	
	
	/*I want to use a particular API that has a Version
	 * If that version did not exist, the API uses the newest version*/
	
	public String getVersionFromRequest(Reference reference){
		
		String version;
    	if (reference != null){
    		
	    	try{
	    		List<String> segments = reference.getSegments();
	    		if (segments.size() > 0){
	    			if (segments.size() == 1) version = getNewestAPIVersion();
	    			else version = segments.get(1);
	    		}
	    		else version = getNewestAPIVersion();
	    	
	    	}
	    	catch(Exception e){ version = getNewestAPIVersion(); }
    	}
    	else{ version = getNewestAPIVersion(); }    	
    	return version;
		
	}
	
	/*Method used whenever I send a response. It packs the fundamentals: data, type, query result*/

	public Representation packResult(Status status, JSONObject data, MediaType mediaType) {
		JSONMessageRepresentation message = new JSONMessageRepresentation(status, data);    	    
        Representation result = new StringRepresentation(message.getJsonObjectResponse().toString());        
        result.setMediaType(mediaType);
		return result;
	}
	
}
