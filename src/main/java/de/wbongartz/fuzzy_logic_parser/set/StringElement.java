package de.wbongartz.fuzzy_logic_parser.set;

import java.math.BigDecimal;

public class StringElement extends SetElement {
	
	private String _value;
	
	public StringElement(String value) {
		_value = value;
	}

	public StringElement(SetElement element) {
		_value = element.toString();
	}

	@Override
	public boolean equals(Object o) {
		if( this==o ) return true;
		if( ! (o instanceof StringElement) ) return false;
		StringElement otherElement = (StringElement) o;
		return _value.equals(otherElement._value);
	}
	
	@Override
	public int hashCode() {
		return _value.hashCode();
	}

	@Override
	public String toString() {
		return _value;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(SetElement o) {
		if(o instanceof StringElement) 
			return this.compareTo((StringElement)o);
		else {
			StringElement c = new StringElement(o);
			return this.compareTo(c);
		}
	}

	public int compareTo(StringElement o) {
		return _value.compareTo(o._value);
	}

	/* (non-Javadoc)
	 * @see set.SetElement#get_value()
	 */
	@Override
	public BigDecimal get_value() {
		try {
			return new BigDecimal(Integer.parseInt(_value));
		} catch(Exception ex) {
			throw new UnsupportedOperationException("This StringElement has no value!");
		}
	}

}
