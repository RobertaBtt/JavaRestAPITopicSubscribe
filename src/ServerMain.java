import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.Server;

import org.restlet.resource.ServerResource;

public class ServerMain extends ServerResource {

   
    public static void main(String...args) throws Exception {
    	Component component = new Component();
    	
    	Server server = new Server(Protocol.HTTP, 8084, CurrentTimeResource.class); 
    	
    	component.getServers().add(server);
    	component.getDefaultHost().attach("/test", ServerMain.class);
    	component.getDefaultHost().attach("/time", CurrentTimeResource.class);
    	component.getDefaultHost().attach("/time/{param}", CurrentTimeResource.class);
    	component.start();


    }


}