package de.wbongartz.fuzzy_logic_parser.set;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;


/**
 * @author Wolfgang Bongartz
 */
public class SharpSet implements Set {

	private TreeSet<SetElement> _elements;

	/**
	 * Creates an empty set.
	 */
	public SharpSet() {
		_elements = new TreeSet<SetElement>();
	}

	/**
	 * 
	 * @param elements
	 */
	public SharpSet(TreeSet<SetElement> elements) {
		this();
		setElements(elements.iterator());
		performConsistencyCheck();
	}

	/**
	 * 
	 * @param elements
	 */
	public SharpSet(ArrayList<SetElement> elements) {
		this();
		setElements(elements.iterator());
		performConsistencyCheck();
	}

	/**
	 * Fills _elements and makes sure that the set contains only elements of the same type.
	 * @param iter
	 */
	private void setElements(Iterator<SetElement> iter) {
		Class<?> type = null;
		while(iter.hasNext()) {
			SetElement current = iter.next();
			if(type!=null  && current.getClass()!=type) throw new IllegalArgumentException("A set cannot contain elements of different type! " + current.getClass().getSimpleName() + " and " + type.getSimpleName());
			type=current.getClass();
			_elements.add(current);
		}
	}
	
	@Override
	public int getCardinalNumber() {
		return _elements.size();
	}

	@Override
	public boolean equals(Object rhs) {
		if(rhs==null) return false;
		if(this==rhs) return true;

		if( ! (rhs instanceof SharpSet) ) return false;
		
		SharpSet other=convert(rhs);
		if(other==null) return false;
		
		if(getCardinalNumber()!=other.getCardinalNumber()) return false;
		
		SetElement current_this;
		SetElement current_other;
		Iterator<SetElement> it_this  = _elements.iterator();
		Iterator<SetElement> it_other = other._elements.iterator();
		if(it_this.hasNext())  current_this  = it_this.next();  else current_this=null;
		if(it_other.hasNext()) current_other = it_other.next(); else current_other=null;
		while( ! ( current_this==null && current_other==null ) ) {
			if( current_other==null || ( current_this!=null && current_other.compareTo(current_this)>0 ) ) {
				// Element in 'current_this' is only in 'this'.
				return false;
//				if(it_this.hasNext())  current_this  = it_this.next();  else current_this=null;
			} else if( current_this==null || ( current_other!=null &&  current_this.compareTo(current_other)>0 ) ) {
				// Element in 'current_other' is only in 'other'.
				return false;
//				if(it_other.hasNext()) current_other = it_other.next(); else current_other=null;
			} else {
				// Element is in both sets.
				if(it_this.hasNext())  current_this  = it_this.next();  else current_this=null;
				if(it_other.hasNext()) current_other = it_other.next(); else current_other=null;
			}
		}
		
		return true;
		
	}

	@Override
	public Iterator<SetElement> iterator() {
		return (Iterator<SetElement>) _elements.iterator();
	}

	@Override
	public boolean isEmptySet() {
		return getCardinalNumber()==0;
	}

	@Override
	public boolean isSubset(Set rhs) {
		if( ! (rhs instanceof SharpSet) ) return false; 
		if(getCardinalNumber()>rhs.getCardinalNumber()) return false;
		
		for(SetElement e: this) {
			if(!rhs.containsElement(e)) return false;
		}
		
		return true;
	}

	@Override
	public boolean isIntersectionSet(Set a, Set b) {
		if( ! (a instanceof SharpSet) ) return false; 
		if( ! (b instanceof SharpSet) ) return false; 
		
		Set c = a.createIntersectionSet(b);
		
		return this.equals(c);
	}

	@Override
	public boolean isUnionSet(Set a, Set b) {
		if( ! (a instanceof SharpSet) ) return false; 
		if( ! (b instanceof SharpSet) ) return false; 

		Set c = a.createUnionSet(b);
		
		return this.equals(c);
	}

	@Override
	public boolean isComplementSet(Set other) {
		if( ! (other instanceof SharpSet) ) return false; 
		return (this.isEmptySet() ^ other.isEmptySet());
	}

	@Override
	public boolean isComplementSet(Set other, Set baseSet) {
		if( ! (other instanceof SharpSet) ) return false; 
		if( ! (baseSet instanceof SharpSet) ) return false; 
		if( baseSet==null || ! this.isSubset(baseSet) ) throw new IllegalArgumentException("baseSet");
		if( other==null || ! other.isSubset(baseSet) ) throw new IllegalArgumentException("other");
		for(SetElement e: baseSet) {
			if( ! ( this.containsElement(e) ^ other.containsElement(e) ) ) return false;
		}
		return true;
	}

	@Override
	public boolean isSymmetricComplementSet(Set a, Set b) {
		if( ! (a instanceof SharpSet) ) return false; 
		if( ! (b instanceof SharpSet) ) return false; 
		Set c = a.createSymmetricComplementSet(b);
		return this.equals(c);
	}

