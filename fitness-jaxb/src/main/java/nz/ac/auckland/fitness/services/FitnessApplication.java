package nz.ac.auckland.fitness.services;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import java.util.HashSet;
import java.util.Set;

/**
 * Application subclass for the Parolee Web service.
 * 
 * @author Jacob Holden
 *
 */
@ApplicationPath("/services")
public class FitnessApplication extends Application
{
   private Set<Object> singletons = new HashSet<Object>();
   private Set<Class<?>> classes = new HashSet<Class<?>>();

   public FitnessApplication()
   {
	  // Register the ParoleeResource class to handle HTTP requests.
	  classes.add(FitnessResourceImpl.class);
      
      // Register the ContextResolver class for JAXB.
      classes.add(FitnessResolver.class);
     
   }

   @Override
   public Set<Object> getSingletons()
   {
      return singletons;
   }
   
   @Override
   public Set<Class<?>> getClasses()
   {
      return classes;
   }
}
