package nz.ac.auckland.fitness.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.annotate.JsonTypeName;

/*
 * DTO Class used to represent an exercise involving sets of weights
 */
@XmlRootElement(name="weight_exercise")
@XmlAccessorType(XmlAccessType.FIELD)
@JsonTypeName("weightDTO")
public class WeightExercise extends Exercise{
	
	@XmlElementWrapper(name="reps")
	@XmlElement(name="rep")
	private List<Integer> _reps;
	
	@XmlElementWrapper(name="weights")
	@XmlElement(name="weight")
	private List<Double> _weights;
	
	public WeightExercise() {
		super();
		_reps = new ArrayList<Integer>();
		_weights = new ArrayList<Double>();
	}
	
	/**
	 * Constructs a DTO WeightExercise instance. This method is intended to be called
	 * by Web service clients when creating new WeightExercise. The id field is not 
	 * required because it is generated by the Web service.
     *
	 */
	public WeightExercise(String name, String description, List<Integer> reps, List<Double> weights) throws IllegalArgumentException {
		this(0,name,description,reps,weights);
	}
	
	/**
	 * Constructs a DTO WeightExercise instance. This method should NOT be called by 
	 * Web Service clients. It is intended to be used by the Web Service 
	 * implementation when creating a DTO WeightExercise from a domain-model WeightExercise 
	 * object.
	 */
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
