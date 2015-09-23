package nz.ac.auckland.fitness.domain;

import java.util.ArrayList;
import java.util.List;

/*
 * Class used to represent an exercise involving sets of weights
 */
public class WeightExercise extends Exercise{

	private List<Integer> _reps;

	private List<Double> _weights;
	
	public WeightExercise() {
		super();
		_reps = new ArrayList<Integer>();
		_weights = new ArrayList<Double>();
	}
	
	public WeightExercise(int id, String name, String description, List<Integer> reps, List<Double> weights) {
		super(id, name, description);
		_reps = reps;
		_weights = weights;
	}
	
	public void addSet(int rep, double weight) {
		_reps.add(rep);
		_weights.add(weight);
	}

	// DEFAULT SETTERS AND GETTERS
	public List<Integer> get_reps() {
		return _reps;
	}

	public void set_reps(List<Integer> _reps) {
		this._reps = _reps;
	}

	public List<Double> get_weights() {
		return _weights;
	}

	public void set_weights(List<Double> _weights) {
		this._weights = _weights;
	}
	// END DEFAULT SETTERS AND GETTERS
}
