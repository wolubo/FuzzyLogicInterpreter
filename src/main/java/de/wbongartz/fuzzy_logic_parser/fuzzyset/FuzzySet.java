package de.wbongartz.fuzzy_logic_parser.fuzzyset;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.TreeSet;

import de.wbongartz.fuzzy_logic_parser.set.*;

/**
 * Represents a fuzzy set.
 * @author Wolfgang Bongartz
 *
 */
public class FuzzySet implements Set {

	private TreeSet<FuzzyElement> _elements;

	/**
	 * Create an empty set.
	 */
	public FuzzySet() {
		_elements = new TreeSet<FuzzyElement>();
	}
	
	/**
	 * Create a fuzzy set from a sharp set.
	 * @param sharpSet
	 */
	public FuzzySet(SharpSet sharpSet) {
		this();
		if(sharpSet==null) return;
		for(SetElement e: sharpSet) {
			addElement( new FuzzyElement(e, new DecimalElement(BigDecimal.ONE)) );
		}
	}

	/**
	 * Creates elements from a sharp set and an array of BigDecimal-values.
	 * @param sharpSet Reference to a sharp set.
	 * @param values Array of values ("Mü-Werte").
	 */
	public FuzzySet(SharpSet sharpSet, BigDecimal[] values) throws IllegalArgumentException {
		this();
		if(sharpSet==null) throw new IllegalArgumentException("Parameter 'sharpSet' must not be null!");
		if(sharpSet.getCardinalNumber()!=values.length) throw new IllegalArgumentException("Parameter 'sharpSet' and 'values' not of same size!");

		int i=0;
		for(SetElement e: sharpSet) {
			SetElement newElement = (SetElement)e;
			_elements.add( new FuzzyElement(newElement, new DecimalElement(values[i++])) );
		}
	}

	/**
	 * Let elements be computed by a mapping function.
	 * @param sharpSet Reference to a sharp set.
	 * @param mappingFunction Something that is able to compute mapping values.
	 */
	public FuzzySet(SharpSet sharpSet, MappingFunction mappingFunction) throws IllegalArgumentException {
		this();
		if(sharpSet==null) throw new IllegalArgumentException("Parameter 'sharpSet' must not be null!");
		if(mappingFunction==null) throw new IllegalArgumentException("Parameter 'mappingFunction' must not be null!");

		int i=0;
		for(SetElement e: sharpSet) {
			SetElement newElement = (SetElement)e;
			_elements.add( new FuzzyElement(newElement, mappingFunction.compute(i++, e)) );
		}

	}
	
	/**
	 * @param elements
	 */
	public FuzzySet(TreeSet<FuzzyElement> elements) {
		_elements = new TreeSet<FuzzyElement>(elements);
		performConsistencyCheck();
	}

	/**
	 * @param elements
	 */
	public FuzzySet(ArrayList<FuzzyElement> elements) {
		_elements = new TreeSet<FuzzyElement>(elements);
		performConsistencyCheck();
	}

	/**
	 * Copy constructor;
	 * @param rhs
	 */
	public FuzzySet(FuzzySet rhs) {
		this(rhs._elements);
	}

	/**
	 * Compute the absolute value of the set ("Betrag").
	 * @return The absolute value of the set.
	 */
	public BigDecimal getAbsoluteValue() {
		BigDecimal rv=BigDecimal.ZERO;
		for(FuzzyElement e: _elements) {
			rv = rv.add(e.get_value());
		}
		return rv;
	}

	/**
	 * Computes the average value of the set ("relativer Betrag").
	 * @return The average value of the set.
	 */
	public BigDecimal getAverageValue() {
		BigDecimal av = getAbsoluteValue();
		BigDecimal cn = new BigDecimal(getCardinalNumber());
		BigDecimal result = av.divide(cn);
		return result;
	}

