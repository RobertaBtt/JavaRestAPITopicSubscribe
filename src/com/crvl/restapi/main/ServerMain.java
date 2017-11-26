package com.crvl.restapi.main;
import org.json.JSONObject;
import org.restlet.Component;
import org.restlet.data.MediaType;
import org.restlet.data.Protocol;
import org.restlet.data.Reference;
import org.restlet.data.Status;
import org.restlet.engine.connector.HttpProtocolHelper;
import org.restlet.Server;

import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.crvl.restapi.model.JSONMessageRepresentation;
import com.crvl.restapi.server.container.ServerResourceContainerV0;
import com.crvl.restapi.server.container.ServerResourceContainerV1;


public class ServerMain extends ServerResource {
	
	final static int DEFAULTPORT = 8082;
	
    private static Component component;
    public static void main(String[] args) throws Exception {
	
    	Server server;    	
		server = new Server(Protocol.HTTP, getPort(args));    	
		initComponent(server); 
		
    }
    
    public static int getPort(String[] args){
    	int port=DEFAULTPORT;
    	
    	if (args != null && args.length >0){
		 try  { port = Integer.parseInt(args[0]);}  //Check if parameter is numeric
		  catch(NumberFormatException nfe) { }  		  
    	}	
    	return port;
    }
    
    public static void initComponent(Server server){
    	
    	component = new Component();    	
    	component.getServers().add(server);
    	
    	component.getDefaultHost().attach("/", ServerMain.class);
    	component.getDefaultHost().attach("/v/{version}", ServerMain.class);
    	new ServerResourceContainerV0(component);
    	new ServerResourceContainerV1(component);
    	try {
			component.start();
		} catch (Exception e) {System.out.println("Error init restlet component" + e.getMessage());	}

    	
    }
    
    @Get
    public Representation mainProxy(){
    	
    	String version = getVersion(getReference());    	
    	JSONObject data  = new JSONObject();
    	data.put("version", version);    	
    	Status status = Status.SUCCESS_OK;
    	
    	JSONMessageRepresentation message = new JSONMessageRepresentation(status, data);    	    
        Representation result = new StringRepresentation(message.getJsonObjectResponse().toString());        
        result.setMediaType(MediaType.APPLICATION_JSON);
        return result;
            	
    }
    

    
    public String getVersion(Reference reference){
    	String version;
    	if (reference != null){
    		String url = reference.toString();
    		
	    	try{
	    		version = url.substring(url.indexOf("/v/")+3, url.indexOf("/v/")+4);
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