package nz.ac.auckland.fitness.services;

import javax.ws.rs.ext.ContextResolver;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import nz.ac.auckland.fitness.dto.DistanceExercise;
import nz.ac.auckland.fitness.dto.Exercise;
import nz.ac.auckland.fitness.dto.SetExercise;
import nz.ac.auckland.fitness.dto.User;
import nz.ac.auckland.fitness.dto.WeightExercise;
import nz.ac.auckland.fitness.dto.WorkoutRecord;
import nz.ac.auckland.fitness.domain.Tag;
import nz.ac.auckland.fitness.dto.Workout;

public class FitnessResolver implements ContextResolver<JAXBContext> {
	private JAXBContext _context;

	public FitnessResolver() {
		try {
			// The JAXV Context should be able to marshal and unmarshal the
			// specified classes.
			_context = JAXBContext.newInstance(Workout.class,
					DistanceExercise.class, Tag.class, Exercise.class,
					WeightExercise.class, SetExercise.class,
					WorkoutRecord.class, User.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@Override
	public JAXBContext getContext(Class<?> type) {
		if (type.equals(Workout.class) || type.equals(DistanceExercise.class)
				|| type.equals(Tag.class) || type.equals(Exercise.class)
				|| type.equals(WeightExercise.class)
				|| type.equals(SetExercise.class)
				|| type.equals(WorkoutRecord.class) || type.equals(User.class)) {
			return _context;
		} else {
			return null;
		}
	}
}
