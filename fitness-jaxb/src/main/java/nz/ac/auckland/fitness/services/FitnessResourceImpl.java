package nz.ac.auckland.fitness.services;

import java.io.InputStream;
import java.net.URI;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.StreamingOutput;
import javax.ws.rs.core.UriInfo;

import nz.ac.auckland.fitness.domain.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FitnessResourceImpl implements FitnessResource{
	
	// Setup a Logger.
	private static Logger _logger = LoggerFactory.getLogger(FitnessResourceImpl.class);

	@Override
	public Response createWorkout(nz.ac.auckland.fitness.dto.Workout workoutDTO) {
		// First, map to domain model and log
		Workout wo = WorkoutMapper.toDomainModel(workoutDTO);
		_logger.debug("Read workout: " + wo.toString());
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
			
		// Check it exists
		if (!checkWorkout(em,wo)){
			// Then persist the workout in the database.
			em.getTransaction().begin();
			em.persist(wo);
			em.getTransaction().commit();
			
			return Response.created(URI.create("/fitness/workouts/" + wo.get_id()))
					.build();
		}
		else {
			// If it exists, inform user
			throw new WebApplicationException(403);
		}
	}

	@Override
	public GenericType<List<nz.ac.auckland.fitness.dto.Workout>> searchWorkoutByTag(UriInfo uriInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public nz.ac.auckland.fitness.dto.Workout retrieveWorkout(int id) {
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
		Workout wo = null;
		try {
			_logger.debug("Querying the database for the workout of id "+id);
			TypedQuery<Workout> query = em.createQuery(
				"select wo from Workout wo where wo._id = :id", Workout.class
				).setParameter("id", id);
			wo = query.getSingleResult();
		} catch(NoResultException e) {
			// Workout doesn't exist in the database
			throw new WebApplicationException(404);
		}
		return WorkoutMapper.toDto(wo);
	}

	@Override
	public Response updateWorkout(int id, nz.ac.auckland.fitness.dto.Workout workoutDTO) {
		// TODO Auto-generated method stub
		return null;
		
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
	public Response createWorkoutTags(int id, GenericType<List<Tag>> tags) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateWorkoutTags(int id, GenericType<List<Tag>> tags) {
		// TODO Auto-generated method stub
		return null;
		
	}

	@Override
	public Response createExercise(nz.ac.auckland.fitness.dto.Exercise exDTO) {
		// First, map to domain model and log
		Exercise ex = ExerciseMapper.toDomainModel(exDTO);
		_logger.debug("Read exercise: " + ex.toString());
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
		// Check it exists
		if (!checkExercise(em,ex)){
			// Then persist the exercise in the database.
			em.getTransaction().begin();
			em.persist(ex);
			em.getTransaction().commit();
			
			return Response.created(URI.create("/fitness/exercises/" + ex.get_id()))
					.build();
		}
		else {
			// If it exists, inform user
			throw new WebApplicationException(403);
		}
	}

	@Override
	public nz.ac.auckland.fitness.dto.Exercise retrieveExercise(int id) {
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
		Exercise ex = null;
		try {
			_logger.debug("Querying the database for the exercise of id "+id);
			TypedQuery<Exercise> query = em.createQuery(
				"select ex from Exercise ex where ex._id = :id", Exercise.class
				).setParameter("id", id);
			ex = query.getSingleResult();
		} catch(NoResultException e) {
			// Exercise doesn't exist in the database
			throw new WebApplicationException("Could not find exercise with id: "+id,404);
		}
		return ExerciseMapper.toDto(ex);
	}

	@Override
	public Response updateExercise(int id, nz.ac.auckland.fitness.dto.Exercise exDTO) {
		// First, map to domain model and log
		Exercise exNew = ExerciseMapper.toDomainModel(exDTO);
		_logger.debug("Read exercise: " + exNew.toString());
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
		Exercise exOld = null;
		try {
			_logger.debug("Querying the database for the exercise of id "+id);
			TypedQuery<Exercise> query = em.createQuery(
				"select ex from Exercise ex where ex._id = :id", Exercise.class
				).setParameter("id", id);
			// If we can find old result, persist new one
			exOld = query.getSingleResult();
			em.getTransaction().begin();
			exOld = em.merge(exNew);
			em.getTransaction().commit();
			return Response.status(204).build();
		} catch(NoResultException e) {
			// Exercise doesn't exist in the database
			throw new WebApplicationException("Could not find exercise with id: "+id,404);
		}
	}

	@Override
	public GenericType<List<nz.ac.auckland.fitness.dto.Exercise>> retrieveExerciseTags(UriInfo uriInfo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response createExerciseTags(int id, GenericType<List<Tag>> tags) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response updateExerciseTags(int id, GenericType<List<Tag>> tags) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Response createUser(nz.ac.auckland.fitness.dto.User uDTO) {
		// First, map to domain model and log
		User u = UserMapper.toDomainModel(uDTO);
		_logger.debug("Read user: " + u.toString());
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
			
		// Check it exists
		if (!checkUser(em,u)){
			// Then persist the exercise in the database.
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
			
			return Response.created(URI.create("/users/" + u.get_id()))
					.build();
		}
		else {
			// If it exists, inform user
			throw new WebApplicationException(403);
		}
		
	}

	@Override
	public nz.ac.auckland.fitness.dto.User retrieveUser(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeUser(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public GenericType<List<nz.ac.auckland.fitness.dto.WorkoutRecord>> retrieveWorkoutRecords(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public nz.ac.auckland.fitness.dto.WorkoutRecord subscribeToWorkoutRecords(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public nz.ac.auckland.fitness.dto.WorkoutRecord retrieveWorkoutRecord(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public nz.ac.auckland.fitness.dto.WorkoutRecord retrieveWorkoutRecords(int id, int rid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * HELPER METHODS
	 */
	// Checks if an exercise exists already.
	protected boolean checkExercise(EntityManager em, Exercise ex) {
		try {
			_logger.debug("Querying the database for the exercise of name "+ex.get_name());
			TypedQuery<Exercise> query = em.createQuery(
				"select ex from Exercise ex where ex._name = :name", Exercise.class
				).setParameter("name", ex.get_name());
			ex = query.getSingleResult();
		} catch(NoResultException e) {
			// Exercise doesn't exist in the database
			return false;
		}
		return true;
	}
	
	// Checks if a workout exists already.
	protected boolean checkWorkout(EntityManager em, Workout wo) {
		try {
			_logger.debug("Querying the database for the workout of name "+wo.get_name());
			TypedQuery<Workout> query = em.createQuery(
				"select wo from Workout wo where wo._name = :name", Workout.class
				).setParameter("name", wo.get_name());
		} catch(NoResultException e) {
			// Workout doesn't exist in the database
			return false;
		}
		return true;
	}
	
	// Checks if a user exists already.
	protected boolean checkUser(EntityManager em,User u) {
		try {
			_logger.debug("Querying the database for the user of name "+u.get_name());
			TypedQuery<User> query = em.createQuery(
				"select u from User u where u._name = :name", User.class
				).setParameter("name", u.get_name());
		} catch(NoResultException e) {
			// User doesn't exist in the database
			return false;
		}
		return true;
	}

	
}
