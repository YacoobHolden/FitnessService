package nz.ac.auckland.fitness.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.joda.time.Duration;
import org.joda.time.LocalDate;

/*
 * Class used to represent a completed workout
 */
@Entity
public class WorkoutRecord {
	
	@Id
	@GeneratedValue(generator = "ID GENERATOR")
	private int _id;
	
	private User _person;
	
	private Workout _workout;
	
	private LocalDate _date;
	
	private Duration _duration;
	
	public WorkoutRecord(){
	}

	public WorkoutRecord(int id, User person, Workout workout, LocalDate date, Duration duration){
		_id = id;
		_person = person;
		_workout = workout;
		_date = date;
		_duration = duration;
	}

	// DEFAULT SETTERS AND GETTERS
	public int get_id() {
		return _id;
	}

	public void set_id(int _id) {
		this._id = _id;
	}

	public User get_person() {
		return _person;
	}

	public void set_person(User _person) {
		this._person = _person;
	}

	public Workout get_workout() {
		return _workout;
	}

	public void set_workout(Workout _workout) {
		this._workout = _workout;
	}

	public LocalDate get_date() {
		return _date;
	}

	public void set_date(LocalDate _date) {
		this._date = _date;
	}

	public Duration get_duration() {
		return _duration;
	}

	public void set_duration(Duration _duration) {
		this._duration = _duration;
	}
	// END DEFAULT SETTERS AND GETTERS
}
