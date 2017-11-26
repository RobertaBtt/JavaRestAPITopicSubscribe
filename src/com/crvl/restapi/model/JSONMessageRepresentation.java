package com.crvl.restapi.model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;

import org.json.JSONArray;
import org.json.JSONObject;
import org.restlet.data.Status;
import org.restlet.representation.Representation;

public class JSONMessageRepresentation extends Representation {
	
	private JSONObject jsonObjectResponse;
	private JSONArray jsonArrayResponse;
	
	public JSONMessageRepresentation(Status status, JSONObject jsonObject) {
		
		jsonObjectResponse = new JSONObject();
        
		jsonObjectResponse.put("status", status.getDescription());
		jsonObjectResponse.put("code", status.getCode());        
		jsonObjectResponse.put("data", jsonObject);
		jsonObjectResponse.put("type", "1"); //1 for JSONObj, 2 for Array 
	}
	
	public JSONObject getJsonObjectResponse(){
		return this.jsonObjectResponse;
	}
	public JSONMessageRepresentation(Status status, JSONArray jsonArray) {
		JSONObject testJson = new JSONObject();
		testJson.put("status", status.toString());
	}
	@Override
	public ReadableByteChannel getChannel() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reader getReader() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void write(Writer arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(WritableByteChannel arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void write(OutputStream arg0) throws IOException {
		// TODO Auto-generated method stub
		
	}

}
