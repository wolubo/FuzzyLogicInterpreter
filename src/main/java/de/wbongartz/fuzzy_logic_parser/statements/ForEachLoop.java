
package de.wbongartz.fuzzy_logic_parser.statements;

import java.util.HashMap;

import de.wbongartz.fuzzy_logic_parser.set.BooleanElement;
import de.wbongartz.fuzzy_logic_parser.set.Set;
import de.wbongartz.fuzzy_logic_parser.set.SetElement;
import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;


/**
 * @author Wolfgang Bongartz
 *
 */
public class ForEachLoop implements ExecutableItem {
	
	private String _varName;
	private Evaluable<Set> _set;
	private Evaluable<SetElement> _condition;
	private ExecutableItem _executable;
	private HashMap<String, SetElement> _varStore;

	public ForEachLoop(String varName, Evaluable<Set> set, Evaluable<SetElement> condition, ExecutableItem executable, HashMap<String, SetElement> varStore) {
		_varName = varName;
		_set = set;
		_condition = condition;
		_executable = executable;
		_varStore = varStore;
		if(_varStore.containsKey(_varName)) throw new IllegalStateException("The variable " + _varName + " has already been defined. Please choose another name.");
	}
	
	/* (non-Javadoc)
	 * @see statements.ExecutableItem#execute()
	 */
	@Override
	public void execute() {
		if(_varStore.containsKey(_varName)) throw new IllegalStateException("The variable " + _varName + " has already been defined. Please choose another name.");
		
		Set set = _set.evaluate();
		
		boolean doIt = true;
		
		for(SetElement current: set) {
			
			_varStore.put(_varName, current);
			
			if( _condition!=null ){
				SetElement w = _condition.evaluate();
				if( ! (w instanceof BooleanElement)) throw new IllegalArgumentException("The condition does not resolve to a boolean value! " + w.toString());
				BooleanElement clause = (BooleanElement) w;
				doIt = clause.toBoolean();
			}

			if(doIt) _executable.execute();
		}
		_varStore.remove(_varName);
	}

}
