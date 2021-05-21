
package de.wbongartz.fuzzy_logic_parser.statements;

import java.util.ArrayList;

/**
 * @author Wolfgang Bongartz
 *
 */
public class ExecutableBlock implements ExecutableItem {
	
	private ArrayList<ExecutableItem> _statements;
	
	public ExecutableBlock() {
		_statements = new ArrayList<ExecutableItem>();
	}
	
	public void addItem(ExecutableItem item) {
		_statements.add(item);
	}

	/* (non-Javadoc)
	 * @see statements.ExecutableItem#execute()
	 */
	@Override
	public void execute() {
		for(ExecutableItem item: _statements) {
			item.execute();
		}
	}

}
