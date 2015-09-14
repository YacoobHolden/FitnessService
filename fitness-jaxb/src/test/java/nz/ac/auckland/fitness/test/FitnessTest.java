package nz.ac.auckland.fitness.test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;

import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FitnessTest {
	private static final String WEB_SERVICE_URI = "http://localhost:10000/services/parolees";
	private Logger _logger = LoggerFactory.getLogger(FitnessTest.class);

	private static Client _client;

	/**
	 * One-time setup method that creates a Web service client.
	 */
	@BeforeClass
	public static void setUpClient() {
		_client = ClientBuilder.newClient();
	}
	
	@Test
	public void testsPass() {}
	   
}
