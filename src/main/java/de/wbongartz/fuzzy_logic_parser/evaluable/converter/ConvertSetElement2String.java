
package de.wbongartz.fuzzy_logic_parser.evaluable.converter;

import de.wbongartz.fuzzy_logic_parser.set.SetElement;
import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;
import de.wbongartz.fuzzy_logic_parser.evaluable.UnaryOperator;

/**
 * @author Wolfgang Bongartz
 *
 */
public class ConvertSetElement2String implements UnaryOperator<SetElement,String> {
		

		/* (non-Javadoc)
		 * @see evaluable.UnaryOperator#evaluate(evaluable.Evaluable)
		 */
		@Override
		public String evaluate(Evaluable<SetElement> operand) {
			return operand.evaluate().toString();
		}

	}
