package de.wbongartz.fuzzy_logic_parser.evaluable.element;

import de.wbongartz.fuzzy_logic_parser.set.DecimalElement;
import de.wbongartz.fuzzy_logic_parser.set.SetElement;
import de.wbongartz.fuzzy_logic_parser.evaluable.BinaryOperator;
import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;
import de.wbongartz.fuzzy_logic_parser.fuzzyset.FuzzyElement;

/**
 * @author Wolfgang Bongartz
 *
 */
public class CreateFuzzyElement implements BinaryOperator<SetElement,SetElement,SetElement> {

	/* (non-Javadoc)
	 * @see evaluable.BinaryOperator#evaluate(evaluable.Evaluable, evaluable.Evaluable)
	 */
	@Override
	public SetElement evaluate(Evaluable<SetElement> leftOperator, Evaluable<SetElement> rightOperator) {
		DecimalElement d = new DecimalElement(rightOperator.evaluate().get_value());
		return new FuzzyElement(leftOperator.evaluate(), d);
	}

}
