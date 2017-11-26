package com.crvl.restapi.server.container;

import org.restlet.Component;
import com.crvl.restapi.server.resource.*;

public class ServerResourceContainerV1 extends ServerResourceContainer {

	public ServerResourceContainerV1(Component component){
		component.getDefaultHost().attach("/v/1/surveys", SurveysResource.class);
		component.getDefaultHost().attach("/v/1/survey/{id_survey}", SurveyResource.class);
	}
	
	public String getVersion() {
		return "1";
	}
	
}
