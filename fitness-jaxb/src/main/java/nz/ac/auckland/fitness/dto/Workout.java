package nz.ac.auckland.fitness.dto;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import nz.ac.auckland.fitness.domain.Tag;

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
	
	@XmlElementWrapper(name="tags")
	@XmlElement(name="tag")
	private Set<Tag> _tags;
	
	@XmlElementWrapper(name="exercises")
	@XmlElement(name="exercise")
	private Set<String> _exercises;
	
	protected Workout() {
		_tags = new HashSet<Tag>();
		_exercises = new HashSet<String>();
	}
	
	public Workout(String name, String description, Set<Tag> tags, Set<String> exercises) {
		_name = name;
		_description = description;
		_tags = tags;
		_exercises = exercises;
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

	/*
	 * DEFAULT SETTERS AND GETTERS
	 */
	
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

	public Set<Tag> get_tags() {
		return _tags;
	}

	public void set_tags(Set<Tag> _tags) {
		this._tags = _tags;
	}

	public Set<String> get_exercises() {
		return _exercises;
	}

	public void set_exercises(Set<String> _exercises) {
		this._exercises = _exercises;
	}
	
	/*
	 * END DEFAULT SETTERS AND GETTERS
	 */

}
