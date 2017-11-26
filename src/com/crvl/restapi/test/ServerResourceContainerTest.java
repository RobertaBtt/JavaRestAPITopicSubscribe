package com.crvl.restapi.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.restlet.Component;
import org.restlet.data.Reference;

import com.crvl.restapi.server.container.APIResourceVersion0;
import com.crvl.restapi.server.container.ServerResourceContainer;

public class ServerResourceContainerTest {
	
	private Reference reference;
	private String URL1 = "http://localhost:8082/v/1";
	private String URL2 = "http://localhost:8082";
	private String URL3 = "http://localhost:8082/";
	private String URL4 = "http://localhost:8082/v";
	private String URL5 = "http://localhost:8082/v/3";
	
	private Component component;
	private ServerResourceContainer src;
	private APIResourceVersion0 apiVersion0;
	
	
	@Before 
	public void setUp() throws Exception {		
		component = new Component();
		src = ServerResourceContainer.getInstance();
		apiVersion0 = new APIResourceVersion0(component);
		src.addAPIResource(apiVersion0);
		
	}

	@Test
	public void testGetVersionFromURL1() {
		reference = new Reference(URL1);
		assertEquals("1", src.getVersionFromRequest(reference));
	}

	@Test
	public void testGetVersionFromURL2() {
		reference = new Reference(URL2);
		assertEquals("1", src.getVersionFromRequest(reference));
	}
	
	@Test
	public void testGetVersionFromURL3() {
		reference = new Reference(URL3);
		assertEquals("1", src.getVersionFromRequest(reference));
	}
	
	@Test
	public void testGetVersionFromURL4() {
		reference = new Reference(URL4);
		assertEquals("1", src.getVersionFromRequest(reference));
	}
	
	@Test
	public void testGetVersionFromURL5() {
		reference = new Reference(URL5);
		assertEquals("3", src.getVersionFromRequest(reference));
	}

}
