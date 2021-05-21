package de.wbongartz.fuzzy_logic_parser.set;

import java.math.BigDecimal;

public abstract class SetElement implements Comparable<SetElement> {
	
	/**
	 * Try to convert the element to a number.
	 * @return
	 */
	public BigDecimal get_value() {
		throw new UnsupportedOperationException("A " + getElementType() + " has no value!");
	}

	/**
	 * Returns a string representation of the element type.
	 * @return
	 */
	public String getElementType() {
		return this.getClass().getSimpleName();
	}

}
