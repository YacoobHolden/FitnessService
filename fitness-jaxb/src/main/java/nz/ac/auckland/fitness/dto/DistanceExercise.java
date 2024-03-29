package nz.ac.auckland.fitness.dto;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonTypeInfo;
import org.codehaus.jackson.annotate.JsonTypeName;

/*
 * DTO Class used to represent an exercise involving some distance
 */
@XmlRootElement(name="distance_exercise")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonTypeName("distanceDTO")
public class DistanceExercise extends Exercise{
	
	@XmlElement(name="distance")
	private Double _distance;
	
	public DistanceExercise() {
		super();
	} 
	
	/**
	 * Constructs a DTO DistanceExercise instance. This method is intended to be called
	 * by Web service clients when creating new DistanceExercise. The id field is not 
	 * required because it is generated by the Web service.
     *
	 */
	public DistanceExercise(String name, String description, double distance) throws IllegalArgumentException {
		this(0,name,description,distance);
	}
	
	/**
	 * Constructs a DTO DistanceExercise instance. This method should NOT be called by 
	 * Web Service clients. It is intended to be used by the Web Service 
	 * implementation when creating a DTO DistanceExercise from a domain-model DistanceExercise 
	 * object.
	 */
	public DistanceExercise(int id, String name, String description, double distance) {
		super(id, name, description);
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
