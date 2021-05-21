
package de.wbongartz.fuzzy_logic_parser.evaluable.arithmetic;

import java.math.BigDecimal;

import de.wbongartz.fuzzy_logic_parser.evaluable.BinaryOperator;
import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;
import de.wbongartz.fuzzy_logic_parser.set.DecimalElement;
import de.wbongartz.fuzzy_logic_parser.set.SetElement;

/**
 * @author Wolfgang Bongartz
 *
 */
public class Subtraction implements BinaryOperator<SetElement,SetElement,SetElement> {

	/**
	 * @see evaluable.BinaryOperator#evaluate(evaluable.Evaluable, evaluable.Evaluable)
	 */
	@Override
	public SetElement evaluate(Evaluable<SetElement> leftOperator, Evaluable<SetElement> rightOperator) {
		BigDecimal left = leftOperator.evaluate().get_value();
		BigDecimal right = rightOperator.evaluate().get_value();
		DecimalElement result = new DecimalElement(left.subtract(right));
		return result;
	}

}
