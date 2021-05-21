
package de.wbongartz.fuzzy_logic_parser.statements;

import java.util.ArrayList;

import de.wbongartz.fuzzy_logic_parser.set.SetElement;
import de.wbongartz.fuzzy_logic_parser.evaluable.Evaluable;

/**
 * @author Wolfgang Bongartz
 *
 */
public class Print implements ExecutableItem {
	
	private ArrayList<Evaluable<SetElement>> _items;
	
	public Print() {
		_items = new ArrayList<Evaluable<SetElement>>();
	}
	
	public void addItem(Evaluable<SetElement> item) {
		_items.add(item);
	}

	/* (non-Javadoc)
	 * @see statements.ExecutableItem#execute()
	 */
	@Override
	public void execute() {
		String s = "";
		for(Evaluable<?> i: _items) {
			s+=i.evaluate().toString();
		}
		System.out.println(s);
	}

}
