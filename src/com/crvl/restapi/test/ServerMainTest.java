package com.crvl.restapi.test;
import org.restlet.Component;
import org.restlet.Server;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.restlet.data.Protocol;

import com.crvl.restapi.main.ServerMain;
import com.crvl.restapi.server.container.ServerResourceContainer;

public class ServerMainTest {
	ServerMain serverMain;
	Server server;
	private Component component;
	private ServerResourceContainer src;
	private final int PORT = 8082;
	
	@Before
	public void setUp() throws Exception {
		serverMain = new ServerMain();
		server = new Server(Protocol.HTTP, PORT);
		
		component = new Component();
		component.getServers().add(server);
		
		src = ServerResourceContainer.getInstance();
	}

	@Test
	public void testMain() {		
		assert(serverMain != null);
	}

	@Test
	public void testGetPortArgs() {
		String[] args = new String[1];
		args[0] = "8082";
		assertEquals(PORT, serverMain.getPort(args));
	}
	
	@Test
	public void testGetPortNullArgs() {
		String[] args = null;		
		assertEquals(PORT, serverMain.getPort(args));
	}
	
	@Test
	public void testGetPortArgsNotANumber() {
		String[] args = new String[1];
		args[0] = "not_a_number";
		assertEquals(PORT, serverMain.getPort(args));
	}

	@Test
	public void testInitComponent() {
		serverMain.initComponent(server);
		
	}

//	@Test
//	public void testMainProxy() {
//		try {
//			component.start();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		Representation repr = serverMain.mainProxy();
//		String res = "{\"result\":{\"version\":\"1\"},\"code\":\"200\",\"message\":\"\",\"status\":\"ok\"}";
//		assertEquals(res, repr.toString());
//	}
//
//
	 
}
