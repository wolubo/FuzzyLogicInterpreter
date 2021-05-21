package de.wbongartz.fuzzy_logic_parser.set;

public class PairElement extends SetElement {
	
	private SetElement _left;
	private SetElement _right;
	
	public PairElement(SetElement left, SetElement right) {
		if(left==null) throw new IllegalArgumentException("'left' is null!");
		if(right==null) throw new IllegalArgumentException("'right' is null!");
		set_left(left);
		set_right(right);
	}
	
	@Override
	public boolean equals(Object o) {
		if(this==o) return true;
		if( ! (o instanceof PairElement) ) return false;
		PairElement p = (PairElement) o;
		return (_left.equals(p._left)) && (_right.equals(p._right));
	}

	@Override
	public int hashCode() {
		return (_left.toString()+_right.toString()).hashCode();
	}

	public SetElement get_left() {
		return _left;
	}

	protected void set_left(SetElement left) {
		this._left = left;
	}

	public SetElement get_right() {
		return _right;
	}

	protected void set_right(SetElement right) {
		this._right = right;
	}

	@Override
	public String toString() {
		String retVal = "(";
		retVal+=_left.toString();
		retVal+=",";
		retVal+=_right.toString();
		retVal+=")";
		return retVal;
	}

	@Override
	public int compareTo(SetElement o) {
		if(o instanceof PairElement) {
			PairElement pe = (PairElement) o; 
			SetElement lt = (SetElement) get_left();
			SetElement rt = (SetElement) get_right();
			SetElement lo = (SetElement) pe.get_left();
			SetElement ro = (SetElement) pe.get_right();
			int retVal = lt.compareTo(lo);
			if(retVal==0) {
				retVal = rt.compareTo(ro);
			}
			return retVal;
		}
		throw new IllegalArgumentException("Cannot compare a PairElement with a " + o.getClass().getName() + "!");
	}

	public String getElementType() {
		return this.getClass().getSimpleName() + "(" + _left.getElementType() + "," + _right.getElementType() + ")";
	}

}
