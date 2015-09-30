package nz.ac.auckland.fitness.services;

import java.sql.SQLException;
import java.util.List;
import java.util.Set;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.UriInfo;

import nz.ac.auckland.fitness.dto.*;
import nz.ac.auckland.fitness.domain.Tag;

/**
 * Service interface for the Fitness application. This interface allows Workouts
 * and Exercises to be created, queried (by id) and updated.
 * 
 * @author Ian Warren
 * 
 */
@Path("/fitness")
public interface FitnessResource {

	/*
	 * WORKOUT SECTION
	 */

	// workouts
	@POST
	@Consumes({"application/xml","application/json"})
	@Path("/workouts")
	Response createWorkout(Workout workoutDTO);

	@GET
	@Path("/workouts/")
	@Produces({"application/xml","application/json"})
	Set<Workout> searchWorkoutByTag(@Context UriInfo uriInfo);

	// workouts/{id}
	@GET
	@Path("/workouts/{id}")
	@Produces({"application/xml","application/json"})
	Workout retrieveWorkout(@PathParam("id") int id);

	@PUT
	@Path("/workouts/{id}")
	@Consumes({"application/xml","application/json"})
	Response updateWorkout(@PathParam("id") int id, Workout workoutDTO);

	@DELETE
	@Path("/workouts/{id}")
	@Consumes({"application/xml","application/json"})
	void removeWorkout(@PathParam("id") int id);
	
	// workouts/{id}/tags
	@GET
	@Path("/workouts/{id}/tags")
	@Produces({"application/xml","application/json"})
	Set<Tag> retrieveWorkoutTags(@PathParam("id") int id);
	
	@POST
	@Path("/workouts/{id}/tags")
	@Consumes({"application/xml","application/json"})
	Response createWorkoutTags(@PathParam("id") int id, Set<Tag> tags);
	
	@PUT
	@Path("/workouts/{id}/tags")
	@Consumes({"application/xml","application/json"})
	Response updateWorkoutTags(@PathParam("id") int id, Set<Tag> tags);

	/*
	 * EXERCISE SECTION
	 */

	// exercises
	@POST
	@Consumes({"application/xml","application/json"})
	@Path("/exercises")
	Response createExercise(Exercise ex);

	// exercises/{id}
	@GET
	@Path("/exercises/{id}")
	@Produces({"application/xml","application/json"})
	Exercise retrieveExercise(@PathParam("id") int id);
	
	@GET
	@Path("/exercises/")
	@Produces({"application/xml","application/json"})
	Set<Exercise> searchExerciseByTag(@Context UriInfo uriInfo);

	@PUT
	@Path("/exercises/{id}")
	@Consumes({"application/xml","application/json"})
	Response updateExercise(@PathParam("id") int id, Exercise exDTO);
	
	@GET
	@Path("/exercises/{id}/tags")
	@Produces({"application/xml","application/json"})
	Set<Tag> retrieveExerciseTags(@PathParam("id") int id);
	
	@POST
	@Path("/exercises/{id}/tags")
	@Consumes({"application/xml","application/json"})
	Response createExerciseTags(@PathParam("id") int id, Set<Tag> tags);
	
	@PUT
	@Path("/exercises/{id}/tags")
	@Consumes({"application/xml","application/json"})
	Response updateExerciseTags(@PathParam("id") int id, Set<Tag> tags);
	
	/*
	 * USER SECTION
	 */
	
	// users
	@POST
	@Path("/users")
	@Consumes({"application/xml","application/json"})
	@Produces({"application/xml","application/json"})
	Response createUser(User u);
	
	// users/{id}
	@GET
	@Path("/users/{id}")
	@Produces({"application/xml","application/json"})
	User retrieveUser(@PathParam("id") int id);


	@DELETE
	@Path("/users/{id}")
	@Consumes({"application/xml","application/json"})
	void removeUser(@PathParam("id") int id);
	
	// users/{id}/records
	@POST
	@Path("/users/{id}/records")
	@Consumes({"application/xml","application/json"})
	@Produces({"application/xml","application/json"})
	Response createWorkoutRecordForUser(@PathParam("id") int id, WorkoutRecord wrDTO);
	
	// users/{id}/records
	@GET
	@Path("/users/{id}/records")
	@Produces({"application/xml","application/json"})
	void retrieveWorkoutRecordsForUser(@PathParam("id") int id, @Suspended AsyncResponse response);
	
	// users/{id}/records/{rid}
	@GET
	@Path("/users/{id}/records/{rid}")
	@Produces({"application/xml","application/json"})
	WorkoutRecord retrieveWorkoutRecords(@PathParam("id") int id, @PathParam("rid") int rid);
	
	// cleardb
	@GET
	@Path("/cleardb")
	void clearDatabase() throws SQLException;
}
