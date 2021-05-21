
package de.wbongartz.fuzzy_logic_parser.evaluable;

/**
 * @author Wolfgang Bongartz
 *
 */
public class UnaryOperation<OP_TYPE, RESULT_TYPE> implements Evaluable<RESULT_TYPE> {

	Evaluable<OP_TYPE> _operand;
	UnaryOperator<OP_TYPE, RESULT_TYPE> _operator;
	
	public UnaryOperation(Evaluable<OP_TYPE> operand, UnaryOperator<OP_TYPE, RESULT_TYPE> operator) {
		_operand = operand;
		_operator = operator;
	}

	/**
	 * @see evaluable.Evaluable#evaluate()
	 */
	@Override
	public RESULT_TYPE evaluate() {
		return _operator.evaluate(_operand);
	}
}
