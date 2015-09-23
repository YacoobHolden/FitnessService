package nz.ac.auckland.fitness.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import nz.ac.auckland.fitness.dto.Exercise;
import nz.ac.auckland.fitness.dto.DistanceExercise;
import nz.ac.auckland.fitness.domain.Tag;
import nz.ac.auckland.fitness.dto.Workout;
import nz.ac.auckland.fitness.services.FitnessResolver;

import org.junit.BeforeClass;
import org.junit.Test;

public class JaxBTest {

	private static OutputStream os;
	private static JAXBContext exContext;
	private static Marshaller exMarshaller;
	private static JAXBContext woContext;
	private static Marshaller woMarshaller;

	/**
	 * One-time setup method that creates a Web service client.
	 */
	@BeforeClass
	public static void setUpJAXB() {
		os = new ByteArrayOutputStream();
		// Setup ex testing stuff
		exContext = new FitnessResolver().getContext(DistanceExercise.class);
		try {
			exMarshaller = exContext.createMarshaller();
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
	public void testsExerciseXML() {
		// Clear output stream
		os = new ByteArrayOutputStream();
		// Do test
		Exercise distex = new DistanceExercise("Run", "Do it", 5.0);
		try {
			exMarshaller.marshal(distex, os);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		System.out.println(os.toString());
		assertEquals(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><distance_exercise><id>0</id><name>Run</name><description>Do it</description><distance>5.0</distance></distance_exercise>",
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
		System.out.println(os.toString());
		assertEquals(
				"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><workout><id>0</id><name>Chest Day</name><description>Work that</description><exercises><exercise xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" xsi:type=\"distanceExercise\"><id>0</id><name>Run</name><description>Do it</description><distance>5.0</distance></exercise></exercises></workout>",
				os.toString());
	}
}