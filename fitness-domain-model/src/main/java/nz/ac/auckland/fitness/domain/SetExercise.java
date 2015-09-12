package nz.ac.auckland.fitness.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;

/*
 * Class used to represent an exercise involving sets
 */
public class SetExercise extends Exercise{
	@XmlElement(name="reps")
	private List<Integer> _reps;
	
	public SetExercise() {
		super();
		_reps = new ArrayList<Integer>();
	}
	
	public SetExercise(String name, String description) {
		super(name, description);
		_reps = new ArrayList<Integer>();
	}
	
	public void addSet(int rep) {
		_reps.add(rep);
	}
}
