package nz.ac.auckland.fitness.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import nz.ac.auckland.fitness.dto.Exercise;
import nz.ac.auckland.fitness.dto.DistanceExercise;
import nz.ac.auckland.fitness.dto.SetExercise;
import nz.ac.auckland.fitness.dto.User;
import nz.ac.auckland.fitness.dto.WeightExercise;
import nz.ac.auckland.fitness.dto.WorkoutRecord;
import nz.ac.auckland.fitness.domain.Tag;
import nz.ac.auckland.fitness.dto.Workout;
import nz.ac.auckland.fitness.services.FitnessResolver;
import nz.ac.auckland.fitness.services.WorkoutMapper;

import org.joda.time.Duration;
import org.joda.time.LocalDate;
import org.junit.BeforeClass;
import org.junit.Test;

public class JaxBTest {

	private static OutputStream os;
	// DistanceExercise
	private static JAXBContext distExContext;
	private static Marshaller distExMarshaller;
	private static Unmarshaller distExUnmarshaller;
	// WeightExercise
	private static JAXBContext weightExContext;
	private static Marshaller weightExMarshaller;
	private static Unmarshaller weightExUnmarshaller;
	// SetExercise
	private static JAXBContext setExContext;
	private static Marshaller setExMarshaller;
	private static Unmarshaller setExUnmarshaller;
	// Workout
	private static JAXBContext woContext;
	private static Marshaller woMarshaller;
	private static Unmarshaller woUnmarshaller;
	// WorkoutRecord
	private static JAXBContext woReContext;
	private static Marshaller woReMarshaller;
	private static Unmarshaller woReUnmarshaller;
	// User
	private static JAXBContext usrContext;
	private static Marshaller usrMarshaller;
	private static Unmarshaller usrUnmarshaller;

