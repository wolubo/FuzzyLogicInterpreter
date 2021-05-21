
package de.wbongartz.fuzzy_logic_parser.evaluable;



/**
 * @author Wolfgang Bongartz
 *
 */
public class ResolveConstant<T> implements Evaluable<T> {
	
	private T _value;
	
	public ResolveConstant(T value) {
		_value=value;
	}

	/* (non-Javadoc)
	 * @see arithmetic.Computable#compute()
	 */
	@Override
	public T evaluate() {
		return _value;
	}


}
