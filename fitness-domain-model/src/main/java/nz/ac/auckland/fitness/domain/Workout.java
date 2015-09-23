package nz.ac.auckland.fitness.domain;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/*
 * Class used to represent a Workout
 */

@Entity
@Table(name = "WORKOUTS")
public class Workout {
	
	@Id
	@GeneratedValue(generator = "ID GENERATOR")
	@Column(name = "ID")
	private int _id;
	
	@Column(name = "NAME", unique=true)
	private String _name;
	
	@Column(name = "DESCRIPTION")
	private String _description;
	
	@ElementCollection 
	@CollectionTable(
		name = "TAGS",
		joinColumns = @JoinColumn(name = "ID") ) @Column(name = "TEXT")
	private Set<Tag> _tags;
	
	@ElementCollection 
	@CollectionTable(
		name = "WORKOUT_EXERCISES",
		joinColumns = @JoinColumn(name = "ID") )
	private Set<Exercise> _exercises;
	
	protected Workout() {
		_tags = new HashSet<Tag>();
		_exercises = new HashSet<Exercise>();
	}
	
	public Workout(int id, String name, String description, Set<Tag> tags, Set<Exercise> exercises) {
		_name = name;
		_description = description;
		_tags = tags;
		_exercises = exercises;
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

	// DEFAULT SETTERS AND GETTERS
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
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

	public Set<Tag> get_tags() {
		return _tags;
	}

	public void set_tags(Set<Tag> _tags) {
		this._tags = _tags;
	}

	public Set<Exercise> get_exercises() {
		return _exercises;
	}

	public void set_exercises(Set<Exercise> _exercises) {
		this._exercises = _exercises;
	}
	
	/*
	 * END DEFAULT SETTERS AND GETTERS
	 */

}
