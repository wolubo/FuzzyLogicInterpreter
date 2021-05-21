package de.wbongartz.fuzzy_logic_parser.evaluable;

/**
 * @author Wolfgang Bongartz
 *
 */
public interface BinaryOperator<OP_TYPE_LEFT, OP_TYPE_RIGHT, RESULT_TYPE> {
	public RESULT_TYPE evaluate(Evaluable<OP_TYPE_LEFT> leftOperator, Evaluable<OP_TYPE_RIGHT> rightOperator);
}
