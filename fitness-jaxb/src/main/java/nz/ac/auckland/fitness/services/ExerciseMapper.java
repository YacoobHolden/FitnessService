package nz.ac.auckland.fitness.services;

import nz.ac.auckland.fitness.domain.Exercise;

public class ExerciseMapper {
	
	static Exercise toDomainModel(nz.ac.auckland.fitness.dto.Exercise dtoExercise) {
		// Map to the right class
		if (dtoExercise.getClass().equals(nz.ac.auckland.fitness.dto.DistanceExercise.class)) {
			return DistanceExerciseMapper.toDomainModel((nz.ac.auckland.fitness.dto.DistanceExercise) dtoExercise);
		}
		else if (dtoExercise.getClass().equals(nz.ac.auckland.fitness.dto.SetExercise.class)) {
			return SetExerciseMapper.toDomainModel((nz.ac.auckland.fitness.dto.SetExercise) dtoExercise);
		}
		else if (dtoExercise.getClass().equals(nz.ac.auckland.fitness.dto.WeightExercise.class)) {
			return WeightExerciseMapper.toDomainModel((nz.ac.auckland.fitness.dto.WeightExercise) dtoExercise);
		}
		else {
			return null;
		}
	}

	static nz.ac.auckland.fitness.dto.Exercise toDto(Exercise exercise) {
		// Map to the right class
		if (exercise.getClass().equals(nz.ac.auckland.fitness.domain.DistanceExercise.class)) {
			return DistanceExerciseMapper.toDto((nz.ac.auckland.fitness.domain.DistanceExercise) exercise);
		}
		else if (exercise.getClass().equals(nz.ac.auckland.fitness.domain.SetExercise.class)) {
			return SetExerciseMapper.toDto((nz.ac.auckland.fitness.domain.SetExercise) exercise);
		}
		else if (exercise.getClass().equals(nz.ac.auckland.fitness.domain.WeightExercise.class)) {
			return WeightExerciseMapper.toDto((nz.ac.auckland.fitness.domain.WeightExercise) exercise);
		}
		else {
			return null;
		}
	}

}
