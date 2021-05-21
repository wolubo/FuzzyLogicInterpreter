package de.wbongartz.fuzzy_logic_parser.evaluable.converter;

import de.wbongartz.fuzzy_logic_parser.set.Set;
import de.wbongartz.fuzzy_logic_parser.set.SetElement;
import de.wbongartz.fuzzy_logic_parser.set.StringElement;
import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;
import de.wbongartz.fuzzy_logic_parser.evaluable.UnaryOperator;

/**
 * @author Wolfgang Bongartz
 *
 */
public class ConvertSet2String implements UnaryOperator<Set,SetElement> {
		

		/* (non-Javadoc)
		 * @see evaluable.UnaryOperator#evaluate(evaluable.Evaluable)
		 */
		@Override
		public SetElement evaluate(Evaluable<Set> operand) {
			return new StringElement(operand.evaluate().toString());
		}

	}
