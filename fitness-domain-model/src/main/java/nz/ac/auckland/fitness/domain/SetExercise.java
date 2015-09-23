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
 * Class used to represent an exercise involving sets
 */

@Entity
@Table(name = "SET_EXERCISES")
public class SetExercise extends Exercise{
	
	@ElementCollection
	@CollectionTable( name = "SET_REPS" )
	@OrderColumn
	@Column(name = "REP")
	private List<Integer> _reps;
	
	public SetExercise() {
		super();
		_reps = new ArrayList<Integer>();
	}
	
	public SetExercise(int id, String name, String description, List<Integer> reps) {
		super(id,name, description);
		_reps = reps;
	}
	
	public void addSet(int rep) {
		_reps.add(rep);
	}

	// DEFAULT SETTERS AND GETTERS
	public List<Integer> get_reps() {
		return _reps;
	}

	public void set_reps(List<Integer> _reps) {
		this._reps = _reps;
	}
	// END DEFAULT SETTERS AND GETTERS
}
