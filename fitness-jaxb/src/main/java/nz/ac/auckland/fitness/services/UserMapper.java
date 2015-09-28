package nz.ac.auckland.fitness.services;

import nz.ac.auckland.fitness.domain.User;

/**
 * Helper class to convert between domain-model and DTO objects representing
 * Users.
 * 
 * @author Jacob Holden
 * 
 */
public class UserMapper {

	static User toDomainModel(nz.ac.auckland.fitness.dto.User dtoUser) {
		User fullUser = new User(dtoUser.get_id(), dtoUser.get_name());
		return fullUser;
	}

	public static nz.ac.auckland.fitness.dto.User toDto(User user) {
		// First map the record
		nz.ac.auckland.fitness.domain.WorkoutRecord domRecord = user
				.getLastRecord();
		// Then map the user
		nz.ac.auckland.fitness.dto.User dtoUser = null;
		if (domRecord != null) {
			dtoUser = new nz.ac.auckland.fitness.dto.User(user.get_id(),
					user.get_name(), WorkoutRecordMapper.toDto(domRecord));
		} else {
			dtoUser = new nz.ac.auckland.fitness.dto.User(user.get_id(),
					user.get_name(), null);
		}
		return dtoUser;
	}
}