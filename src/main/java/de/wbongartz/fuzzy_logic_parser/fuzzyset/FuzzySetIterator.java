
package de.wbongartz.fuzzy_logic_parser.fuzzyset;

import java.util.Iterator;

import de.wbongartz.fuzzy_logic_parser.set.SetElement;

/**
 * @author Wolfgang Bongartz
 *
 */
public class FuzzySetIterator implements Iterator<SetElement> {
	
	private Iterator<FuzzyElement> _iter;	

	/**
	 * @param iter
	 */
	public FuzzySetIterator(Iterator<FuzzyElement> iter) {
		_iter = iter;
	}
	
	/* (non-Javadoc)
	 * @see java.util.Iterator#hasNext()
	 */
	@Override
	public boolean hasNext() {
		return _iter.hasNext();
	}

	/* (non-Javadoc)
	 * @see java.util.Iterator#next()
	 */
	@Override
	public SetElement next() {
		FuzzyElement e = _iter.next();
		return e;
	}

}