	@Override
	public int getCardinalNumber() {
		int rv=0;
		for(FuzzyElement e: _elements) {
			if(e.get_value().compareTo(BigDecimal.ZERO)>0) rv++;
		}
		return rv;
	}

	@Override
	public boolean equals(Object o) {
		if(o==null) return false;
		if(this==o) return true;

		FuzzySet other = convert(o);
		if(other==null) return false;

		if(this.getCardinalNumber()!=other.getCardinalNumber()) return false;
		if(this.isEmptySet()) return true;

		FuzzyElement current_this;
		FuzzyElement current_other;
		Iterator<FuzzyElement> it_this  = _elements.iterator();
		Iterator<FuzzyElement> it_other = other._elements.iterator();
		if(it_this.hasNext())  current_this  = it_this.next();  else current_this=null;
		if(it_other.hasNext()) current_other = it_other.next(); else current_other=null;

		while( ! ( current_this==null && current_other==null ) ) {
			if( current_other==null || ( current_this!=null && current_other.get_element().compareTo(current_this.get_element())>0 ) ) {
				// Element in 'current_this' is only in 'this'.
				if(current_this.get_value().compareTo(BigDecimal.ZERO)>0) return false;
				if(it_this.hasNext())  current_this  = it_this.next();  else current_this=null;
			} else if( current_this==null || ( current_other!=null &&  current_this.get_element().compareTo(current_other.get_element())>0 ) ) {
				// Element in 'current_other' is only in 'other'.
				if(current_other.get_value().compareTo(BigDecimal.ZERO)>0) return false;
				if(it_other.hasNext()) current_other = it_other.next(); else current_other=null;
			} else {
				// Element is in both sets.
				if(current_this.get_value().compareTo(current_other.get_value())!=0) return false;
				if(it_this.hasNext())  current_this  = it_this.next();  else current_this=null;
				if(it_other.hasNext()) current_other = it_other.next(); else current_other=null;
			}
		}
		
		return true;
	}

	@Override
	public Iterator<SetElement> iterator() {
		return new FuzzySetIterator(_elements.iterator());
	}

	@Override
	public boolean isEmptySet() {
		if(_elements.size()==0)
			return true;
		else {
			for(FuzzyElement f: _elements) {
				if(BigDecimal.ZERO.compareTo(f.get_value())!=0) return false;
			}
			return true;
		}
	}

	@Override
	public boolean isSubset(Set rhs) {
		if(rhs==null) throw new IllegalArgumentException("Parameter 'rhs' must not be null!");

		if( this==rhs ) return true;
		if( this.isEmptySet() ) return true;
		
		FuzzyElement current_this, current_other;

		FuzzySet rhs_fs = convert(rhs);

		Iterator<FuzzyElement> it_this  = _elements.iterator();
		Iterator<FuzzyElement> it_other = rhs_fs._elements.iterator();

		if(it_this.hasNext())  current_this  = it_this.next();  else current_this=null;
		if(it_other.hasNext()) current_other = it_other.next(); else current_other=null;

		while( ! ( current_this==null && current_other==null ) ) {
			if( current_other==null || ( current_this!=null && current_other.get_element().compareTo(current_this.get_element())>0 ) ) {
				// Element in 'current_this' is only in 'this'.
				return false;
//				if(it_this.hasNext())  current_this  = it_this.next();  else current_this=null;
			} else if( current_this==null || ( current_other!=null &&  current_this.get_element().compareTo(current_other.get_element())>0 ) ) {
				// Element in 'current_other' is only in 'other'.
				if(it_other.hasNext()) current_other = it_other.next(); else current_other=null;
			} else {
				// Element is in both sets.
				BigDecimal this_value  = current_this.get_value(); 
				BigDecimal other_value = current_other.get_value(); 
				if( this_value.compareTo(other_value)>0 ) return false;

				if(it_this.hasNext())  current_this  = it_this.next();  else current_this=null;
				if(it_other.hasNext()) current_other = it_other.next(); else current_other=null;
			}
		}
		
		return true;
	}

