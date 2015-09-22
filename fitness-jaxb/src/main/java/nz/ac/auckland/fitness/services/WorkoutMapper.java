package nz.ac.auckland.fitness.services;

import java.util.HashSet;

import nz.ac.auckland.fitness.domain.Tag;
import nz.ac.auckland.fitness.domain.Workout;

/**
 * Helper class to convert between domain-model and DTO objects representing
 * Workouts.
 * 
 * @author Jacob Holden
 * 
 */
public class WorkoutMapper {

	static Workout toDomainModel(nz.ac.auckland.fitness.dto.Workout dtoWorkout) {
		Workout fullWorkout = new Workout(dtoWorkout.get_id(), dtoWorkout.get_name(),
				dtoWorkout.get_description(), new HashSet<Tag>(), dtoWorkout.get_exercises());
		return fullWorkout;
	}

	static nz.ac.auckland.fitness.dto.Workout toDto(Workout workout) {
		nz.ac.auckland.fitness.dto.Workout dtoWorkout = new nz.ac.auckland.fitness.dto.Workout(
				workout.get_id(), workout.get_name(), workout.get_description(), workout.get_exercises());
		return dtoWorkout;
	}
}