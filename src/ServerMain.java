import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.Server;
import org.restlet.resource.Get;

import org.restlet.resource.ServerResource;

public class ServerMain extends ServerResource {

   
    public static void main(String...args) throws Exception {
    	Component component = new Component();
    	
    	Server server = new Server(Protocol.HTTP, 8084, CurrentTimeResource.class); 
    	
    	component.getServers().add(server);
    	component.getDefaultHost().attach("/test", ServerMain.class);
    	component.getDefaultHost().attach("/time", CurrentTimeResource.class);    	
    	component.start();


    }

    @Get
    public String toStringParam(){
    	return "Resource URI  : " + getReference() + '\n' + "Root URI      : "
                + getRootRef() + '\n' + "Routed part   : "
                + getReference().getBaseRef() + '\n' + "Remaining part: "
                + getReference().getRemainingPart();
    }
    

}