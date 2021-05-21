package de.wbongartz.fuzzy_logic_parser.fuzzyset;

import java.math.BigDecimal;

import de.wbongartz.fuzzy_logic_parser.set.DecimalElement;
import de.wbongartz.fuzzy_logic_parser.set.PairElement;
import de.wbongartz.fuzzy_logic_parser.set.SetElement;

/**
 * 
 * @author Wolfgang Bongartz
 *
 */
public class FuzzyElement extends PairElement {
	
	public FuzzyElement(SetElement element, DecimalElement value) {
		super(element, value);
		set_right(value); // To make sure that the value is checked.
	}

	public FuzzyElement(SetElement element, BigDecimal value) {
		this(element, new DecimalElement(value));
	}

	@Override
	public BigDecimal get_value() {
		return ((DecimalElement)get_right()).get_value();
	}

	
	@Override
	protected void set_right(SetElement right) {
		if(!(right instanceof DecimalElement)) throw new IllegalArgumentException("Parameter 'right' must be of type DecimalElement!");
		DecimalElement r = (DecimalElement) right;
		if(r.get_value().compareTo(BigDecimal.ZERO)<0) throw new IllegalArgumentException("Parameter 'right' must not be less than 0!");
		if(r.get_value().compareTo(BigDecimal.ONE)>0) throw new IllegalArgumentException("Parameter 'right' must not be greater than 1!");
		super.set_right(right);
	}

	public SetElement get_element() {
		return get_left();
	}
	
	@Override
	public boolean equals(Object o) {
		if(this==o) return true;
		if( ! (o instanceof FuzzyElement) ) return false;
		FuzzyElement fe = (FuzzyElement) o;
		return ( get_left().equals( fe.get_left() ) );
	}
	
	@Override
	public int hashCode() {
		return get_left().hashCode();
	}

	@Override
	public int compareTo(SetElement o) {
		if(this==o) return 0;
		int result=0;
		if(o instanceof FuzzyElement) {
			FuzzyElement o_fs = (FuzzyElement) o;
			result = this.get_element().compareTo(o_fs.get_element());
		} else if(o instanceof PairElement) {
			PairElement pe = (PairElement) o;
			result = pe.compareTo(this) * -1;
		} else {
			result = this.get_element().compareTo(o);
		}
		return result;
	}
	
	@Override
	public String toString() {
		String retVal = "[";
		retVal+=get_left().toString();
		retVal+=",";
		retVal+=get_right().toString();
		retVal+="]";
		return retVal;
	}
}
