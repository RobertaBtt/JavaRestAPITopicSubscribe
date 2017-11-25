import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.json.JSONObject;

public class CurrentTimeResource extends ServerResource {
	
	
    @Get 
    public Representation getTime() {

   	
    	//Hot to get Attributes from request:
    	//String message = "time \"" + request.getAttributes().get("now") + "\"";
    	     	
        long now = System.currentTimeMillis(); 
        String nowstr = String.valueOf(now); 
        
        
        JSONObject testJson = new JSONObject();
        testJson.put("status", "ok");
        testJson.put("code", "200");
        testJson.put("message", "");
        testJson.put("result", nowstr);
        
        
        Representation result = new StringRepresentation(testJson.toString());
        
        result.setMediaType(MediaType.APPLICATION_JSON);        
        
        return result;
    }
}