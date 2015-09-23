@XmlJavaTypeAdapters({
    @XmlJavaTypeAdapter(type=LocalDate.class, 
        value=LocalDateAdapter.class),
    @XmlJavaTypeAdapter(type=Duration.class,
        value=DurationAdapter.class)
})

@org.hibernate.annotations.GenericGenerator( name = "ID GENERATOR",
strategy = "enhanced-sequence"
)

package nz.ac.auckland.fitness.domain;
 
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapters;
 
import nz.ac.auckland.fitness.jaxb.LocalDateAdapter;
import nz.ac.auckland.fitness.jaxb.DurationAdapter;

import org.joda.time.Duration;
import org.joda.time.LocalDate;