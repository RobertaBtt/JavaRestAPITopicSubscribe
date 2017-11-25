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
import com.crvl.restapi.server.resource.VersionResource;

public class ServerMain extends ServerResource {

    private static Component component;
    public static void main(String[] args) throws Exception {
    	
    	String port = "";
    	if (args.length >0){
    		port = args[0];
    	}
    	
    	Server server;
    	if (port != ""){
    		server = new Server(Protocol.HTTP, port);
    	}			
    	else
    		server = new Server(Protocol.HTTP, 8084); //default port
    	
		component = new Component();    	
    	component.getServers().add(server);
    	
    	component.getDefaultHost().attach("/", ServerMain.class);
    	component.getDefaultHost().attach("/v/{version}", ServerMain.class);
    	new ServerResourceContainerV0(component);
    	new ServerResourceContainerV1(component);
    	component.start();


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
    	
    	String version = url.substring(url.indexOf("/v/")+3, url.indexOf("/v/")+4);
    	System.out.println("Version: "+version);
    	return version;
    }


}