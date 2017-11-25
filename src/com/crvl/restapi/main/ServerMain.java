package com.crvl.restapi.main;
import org.json.JSONObject;
import org.restlet.Component;
import org.restlet.data.MediaType;
import org.restlet.data.Protocol;
import org.restlet.data.Reference;
import org.restlet.Server;

import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;

import com.crvl.restapi.server.container.ServerResourceContainerV0;
import com.crvl.restapi.server.container.ServerResourceContainerV1;

public class ServerMain extends ServerResource {

    private static Component component;
    public static void main(String[] args) throws Exception {
    	
    	Server server;    	
		server = new Server(Protocol.HTTP, getPort(args));    	
		initComponent(server);
		
    }
    
    public static int getPort(String[] args){
    	int port=8082;
    	
    	if (args.length >0){
    		
    		//Check if parameter is numeric
		 try  { port = Integer.parseInt(args[0]);}  
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
		} catch (Exception e) {System.out.println("Error init restlet component" + e.getMessage());
		}

    	
    }
    
    @Get
    public Representation mainProxy(){
    	
    	String version = getVersion(getReference());
    	
    	JSONObject testJson = new JSONObject();
    	
        testJson.put("status", "ok");
        testJson.put("code", "200");
        testJson.put("message", "");
        
        
        JSONObject jsonVersion = new JSONObject();
        jsonVersion.put("version", version);
        
        testJson.put("result", jsonVersion);
        
        Representation result = new StringRepresentation(testJson.toString());
        
        result.setMediaType(MediaType.APPLICATION_JSON);        
        
        return result;
    
            	
    }
    

    
    public String getVersion(Reference reference){
    	
    	String url = reference.toString();
    	String version;
    	try{
    		version = url.substring(url.indexOf("/v/")+3, url.indexOf("/v/")+4);
    	}
    	catch(StringIndexOutOfBoundsException e){
    		version = "1";
    	}
    	
    	return version;
    }


}