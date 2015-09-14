package nz.ac.auckland.fitness.domain;

import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/*
 * Abstract class used to represent an exercise
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Exercise {
	
	@XmlElement(name="name")
	private String _name;
	
	@XmlElement(name="description")
	private String _description;
	
	@XmlElement(name="tags")
	private Set<Tag> _tags;
	
	public Exercise() {
		_tags = new HashSet<Tag>();
	}
	
	public Exercise(String name, String description) {
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

}