
package de.wbongartz.fuzzy_logic_parser.evaluable.arithmetic;

import de.wbongartz.fuzzy_logic_parser.set.*;
import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;
import de.wbongartz.fuzzy_logic_parser.evaluable.UnaryOperator;

/**
 * Computes the cardinal number of the set ("Mächtigkeit", "Kardinalzahl").
 * @author Wolfgang Bongartz
 *
 */
public class GetCardinalValue implements UnaryOperator<Set,SetElement> {

	/* (non-Javadoc)
	 * @see evaluable.UnaryOperator#evaluate(evaluable.Evaluable)
	 */
	@Override
	public SetElement evaluate(Evaluable<Set> operand) {
		return new IntegerElement(operand.evaluate().getCardinalNumber()); 
	}

}
