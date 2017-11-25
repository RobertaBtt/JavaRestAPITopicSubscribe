package com.crvl.restapi.server.resource;

import org.restlet.data.MediaType;
import org.restlet.representation.Representation;
import org.restlet.representation.StringRepresentation;
import org.restlet.resource.Get;
import org.restlet.resource.ServerResource;
import org.json.JSONArray;
import org.json.JSONObject;

public class VersionResource extends ServerResource {
	
	
    @Get 
    public Representation getVersion() {

        
        JSONObject testJson = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        
        testJson.put("status", "ok");
        testJson.put("code", "200");
        testJson.put("message", "List of [TODO]");
        testJson.put("result", jsonArray );        
        
        Representation result = new StringRepresentation(testJson.toString());
        //Representation result = new StringRepresentation(resultString);
        result.setMediaType(MediaType.APPLICATION_JSON);        
        
        return result;
    }
}