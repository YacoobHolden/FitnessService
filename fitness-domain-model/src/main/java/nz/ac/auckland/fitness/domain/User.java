package nz.ac.auckland.fitness.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/*
 * Class used to represent a web service user
 */
@Entity
@Table(name = "USERS")
public class User {
	
	@Id
	@GeneratedValue(generator = "ID GENERATOR")
	@Column(name = "ID")
	private int _id;
	
	@Column(name="NAME", unique=true)
	private String _name;
	
	@OneToMany( mappedBy = "_person" , fetch = FetchType.EAGER, cascade = CascadeType.REMOVE )
	private List<WorkoutRecord> _records;
	
	public User(){
		_records = new ArrayList<WorkoutRecord>();
	}
	
	public User(int id, String name){
		_id=id;
		_name=name;
		_records = new ArrayList<WorkoutRecord>();
	}
	
	public User(int id, String name, ArrayList<WorkoutRecord> records){
		_id=id;
		_name=name;
		_records = records;
	}
	
	public WorkoutRecord getLastRecord(){
		WorkoutRecord record = null;
		if(!_records.isEmpty()) {
			record = _records.get(0);
		}
		return record;
	}
	
	public void addRecord(WorkoutRecord wr){
		_records.add(wr);
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

	public List<WorkoutRecord> get_records() {
		return _records;
	}

	public void set_records(List<WorkoutRecord> _records) {
		this._records = _records;
	}
	// END DEFAULT SETTERS AND GETTERS

}
