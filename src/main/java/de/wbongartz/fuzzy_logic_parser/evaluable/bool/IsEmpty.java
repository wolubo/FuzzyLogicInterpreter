package de.wbongartz.fuzzy_logic_parser.evaluable.bool;

import de.wbongartz.fuzzy_logic_parser.set.Set;
import de.wbongartz.fuzzy_logic_parser.set.SetElement;
import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;
import de.wbongartz.fuzzy_logic_parser.evaluable.UnaryOperator;
import de.wbongartz.fuzzy_logic_parser.set.BooleanElement;

/**
 * @author Wolfgang Bongartz
 *
 */
public class IsEmpty implements UnaryOperator<Set,SetElement> {

	/* (non-Javadoc)
	 * @see evaluable.UnaryOperator#evaluate(evaluable.Evaluable)
	 */
	@Override
	public SetElement evaluate(Evaluable<Set> operand) {
		return new BooleanElement( operand.evaluate().isEmptySet() );
	}


}
