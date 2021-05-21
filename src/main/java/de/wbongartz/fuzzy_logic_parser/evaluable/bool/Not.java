
package de.wbongartz.fuzzy_logic_parser.evaluable.bool;

import de.wbongartz.fuzzy_logic_parser.set.SetElement;
import de.wbongartz.fuzzy_logic_parser.evaluable.UnaryOperator;
import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;
import de.wbongartz.fuzzy_logic_parser.set.BooleanElement;

/**
 * @author Wolfgang Bongartz
 *
 */
public class Not implements UnaryOperator<SetElement,SetElement> {

	/* (non-Javadoc)
	 * @see evaluable.UnaryOperator#evaluate(evaluable.Evaluable)
	 */
	@Override
	public SetElement evaluate(Evaluable<SetElement> operand) {
		SetElement l = operand.evaluate();
		if( ! ( l instanceof BooleanElement)) throw new IllegalArgumentException(l.toString() + " is not a boolean value!");
		BooleanElement left = (BooleanElement) l;
		
		return new BooleanElement( ! left.toBoolean() );
	}

}
