package com.crvl.restapi.server.container;

import org.restlet.Component;
import com.crvl.restapi.server.resource.*;

public class APIResourceVersion0 implements IAPIResource  {

	public String getAPIVersion() { return "0"; }
	
	public APIResourceVersion0(Component component){
		component.getDefaultHost().attach("/v/0/surveys", SurveyResource.class);
		component.getDefaultHost().attach("/v/0/survey/{id_survey}", SurveyResource.class);
	}
	
}
