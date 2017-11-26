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
import com.crvl.restapi.server.container.APIResourceVersion0;
import com.crvl.restapi.server.container.APIResourceVersion1;
import com.crvl.restapi.server.container.ServerResourceContainer;


public class ServerMain extends ServerResource {
	
	final static int DEFAULTPORT = 8082;
	
    private static Component component;
    private static ServerResourceContainer serverResourceContainer;
    
    public static void main(String[] args) throws Exception {
	
    	Server server;    	
		server = new Server(Protocol.HTTP, getPort(args));    	
		component = initComponent(server); 
		component = initAPIContainer(component);
		
		try {
			component.start();
		} catch (Exception e) {System.out.println("Error init restlet component" + e.getMessage());	}
		
    }
    
    public static int getPort(String[] args){
    	int port=DEFAULTPORT;
    	
    	if (args != null && args.length >0){
		 try  { port = Integer.parseInt(args[0]);}  //Check if parameter is numeric
		  catch(NumberFormatException nfe) { }  		  
    	}	
    	return port;
    }
    
    public static Component initComponent(Server server){    	
    	component = new Component();    	
    	component.getServers().add(server);    	
    	component.getDefaultHost().attach("/", ServerMain.class);
    	component.getDefaultHost().attach("/v/{version}", ServerMain.class);
    	return component;    	
    }
    
    public static Component initAPIContainer(Component component){
    	serverResourceContainer = new ServerResourceContainer();    	
    	serverResourceContainer.addAPIResource(new APIResourceVersion0(component));
    	serverResourceContainer.addAPIResource(new APIResourceVersion1(component));
    	return component;
    }
    
    @Get
    public Representation mainProxy(){     	
    	
    	String version = serverResourceContainer.getVersionFromRequest(getReference());    	
    	JSONObject data  = new JSONObject();
    	data.put("version", version);    	
    	Status status = Status.SUCCESS_OK;
    	
    	JSONMessageRepresentation message = new JSONMessageRepresentation(status, data);    	    
        Representation result = new StringRepresentation(message.getJsonObjectResponse().toString());        
        result.setMediaType(MediaType.APPLICATION_JSON);
        return result;            	
    }

}