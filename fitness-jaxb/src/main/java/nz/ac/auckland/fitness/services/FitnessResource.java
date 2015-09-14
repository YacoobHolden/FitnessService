package nz.ac.auckland.fitness.services;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

/**
 * Service interface for the Fitness application. This interface allows 
 * Workouts and Exercises to be created, queried (by name) and updated.
 * 
 * @author Ian Warren
 *
 */
@Path("/fitness")
public interface FitnessResource {

	/*
	 * WORKOUT SECTION
	 */
	
   @POST
   @Consumes("application/xml")
   @Path("/workouts")
   Response createWorkout(nz.ac.auckland.fitness.dto.Workout Workout);


   @GET
   @Path("/workouts/{name}")
   @Produces("application/xml")
   StreamingOutput retrieveWorkout(@PathParam("name") String name);

 
   @PUT
   @Path("/workouts/{name}")
   @Consumes("application/xml")
   void updateWorkout(@PathParam("name") String name, InputStream is);
   
   /*
	* EXERCISE SECTION
	*/
	
  @POST
  @Consumes("application/xml")
  @Path("/exercises")
  Response createExercise(InputStream is);


  @GET
  @Path("/exercises/{name}")
  @Produces("application/xml")
  StreamingOutput retrieveExercise(@PathParam("name") String name);


  @PUT
  @Path("/exercises/{name}")
  @Consumes("application/xml")
  void updateExercise(@PathParam("name") String name, InputStream is);
}
