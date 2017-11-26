package com.crvl.restapi.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.restlet.Component;
import org.restlet.data.Reference;

import com.crvl.restapi.server.container.ServerResourceContainerV0;

public class ServerResourceContainerTest {
	
	private Reference reference;
	private String URL1 = "localhost:8082/v/1";
	private String URL2 = "localhost:8082";
	private String URL3 = "localhost:8082/";
	private String URL4 = "localhost:8082/v";
	private String URL5 = "localhost:8082/v/3";
	
	private ServerResourceContainerV0 src0;
	private Component component;
	@Before 
	public void setUp() throws Exception {		
		component = new Component();
		src0 = new ServerResourceContainerV0(component);
		
	}

	@Test
	public void testGetVersionFromURL1() {
		reference = new Reference(URL1);
		assertEquals("1", src0.getVersionFromRequest(reference));
	}

	@Test
	public void testGetVersionFromURL2() {
		reference = new Reference(URL2);
		assertEquals("1", src0.getVersionFromRequest(reference));
	}
	
	@Test
	public void testGetVersionFromURL3() {
		reference = new Reference(URL3);
		assertEquals("1", src0.getVersionFromRequest(reference));
	}
	
	@Test
	public void testGetVersionFromURL4() {
		reference = new Reference(URL4);
		assertEquals("1", src0.getVersionFromRequest(reference));
	}
	
	@Test
	public void testGetVersionFromURL5() {
		reference = new Reference(URL5);
		assertEquals("3", src0.getVersionFromRequest(reference));
	}

}
