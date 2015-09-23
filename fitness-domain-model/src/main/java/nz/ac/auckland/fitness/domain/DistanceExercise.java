package nz.ac.auckland.fitness.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/*
 * Class used to represent an exercise involving some distance
 */

@Entity
@Table(name = "DISTANCE_EXERCISES")
public class DistanceExercise extends Exercise{
	
	@Column(name = "DISTANCE")
	private Double _distance;
	
	public DistanceExercise() {
		super();
	}
	
	public DistanceExercise(int id, String name, String description, double distance) {
		super(id,name, description);
		_distance = distance;
	}
	
	// DEFAULT SETTERS AND GETTERS
	public Double get_distance() {
		return _distance;
	}

	public void set_distance(Double _distance) {
		this._distance = _distance;
	}
	// END DEFAULT SETTERS AND GETTERS
	
}
