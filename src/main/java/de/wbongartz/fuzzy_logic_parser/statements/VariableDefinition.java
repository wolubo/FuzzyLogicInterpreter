
package de.wbongartz.fuzzy_logic_parser.statements;

import java.util.HashMap;

import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;

/**
 * @author Wolfgang Bongartz
 *
 */
public class VariableDefinition<T> implements ExecutableItem {
	
	private String _name;
	private Evaluable<T> _setExp;
	private HashMap<String, T> _store;
	
	public VariableDefinition(String name, Evaluable<T> setExp, HashMap<String, T> store) {
		_name = name;
		_setExp = setExp;
		_store = store;
	}

	/* (non-Javadoc)
	 * @see statements.ExecutableItem#execute()
	 */
	@Override
	public void execute() {
		T expression = _setExp.evaluate();
		_store.put(_name, expression);
	}

}
