
package de.wbongartz.fuzzy_logic_parser.statements;

import java.util.HashMap;

/**
 * @author Wolfgang Bongartz
 *
 */
public class VariableDelete<T> implements ExecutableItem {
	
	private String _name;
	private HashMap<String, T> _store;
	
	public VariableDelete(String name, HashMap<String, T> store) {
		_name = name;
		_store = store;
	}

	/* (non-Javadoc)
	 * @see statements.ExecutableItem#execute()
	 */
	@Override
	public void execute() {
		_store.remove(_name);
	}

}
