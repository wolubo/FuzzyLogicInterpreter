package de.wbongartz.fuzzy_logic_parser.evaluable.bool;

import de.wbongartz.fuzzy_logic_parser.set.BooleanElement;
import de.wbongartz.fuzzy_logic_parser.set.SetElement;
import de.wbongartz.fuzzy_logic_parser.evaluable.BinaryOperator;
import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;

/**
 * @author Wolfgang Bongartz
 *
 */
public class And implements BinaryOperator<SetElement,SetElement,SetElement> {

	/* (non-Javadoc)
	 * @see evaluable.BinaryOperator#evaluate(evaluable.Evaluable, evaluable.Evaluable)
	 */
	@Override
	public SetElement evaluate(Evaluable<SetElement> leftOperator, Evaluable<SetElement> rightOperator) {
		SetElement l = leftOperator.evaluate();
		if( ! ( l instanceof BooleanElement)) throw new IllegalArgumentException(l.toString() + " is not a boolean value!");
		BooleanElement left = (BooleanElement) l;
		
		SetElement r = rightOperator.evaluate();
		if( ! ( r instanceof BooleanElement)) throw new IllegalArgumentException(r.toString() + " is not a boolean value!");
		BooleanElement right = (BooleanElement) r;
		
		return new BooleanElement(left.toBoolean() && right.toBoolean());
	}

}
