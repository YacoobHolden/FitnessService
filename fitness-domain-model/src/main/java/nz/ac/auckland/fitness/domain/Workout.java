package nz.ac.auckland.fitness.domain;

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
public abstract class Workout {
	
	@XmlElement(name="name")
	private String _name;
	
	@XmlElement(name="description")
	private String _description;
	
	@XmlElement(name="tags")
	private Set<Tag> _tags;
	
	@XmlElement(name="exercises")
	private Set<Exercise> _exercises;
	
	protected Workout() {
		_tags = new HashSet<Tag>();
		_exercises = new HashSet<Exercise>();
	}
	
	public Workout(String name, String description) {
		_name = name;
		_description = description;
		_tags = new HashSet<Tag>();
		_exercises = new HashSet<Exercise>();
	}
	
	public void addTag(Tag tag) {
		_tags.add(tag);
	}
	
	public void addExercise(Exercise exercise) {
		_exercises.add(exercise);
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

}
