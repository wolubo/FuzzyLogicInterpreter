
package de.wbongartz.fuzzy_logic_parser.evaluable.set;

import de.wbongartz.fuzzy_logic_parser.set.Set;
import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;
import de.wbongartz.fuzzy_logic_parser.evaluable.UnaryOperator;

/**
 * @author Wolfgang Bongartz
 *
 */
public class UnaryComplement implements UnaryOperator<Set,Set> {

	/* (non-Javadoc)
	 * @see evaluable.UnaryOperator#evaluate(evaluable.Evaluable)
	 */
	@Override
	public Set evaluate(Evaluable<Set> operand) {
		Set op = operand.evaluate();
		Set result = op.createComplementSet();
		return result;
	}

}
