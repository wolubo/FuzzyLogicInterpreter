
package de.wbongartz.fuzzy_logic_parser.evaluable;

import java.util.HashMap;

/**
 * @author Wolfgang Bongartz
 *
 */
public class ResolveVariable<T> implements Evaluable<T> {
	
	private String _variable;
	HashMap<String, T> _varStore;
	
	public ResolveVariable(String variable, HashMap<String, T> varStore) {
		_variable = variable;
		_varStore = varStore;
	}

	/* (non-Javadoc)
	 * @see arithmetic.Computable#compute()
	 */
	@Override
	public T evaluate() {
		if(_varStore.get(_variable)==null) {
			throw new IllegalArgumentException("Variable " + _variable + " is not defined!");
		}
		T e = _varStore.get(_variable);
		return e;
	}
	
}
