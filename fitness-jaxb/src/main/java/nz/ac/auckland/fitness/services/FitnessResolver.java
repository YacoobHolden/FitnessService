package nz.ac.auckland.fitness.services;

import javax.ws.rs.ext.ContextResolver;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;

import nz.ac.auckland.fitness.domain.Exercise;
import nz.ac.auckland.fitness.domain.Tag;
import nz.ac.auckland.fitness.dto.Workout;

public class FitnessResolver implements ContextResolver<JAXBContext> {
	private JAXBContext _context;

	public FitnessResolver() {
		try {
			// The JAXV Context should be able to marshal and unmarshal the
			// specified classes.
			_context = JAXBContext.newInstance(Workout.class, Exercise.class,
					Tag.class);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}

	@Override
	public JAXBContext getContext(Class<?> type) {
		if (type.equals(Workout.class) || type.equals(Exercise.class)
				|| type.equals(Tag.class)) {
			return _context;
		} else {
			return null;
		}
	}
}
