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
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;

import nz.ac.auckland.fitness.domain.Tag;
import nz.ac.auckland.fitness.dto.DistanceExercise;
import nz.ac.auckland.fitness.dto.Exercise;
import nz.ac.auckland.fitness.dto.User;
import nz.ac.auckland.fitness.dto.Workout;
import nz.ac.auckland.fitness.dto.WorkoutRecord;

import org.joda.time.Duration;
import org.joda.time.LocalDate;
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
		// Clear db
		_client.target(WEB_SERVICE_URI+"/cleardb").request().get();
	}
	
	@AfterClass
	public static void teardownClient() {
		// Clear db
		_client.target(WEB_SERVICE_URI+"/cleardb").request().get();
		_client.close();
	}
	
	@Test
	public void testExercise() {
		// Random name
		Double nameNum = Math.random()*10000;
		String name = nameNum.toString();
		Exercise run = new DistanceExercise(name, "Units in KM",5.0);

		// TEST POST
		Response response = _client
				.target(WEB_SERVICE_URI+"/exercises").request()
				.post(Entity.xml(run));
		if (response.getStatus() != 201) {
			fail("Failed to create new Exercise");
		}

		String location = response.getLocation().toString();
		response.close();
		
		// TEST GET
		Exercise exFromService = null;
		exFromService = (DistanceExercise)_client.target(location).request().get(Exercise.class);
		assertEquals(run, exFromService);
		
		// TEST PUT
		exFromService.set_name("New Namer");
		Response response2 = _client
				.target(WEB_SERVICE_URI+"/exercises/" + exFromService.get_id()).request()
				.put(Entity.xml(exFromService));
		if (response2.getStatus() != 204) {
			fail("Failed to put existing Exercise");
		}
		response2.close();
		
		// TEST POST TAGS
		Set<Tag> tags = new HashSet<Tag>();
		Tag t1 = new Tag("Hardcore");
		Tag t2 = new Tag("Running");
		tags.add(t1);
		tags.add(t2);
		// Wrap as generic entity
		GenericEntity<Set<Tag>> tagEntity = new GenericEntity<Set<Tag>>(tags) { };
		Response response3 = _client
				.target(WEB_SERVICE_URI+"/exercises/" + exFromService.get_id() +"/tags").request()
				.post(Entity.xml(tagEntity));
		if (response3.getStatus() != 204) {
			fail("Failed to post new tags");
		}
		response3.close();
		
		// TEST SEARCH BY TAGS
		Set<Exercise> exerciseList = _client
				.target(WEB_SERVICE_URI+"/exercises?tag=Running&tag=Hardcore").request()
				.get( new GenericType<Set<Exercise>>(){});
		assertEquals(exFromService,(Exercise)exerciseList.toArray()[0]);

	}
	
	@Test
	public void testWorkout() {
		Double nameNum = Math.random()*10000;
		String name = nameNum.toString();
		Exercise runner = new DistanceExercise(name, "Units in KM",5.0);

		// TEST POST
		Response response = _client
				.target(WEB_SERVICE_URI+"/exercises").request()
				.post(Entity.xml(runner));
		if (response.getStatus() != 201) {
			fail("Failed to create new Exercise");
		}

		String location = response.getLocation().toString();
		response.close();
		
		// TEST GET
		Exercise run = null;
		run = (DistanceExercise)_client.target(location).request().get(Exercise.class);
				
		Set<Exercise> exSet = new HashSet<Exercise>();
		exSet.add(run);
		Workout wo = new Workout(name, "Running Workout", exSet);

		// TEST POST
		Response response2 = _client
				.target(WEB_SERVICE_URI+"/workouts").request()
				.post(Entity.xml(wo));
		if (response2.getStatus() != 201) {
			fail("Failed to create new Workout");
		}

		String location2 = response2.getLocation().toString();
		response2.close();
		
		// TEST GET
		Workout woFromService = null;
		woFromService = (Workout)_client.target(location2).request().get(Workout.class);
		assertEquals(wo, woFromService);
		
		// TEST PUT
		woFromService.set_name("New Namer");
		Response response3 = _client
				.target(WEB_SERVICE_URI+"/workouts/" + woFromService.get_id()).request()
				.put(Entity.xml(woFromService));
		if (response3.getStatus() != 204) {
			fail("Failed to put existing Workout");
		}
		response3.close();
		
		// TEST POST TAGS
		Set<Tag> tags = new HashSet<Tag>();
		Tag t1 = new Tag("Hardcore");
		Tag t2 = new Tag("Running");
		tags.add(t1);
		tags.add(t2);
		// Wrap as generic entity
		GenericEntity<Set<Tag>> tagEntity = new GenericEntity<Set<Tag>>(tags) { };
		Response response4 = _client
				.target(WEB_SERVICE_URI+"/workouts/" + woFromService.get_id() +"/tags").request()
				.post(Entity.xml(tagEntity));
		if (response4.getStatus() != 204) {
			fail("Failed to post new tags");
		}
		response4.close();
		
		// TEST SEARCH BY TAGS
		Set<Workout> workoutList = _client
				.target(WEB_SERVICE_URI+"/workouts?tag=Running&tag=Hardcore").request()
				.get( new GenericType<Set<Workout>>(){});
		Workout wo2 = (Workout)workoutList.toArray()[0];
		assertEquals(woFromService, (Workout)workoutList.toArray()[0]);
		
		// TEST REMOVE
		Response response5 = _client.target(WEB_SERVICE_URI+"/workouts/"+woFromService.get_id()).request()
				.delete();
		if (response5.getStatus() != 204) {
			fail("Failed to delete workout");
		}
		response5.close();
	}
	
	@Test
	public void testUser() {
		// Create user
		User user = new User("John Andrews",null);
		
		// TEST POST
		Response response = _client
				.target(WEB_SERVICE_URI+"/users").request()
				.post(Entity.xml(user));
		if (response.getStatus() != 201) {
			fail("Failed to create new User");
		}

		String location = response.getLocation().toString();
		response.close();
		
		// TEST GET
		User uFromService = null;
		uFromService = (User)_client.target(location).request().get(User.class);
		assertEquals(user, uFromService);

		// GET WORKOUT SETUP
		Double nameNum = Math.random()*10000;
		String name = nameNum.toString();
		Exercise runner = new DistanceExercise(name, "Units in KM",5.0);
		Response response2 = _client
				.target(WEB_SERVICE_URI+"/exercises").request()
				.post(Entity.xml(runner));
		if (response2.getStatus() != 201) {
			fail("Failed to create new Exercise");
		}
		response2.close();
		String location2 = response2.getLocation().toString();
		Exercise run = null;
		run = (DistanceExercise)_client.target(location2).request().get(Exercise.class);
		Set<Exercise> exSet = new HashSet<Exercise>();
		exSet.add(run);
		Workout wo = new Workout(name, "Running Workout v2", exSet);
		Response response3 = _client
				.target(WEB_SERVICE_URI+"/workouts").request()
				.post(Entity.xml(wo));
		if (response3.getStatus() != 201) {
			fail("Failed to create new Workout");
		}
		response3.close();
		String location3 = response3.getLocation().toString();
		Workout woFromService = null;
		woFromService = (Workout)_client.target(location3).request().get(Workout.class);
		
		// TEST RECORD POST
		WorkoutRecord wr = new WorkoutRecord(uFromService.get_id(), woFromService.get_id(), new LocalDate(2015, 7, 18), Duration.ZERO);
		Response response4 = _client
				.target(WEB_SERVICE_URI+"/users/"+uFromService.get_id()+"/records").request()
				.post(Entity.xml(wr));
		if (response4.getStatus() != 201) {
			fail("Failed to create new WorkoutRecord");
		}
		response4.close();
		String location4 = response4.getLocation().toString();
		_logger.error(location4);
		
		// TEST RECORD GET
		WorkoutRecord wrFromService = null;
		wrFromService = (WorkoutRecord)_client.target(location4).request().get(WorkoutRecord.class);
		assertEquals(wr, wrFromService);
		
		// TEST REMOVE USER AND ASSOCIATED RECORDS
		_client.target(WEB_SERVICE_URI+"/users/"+uFromService.get_id()).request().delete(WorkoutRecord.class);
		try{
			User uFromService2 = null;
			uFromService2 = (User)_client.target(location).request().get(User.class);
			fail("Failed to delete user");
		} catch (WebApplicationException we){
			// Do nothing as it was successful
		}
		
		try{
			WorkoutRecord wrFromService2 = null;
			wrFromService2 = (WorkoutRecord)_client.target(location4).request().get(WorkoutRecord.class);
			fail("Failed to delete associated record");
		} catch (WebApplicationException we){
			// Do nothing as it was successful
		}
		
	}
}
