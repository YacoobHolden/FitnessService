package nz.ac.auckland.fitness.domain;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/*
 * Class used to represent an exercise involving sets of weights
 */
@XmlType(name="1")
public class WeightExercise extends Exercise{
	@XmlElement(name="reps")
	private List<Integer> _reps;
	
	@XmlElement(name="weights")
	private List<Double> _weights;
	
	public WeightExercise() {
		super();
		_reps = new ArrayList<Integer>();
		_weights = new ArrayList<Double>();
	}
	
	public WeightExercise(String name, String description) {
		super(name, description);
		_reps = new ArrayList<Integer>();
		_weights = new ArrayList<Double>();
	}
	
	public void addSet(int rep, double weight) {
		_reps.add(rep);
		_weights.add(weight);
	}
}
