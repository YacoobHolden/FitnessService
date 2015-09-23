package nz.ac.auckland.fitness.domain;

/*
 * Class used to represent an exercise involving some distance
 */
public class DistanceExercise extends Exercise{
	
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
