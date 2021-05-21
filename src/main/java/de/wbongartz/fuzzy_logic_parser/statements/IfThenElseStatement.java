
package de.wbongartz.fuzzy_logic_parser.statements;

import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;
import de.wbongartz.fuzzy_logic_parser.set.BooleanElement;
import de.wbongartz.fuzzy_logic_parser.set.SetElement;

/**
 * @author Wolfgang Bongartz
 *
 */
public class IfThenElseStatement implements ExecutableItem {
	
	Evaluable<SetElement> _condition;
	ExecutableItem _then_statement;
	ExecutableItem _else_statement;
	
	public IfThenElseStatement(Evaluable<SetElement> condition, ExecutableItem then_statement, ExecutableItem else_statement) {
		_condition = condition;
		_then_statement = then_statement;
		_else_statement = else_statement;
	}

	/* (non-Javadoc)
	 * @see statements.ExecutableItem#execute()
	 */
	@Override
	public void execute() {
		SetElement c = _condition.evaluate();
		if( ! (c instanceof BooleanElement)) throw new IllegalArgumentException("The condition does not resolve to a boolean value! " + c.toString());
		BooleanElement bc = (BooleanElement) c;
		boolean chooseThenStatement = bc.toBoolean();
		if(chooseThenStatement)
			_then_statement.execute();
		else {
			if(_else_statement!=null) _else_statement.execute();
		}
	}

}
