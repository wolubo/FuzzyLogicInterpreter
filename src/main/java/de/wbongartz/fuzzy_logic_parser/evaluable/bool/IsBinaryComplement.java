package de.wbongartz.fuzzy_logic_parser.evaluable.bool;

import de.wbongartz.fuzzy_logic_parser.set.Set;
import de.wbongartz.fuzzy_logic_parser.set.SetElement;
import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;
import de.wbongartz.fuzzy_logic_parser.evaluable.TernaryOperator;
import de.wbongartz.fuzzy_logic_parser.set.BooleanElement;

/**
 * @author Wolfgang Bongartz
 *
 */
public class IsBinaryComplement implements TernaryOperator<Set,SetElement> {

	/* (non-Javadoc)
	 * @see evaluable.TernaryOperator#evaluate(evaluable.Evaluable, evaluable.Evaluable, evaluable.Evaluable)
	 */
	@Override
	public SetElement evaluate(Evaluable<Set> operandOne, Evaluable<Set> operandTwo, Evaluable<Set> operandThree) {
		return new BooleanElement( operandOne.evaluate().isComplementSet(operandTwo.evaluate(), operandThree.evaluate()) );
	}

}
