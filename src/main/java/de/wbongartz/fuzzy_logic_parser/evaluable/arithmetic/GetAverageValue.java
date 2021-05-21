
package de.wbongartz.fuzzy_logic_parser.evaluable.arithmetic;

import de.wbongartz.fuzzy_logic_parser.set.DecimalElement;
import de.wbongartz.fuzzy_logic_parser.set.Set;
import de.wbongartz.fuzzy_logic_parser.set.SetElement;
import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;
import de.wbongartz.fuzzy_logic_parser.evaluable.UnaryOperator;
import de.wbongartz.fuzzy_logic_parser.fuzzyset.FuzzySet;

/**
 * Computes the average value of the set ("relativer Betrag"). This exists only for fuzzy sets
 * @author Wolfgang Bongartz
 */
public class GetAverageValue implements UnaryOperator<Set,SetElement> {

	/* (non-Javadoc)
	 * @see evaluable.UnaryOperator#evaluate(evaluable.Evaluable)
	 * @exception IllegalArgumentException
	 */
	@Override
	public SetElement evaluate(Evaluable<Set> operand) {
		Set set = operand.evaluate();
		if( ! (set instanceof FuzzySet) ) throw new IllegalArgumentException("Only fuzzy sets have an average absolute value!");
		FuzzySet fuzzySet = (FuzzySet)set;
		return new DecimalElement(fuzzySet.getAverageValue());
	}

}
