package nz.ac.auckland.fitness.jaxb;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import org.joda.time.Duration;

/** 
 * JAXB XML adapter to convert between Joda Duration instances and Strings.
 * Duration objects are marshalled as Strings, and unmarshalled back into 
 * Duration instances.
 * 
 */
public class DurationAdapter extends XmlAdapter<String, Duration> {

	@Override
	public Duration unmarshal(String durAsString) throws Exception {
		if(durAsString == null) {
			return null;
		}
		
		return new Duration(durAsString);
	}

	@Override
	public String marshal(Duration dur) throws Exception {
		if(dur == null) {
			return null;
		}
		
		return dur.toString();
	}
}