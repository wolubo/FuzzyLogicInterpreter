
package de.wbongartz.fuzzy_logic_parser.evaluable.bool;

import de.wbongartz.fuzzy_logic_parser.set.Set;
import de.wbongartz.fuzzy_logic_parser.set.SetElement;
import de.wbongartz.fuzzy_logic_parser.evaluable.BinaryOperator;
import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;
import de.wbongartz.fuzzy_logic_parser.set.BooleanElement;

/**
 * @author Wolfgang Bongartz
 *
 */
public class IsUnaryComplement implements BinaryOperator<Set,Set,SetElement>  {

	/* (non-Javadoc)
	 * @see evaluable.BinaryOperator#evaluate(evaluable.Evaluable, evaluable.Evaluable)
	 */
	@Override
	public SetElement evaluate(Evaluable<Set> leftOperator, Evaluable<Set> rightOperator) {
		return new BooleanElement( leftOperator.evaluate().isComplementSet(rightOperator.evaluate() ) );
	}

}
