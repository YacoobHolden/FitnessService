package nz.ac.auckland.fitness.services;

import java.io.InputStream;
import java.net.URI;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;

import nz.ac.auckland.fitness.domain.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FitnessResourceImpl implements FitnessResource{
	
	// Setup a Logger.
	private static Logger _logger = LoggerFactory.getLogger(FitnessResourceImpl.class);

	@Override
	public Response createWorkout(nz.ac.auckland.fitness.dto.Workout dtoWorkout) {
		_logger.debug("Read workout: " + dtoWorkout);
		return null;
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
	public Response createExercise(nz.ac.auckland.fitness.dto.Exercise exDTO) {
		// First, map to domain model and log
		Exercise ex = ExerciseMapper.toDomainModel(exDTO);
		_logger.debug("Read exercise:: " + ex.toString());
		
		// Then persist the exercise in the database.
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
		em.persist(ex);
		em.getTransaction().commit();
		
		return Response.created(URI.create("/exercises/" + ex.get_id()))
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
