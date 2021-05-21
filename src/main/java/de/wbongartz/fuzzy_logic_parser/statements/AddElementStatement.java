package de.wbongartz.fuzzy_logic_parser.statements;

import de.wbongartz.fuzzy_logic_parser.set.Set;
import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;

/**
 * @author Wolfgang Bongartz
 *
 */
public class AddElementStatement implements ExecutableItem  {

	private Evaluable<Set> _exp;
	
	public AddElementStatement(Evaluable<Set> exp) {
		_exp = exp;
	}
	
	/* (non-Javadoc)
	 * @see statements.ExecutableItem#execute()
	 */
	@Override
	public void execute() {
		_exp.evaluate();
	}

}
