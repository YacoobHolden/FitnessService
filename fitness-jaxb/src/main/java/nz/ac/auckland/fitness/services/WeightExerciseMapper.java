package nz.ac.auckland.fitness.services;

import nz.ac.auckland.fitness.domain.WeightExercise;

/**
 * Helper class to convert between domain-model and DTO objects representing
 * WeightExercises.
 * 
 * @author Jacob Holden
 * 
 */
public class WeightExerciseMapper {

	static WeightExercise toDomainModel(nz.ac.auckland.fitness.dto.WeightExercise dtoWeightExercise) {
		WeightExercise fullWeightExercise = new WeightExercise(dtoWeightExercise.get_id(), dtoWeightExercise.get_name(),
				dtoWeightExercise.get_description(), dtoWeightExercise.get_reps(), dtoWeightExercise.get_weights());
		return fullWeightExercise;
	}

	static nz.ac.auckland.fitness.dto.WeightExercise toDto(WeightExercise exercise) {
		nz.ac.auckland.fitness.dto.WeightExercise dtoWeightExercise = new nz.ac.auckland.fitness.dto.WeightExercise(
				exercise.get_id(), exercise.get_name(), exercise.get_description(), exercise.get_reps(), exercise.get_weights());
		return dtoWeightExercise;
	}
}