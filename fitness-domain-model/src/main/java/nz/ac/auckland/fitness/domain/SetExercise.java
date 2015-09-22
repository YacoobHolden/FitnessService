package nz.ac.auckland.fitness.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * Class used to represent an exercise involving sets
 */
@XmlType(name="2")
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
