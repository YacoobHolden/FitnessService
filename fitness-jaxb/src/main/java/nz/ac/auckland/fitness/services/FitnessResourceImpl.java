package nz.ac.auckland.fitness.services;

import java.io.InputStream;
import java.net.URI;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;

import nz.ac.auckland.fitness.domain.*;
import nz.ac.auckland.fitness.dto.Exercise;
import nz.ac.auckland.fitness.dto.User;
import nz.ac.auckland.fitness.dto.Workout;
import nz.ac.auckland.fitness.dto.WorkoutRecord;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FitnessResourceImpl implements FitnessResource{
	
	// Setup a Logger.
	private static Logger _logger = LoggerFactory.getLogger(FitnessResourceImpl.class);

	@Override
	public Response createWorkout(Workout Workout) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public GenericType<List<Workout>> searchWorkoutByTag(UriInfo uriInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Workout retrieveWorkout(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateWorkout(int id, Workout workout) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void removeWorkout(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GenericType<List<Tag>> retrieveWorkoutTags(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createWorkoutTags(int id, GenericType<List<Tag>> tags) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateWorkoutTags(int id, GenericType<List<Tag>> tags) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Response createExercise(Exercise ex) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Exercise retrieveExercise(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateExercise(int id, Exercise ex) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GenericType<List<Exercise>> retrieveExerciseTags(UriInfo uriInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void createExerciseTags(int id, GenericType<List<Tag>> tags) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateExerciseTags(int id, GenericType<List<Tag>> tags) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void createUser(User u) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public User retrieveUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeUser(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GenericType<List<WorkoutRecord>> retrieveWorkoutRecords(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WorkoutRecord subscribeToWorkoutRecords(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public StreamingOutput retrieveWorkoutRecord(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public WorkoutRecord retrieveWorkoutRecords(int id, int rid) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