	/**
	 * One-time setup method that creates a Web service client.
	 */
	@BeforeClass
	public static void setUpJAXB() {
		os = new ByteArrayOutputStream();
		// Setup distance exercise testing stuff
		distExContext = new FitnessResolver().getContext(DistanceExercise.class);
		try {
			distExMarshaller = distExContext.createMarshaller();
			distExUnmarshaller = distExContext.createUnmarshaller();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Setup weight exercise testing stuff
		weightExContext = new FitnessResolver().getContext(WeightExercise.class);
		try {
			weightExMarshaller = weightExContext.createMarshaller();
			weightExUnmarshaller = weightExContext.createUnmarshaller();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Setup set exercise testing stuff
		setExContext = new FitnessResolver().getContext(SetExercise.class);
		try {
			setExMarshaller = setExContext.createMarshaller();
			setExUnmarshaller = setExContext.createUnmarshaller();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Setup wo testing stuff
		woContext = new FitnessResolver().getContext(Workout.class);
		try {
			woMarshaller = woContext.createMarshaller();
			woUnmarshaller = woContext.createUnmarshaller();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Setup woRecord testing stuff
		woReContext = new FitnessResolver().getContext(WorkoutRecord.class);
		try {
			woReMarshaller = woReContext.createMarshaller();
			woReUnmarshaller = woReContext.createUnmarshaller();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Setup usr testing stuff
		usrContext = new FitnessResolver().getContext(User.class);
		try {
			usrMarshaller = woReContext.createMarshaller();
			usrUnmarshaller = woReContext.createUnmarshaller();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testsPass() {
	}

	@Test
	public void testDistanceExerciseXML() {
		// Clear output stream
		os = new ByteArrayOutputStream();
		// Set up
		Exercise distex = new DistanceExercise("Run", "Do it", 5.0);
		
		// First, test marshall
		try {
			distExMarshaller.marshal(distex, os);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		assertEquals(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><distance_exercise><id>0</id><name>Run</name><description>Do it</description><distance>5.0</distance></distance_exercise>",
				os.toString());
	
		// Then, test unmarshall
		StringReader reader = new StringReader(os.toString());
		Exercise unmarshalledEx = null;
		try {
			unmarshalledEx = (DistanceExercise) distExUnmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		assertEquals(unmarshalledEx,distex);
	}
	
	@Test
	public void testSetExerciseXML() {
		// Clear output stream
		os = new ByteArrayOutputStream();
		// Set up
		List<Integer> sets = new ArrayList<Integer>();
		sets.add(25);
		sets.add(25);
		sets.add(25);
		Exercise setex = new SetExercise("Press Ups", "Do them", sets);
		
		// First, test marshall
		try {
			setExMarshaller.marshal(setex, os);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		assertEquals(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><set_exercise><id>0</id><name>Press Ups</name><description>Do them</description><reps><rep>25</rep><rep>25</rep><rep>25</rep></reps></set_exercise>",
				os.toString());
		
		// Then, test unmarshall
		StringReader reader = new StringReader(os.toString());
		Exercise unmarshalledEx = null;
		try {
			unmarshalledEx = (SetExercise) setExUnmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		assertEquals(unmarshalledEx,setex);
	}
	
	@Test
	public void testWeightExerciseXML() {
		// Clear output stream
		os = new ByteArrayOutputStream();
		// Set up
		List<Integer> sets = new ArrayList<Integer>();
		sets.add(10);
		sets.add(10);
		sets.add(10);
		List<Double> weights = new ArrayList<Double>();
		weights.add(10.0);
		weights.add(15.0);
		weights.add(20.0);
		Exercise weightex = new WeightExercise("Chest Press 10-20 x 3", "Do them", sets, weights);
		
		// First, test marshall
		try {
			weightExMarshaller.marshal(weightex, os);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		assertEquals(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><weight_exercise><id>0</id><name>Chest Press 10-20 x 3</name><description>Do them</description><reps><rep>10</rep><rep>10</rep><rep>10</rep></reps><weights><weight>10.0</weight><weight>15.0</weight><weight>20.0</weight></weights></weight_exercise>",
				os.toString());
		
		// Then, test unmarshal
		StringReader reader = new StringReader(os.toString());
		Exercise unmarshalledEx = null;
		try {
			unmarshalledEx = (WeightExercise) weightExUnmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		assertEquals(unmarshalledEx,weightex);
	}

	@Test
	public void testsWorkoutXML() {
		// Clear output stream
		os = new ByteArrayOutputStream();
		// Set up
		Exercise distex = new DistanceExercise("Run", "Do it", 5.0);
		Set<Exercise> exList = new HashSet<Exercise>();
		exList.add(distex);
		Workout wo = new Workout("Chest Day", "Work that", exList);
		
		// First, test marshal
		try {
			woMarshaller.marshal(wo, os);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		assertEquals(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><workout><id>0</id><name>Chest Day</name><description>Work that</description><exercises><exercise xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"distanceExercise\"><id>0</id><name>Run</name><description>Do it</description><distance>5.0</distance></exercise></exercises></workout>",
				os.toString());
		
		// Then, test unmarshal
		StringReader reader = new StringReader(os.toString());
		Workout unmarshalledWo = null;
		try {
			unmarshalledWo = (Workout) woUnmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		assertEquals(unmarshalledWo,wo);
	}
	
	@Test
	public void testsWorkoutMapping() {
		// Clear output stream
		os = new ByteArrayOutputStream();
		// Set up
		nz.ac.auckland.fitness.domain.Exercise distex = new nz.ac.auckland.fitness.domain.DistanceExercise(0,"Run", "Do it", 5.0);
		Set<nz.ac.auckland.fitness.domain.Exercise> exList = new HashSet<nz.ac.auckland.fitness.domain.Exercise>();
		exList.add(distex);
		
		Tag t1 = new Tag("Running");
		Set<Tag> tagList = new HashSet<Tag>();
		tagList.add(t1);
		nz.ac.auckland.fitness.domain.Workout woMap = new nz.ac.auckland.fitness.domain.Workout(0, "Chest Day", "Work that", tagList, exList);
		
		// Then try to map to DTO workout
		Workout wo = WorkoutMapper.toDto(woMap);
		
		// First, test marshal
		try {
			woMarshaller.marshal(wo, os);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		assertEquals(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><workout><id>0</id><name>Chest Day</name><description>Work that</description><exercises><exercise xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"distanceExercise\"><id>0</id><name>Run</name><description>Do it</description><distance>5.0</distance></exercise></exercises></workout>",
				os.toString());
		
		// Then, test unmarshal
		StringReader reader = new StringReader(os.toString());
		Workout unmarshalledWo = null;
		try {
			unmarshalledWo = (Workout) woUnmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		assertEquals(unmarshalledWo,wo);
	}
	
	@Test
	public void testsWorkoutRecordXML() {
		// Clear output stream
		os = new ByteArrayOutputStream();
		// Set up
		WorkoutRecord worec = new WorkoutRecord(0, 0, new LocalDate(2015, 7, 18), Duration.ZERO);
		
		// First, test marshal
		try {
			woReMarshaller.marshal(worec, os);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		assertEquals(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><workoutRecord><id>0</id><person_id>0</person_id><workout_id>0</workout_id><date>2015-07-18</date><duration>PT0S</duration></workoutRecord>",
				os.toString());
		
		// Then, test unmarshal
		StringReader reader = new StringReader(os.toString());
		WorkoutRecord unmarshalledWoRec = null;
		try {
			unmarshalledWoRec = (WorkoutRecord) woReUnmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		assertEquals(unmarshalledWoRec,worec);
	}
	
	@Test
	public void testsUserXML() {
		// Clear output stream
		os = new ByteArrayOutputStream();
		// Set up
		WorkoutRecord worec = new WorkoutRecord(0, 0, new LocalDate(2015, 7, 18), Duration.ZERO);
		User usr = new User("Mitchell Musso",worec);
		
		// First, test marshal
		try {
			usrMarshaller.marshal(usr, os);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		assertEquals(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><user><id>0</id><name>Mitchell Musso</name><last_record><id>0</id><person_id>0</person_id><workout_id>0</workout_id><date>2015-07-18</date><duration>PT0S</duration></last_record></user>",
				os.toString());
		
		// Then, test unmarshal
		StringReader reader = new StringReader(os.toString());
		User unmarshalledUsr = null;
		try {
			unmarshalledUsr = (User) usrUnmarshaller.unmarshal(reader);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		assertEquals(unmarshalledUsr,usr);
	}
}
