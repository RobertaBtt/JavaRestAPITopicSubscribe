package com.crvl.restapi.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.restlet.representation.Representation;

import com.crvl.restapi.main.ServerMain;

public class ServerMainTest {
	ServerMain serverMain;

	@Before
	public void setUp() throws Exception {
		serverMain = new ServerMain();
	}

	@Test
	public void testMain() {		
		assert(serverMain != null);
	}

	@Test
	public void testGetPort() {
		String[] args = null;
		
		assertEquals(8082, serverMain.getPort(args));
	}

	@Test
	public void testInitComponent() {
		
	}

	@Test
	public void testMainProxy() {
		Representation repr = serverMain.mainProxy();
		String res = "{\"result\":{\"version\":\"1\"},\"code\":\"200\",\"message\":\"\",\"status\":\"ok\"}";
		assertEquals(res, repr.toString());
	}

	@Test
	public void testGetVersion() {
		assertEquals("1", serverMain.getVersion(null));
	}

}
