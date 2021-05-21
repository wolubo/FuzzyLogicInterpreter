package de.wbongartz.fuzzy_logic_parser.evaluable;


/**
 * @author Wolfgang Bongartz
 *
 */
public class BinaryOperation<OP_TYPE_LEFT, OP_TYPE_RIGHT, RESULT_TYPE> implements Evaluable<RESULT_TYPE> {

	Evaluable<OP_TYPE_LEFT> _leftOperand;
	Evaluable<OP_TYPE_RIGHT> _rightOperand;
	BinaryOperator<OP_TYPE_LEFT, OP_TYPE_RIGHT, RESULT_TYPE> _operator;
	
	public BinaryOperation(Evaluable<OP_TYPE_LEFT> leftOperand, Evaluable<OP_TYPE_RIGHT> rightOperand, BinaryOperator<OP_TYPE_LEFT, OP_TYPE_RIGHT, RESULT_TYPE> operator) {
		_leftOperand = leftOperand;
		_rightOperand = rightOperand;
		_operator = operator;
	}
	
	/**
	 * @see Evaluable#evaluate()
	 */
	@Override
	public RESULT_TYPE evaluate() {
		return _operator.evaluate(_leftOperand, _rightOperand);
	}

}
