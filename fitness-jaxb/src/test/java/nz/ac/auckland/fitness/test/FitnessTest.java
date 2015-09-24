package nz.ac.auckland.fitness.test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import nz.ac.auckland.fitness.dto.DistanceExercise;
import nz.ac.auckland.fitness.dto.Exercise;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FitnessTest {
	// JDBC connection to the database.
	private static Connection _jdbcConnection = null;
	
	private static final String WEB_SERVICE_URI = "http://localhost:10001/services/fitness";
	
	private static Logger _logger = LoggerFactory.getLogger(FitnessTest.class);

	private static Client _client;

	/**
	 * One-time setup method that creates a Web service client.
	 */
	@BeforeClass
	public static void setUpClient() throws ClassNotFoundException, SQLException{
		_client = ClientBuilder.newClient();
	}
	
	@AfterClass
	public static void teardownClient() {
		_client.close();
	}
	
	@Test
	public void testsPass() {}
	
	@Test
	public void addExercise() {
		Exercise run = new DistanceExercise("Magicallys", "Units in KM",5.0);

		Response response = _client
				.target(WEB_SERVICE_URI+"/exercises").request()
				.post(Entity.xml(run));
		if (response.getStatus() != 201) {
			fail("Failed to create new Exercise");
		}

		String location = response.getLocation().toString();
		response.close();
		
		Exercise exFromService = null;
		exFromService = _client.target(location).request().get(Exercise.class);

		assertEquals(run.get_name(), exFromService.get_name());
	}
	   
}
