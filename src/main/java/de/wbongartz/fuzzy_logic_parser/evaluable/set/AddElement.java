package de.wbongartz.fuzzy_logic_parser.evaluable.set;

import de.wbongartz.fuzzy_logic_parser.set.Set;
import de.wbongartz.fuzzy_logic_parser.set.SetElement;
import de.wbongartz.fuzzy_logic_parser.evaluable.BinaryOperator;
import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;

/**
 * @author Wolfgang Bongartz
 *
 */
public class AddElement implements BinaryOperator<Set,SetElement,Set> {

	/* (non-Javadoc)
	 * @see evaluable.BinaryOperator#evaluate(evaluable.Evaluable, evaluable.Evaluable)
	 */
	@Override
	public Set evaluate(Evaluable<Set> leftOperator, Evaluable<SetElement> rightOperator) {
		leftOperator.evaluate().addElement(rightOperator.evaluate());
		return null;
	}

}