	@Override
	public boolean isIntersectionSet(Set a, Set b) {
		if(a==null) throw new IllegalArgumentException("Parameter 'a' must not be null!");
		if(b==null) throw new IllegalArgumentException("Parameter 'b' must not be null!");

		FuzzySet a_fs = convert(a);
		FuzzySet b_fs = convert(b);
		
		Set checkSet = a_fs.createIntersectionSet(b_fs);
		
		return this.equals(checkSet);
	}

	@Override
	public boolean isUnionSet(Set a, Set b) {
		if(a==null) throw new IllegalArgumentException("Parameter 'a' must not be null!");
		if(b==null) throw new IllegalArgumentException("Parameter 'b' must not be null!");

		FuzzySet a_fs = convert(a);
		FuzzySet b_fs = convert(b);
		
		Set checkSet = a_fs.createUnionSet(b_fs);
		
		return this.equals(checkSet);
	}

	@Override
	public boolean isComplementSet(Set rhs) {
		if(rhs==null) throw new IllegalArgumentException("Parameter 'rhs' must not be null!");

		FuzzySet rhs_fs = convert(rhs);
		
		Set checkSet = rhs_fs.createComplementSet();
		
		return this.equals(checkSet);
	}

	@Override
	public boolean isComplementSet(Set a, Set b) {
		if(a==null) throw new IllegalArgumentException("Parameter 'a' must not be null!");
		if(b==null) throw new IllegalArgumentException("Parameter 'b' must not be null!");

		FuzzySet a_fs = convert(a);
		FuzzySet b_fs = convert(b);
		
		Set checkSet = a_fs.createComplementSet(b_fs);
		
		return this.equals(checkSet);
	}

	@Override
	public boolean isSymmetricComplementSet(Set a, Set b) {
		if(a==null) throw new IllegalArgumentException("Parameter 'a' must not be null!");
		if(b==null) throw new IllegalArgumentException("Parameter 'b' must not be null!");
		return this.equals(a.createSymmetricComplementSet(b));
	}

	@Override
	public Set createUnionSet(Set rhs) {
		if(rhs==null) throw new IllegalArgumentException("Parameter 'rhs' must not be null!");
		
		FuzzySet rhs_fs = convert(rhs);

		if(this.isEmptySet()) return new FuzzySet(rhs_fs);
		if(rhs.isEmptySet()) return new FuzzySet(this);
		
		ArrayList<FuzzyElement> retVal = new ArrayList<FuzzyElement>();

		FuzzyElement current_this;
		FuzzyElement current_other;
		Iterator<FuzzyElement> it_this  = _elements.iterator();
		Iterator<FuzzyElement> it_other = rhs_fs._elements.iterator();
		if(it_this.hasNext())  current_this  = it_this.next();  else current_this=null;
		if(it_other.hasNext()) current_other = it_other.next(); else current_other=null;

		while( ! ( current_this==null && current_other==null ) ) {
			if( current_other==null || ( current_this!=null && current_other.get_element().compareTo(current_this.get_element())>0 ) ) {
				// Element in 'current_this' is only in 'this'.
				retVal.add(current_this);
				if(it_this.hasNext())  current_this  = it_this.next();  else current_this=null;
			} else if( current_this==null || ( current_other!=null &&  current_this.get_element().compareTo(current_other.get_element())>0 ) ) {
				// Element in 'current_other' is only in 'other'.
				retVal.add(current_other);
				if(it_other.hasNext()) current_other = it_other.next(); else current_other=null;
			} else {
				// Element is in both sets.
				BigDecimal newValue = current_this.get_value().max(current_other.get_value());
				retVal.add(new FuzzyElement(current_this.get_element(), new DecimalElement(newValue)));
				if(it_this.hasNext())  current_this  = it_this.next();  else current_this=null;
				if(it_other.hasNext()) current_other = it_other.next(); else current_other=null;
			}
		}
		
		return new FuzzySet(retVal);
	}

