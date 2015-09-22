package nz.ac.auckland.fitness.dto;

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
	
	@XmlElement(name="id")
	private String _id;
	
	@XmlElement(name="name")
	private String _name;
	
	@XmlElement(name="description")
	private String _description;
	
	public Exercise() {
	}
	
	public Exercise(String name, String description) {
		_name = name;
		_description = description;
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

}
