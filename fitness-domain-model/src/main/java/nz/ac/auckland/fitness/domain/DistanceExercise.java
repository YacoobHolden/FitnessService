package nz.ac.auckland.fitness.domain;

import javax.xml.bind.annotation.XmlElement;

/*
 * Class used to represent an exercise involving some distance
 */
public class DistanceExercise extends Exercise{
	@XmlElement(name="distance")
	private Double _distance;
	
	public DistanceExercise() {
		super();
	}
	
	public DistanceExercise(String name, String description, double distance) {
		super(name, description);
		_distance = distance;
	}
	
}
