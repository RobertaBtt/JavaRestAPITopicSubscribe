package com.crvl.restapi.main;
import org.json.JSONObject;
import org.restlet.Component;
import org.restlet.data.MediaType;
import org.restlet.data.Protocol;
import org.restlet.data.Status;
import org.restlet.Server;

import org.restlet.representation.Representation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.crvl.restapi.server.container.APIResourceVersion0;
import com.crvl.restapi.server.container.APIResourceVersion1;
import com.crvl.restapi.server.container.ServerResourceContainer;


public class ServerMain extends ServerResource {
	
	final static int DEFAULTPORT = 8082;
	public final static MediaType MEDIA_TYPE = MediaType.APPLICATION_JSON;
	
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
    
    //We can manage request from two different version of the API. 0 and 1 (the newest)
    public static Component initAPIContainer(Component component){
    	serverResourceContainer = ServerResourceContainer.getInstance();    	
    	serverResourceContainer.addAPIResource(new APIResourceVersion0(component));
    	serverResourceContainer.addAPIResource(new APIResourceVersion1(component));
    	return component;
    }
    
    
    @Get
    public Representation mainProxy(){
    	/***
    	 * Without parameters, home returns a Json that contains current Version of API
    	 * This method is intended to be like a mock.
    	 */
    	
    	String version = serverResourceContainer.getVersionFromRequest(getReference());
    	
    	JSONObject data  = new JSONObject();    	
    	data.put("version", version);/*Data: version requested. does not mean it exists.*/    	
    	Status status = Status.SUCCESS_OK;/*Status: always success*/
    	    	
        return serverResourceContainer.packResult(status, data, MEDIA_TYPE);            	
    }

}