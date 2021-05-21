
package de.wbongartz.fuzzy_logic_parser.evaluable;


/**
 * @author Wolfgang Bongartz
 *
 */
public class TernaryOperation<OP_TYPE, RESULT_TYPE> implements Evaluable<RESULT_TYPE> {

	Evaluable<OP_TYPE> _operandOne;
	Evaluable<OP_TYPE> _operandTwo;
	Evaluable<OP_TYPE> _operandThree;
	TernaryOperator<OP_TYPE, RESULT_TYPE> _operator;
	
	public TernaryOperation(Evaluable<OP_TYPE> operandOne, Evaluable<OP_TYPE> operandTwo, Evaluable<OP_TYPE> operandThree, TernaryOperator<OP_TYPE, RESULT_TYPE> operator) {
		_operandOne = operandOne;
		_operandTwo = operandTwo;
		_operandThree = operandThree;
		_operator = operator;
	}
	
	/**
	 * @see evaluable.Evaluable#evaluate()
	 */
	@Override
	public RESULT_TYPE evaluate() {
		return _operator.evaluate(_operandOne, _operandTwo, _operandThree);
	}

}
