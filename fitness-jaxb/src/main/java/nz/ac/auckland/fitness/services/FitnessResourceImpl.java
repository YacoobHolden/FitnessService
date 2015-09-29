package nz.ac.auckland.fitness.services;

import java.io.InputStream;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
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
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
		// First, map to domain model and log
		Workout wo = WorkoutMapper.toDomainModel(workoutDTO);
		_logger.debug("Read workout: " + wo.toString());
		// Check it exists
		if (!checkWorkout(em,wo)){
			em.getTransaction().begin();
			em.persist(wo);
			em.getTransaction().commit();
			em.close();
			return Response.created(URI.create("/fitness/workouts/" + wo.get_id()))
					.build();
		}
		else {
			// If it exists, inform user
			_logger.error("Workout of id:  "+wo.get_id()+" already exists");
			throw new WebApplicationException("Workout of id:  "+wo.get_id()+" already exists",403);
		}
	}

	@Override
	public Set<nz.ac.auckland.fitness.dto.Workout> searchWorkoutByTag(UriInfo uriInfo) {
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
		Set<Workout> resultSet = new HashSet<Workout>();
		List<String> tags = uriInfo.getQueryParameters().get("tag");
		for (String s : tags){
			TypedQuery<Workout> query = em.createQuery(
					"select wo from Workout wo where :tag member of wo._tags", Workout.class
					).setParameter("tag", s);
			resultSet.addAll(query.getResultList());
		}
		// Map results to DTOS
		Set<nz.ac.auckland.fitness.dto.Workout> returnSet = new HashSet<nz.ac.auckland.fitness.dto.Workout>();
		for (Workout wo : resultSet){
			returnSet.add(WorkoutMapper.toDto(wo));
		}
		em.close();
		return returnSet;
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
			_logger.error("Could not find workout with id: "+id);
			throw new WebApplicationException("Could not find workout with id: "+id, 404);
		}
		em.close();
		return WorkoutMapper.toDto(wo);
	}

	@Override
	public Response updateWorkout(int id, nz.ac.auckland.fitness.dto.Workout workoutDTO) {
		// First, map to domain model and log
		Workout woNew = WorkoutMapper.toDomainModel(workoutDTO);
		_logger.debug("Read Workout: " + woNew.toString());
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
		Workout woOld = null;
		try {
			_logger.debug("Querying the database for the Workout of id "+id);
			TypedQuery<Workout> query = em.createQuery(
				"select ex from Workout ex where ex._id = :id", Workout.class
				).setParameter("id", id);
			// If we can find old result, persist new one
			woOld = query.getSingleResult();	
			em.getTransaction().begin();
			woOld.set_name(woNew.get_name());
			woOld.set_description(woNew.get_description());
			woOld.set_exercises(woNew.get_exercises());
			em.getTransaction().commit();
			em.close();
			return Response.status(204).build();
		} catch(NoResultException e) {
			// Workout doesn't exist in the database
			_logger.error("Could not find workout with id: "+id);
			throw new WebApplicationException("Could not find workout with id: "+id,404);
		}
	}

	@Override
	public void removeWorkout(int id) {
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
		Workout woOld = null;
		try {
			_logger.debug("Querying the database for the Workout of id "+id);
			TypedQuery<Workout> query = em.createQuery(
				"select ex from Workout ex where ex._id = :id", Workout.class
				).setParameter("id", id);
			// If we can find old result, persist new one
			woOld = query.getSingleResult();
			em.getTransaction().begin();
			em.remove(woOld);
			em.getTransaction().commit();
		} catch(NoResultException e) {
			// Workout doesn't exist in the database
			_logger.error("Could not find workout with id: "+id);
			throw new WebApplicationException("Could not find workout with id: "+id,404);
		}
		em.close();
	}

	@Override
	public Set<Tag> retrieveWorkoutTags(int id) {
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
		Workout wo = null;
		try {
			_logger.debug("Querying the database for the Workout of id "+id);
			TypedQuery<Workout> query = em.createQuery(
				"select wo from Workout wo where wo._id = :id", Workout.class
				).setParameter("id", id);
			// If we can find old result, persist new one
			wo = query.getSingleResult();
			em.close();
			return wo.get_tags();
		} catch(NoResultException e) {
			// Workout doesn't exist in the database
			_logger.error("Could not find workout with id: "+id);
			throw new WebApplicationException("Could not find workout with id: "+id,404);
		}
	}

	@Override
	public Response createWorkoutTags(int id, Set<Tag> tags) {
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
		Workout wo = null;
		try {
			_logger.debug("Querying the database for the Workout of id "+id);
			TypedQuery<Workout> query = em.createQuery(
				"select wo from Workout wo where wo._id = :id", Workout.class
				).setParameter("id", id);
			// If we can find old result, persist new one
			wo = query.getSingleResult();
			em.getTransaction().begin();
			wo.set_tags(tags);
			em.getTransaction().commit();
			em.close();
			return Response.status(204).build();
		} catch(NoResultException e) {
			// Workout doesn't exist in the database
			_logger.error("Could not find workout with id: "+id);
			throw new WebApplicationException("Could not find workout with id: "+id,404);
		}
	}

	@Override
	public Response updateWorkoutTags(int id, Set<Tag> tags) {
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
		Workout wo = null;
		try {
			_logger.debug("Querying the database for the Workout of id "+id);
			TypedQuery<Workout> query = em.createQuery(
				"select wo from Workout wo where wo._id = :id", Workout.class
				).setParameter("id", id);
			// If we can find old result, persist new one
			wo = query.getSingleResult();
			em.getTransaction().begin();
			wo.set_tags(tags);
			em.getTransaction().commit();
			em.close();
			return Response.status(204).build();
		} catch(NoResultException e) {
			// Workout doesn't exist in the database
			_logger.error("Could not find workout with id: "+id);
			throw new WebApplicationException("Could not find workout with id: "+id,404);
		}
	}

	@Override
	public Response createExercise(nz.ac.auckland.fitness.dto.Exercise exDTO) {
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
		// First, map to domain model and log
		Exercise ex = ExerciseMapper.toDomainModel(exDTO);
		_logger.debug("Read exercise: " + ex.toString());
		// Check it exists
		if (!checkExercise(em,ex)){
			// Then persist the exercise in the database.
			em.getTransaction().begin();
			em.persist(ex);
			em.getTransaction().commit();
			em.close();
			return Response.created(URI.create("/fitness/exercises/" + ex.get_id()))
					.build();
		}
		else {
			// If it exists, inform user
			_logger.error("Exercise of id: "+ex.get_id()+" already exists");
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
			_logger.error("Could not find exercise with id: "+id);
			throw new WebApplicationException("Could not find exercise with id: "+id,404);
		}
		em.close();
		return ExerciseMapper.toDto(ex);
	}
	
	// Example is exercises?tag=running&tag=test
	@Override
	public Set<nz.ac.auckland.fitness.dto.Exercise> searchExerciseByTag(
			UriInfo uriInfo) {
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
		Set<Exercise> resultSet = new HashSet<Exercise>();
		List<String> tags = uriInfo.getQueryParameters().get("tag");
		for (String s : tags){
			TypedQuery<Exercise> query = em.createQuery(
					"select ex from Exercise ex where :tag member of ex._tags", Exercise.class
					).setParameter("tag", s);
			resultSet.addAll(query.getResultList());
		}
		// Map results to DTOS
		Set<nz.ac.auckland.fitness.dto.Exercise> returnSet = new HashSet<nz.ac.auckland.fitness.dto.Exercise>();
		for (Exercise ex : resultSet){
			returnSet.add(ExerciseMapper.toDto(ex));
		}
		em.close();
		return returnSet;
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
			exOld.set_name(exNew.get_name());
			exOld.set_description(exNew.get_description());
			em.getTransaction().commit();
			em.close();
			return Response.status(204).build();
		} catch(NoResultException e) {
			// Exercise doesn't exist in the database
			_logger.error("Could not find exercise with id: "+id);
			throw new WebApplicationException("Could not find exercise with id: "+id,404);
		}
	}

	@Override
	public Set<Tag> retrieveExerciseTags(int id) {
		Exercise ex = null;
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
		try {
			_logger.debug("Querying the database for the exercise of id "+id);
			TypedQuery<Exercise> query = em.createQuery(
				"select ex from Exercise ex where ex._id = :id", Exercise.class
				).setParameter("id", id);
			// If we can find old result, persist new one
			ex = query.getSingleResult();
			em.close();
			return ex.get_tags();
		} catch(NoResultException e) {
			// Exercise doesn't exist in the database
			_logger.error("Could not find exercise with id: "+id);
			throw new WebApplicationException("Could not find exercise with id: "+id,404);
		}
	}

	@Override
	public Response createExerciseTags(int id, Set<Tag> tags) {
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
		Exercise ex = null;
		try {
			_logger.debug("Querying the database for the exercise of id "+id);
			TypedQuery<Exercise> query = em.createQuery(
				"select ex from Exercise ex where ex._id = :id", Exercise.class
				).setParameter("id", id);
			// If we can find old result, persist new one
			ex = query.getSingleResult();
			em.getTransaction().begin();
			ex.set_tags(tags);
			em.getTransaction().commit();
			em.close();
			return Response.status(204).build();
		} catch(NoResultException e) {
			// Exercise doesn't exist in the database
			_logger.error("Could not find exercise with id: "+id);
			throw new WebApplicationException("Could not find exercise with id: "+id,404);
		}
	}

	@Override
	public Response updateExerciseTags(int id, Set<Tag> tags) {
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
		Exercise ex = null;
		try {
			_logger.debug("Querying the database for the exercise of id "+id);
			TypedQuery<Exercise> query = em.createQuery(
				"select ex from Exercise ex where ex._id = :id", Exercise.class
				).setParameter("id", id);
			// If we can find old result, persist new one
			ex = query.getSingleResult();
			em.getTransaction().begin();
			ex.set_tags(tags);
			em.getTransaction().commit();
			em.close();
			return Response.status(204).build();
		} catch(NoResultException e) {
			// Exercise doesn't exist in the database
			_logger.error("Could not find exercise with id: "+id);
			throw new WebApplicationException("Could not find exercise with id: "+id,404);
		}
	}

	@Override
	public Response createUser(nz.ac.auckland.fitness.dto.User uDTO) {
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
		// First, map to domain model and log
		User u = UserMapper.toDomainModel(uDTO);
		_logger.debug("Read user: " + u.toString());
		// Check it exists
		if (!checkUser(em,u)){
			// Then persist the exercise in the database.
			em.getTransaction().begin();
			em.persist(u);
			em.getTransaction().commit();
			em.close();
			return Response.created(URI.create("/fitness/users/" + u.get_id()))
					.build();
		}
		else {
			// If it exists, inform user
			_logger.error("User of id "+u.get_id()+" already exists");
			throw new WebApplicationException("User of id "+u.get_id()+" already exists",403);
		}
		
	}

	@Override
	public nz.ac.auckland.fitness.dto.User retrieveUser(int id) {
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
		User u = null;
		try {
			_logger.debug("Querying the database for the user of id "+id);
			TypedQuery<User> query = em.createQuery(
				"select u from User u where u._id = :id", User.class
				).setParameter("id", id);
			u = query.getSingleResult();
		} catch(NoResultException e) {
			// User doesn't exist in the database
			_logger.error("User of id "+id+" not found");
			throw new WebApplicationException("User of id "+id+" not found",404);
		}
		em.close();
		return UserMapper.toDto(u);
	}

	@Override
	public void removeUser(int id) {
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
		User u = null;
		try {
			_logger.debug("Querying the database for the user of id "+id);
			TypedQuery<User> query = em.createQuery(
				"select u from User u where u._id = :id", User.class
				).setParameter("id", id);
			u = query.getSingleResult();
		} catch(NoResultException e) {
			// User doesn't exist in the database
			_logger.error("User of id "+id+" not found");
			throw new WebApplicationException("User of id "+id+" not found",404);
		}
		em.getTransaction().begin();
		em.remove(u);
		em.getTransaction().commit();
		em.close();
	}

	@Override
	public Response createWorkoutRecordForUser(int id, nz.ac.auckland.fitness.dto.WorkoutRecord wrDTO) {
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
		User u = null;
		try {
			_logger.debug("Querying the database for the user of id "+id);
			TypedQuery<User> query = em.createQuery(
				"select u from User u where u._id = :id", User.class
				).setParameter("id", id);
			u = query.getSingleResult();
		} catch(NoResultException e) {
			// User doesn't exist in the database
			_logger.error("User of id "+id+" not found");
			throw new WebApplicationException("User of id "+id+" not found", 404);
		}
		em.getTransaction().begin();
		// Reconstitute fields from DTO
		Workout wrsWorkout = em.find(Workout.class, wrDTO.get_workout_id());
		if (wrsWorkout == null){
			_logger.error("Workout of id "+wrDTO.get_workout_id()+" not found");
			throw new WebApplicationException("Workout of id "+wrDTO.get_workout_id()+" not found", 404);
		}
		WorkoutRecord wr = WorkoutRecordMapper.toDomainModel(wrDTO);
		wr.set_person(u);
		wr.set_workout(wrsWorkout);
		u.addRecord(wr);
		em.persist(wr);
		em.merge(u);
		em.getTransaction().commit();
		em.close();
		return Response.created(URI.create("/fitness/users/" + u.get_id() + "/records/" + wr.get_id()))
				.build();
	}

	@Override
	public nz.ac.auckland.fitness.dto.WorkoutRecord subscribeToWorkoutRecords(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<nz.ac.auckland.fitness.dto.WorkoutRecord> retrieveWorkoutRecordsForUser(int id){
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
		User u = null;
		try {
			_logger.debug("Querying the database for the user of id "+id);
			TypedQuery<User> query = em.createQuery(
				"select u from User u where u._id = :id", User.class
				).setParameter("id", id);
			u = query.getSingleResult();
		} catch(NoResultException e) {
			// User doesn't exist in the database
			_logger.error("User of id "+id+" not found");
			throw new WebApplicationException("User of id "+id+" not found",404);
		}
		List<nz.ac.auckland.fitness.dto.WorkoutRecord> recordList =  new ArrayList<nz.ac.auckland.fitness.dto.WorkoutRecord>();
		for (WorkoutRecord wr : u.get_records()){
			recordList.add(WorkoutRecordMapper.toDto(wr));
		}
		em.close();
		return recordList;
	}

	@Override
	public nz.ac.auckland.fitness.dto.WorkoutRecord retrieveWorkoutRecords(int id, int rid) {
		EntityManager em = Persistence.createEntityManagerFactory("auditorPU").createEntityManager();
		WorkoutRecord wr = null;
		try {
			_logger.debug("Querying the database for the workout record of id "+rid);
			TypedQuery<WorkoutRecord> query = em.createQuery(
				"select wr from WorkoutRecord wr where wr._id = :id", WorkoutRecord.class
				).setParameter("id", rid);
			wr = query.getSingleResult();
		} catch(NoResultException e) {
			// Record doesn't exist in the database
			_logger.error("Record of id "+rid+" not found");
			throw new WebApplicationException("Record of id "+rid+" not found", 404);
		}
		em.close();
		return WorkoutRecordMapper.toDto(wr);
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
			Workout wo2 = query.getSingleResult();
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
			u = query.getSingleResult();
		} catch(NoResultException e) {
			// User doesn't exist in the database
			return false;
		}
		return true;
	}
	
	@Override
	public void clearDatabase() throws SQLException {
		Connection jdbcConnection = DriverManager.getConnection("jdbc:h2:~/test;mv_store=false", "sa", "sa");
		Statement s = jdbcConnection.createStatement();
		s.execute("SET REFERENTIAL_INTEGRITY FALSE");
		Set<String> tables = new HashSet<String>();
		ResultSet rs = s.executeQuery("select table_name "
				+ "from INFORMATION_SCHEMA.tables "
				+ "where table_type='TABLE' and table_schema='PUBLIC'");

		while (rs.next()) {
			tables.add(rs.getString(1));
		}
		rs.close();
		for (String table : tables) {
			_logger.debug("Deleting content from " + table);
			s.executeUpdate("DELETE FROM " + table);
			/*
			if (dropTables) {
				s.executeUpdate("DROP TABLE " + table);
			}*/
		}
		s.execute("SET REFERENTIAL_INTEGRITY TRUE");
		s.close();
	}
	
}
