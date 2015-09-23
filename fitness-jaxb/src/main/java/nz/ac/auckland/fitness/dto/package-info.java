@XmlJavaTypeAdapters({
    @XmlJavaTypeAdapter(type=LocalDate.class, 
        value=LocalDateAdapter.class),
    @XmlJavaTypeAdapter(type=Duration.class,
        value=DurationAdapter.class)
})

package nz.ac.auckland.fitness.dto;
 
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
 
import nz.ac.auckland.fitness.jaxb.LocalDateAdapter;
import nz.ac.auckland.fitness.jaxb.DurationAdapter;

import org.joda.time.Duration;
import org.joda.time.LocalDate;