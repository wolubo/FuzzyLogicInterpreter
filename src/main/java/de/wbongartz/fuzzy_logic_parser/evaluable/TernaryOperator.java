
package de.wbongartz.fuzzy_logic_parser.evaluable;

/**
 * @author Wolfgang Bongartz
 *
 */
public interface TernaryOperator<OP_TYPE, RESULT_TYPE> {
	public RESULT_TYPE evaluate(Evaluable<OP_TYPE> operandOne, Evaluable<OP_TYPE> operandTwo, Evaluable<OP_TYPE> operandThree);
}
