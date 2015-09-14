package nz.ac.auckland.fitness.dto;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/*
 * Class used to represent a Workout
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Workout {
	
	@XmlElement(name="name")
	private String _name;
	
	@XmlElement(name="description")
	private String _description;
	
	@XmlElement(name="tags")
	private Set<String> _tags;
	
	@XmlElement(name="exercises")
	private Set<String> _exercises;
	
	protected Workout() {
		_tags = new HashSet<String>();
		_exercises = new HashSet<String>();
	}
	
	/**
	 * Constructs a DTO Workout instance. This method should NOT be called by 
	 * Web Service clients. It is intended to be used by the Web Service 
	 * implementation when creating a DTO Workout from a domain-model Workout 
	 * object.
	 */
	public Workout(String name, String description) {
		_name = name;
		_description = description;
		_tags = new HashSet<String>();
		_exercises = new HashSet<String>();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Workout))
            return false;
        if (obj == this)
            return true;

        Workout rhs = (Workout) obj;
        return this._name.equalsIgnoreCase(rhs._name);
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31). 
	            append(_name).
	            toHashCode();
	}
	
	@Override
	public String toString() {
		return _name;
	}

	public String get_name() {
		return _name;
	}

	public void set_name(String _name) {
		this._name = _name;
	}

	public String get_description() {
		return _description;
	}

	public void set_description(String _description) {
		this._description = _description;
	}

	public Set<String> get_tags() {
		return _tags;
	}

	public void set_tags(Set<String> _tags) {
		this._tags = _tags;
	}

	public Set<String> get_exercises() {
		return _exercises;
	}

	public void set_exercises(Set<String> _exercises) {
		this._exercises = _exercises;
	}

}
