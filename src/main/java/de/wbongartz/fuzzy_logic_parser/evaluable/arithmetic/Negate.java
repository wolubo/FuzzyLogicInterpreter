package de.wbongartz.fuzzy_logic_parser.evaluable.arithmetic;

import java.math.BigDecimal;

import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;
import de.wbongartz.fuzzy_logic_parser.evaluable.UnaryOperator;
import de.wbongartz.fuzzy_logic_parser.set.DecimalElement;
import de.wbongartz.fuzzy_logic_parser.set.SetElement;

/**
 * @author Wolfgang Bongartz
 *
 */
public class Negate implements UnaryOperator<SetElement,SetElement> {

	/* (non-Javadoc)
	 * @see arithmetic.UnaryOperator#compute(arithmetic.Computable)
	 */
	@Override
	public SetElement evaluate(Evaluable<SetElement> operand) {
		BigDecimal value = operand.evaluate().get_value();
		DecimalElement result = new DecimalElement(value.negate());
		return result;
	}
	
}
