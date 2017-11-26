package com.crvl.restapi.server.container;

import java.util.ArrayList;
import java.util.List;

import org.restlet.data.Reference;

public  class ServerResourceContainer {

	private List<IAPIResource> apiResourceList = new ArrayList<IAPIResource>();
	
	
	private String getNewestAPIVersion(){
		return "1";
	}
	
	public void addAPIResource(IAPIResource apiResourceClass){
		apiResourceList.add(apiResourceClass);
	}
	
	
	public String getVersionFromRequest(Reference reference){
		
		String version;
    	if (reference != null){
    		
	    	try{
	    		List<String> segments = reference.getSegments();
	    		if (segments.size() > 0){
	    			if (segments.size() == 1)
	    				version = getNewestAPIVersion();
	    			else
    					version = segments.get(1);
	    		}
	    		else
	    			version = getNewestAPIVersion();
	    	
	    	}
	    	catch(Exception e){
	    		version = getNewestAPIVersion();
	    	}
    	}
    	else{
    		version = getNewestAPIVersion();
    	}
    	
    	return version;
		
	}
	
}
