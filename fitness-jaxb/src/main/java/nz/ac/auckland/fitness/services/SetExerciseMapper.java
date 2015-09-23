package nz.ac.auckland.fitness.services;

import nz.ac.auckland.fitness.domain.SetExercise;

/**
 * Helper class to convert between domain-model and DTO objects representing
 * SetExercises.
 * 
 * @author Jacob Holden
 * 
 */
public class SetExerciseMapper {

	static SetExercise toDomainModel(nz.ac.auckland.fitness.dto.SetExercise dtoSetExercise) {
		SetExercise fullSetExercise = new SetExercise(dtoSetExercise.get_id(), dtoSetExercise.get_name(),
				dtoSetExercise.get_description(), dtoSetExercise.get_reps());
		return fullSetExercise;
	}

	static nz.ac.auckland.fitness.dto.SetExercise toDto(SetExercise exercise) {
		nz.ac.auckland.fitness.dto.SetExercise dtoSetExercise = new nz.ac.auckland.fitness.dto.SetExercise(
				exercise.get_id(), exercise.get_name(), exercise.get_description(), exercise.get_reps());
		return dtoSetExercise;
	}
}