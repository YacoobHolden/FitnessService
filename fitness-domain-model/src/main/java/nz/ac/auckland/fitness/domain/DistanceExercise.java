package nz.ac.auckland.fitness.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/*
 * Class used to represent an exercise involving some distance
 */
@XmlRootElement(name="distance")
@XmlAccessorType(XmlAccessType.FIELD)
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