	@Override
	public Set createUnionSet(Set rhs) {
		if( ! (rhs instanceof SharpSet) ) rhs.createUnionSet(this);  // Hopefully the other set knows how to do it...

		SharpSet other=convert(rhs);
		if(other==null) throw new IllegalArgumentException("Cannot operate on " + this.getClass().getSimpleName() + " and " + rhs.getClass().getSimpleName() + "!");

		TreeSet<SetElement> el = new TreeSet<SetElement>(_elements);

		for(SetElement e: other._elements) {
			if( ! el.contains(e) ) el.add(e);
		}
		return new SharpSet(el);
	}

	@Override
	public Set createIntersectionSet(Set rhs) {
		if( ! (rhs instanceof SharpSet) ) rhs.createIntersectionSet(this); // Hopefully the other set knows how to do it...
		
		SharpSet other=convert(rhs);
		if(other==null) throw new IllegalArgumentException("Cannot operate on " + this.getClass().getSimpleName() + " and " + rhs.getClass().getSimpleName() + "!");

		ArrayList<SetElement> rv = new ArrayList<SetElement>();
		for(SetElement e: this._elements) {
			if(rhs.containsElement(e)) rv.add(e);
		}
		return new SharpSet(rv);
	}

	@Override
	public Set createCartesianProduct(Set rhs) {
		if( ! (rhs instanceof SharpSet) ) throw new IllegalArgumentException("Cannot operate on " + rhs.getClass().getSimpleName()); 

		SharpSet other=convert(rhs);
		if(other==null) throw new IllegalArgumentException("Cannot operate on " + this.getClass().getSimpleName() + " and " + rhs.getClass().getSimpleName() + "!");

		SharpSet rv = new SharpSet();
		for(SetElement e_left: this._elements) {
			for(SetElement e_right: other) {
				PairElement pe = new PairElement(e_left, e_right);
				rv.addElement(pe);
			}
		}
		return rv;
	}

	@Override
	public boolean containsElement(SetElement element) {
		for(SetElement c: this) {
			if(element.equals(c)) return true;
		}
		return false;
	}

	@Override
	public Set createComplementSet(Set rhs) {
		if( rhs==null) throw new IllegalArgumentException("'rhs' is NULL.");
		if( ! (rhs instanceof SharpSet) ) throw new IllegalArgumentException("Cannot operate on " + rhs.getClass().getSimpleName()); 

		SharpSet other=convert(rhs);
		if(other==null) throw new IllegalArgumentException("Cannot operate on " + this.getClass().getSimpleName() + " and " + rhs.getClass().getSimpleName() + "!");

		ArrayList<SetElement> rv = new ArrayList<SetElement>(); 
		for(SetElement e: other._elements) {
			if( ! this.containsElement(e) ) rv.add(e);
		}
		return new SharpSet(rv);
	}

	@Override
	public Set createSymmetricComplementSet(Set rhs) {
		if( ! (rhs instanceof SharpSet) ) rhs.createSymmetricComplementSet(this); // Hopefully the other set knows how to do it...

		SharpSet other=convert(rhs);
		if(other==null) throw new IllegalArgumentException("Cannot operate on " + this.getClass().getSimpleName() + " and " + rhs.getClass().getSimpleName() + "!");

		Set left  = this.createComplementSet(rhs);
		Set right = rhs.createComplementSet(this);
		return left.createUnionSet(right);
	}

	@Override
	public String toString() {
		boolean first = true;
		String retVal = "{ ";
		for(SetElement e: _elements) {
			if(!first) 
				retVal+=", ";
			else 
				first=false;
			retVal+=e.toString();
		}
		if(first) retVal+=" <EMPTY> ";
		retVal += " }";
		return retVal;
	}

	@Override
	public Set createComplementSet() {
		return new SharpSet();
	}
	
	private SharpSet convert(Object rhs) {
		try {
			return (SharpSet) rhs;
		} catch(ClassCastException ex) {
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see set.Set#addElement(set.SetElement)
	 */
	@Override
	public void addElement(SetElement element) {
		try {
			SetElement e = (SetElement) element;
			_elements.add(e);
		} catch(ClassCastException ex) {
			throw new IllegalArgumentException("Cannot add an element of type " + element.getClass().getSimpleName() + "!");
		}
		performConsistencyCheck();
	}
	
	protected void performConsistencyCheck() {
		String elementType_current=null, elementType_last=null;
		for(SetElement e: _elements) {
			elementType_current=e.getElementType();
			if(elementType_last==null) {
				elementType_last=e.getElementType();
			} else {
				if(elementType_current.compareTo(elementType_last)!=0) {
					throw new IllegalStateException("The element '" + e.toString() + "' has a different element type (" + elementType_current + ") than the other elements in the set (" + elementType_last + ").");
				}
				elementType_last=e.getElementType();
			}
		}
		
	}

}
