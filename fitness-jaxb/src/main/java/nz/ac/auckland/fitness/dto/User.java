package nz.ac.auckland.fitness.dto;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

/*
 * DTO Class used to represent a web service user
 */

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class User {
	
	@XmlElement(name="id")
	private int _id;
	
	@XmlElement(name="name")
	private String _name;
	
	@XmlElement(name="last_record")
	private WorkoutRecord _last_record;
	
	public User() {
	}
	
	public User(String name, WorkoutRecord last_record){
		this(0,name,last_record);
	}
	
	public User(int id, String name, WorkoutRecord last_record){
		_id=id;
		_name=name;
		_last_record = last_record;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof User))
            return false;
        if (obj == this)
            return true;

        User rhs = (User) obj;
        return this._name.equalsIgnoreCase(rhs._name);
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

	public WorkoutRecord get_last_record() {
		return _last_record;
	}

	public void set_last_record(WorkoutRecord last_record) {
		this._last_record = last_record;
	}
	// END DEFAULT SETTERS AND GETTERS

}
