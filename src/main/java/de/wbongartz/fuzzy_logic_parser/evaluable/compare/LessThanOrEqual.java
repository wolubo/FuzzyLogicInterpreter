
package de.wbongartz.fuzzy_logic_parser.evaluable.compare;

import java.math.BigDecimal;
import de.wbongartz.fuzzy_logic_parser.set.BooleanElement;
import de.wbongartz.fuzzy_logic_parser.set.SetElement;
import de.wbongartz.fuzzy_logic_parser.evaluable.BinaryOperator;
import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;

/**
 * @author Wolfgang Bongartz
 *
 */
public class LessThanOrEqual implements BinaryOperator<SetElement,SetElement,SetElement> {

	/* (non-Javadoc)
	 * @see evaluable.BinaryOperator#evaluate(evaluable.Evaluable, evaluable.Evaluable)
	 */
	@Override
	public SetElement evaluate(Evaluable<SetElement> leftOperator, Evaluable<SetElement> rightOperator) {
		BigDecimal left  = leftOperator.evaluate().get_value();
		BigDecimal right = rightOperator.evaluate().get_value();
		return new BooleanElement(left.compareTo(right)<=0);
	}

}
