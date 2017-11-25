package com.crvl.restapi.server.container;

import org.restlet.Component;
import com.crvl.restapi.server.resource.*;

public class ServerResourceContainerV0 implements IServerResourceContainer {

	public ServerResourceContainerV0(Component component){
		component.getDefaultHost().attach("/v/0/surveys", CurrentTimeResource.class);
		component.getDefaultHost().attach("/v/0/survey/{id_survey}", CurrentTimeResource.class);
	}
	
	public String getVersion() {
		return "0";
	}
	
}
