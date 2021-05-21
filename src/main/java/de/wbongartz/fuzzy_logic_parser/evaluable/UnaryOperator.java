
package de.wbongartz.fuzzy_logic_parser.evaluable;

/**
 * @author Wolfgang Bongartz
 *
 */
public interface UnaryOperator<OP_TYPE, RESULT_TYPE> {
	RESULT_TYPE evaluate(Evaluable<OP_TYPE> operand);
}
