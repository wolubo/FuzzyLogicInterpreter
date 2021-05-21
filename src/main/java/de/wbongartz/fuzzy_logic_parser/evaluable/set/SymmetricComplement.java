
package de.wbongartz.fuzzy_logic_parser.evaluable.set;

import de.wbongartz.fuzzy_logic_parser.set.Set;
import de.wbongartz.fuzzy_logic_parser.evaluable.BinaryOperator;
import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;

/**
 * @author Wolfgang Bongartz
 *
 */
public class SymmetricComplement implements BinaryOperator<Set,Set,Set> {
	
	/* (non-Javadoc)
	 * @see evaluable.BinaryOperator#evaluate(evaluable.Evaluable, evaluable.Evaluable)
	 */
	@Override
	public Set evaluate(Evaluable<Set> leftOperator, Evaluable<Set> rightOperator) {
		Set left = leftOperator.evaluate();
		Set right = rightOperator.evaluate();
		Set result = left.createSymmetricComplementSet(right);
		return result;
	}

}
