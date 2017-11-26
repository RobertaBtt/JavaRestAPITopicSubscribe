package com.crvl.restapi.server.container;

import org.restlet.Component;
import com.crvl.restapi.server.resource.*;

public class APIResourceVersion1 implements IAPIResource {

	public String getAPIVersion() { return "1"; }
	public APIResourceVersion1(Component component){
		component.getDefaultHost().attach("/v/1/surveys", SurveysResource.class);
		component.getDefaultHost().attach("/v/1/survey/{id_survey}", SurveyResource.class);
		component.getDefaultHost().attach("/v/1/subjects", SubjectsResource.class);
		component.getDefaultHost().attach("/v/1/subject/{id_subject}/subscribe", SubscriberResource.class);
		component.getDefaultHost().attach("/v/1/subject/{id_subject}/unsubscribe", UnsubscriberResource.class);
	}
	
	
	
}
