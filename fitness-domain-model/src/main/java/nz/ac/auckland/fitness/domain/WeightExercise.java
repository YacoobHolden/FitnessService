package nz.ac.auckland.fitness.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

/*
 * Class used to represent an exercise involving sets of weights
 */

@Entity
@Table(name = "WEIGHT_EXERCISES")
public class WeightExercise extends Exercise{

	@ElementCollection
	@CollectionTable( name = "WEIGHT_REPS" )
	@OrderColumn
	@Column(name = "REP")
	private List<Integer> _reps;

	@ElementCollection
	@CollectionTable( name = "WEIGHTS" )
	@OrderColumn
	@Column(name = "WEIGHT")
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
