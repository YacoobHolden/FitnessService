package nz.ac.auckland.fitness.test;

import static org.junit.Assert.*;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import nz.ac.auckland.fitness.dto.DistanceExercise;
import nz.ac.auckland.fitness.dto.Exercise;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FitnessTest {
	private static final String WEB_SERVICE_URI = "http://localhost:10001/services/fitness";
	private Logger _logger = LoggerFactory.getLogger(FitnessTest.class);

	private static Client _client;

	/**
	 * One-time setup method that creates a Web service client.
	 */
	@BeforeClass
	public static void setUpClient() {
		_client = ClientBuilder.newClient();
	}
	
	/**
	 * One-time tear-down method that tears down a Web service client.
	 */
	@AfterClass
	public static void teardownClient() {
		_client.close();
	}
	
	@Test
	public void testsPass() {}
	
	@Test
	public void addExercise() {
		Exercise run = new DistanceExercise("Magical", "Units in KM",5.0);

		Response response = _client
				.target(WEB_SERVICE_URI+"/exercises").request()
				.post(Entity.xml(run));
		if (response.getStatus() != 201) {
			fail("Failed to create new Exercise");
		}

		String location = response.getLocation().toString();
		response.close();
		
		// Query the Web service for the new Exercise.
		Exercise exFromService = _client.target(WEB_SERVICE_URI+"/exercises/2").request().get(Exercise.class);

		assertEquals(run.get_name(), exFromService.get_name());
	}
	   
}
