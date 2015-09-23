package nz.ac.auckland.fitness.services;

import nz.ac.auckland.fitness.domain.WorkoutRecord;

/**
 * Helper class to convert between domain-model and DTO objects representing
 * WorkoutRecordRecords.
 * 
 * @author Jacob Holden
 * 
 */
public class WorkoutRecordMapper {

	static WorkoutRecord toDomainModel(nz.ac.auckland.fitness.dto.WorkoutRecord dtoWorkoutRecord) {
		WorkoutRecord fullWorkoutRecord = new WorkoutRecord(
				dtoWorkoutRecord.get_id(), 
				null,
				null, 
				dtoWorkoutRecord.get_date(), 
				dtoWorkoutRecord.get_duration());
		return fullWorkoutRecord;
	}

	static nz.ac.auckland.fitness.dto.WorkoutRecord toDto(WorkoutRecord workout) {
		nz.ac.auckland.fitness.dto.WorkoutRecord dtoWorkoutRecord = new nz.ac.auckland.fitness.dto.WorkoutRecord(
				workout.get_id(), 
				workout.get_person().get_id(), 
				workout.get_workout().get_id(), 
				workout.get_date(), 
				workout.get_duration());
		return dtoWorkoutRecord;
	}
}