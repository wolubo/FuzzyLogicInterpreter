package de.wbongartz.fuzzy_logic_parser.set;

import java.math.BigDecimal;

public class IntegerElement extends SetElement  {

	private int _value;
	
	public IntegerElement(int value) {
		_value = value;
	}
	
	@Override
	public BigDecimal get_value() {
		return new BigDecimal(_value);
	}

	@Override
	public boolean equals(Object o) {
		if(this==o) return true;
		if( ! (o instanceof IntegerElement) ) return false;
		IntegerElement otherElement = (IntegerElement) o;
		return _value==otherElement._value;
	}

	@Override
	public int hashCode() {
		return (new Integer(_value)).hashCode();
	}

	public int compareTo(DecimalElement o) {
		return (new DecimalElement(this)).compareTo(o);
	}

	public int compareTo(IntegerElement o) {
		int lhs, rhs;
		lhs = this._value;
		rhs = o._value;
		if(lhs==rhs)
			return 0;
		else if(lhs>rhs)
			return 1;
		else 
			return -1;
	}

	@Override
	public int compareTo(SetElement o) {
		if(o instanceof IntegerElement) return this.compareTo((IntegerElement)o);
		if(o instanceof DecimalElement) return (new DecimalElement(this)).compareTo(o);
		throw new IllegalArgumentException("Cannot compare a NaturalNumberElement with a " + o.getClass().getName() + "!");
	}
	
	public BigDecimal asBigDecimal() {
		return new BigDecimal(_value);
	}

	@Override
	public String toString() {
		return (new Integer(_value)).toString();
	}
}
