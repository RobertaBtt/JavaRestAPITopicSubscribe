import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;


public class CurrentTimeResource extends ServerResource {

    @Get 
    public Representation getTime() {
        long now = System.currentTimeMillis(); 
        String nowstr = String.valueOf(now); 
        Representation result = new StringRepresentation(nowstr);
        return result;
    }
}