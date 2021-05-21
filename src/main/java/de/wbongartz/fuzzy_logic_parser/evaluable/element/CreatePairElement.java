
package de.wbongartz.fuzzy_logic_parser.evaluable.element;

import de.wbongartz.fuzzy_logic_parser.set.PairElement;
import de.wbongartz.fuzzy_logic_parser.set.SetElement;
import de.wbongartz.fuzzy_logic_parser.evaluable.BinaryOperator;
import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;

/**
 * @author Wolfgang Bongartz
 *
 */
public class CreatePairElement implements BinaryOperator<SetElement,SetElement,SetElement> {

	/* (non-Javadoc)
	 * @see evaluable.BinaryOperator#evaluate(evaluable.Evaluable, evaluable.Evaluable)
	 */
	@Override
	public SetElement evaluate(Evaluable<SetElement> leftOperator, Evaluable<SetElement> rightOperator) {
		return new PairElement(leftOperator.evaluate(), rightOperator.evaluate());
	}

}
