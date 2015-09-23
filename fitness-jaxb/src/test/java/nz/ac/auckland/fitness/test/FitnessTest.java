package nz.ac.auckland.fitness.test;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import nz.ac.auckland.fitness.dto.*;
import nz.ac.auckland.fitness.services.FitnessResolver;

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
	
	@Test
	public void testsPass() {}
	
	/*
	@Test
	public void addExercise() {
		JAXBContext distExContext = new FitnessResolver().getContext(DistanceExercise.class);
		Marshaller distExMarshaller = null;
		
		try {
			distExMarshaller = distExContext.createMarshaller();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		OutputStream os = new ByteArrayOutputStream();
		
		Exercise run = new DistanceExercise("Run", "Do it",5.0);
		try {
			distExMarshaller.marshal(run, os);
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String runXML = os.toString();

		Response response = _client
				.target(WEB_SERVICE_URI+"/exercises").request()
				.post(Entity.xml(runXML));
		if (response.getStatus() != 201) {
			fail("Failed to create new Exercise");
		}

		String location = response.getLocation().toString();
		response.close();
		
		// Query the Web service for the new Exercise.
		Exercise exFromService = _client.target(location).request()
				.accept("application/xml").get(Exercise.class);

		assertEquals(run.get_name(), exFromService.get_name());

	}*/
	   
}
