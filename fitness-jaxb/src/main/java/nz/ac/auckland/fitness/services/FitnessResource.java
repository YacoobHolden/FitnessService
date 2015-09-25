package nz.ac.auckland.fitness.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
	@Consumes("application/xml")
	@Path("/workouts")
	Response createWorkout(Workout workoutDTO);

	@GET
	@Path("/workouts/")
	@Produces("application/xml")
	GenericType<List<Workout>> searchWorkoutByTag(@Context UriInfo uriInfo);

	// workouts/{id}
	@GET
	@Path("/workouts/{id}")
	@Produces("application/xml")
	Workout retrieveWorkout(@PathParam("id") int id);

	@PUT
	@Path("/workouts/{id}")
	@Consumes("application/xml")
	Response updateWorkout(@PathParam("id") int id, Workout workoutDTO);

	@DELETE
	@Path("/workouts/{id}")
	@Consumes("application/xml")
	void removeWorkout(@PathParam("id") int id);
	
	// workouts/{id}/tags
	@GET
	@Path("/workouts/{id}/tags")
	@Produces("application/xml")
	GenericType<List<Tag>> retrieveWorkoutTags(@PathParam("id") int id);
	
	@POST
	@Path("/workouts/{id}/tags")
	@Consumes("application/xml")
	Response createWorkoutTags(@PathParam("id") int id, GenericType<List<Tag>> tags);
	
	@PUT
	@Path("/workouts/{id}/tags")
	@Consumes("application/xml")
	Response updateWorkoutTags(@PathParam("id") int id, GenericType<List<Tag>> tags);

	/*
	 * EXERCISE SECTION
	 */

	// exercises
	@POST
	@Consumes("application/xml")
	@Path("/exercises")
	Response createExercise(Exercise ex);

	// exercises/{id}
	@GET
	@Path("/exercises/{id}")
	@Produces("application/xml")
	Exercise retrieveExercise(@PathParam("id") int id);

	@PUT
	@Path("/exercises/{id}")
	@Consumes("application/xml")
	Response updateExercise(@PathParam("id") int id, Exercise exDTO);
	
	// workouts/{id}/tags
	@GET
	@Path("/exercises/{id}/tags")
	@Produces("application/xml")
	GenericType<List<Exercise>> retrieveExerciseTags(@Context UriInfo uriInfo);
	
	@POST
	@Path("/exercises/{id}/tags")
	@Consumes("application/xml")
	Response createExerciseTags(@PathParam("id") int id, GenericType<List<Tag>> tags);
	
	@PUT
	@Path("/exercises/{id}/tags")
	@Consumes("application/xml")
	Response updateExerciseTags(@PathParam("id") int id, GenericType<List<Tag>> tags);
	
	/*
	 * USER SECTION
	 */
	
	// users
	@POST
	@Path("/users")
	@Consumes("application/xml")
	@Produces("application/xml")
	Response createUser(User u);
	
	// users/{id}
	@GET
	@Path("/users/{id}")
	@Produces("application/xml")
	User retrieveUser(@PathParam("id") int id);


	@DELETE
	@Path("/users/{id}")
	@Consumes("application/xml")
	void removeUser(@PathParam("id") int id);
	
	// users/{id}/records
	@GET
	@Path("/users/{id}/records")
	@Produces("application/xml")
	GenericType<List<WorkoutRecord>> retrieveWorkoutRecords(@PathParam("id") int id);
	
	// users/{id}/records
	@GET
	@Path("/users/{id}/records/subscribe")
	@Produces("application/xml")
	WorkoutRecord subscribeToWorkoutRecords(@PathParam("id") int id);
	
	@POST
	@Path("/users/{id}/records")
	@Produces("application/xml")
	WorkoutRecord retrieveWorkoutRecord(@PathParam("id") int id);
	
	// users/{id}/records/{rid}
	@GET
	@Path("/users/{id}/records/{rid}")
	@Produces("application/xml")
	WorkoutRecord retrieveWorkoutRecords(@PathParam("id") int id, @PathParam("rid") int rid);
	
}
