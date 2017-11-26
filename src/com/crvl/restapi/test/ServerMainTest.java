package com.crvl.restapi.test;
import org.restlet.Server;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.restlet.data.Protocol;
import org.restlet.representation.Representation;

import com.crvl.restapi.main.ServerMain;

public class ServerMainTest {
	ServerMain serverMain;
	Server server;

	@Before
	public void setUp() throws Exception {
		serverMain = new ServerMain();
		server = new Server(Protocol.HTTP, 8082);
	}

	@Test
	public void testMain() {		
		assert(serverMain != null);
	}

	@Test
	public void testGetPortArgs() {
		String[] args = new String[1];
		args[0] = "8089";
		assertEquals(8089, serverMain.getPort(args));
	}
	
	@Test
	public void testGetPortNullArgs() {
		String[] args = null;		
		assertEquals(8082, serverMain.getPort(args));
	}
	
	@Test
	public void testGetPortArgsNotANumber() {
		String[] args = new String[1];
		args[0] = "not_a_number";
		assertEquals(8082, serverMain.getPort(args));
	}

	@Test
	public void testInitComponent() {
		serverMain.initComponent(server);
		
	}

	@Test
	public void testMainProxy() {
		Representation repr = serverMain.mainProxy();
		String res = "{\"result\":{\"version\":\"1\"},\"code\":\"200\",\"message\":\"\",\"status\":\"ok\"}";
		assertEquals(res, repr.toString());
	}


	 
}
