package de.wbongartz.fuzzy_logic_parser.evaluable.compare;

//import java.math.BigDecimal;
import de.wbongartz.fuzzy_logic_parser.set.BooleanElement;
import de.wbongartz.fuzzy_logic_parser.set.SetElement;
import de.wbongartz.fuzzy_logic_parser.evaluable.BinaryOperator;
import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;

/**
 * @author Wolfgang Bongartz
 *
 */
public class Equal implements BinaryOperator<SetElement,SetElement,SetElement> {

	/* (non-Javadoc)
	 * @see evaluable.BinaryOperator#evaluate(evaluable.Evaluable, evaluable.Evaluable)
	 */
	@Override
	public SetElement evaluate(Evaluable<SetElement> leftOperator, Evaluable<SetElement> rightOperator) {
		SetElement le = leftOperator.evaluate();
		SetElement re = rightOperator.evaluate();
		return new BooleanElement(le.compareTo(re)==0);
	
//		BigDecimal left  = le.get_value();
//		BigDecimal right = re.get_value();
//		return new BooleanElement(left.compareTo(right)==0);
	}

}
