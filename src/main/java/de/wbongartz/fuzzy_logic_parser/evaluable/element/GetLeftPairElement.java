
package de.wbongartz.fuzzy_logic_parser.evaluable.element;

import de.wbongartz.fuzzy_logic_parser.set.PairElement;
import de.wbongartz.fuzzy_logic_parser.set.SetElement;
import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;
import de.wbongartz.fuzzy_logic_parser.evaluable.UnaryOperator;

/**
 * @author Wolfgang Bongartz
 *
 */
public class GetLeftPairElement implements UnaryOperator<SetElement,SetElement> {

	/* (non-Javadoc)
	 * @see evaluable.UnaryOperator#evaluate(evaluable.Evaluable)
	 */
	@Override
	public SetElement evaluate(Evaluable<SetElement> operand) {
		SetElement op = operand.evaluate();
		if( ! (op instanceof PairElement) ) throw new IllegalArgumentException("This isn't a pair element!");
		PairElement p = (PairElement) op;
		return p.get_left();	
	}

}
