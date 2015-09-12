package nz.ac.auckland.fitness.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

import org.apache.commons.lang3.builder.HashCodeBuilder;

/*
 * Class used to represent a tag
 */
@XmlAccessorType(XmlAccessType.FIELD)
public abstract class Tag {
	
	@XmlElement(name="text")
	private String _text;
	
	protected Tag() {}
	
	public Tag(String text) {
		_text = text;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Tag))
            return false;
        if (obj == this)
            return true;

        Tag rhs = (Tag) obj;
        return this._text.equalsIgnoreCase(rhs._text);
	}
	
	@Override
	public int hashCode() {
		return new HashCodeBuilder(17, 31). 
	            append(_text).
	            toHashCode();
	}
	
	@Override
	public String toString() {
		return _text;
	}

}
