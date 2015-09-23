package nz.ac.auckland.fitness.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import nz.ac.auckland.fitness.dto.Exercise;
import nz.ac.auckland.fitness.dto.DistanceExercise;
import nz.ac.auckland.fitness.dto.SetExercise;
import nz.ac.auckland.fitness.dto.WeightExercise;
import nz.ac.auckland.fitness.domain.Tag;
import nz.ac.auckland.fitness.dto.Workout;
import nz.ac.auckland.fitness.services.FitnessResolver;

import org.junit.BeforeClass;
import org.junit.Test;

public class JaxBTest {

	private static OutputStream os;
	private static JAXBContext distExContext;
	private static Marshaller distExMarshaller;
	private static JAXBContext weightExContext;
	private static Marshaller weightExMarshaller;
	private static JAXBContext setExContext;
	private static Marshaller setExMarshaller;
	private static JAXBContext woContext;
	private static Marshaller woMarshaller;

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
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Setup weight exercise testing stuff
		weightExContext = new FitnessResolver().getContext(WeightExercise.class);
		try {
			weightExMarshaller = weightExContext.createMarshaller();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Setup set exercise testing stuff
		setExContext = new FitnessResolver().getContext(SetExercise.class);
		try {
			setExMarshaller = setExContext.createMarshaller();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// Setup wo testing stuff
		woContext = new FitnessResolver().getContext(Workout.class);
		try {
			woMarshaller = woContext.createMarshaller();
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
		// Do test
		Exercise distex = new DistanceExercise("Run", "Do it", 5.0);
		try {
			distExMarshaller.marshal(distex, os);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		assertEquals(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><distance_exercise><id>0</id><name>Run</name><description>Do it</description><distance>5.0</distance></distance_exercise>",
				os.toString());
	}
	
	@Test
	public void testSetExerciseXML() {
		// Clear output stream
		os = new ByteArrayOutputStream();
		// Do test
		List<Integer> sets = new ArrayList<Integer>();
		sets.add(25);
		sets.add(25);
		sets.add(25);
		Exercise setex = new SetExercise("Press Ups", "Do them", sets);
		try {
			setExMarshaller.marshal(setex, os);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		System.out.println(os.toString());
		assertEquals(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><set_exercise><id>0</id><name>Press Ups</name><description>Do them</description><reps><rep>25</rep><rep>25</rep><rep>25</rep></reps></set_exercise>",
				os.toString());
	}
	
	@Test
	public void testWeightExerciseXML() {
		// Clear output stream
		os = new ByteArrayOutputStream();
		// Do test
		List<Integer> sets = new ArrayList<Integer>();
		sets.add(10);
		sets.add(10);
		sets.add(10);
		List<Double> weights = new ArrayList<Double>();
		weights.add(10.0);
		weights.add(15.0);
		weights.add(20.0);
		Exercise weightex = new WeightExercise("Chest Press 10-20 x 3", "Do them", sets, weights);
		try {
			weightExMarshaller.marshal(weightex, os);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		System.out.println(os.toString());
		assertEquals(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><weight_exercise><id>0</id><name>Chest Press 10-20 x 3</name><description>Do them</description><reps><rep>10</rep><rep>10</rep><rep>10</rep></reps><weights><weight>10.0</weight><weight>15.0</weight><weight>20.0</weight></weights></weight_exercise>",
				os.toString());
	}

	@Test
	public void testsWorkoutXML() {
		// Clear output stream
		os = new ByteArrayOutputStream();
		// Make test objects
		Exercise distex = new DistanceExercise("Run", "Do it", 5.0);
		Set<Exercise> exList = new HashSet<Exercise>();
		exList.add(distex);
		Workout wo = new Workout("Chest Day", "Work that", exList);
		try {
			woMarshaller.marshal(wo, os);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		assertEquals(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><workout><id>0</id><name>Chest Day</name><description>Work that</description><exercises><exercise xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"distanceExercise\"><id>0</id><name>Run</name><description>Do it</description><distance>5.0</distance></exercise></exercises></workout>",
				os.toString());
	}
}
