package de.wbongartz.fuzzy_logic_parser.set;


public class BooleanElement extends SetElement  {

	private boolean _value;
	
	public BooleanElement(boolean value) {
		_value = value;
	}
	
	@Override
	public boolean equals(Object o) {
		if(this==o) return true;
		if( ! (o instanceof BooleanElement) ) return false;
		BooleanElement otherElement = (BooleanElement) o;
		return _value==otherElement._value;
	}

	@Override
	public int hashCode() {
		return (new Boolean(_value)).hashCode();
	}

	@Override
	public int compareTo(SetElement o) {
		if(o instanceof BooleanElement) {
			BooleanElement be=(BooleanElement)o;
			boolean lhs, rhs;
			lhs = this._value;
			rhs = be._value;
			if(lhs==rhs)
				return 0;
			else if(lhs)
				return 1;
			else 
				return -1;
		}
		throw new IllegalArgumentException("Cannot compare a NaturalNumberElement with a " + o.getClass().getName() + "!");
	}
	
	public boolean toBoolean() {
		return _value;
	}
	
	@Override
	public String toString() {
		return (new Boolean(_value)).toString();
	}
}
