package nz.ac.auckland.fitness.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.joda.time.Duration;
import org.joda.time.LocalDate;

/*
 * Class used to represent a completed workout
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkoutRecord {
	
	@XmlElement(name="person")
	private String _person;
	
	@XmlElement(name="workout")
	private Workout _workout;
	
	@XmlElement(name="date")
	private LocalDate _date;
	
	@XmlElement(name="duration")
	private Duration _duration;

}
