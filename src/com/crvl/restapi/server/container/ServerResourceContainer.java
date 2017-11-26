package com.crvl.restapi.server.container;

import org.restlet.data.Reference;

public abstract class ServerResourceContainer {

	public abstract String getVersion();
	
	public String getVersionFromRequest(Reference reference){
		
		String version;
    	if (reference != null){
    		String url = reference.toString();
    		
	    	try{
	    		if (url.indexOf("/") != -1)
	    			version = url.substring(url.indexOf("/")+5, url.indexOf("/")+6);
	    		else version = "1";
	    	}
	    	catch(StringIndexOutOfBoundsException e){
	    		version = "1";
	    	}
    	}
    	else{
    		version = "1";
    	}
    	
    	return version;
		
	}
	
}
