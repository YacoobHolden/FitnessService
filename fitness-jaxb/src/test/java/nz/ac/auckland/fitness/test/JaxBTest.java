package nz.ac.auckland.fitness.test;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.util.HashSet;
import java.util.Set;

import javax.naming.spi.ObjectFactory;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import nz.ac.auckland.fitness.domain.DistanceExercise;
import nz.ac.auckland.fitness.domain.Exercise;
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
	private static JAXBContext tagContext;
	private static Marshaller tagMarshaller;
	
	/**
	 * One-time setup method that creates a Web service client.
	 */
	@BeforeClass
	public static void setUpJAXB() {
		os = new ByteArrayOutputStream();
		// Setup ex testing stuff
		exContext = new FitnessResolver().getContext(Exercise.class);
	    try {
			exMarshaller = exContext.createMarshaller();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    // Setup wo testing stuff
 		woContext = new FitnessResolver().getContext(Workout.class);
 	    try {
 			woMarshaller = exContext.createMarshaller();
 		} catch (JAXBException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
 	    // Setup wo testing stuff
 		tagContext = new FitnessResolver().getContext(Tag.class);
 	    try {
 			tagMarshaller = exContext.createMarshaller();
 		} catch (JAXBException e) {
 			// TODO Auto-generated catch block
 			e.printStackTrace();
 		}
	}

	@Test
	public void testsPass() {}
	
	@Test
	public void testsExerciseXML() {
		// Clear output stream
		os = new ByteArrayOutputStream();
		// Do test
		DistanceExercise distex = new DistanceExercise("Run","Do it",5.0);
		try {
			exMarshaller.marshal(distex, os);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		System.out.println(os.toString());
		assertEquals(os.toString(),"<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?><exercise><name>Run</name><description>Do it</description></exercise>");
	}
	
	@Test
	public void testsWorkoutXML() {
		// Clear output stream
		os = new ByteArrayOutputStream();
		// Do test
		Tag t1 = new Tag("Hardcore");
		Set<Tag> tagList = new HashSet<Tag>();
		tagList.add(t1);
		Set<String> exList = new HashSet<String>();
		exList.add("Bench Press");
		Workout wo = new Workout("Chest Day","Work that",tagList,exList);
		try {
			woMarshaller.marshal(wo, os);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		System.out.println(os.toString());
	}
}
