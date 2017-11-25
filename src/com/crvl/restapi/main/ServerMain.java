package com.crvl.restapi.main;
import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.data.Reference;
import org.restlet.Server;

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
    	
    	component.start();


    }
    
    @Get
    public void mainProxy(){
    	
    	String version = getVersion(getReference());
    	
    	if (version.equals("0")) 
    		{new ServerResourceContainerV0(component);
    		System.out.print("Versione trovata: 0");}
    	
    	else if(version.equals("1")) 
    		{ System.out.print("Versione trovata: 1");
    		new ServerResourceContainerV1(component);}    	
    	
    	else 		
    		// Default Behavior: get the most recent API version
    		new ServerResourceContainerV1(component);    	
    }
    

    
    public String getVersion(Reference reference){
    	String url = reference.toString();
    	
    	String version = url.substring(url.indexOf("/v/")+3, url.indexOf("/v/")+4);
    	System.out.println("Version: "+version);
    	return version;
    }


}