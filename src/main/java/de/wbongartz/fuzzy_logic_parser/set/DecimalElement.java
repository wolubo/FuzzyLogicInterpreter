package de.wbongartz.fuzzy_logic_parser.set;

import java.math.BigDecimal;

public class DecimalElement extends SetElement {
	
	private BigDecimal _value;
	
	public DecimalElement(BigDecimal value) {
		_value = value;
	}
	
	public DecimalElement(IntegerElement value) {
		_value = value.get_value();
	}
	
	public DecimalElement(int value) {
		_value = new BigDecimal(value);
	}
	
	@Override
	public BigDecimal get_value() {
		return _value;
	}

	@Override
	public boolean equals(Object o) {
		if(this==o) return true;
		if( ! (o instanceof DecimalElement) ) return false;
		DecimalElement otherElement = (DecimalElement) o;
		return ( otherElement._value.compareTo(this._value)==0 ); 
	}

	@Override
	public int hashCode() {
		return _value.hashCode();
	}

	public int compareTo(DecimalElement o) {
		String lhs, rhs;
		lhs = this.toString();
		rhs = o.toString();
		return lhs.compareTo(rhs);
	}
	
	@Override
	public int compareTo(SetElement o) {
		if(o instanceof DecimalElement) return this.compareTo((DecimalElement)o);
		if(o instanceof IntegerElement) return this.compareTo(new DecimalElement((IntegerElement)o));
		throw new IllegalArgumentException("Cannot compare a RealNumberElement with a " + o.getClass().getName() + "!");
	}

	@Override
	public String toString() {
		return _value.toString();
	}
}
