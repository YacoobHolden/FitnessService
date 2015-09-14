package nz.ac.auckland.fitness.services;

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
		Workout fullWorkout = new Workout(dtoWorkout.get_name(),dtoWorkout.get_description());
		return fullWorkout;
	}
	
	static nz.ac.auckland.fitness.dto.Workout toDto(Workout workout) {
		nz.ac.auckland.fitness.dto.Workout dtoWorkout = 
				new nz.ac.auckland.fitness.dto.Workout(workout.get_name(),workout.get_description());
		return dtoWorkout;
	}
}