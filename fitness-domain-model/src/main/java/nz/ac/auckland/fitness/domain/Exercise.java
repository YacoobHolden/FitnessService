package nz.ac.auckland.fitness.domain;

import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

/*
 * Abstract class used to represent an exercise
 */
@Entity
@Inheritance( strategy = InheritanceType.JOINED )
@Table(name = "EXERCISES")
public abstract class Exercise {
	
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
	
	public Exercise() {
		_tags = new HashSet<Tag>();
	}
	
	public Exercise(int id, String name, String description) {
		_id = id;
		_name = name;
		_description = description;
		_tags = new HashSet<Tag>();
	}
	
	public void addTag(Tag tag) {
		_tags.add(tag);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Exercise))
            return false;
        if (obj == this)
            return true;

        Exercise rhs = (Exercise) obj;
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
	// END DEFAULT SETTERS AND GETTERS

}
