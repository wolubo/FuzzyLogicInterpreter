
package de.wbongartz.fuzzy_logic_parser.evaluable.arithmetic;

import java.math.BigDecimal;
import java.math.RoundingMode;

import de.wbongartz.fuzzy_logic_parser.evaluable.BinaryOperator;
import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;
import de.wbongartz.fuzzy_logic_parser.set.DecimalElement;
import de.wbongartz.fuzzy_logic_parser.set.SetElement;

/**
 * @author Wolfgang Bongartz
 *
 */
public class Round implements BinaryOperator<SetElement,SetElement,SetElement> {

	/**
	 * @see evaluable.BinaryOperator#evaluate(evaluable.Evaluable, evaluable.Evaluable)
	 */
	@Override
	public SetElement evaluate(Evaluable<SetElement> leftOperator, Evaluable<SetElement> rightOperator) {
		BigDecimal number = leftOperator.evaluate().get_value();
		int digits = rightOperator.evaluate().get_value().intValue();
		BigDecimal rounded_value = number.setScale(digits,RoundingMode.HALF_UP);
		DecimalElement result = new DecimalElement(rounded_value);			
		return result;
	}

}
