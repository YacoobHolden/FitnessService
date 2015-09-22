package nz.ac.auckland.fitness.services;

import java.io.InputStream;
import java.net.URI;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import nz.ac.auckland.fitness.domain.Exercise;
import nz.ac.auckland.fitness.domain.Workout;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FitnessResourceImpl implements FitnessResource{
	
	// Setup a Logger.
	private static Logger _logger = LoggerFactory.getLogger(FitnessResourceImpl.class);
	// Fake a DB
	private Map<String, Workout> _workoutDB = new ConcurrentHashMap<String, Workout>();
	private Map<String, Exercise> _exDB = new ConcurrentHashMap<String, Exercise>();

	@Override
	public Response createWorkout(nz.ac.auckland.fitness.dto.Workout dtoWorkout) {
		_logger.debug("Read workout: " + dtoWorkout);
		Workout workout = WorkoutMapper.toDomainModel(dtoWorkout);
		_workoutDB.put(workout.get_name(), workout);

		_logger.debug("Created parolee: " + workout);
		return Response.created(URI.create("/workout/" + workout.get_name()))
				.build();
	}

	@Override
	public StreamingOutput retrieveWorkout(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateWorkout(String name, InputStream is) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Response createExercise(nz.ac.auckland.fitness.domain.Exercise ex) {
		_logger.debug("Read exercise:: " + ex);
		_exDB.put(ex.get_name(), ex);

		_logger.debug("Created exercise: " + ex.toString());
		return Response.created(URI.create("/exercise/" + ex.get_name()))
				.build();
	}

	@Override
	public StreamingOutput retrieveExercise(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void updateExercise(String name, InputStream is) {
		// TODO Auto-generated method stub
		
	}

}
