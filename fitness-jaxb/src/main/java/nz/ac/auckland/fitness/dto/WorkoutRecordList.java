package nz.ac.auckland.fitness.dto;

import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class WorkoutRecordList {
	
	@XmlElementWrapper(name="records")
	@XmlElement(name="record")
	private List<WorkoutRecord> _records;
	
	protected WorkoutRecordList() {
	}
	
	public WorkoutRecordList(List<WorkoutRecord> wrList) {
		_records = wrList;
	}
	
	public List<WorkoutRecord> get_records(){
		return _records;
	}
}
