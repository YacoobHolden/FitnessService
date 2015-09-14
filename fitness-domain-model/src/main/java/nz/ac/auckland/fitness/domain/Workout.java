package nz.ac.auckland.fitness.domain;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/*
 * Class used to represent a Workout
 */
public class Workout {
	
	private String _name;
	
	private String _description;
	
	private Set<Tag> _tags;
	
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
	
	public Workout(String name, String description, HashSet<Tag> tags, HashSet<Exercise> exercises) {
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

	public Set<Exercise> get_exercises() {
		return _exercises;
	}

	public void set_exercises(Set<Exercise> _exercises) {
		this._exercises = _exercises;
	}
	
	/*
	 * END DEFAULT SETTERS AND GETTERS
	 */
	
	public Set<String> getExStrings(){
		Set<String> exStrings = new HashSet<String>();
		for (Exercise e : _exercises){
			exStrings.add(e.toString());
		}
		return exStrings;
	}

}
