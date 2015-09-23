package nz.ac.auckland.fitness.services;

import nz.ac.auckland.fitness.domain.DistanceExercise;

/**
 * Helper class to convert between domain-model and DTO objects representing
 * DistanceExercises.
 * 
 * @author Jacob Holden
 * 
 */
public class DistanceExerciseMapper {

	static DistanceExercise toDomainModel(nz.ac.auckland.fitness.dto.DistanceExercise dtoDistanceExercise) {
		DistanceExercise fullDistanceExercise = new DistanceExercise(dtoDistanceExercise.get_id(), dtoDistanceExercise.get_name(),
				dtoDistanceExercise.get_description(), dtoDistanceExercise.get_distance());
		return fullDistanceExercise;
	}

	static nz.ac.auckland.fitness.dto.DistanceExercise toDto(DistanceExercise exercise) {
		nz.ac.auckland.fitness.dto.DistanceExercise dtoDistanceExercise = new nz.ac.auckland.fitness.dto.DistanceExercise(
				exercise.get_id(), exercise.get_name(), exercise.get_description(), exercise.get_distance());
		return dtoDistanceExercise;
	}
}