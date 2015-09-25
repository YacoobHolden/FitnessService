package nz.ac.auckland.fitness.services;

import java.util.HashSet;
import java.util.Set;

import nz.ac.auckland.fitness.domain.Exercise;
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

	public static Workout toDomainModel(nz.ac.auckland.fitness.dto.Workout dtoWorkout) {
		// First map the exercises
		HashSet<Exercise> mappedExs = new HashSet<Exercise>();
		for (nz.ac.auckland.fitness.dto.Exercise ex : dtoWorkout.get_exercises()){
			mappedExs.add(ExerciseMapper.toDomainModel(ex));
		}
		// Then map the workout
		Workout fullWorkout = new Workout(dtoWorkout.get_id(), dtoWorkout.get_name(),
				dtoWorkout.get_description(), new HashSet<Tag>(), mappedExs);
		return fullWorkout;
	}

	public static nz.ac.auckland.fitness.dto.Workout toDto(Workout workout) {
		// First map the exercises
		HashSet<nz.ac.auckland.fitness.dto.Exercise> mappedExs = new HashSet<nz.ac.auckland.fitness.dto.Exercise>();
		for (Exercise ex : workout.get_exercises()){
			mappedExs.add(ExerciseMapper.toDto(ex));
		}
		// Then map the workout
		nz.ac.auckland.fitness.dto.Workout dtoWorkout = new nz.ac.auckland.fitness.dto.Workout(
				workout.get_id(), workout.get_name(), workout.get_description(), mappedExs);
		return dtoWorkout;
	}
}