	@Override
	public Set createIntersectionSet(Set rhs) {
		if(rhs==null) throw new IllegalArgumentException("Parameter 'rhs' must not be null!");
		
		if( this.isEmptySet() || rhs.isEmptySet() ) return new FuzzySet();
		
		ArrayList<FuzzyElement> retVal = new ArrayList<FuzzyElement>();

		FuzzySet rhs_fs = convert(rhs);

		FuzzyElement current_this;
		FuzzyElement current_other;
		Iterator<FuzzyElement> it_this  = _elements.iterator();
		Iterator<FuzzyElement> it_other = rhs_fs._elements.iterator();
		if(it_this.hasNext())  current_this  = it_this.next();  else current_this=null;
		if(it_other.hasNext()) current_other = it_other.next(); else current_other=null;

		while( ! ( current_this==null && current_other==null ) ) {
			if( current_other==null || ( current_this!=null && current_other.get_element().compareTo(current_this.get_element())>0 ) ) {
				// Element in 'current_this' is only in 'this'.
				if(it_this.hasNext())  current_this  = it_this.next();  else current_this=null;
			} else if( current_this==null || ( current_other!=null &&  current_this.get_element().compareTo(current_other.get_element())>0 ) ) {
				// Element in 'current_other' is only in 'other'.
				if(it_other.hasNext()) current_other = it_other.next(); else current_other=null;
			} else {
				// Element is in both sets.
				DecimalElement newValue = new DecimalElement(current_this.get_value().min(current_other.get_value()));
				retVal.add(new FuzzyElement(current_this.get_element(), newValue));
				if(it_this.hasNext())  current_this  = it_this.next();  else current_this=null;
				if(it_other.hasNext()) current_other = it_other.next(); else current_other=null;
			}
		}
		
		return new FuzzySet(retVal);
	}

	@Override
	public Set createCartesianProduct(Set rhs) {
		if(rhs==null) throw new IllegalArgumentException("Parameter 'rhs' must not be null!");

		if( this.isEmptySet() || rhs.isEmptySet() ) return new FuzzySet();

		FuzzySet rhs_fs=null;
		if( rhs instanceof FuzzySet ) {
			rhs_fs = (FuzzySet) rhs;
		} else if( rhs instanceof SharpSet ) {
			SharpSet sharp = (SharpSet) rhs;
			rhs_fs =  new FuzzySet(sharp);
		}
		
		FuzzySet retVal = new FuzzySet();

		for(FuzzyElement this_current: _elements) {
			for(FuzzyElement rhs_current: rhs_fs._elements) {
				DecimalElement v = new DecimalElement(this_current.get_value().min(rhs_current.get_value()));
				PairElement p = new PairElement(this_current.get_element(), rhs_current.get_element());
				FuzzyElement newElement = new FuzzyElement(p,v);
				retVal.addElement(newElement);
			}
		}
		
		return new FuzzySet(retVal);
	}

	@Override
	public boolean containsElement(SetElement element) {
		for(FuzzyElement e: _elements) {
			if(e.compareTo(element)==0) {
				return e.get_value().compareTo(BigDecimal.ZERO)>0;
			}
		}
		return false;
	}

	public boolean containsElement(FuzzyElement element) {
		for(FuzzyElement e: _elements) {
			if(element.compareTo(e)==0) {
				return e.get_value().compareTo(BigDecimal.ZERO)>0;
			}
		}
		return false;
	}

	@Override
	public Set createSymmetricComplementSet(Set rhs) {
		FuzzySet rhs_fs = convert(rhs);

		Set complementOfThis = this.createComplementSet(rhs_fs);
		Set complementOfRhs  = rhs_fs.createComplementSet(this);
		return complementOfThis.createUnionSet(complementOfRhs);
	}

