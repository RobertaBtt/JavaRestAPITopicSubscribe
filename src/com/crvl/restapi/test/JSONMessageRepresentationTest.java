package com.crvl.restapi.test;


import org.json.JSONObject;
import org.junit.Before;
import org.junit.Test;
import org.restlet.data.Status;

import com.crvl.restapi.model.JSONMessageRepresentation;

public class JSONMessageRepresentationTest {

	private JSONMessageRepresentation jsonMsgRepresentation;
	private Status status = Status.SUCCESS_OK;
	@Before
	public void setUp() throws Exception {
		
		jsonMsgRepresentation = new JSONMessageRepresentation(status, new JSONObject().put("key", "value"));
		
	}

	@Test
	public void testGetJsonObjectResponse() {
		assert(jsonMsgRepresentation.getJsonObjectResponse()!= null);
	}


}