	@Override
	public Set createComplementSet() {
		if(this.isEmptySet()) return new FuzzySet();
		
		ArrayList<FuzzyElement> retVal = new ArrayList<FuzzyElement>();
		for(FuzzyElement current: _elements) {
			DecimalElement newValue = new DecimalElement(BigDecimal.ONE.subtract(current.get_value()));
			retVal.add(new FuzzyElement(current.get_element(), newValue));
		}
		return new FuzzySet(retVal);
	}

	@Override
	public Set createComplementSet(Set rhs) {

		if(rhs==null) return this.createComplementSet();
		
		ArrayList<FuzzyElement> retVal = new ArrayList<FuzzyElement>();

		FuzzySet rhs_fs = convert(rhs);

		FuzzyElement current_this;
		FuzzyElement current_other;
		Iterator<FuzzyElement> it_this  = _elements.iterator();
		Iterator<FuzzyElement> it_other = rhs_fs._elements.iterator();
		if(it_this.hasNext())  current_this  = it_this.next();  else current_this=null;
		if(it_other.hasNext()) current_other = it_other.next(); else current_other=null;

		while( ! ( current_this==null && current_other==null ) ) {
			if( current_other==null || ( current_this!=null && current_other.get_element().compareTo(current_this.get_element())>0 ) ) {
				// Element in 'current_this' is only in 'this'.
				DecimalElement newValue = new DecimalElement(current_this.get_value());
				retVal.add(new FuzzyElement(current_this.get_element(), newValue));
				if(it_this.hasNext()) current_this = it_this.next(); else current_this=null;
			} else if( current_this==null || ( current_other!=null &&  current_this.get_element().compareTo(current_other.get_element())>0 ) ) {
				// Element in 'current_other' is only in 'other'.
				if(it_other.hasNext()) current_other = it_other.next(); else current_other=null;
			} else {
				// Element is in both sets.
				DecimalElement newValue = new DecimalElement(BigDecimal.ZERO.max(current_this.get_value().subtract(current_other.get_value())));
				retVal.add(new FuzzyElement(current_this.get_element(), newValue));
				if(it_this.hasNext()) current_this = it_this.next(); else current_this=null;
				if(it_other.hasNext()) current_other = it_other.next(); else current_other=null;
			}
		}
		
		return new FuzzySet(retVal);
	}

	@Override
	public String toString() {
		boolean first = true;
		String retVal = "#{ ";

		for(FuzzyElement current: _elements) {
			if(!first) 
				retVal+=", ";
			else 
				first=false;
			retVal+=current.toString();

		}

		if(first) retVal+=" <EMPTY> ";
		retVal += " }";
		return retVal;
	}

	private FuzzySet convert(Object s) {
		if(s==null) return null;
		
		if( s instanceof FuzzySet ) {
			try {
				return (FuzzySet) s;
			} catch(ClassCastException ex) {
				return null;
			}
		} else if( s instanceof SharpSet ) {
			try {
				SharpSet sharp = (SharpSet) s;
				return new FuzzySet(sharp);
			} catch(ClassCastException ex) {
				return null;
			}
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see set.Set#addElement(set.SetElement)
	 */
	@Override
	public void addElement(SetElement element) {
		if(element instanceof FuzzyElement)
			_elements.add((FuzzyElement)element);
		else
			_elements.add(new FuzzyElement(element,BigDecimal.ONE));
		performConsistencyCheck();
	}

	protected void performConsistencyCheck() {
		String elementType_current=null, elementType_last=null;
		for(SetElement e: _elements) {
			elementType_current=e.getElementType();
			if(elementType_last==null) {
				elementType_last=e.getElementType();
			} else {
				if(elementType_current.compareTo(elementType_current)!=0) {
					throw new IllegalStateException("The element " + e.toString() + " has a different element type (" + elementType_current + ") than the other elements in the set (" + elementType_last + ").");
				}
				elementType_last=e.getElementType();
			}
		}
		
	}

}