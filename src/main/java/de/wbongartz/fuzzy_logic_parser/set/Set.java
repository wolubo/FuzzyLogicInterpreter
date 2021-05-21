package de.wbongartz.fuzzy_logic_parser.set;

/**
 * Ancestor of all sets.
 * @author Wolfgang Bongartz
 *
 */
public interface Set extends Iterable<SetElement> {
	
	/**
	 * Checks if the set contains a specific element.
	 * @param element
	 * @return
	 */
	public boolean containsElement(SetElement element);
	
	/**
	 * Adds an element to the set.
	 * @param element
	 */
	public void addElement(SetElement element);

	/**
	 * Delivers the cardinal number of the set ("M�chtigkeit", "Kardinalzahl").
	 * @return The cardinal number of the set.
	 */
	public int getCardinalNumber();
	
	/**
	 * Checks if 'this' is an empty set ("Leere Menge").
	 * @return
	 */
	public boolean isEmptySet();
	
	/**
	 * Checks if 'this' is a subset of 'rhs' ("Teilmenge").
	 * @param rhs Operand on the right hand side ('this' is the left-hand-side-operand).
	 * @return
	 */
	public boolean isSubset(Set rhs);
	
	/**
	 * Checks if 'this' is the intersection set of 'a' and 'b' ("Schnittmenge").
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean isIntersectionSet(Set a, Set b);
	
	/**
	 * Checks if 'this' is the union set of 'a' and 'b' ("Vereinigungsmenge").
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean isUnionSet(Set a, Set b);
	
	/**
	 * Checks if 'this' is the complement set of 'a' ("Komplement").
	 * "a without b" 
	 * @param a
	 * @return
	 */
	public boolean isComplementSet(Set a);

	/**
	 * Checks if 'this' is the complement set of 'a' in relation to 'b' ("Restmenge, Differenz, Komplement").
	 * "a without b" 
	 * @param a
	 * @return
	 */
	public boolean isComplementSet(Set a, Set b);

	/**
	 * Checks if 'this' is the symmetric difference of 'a' and 'b' ("symmetrische Differenz").
	 * @param a
	 * @param b
	 * @return
	 */
	public boolean isSymmetricComplementSet(Set a, Set b);
	
	/**
	 * Creates an union set (disjunction, "Vereinigung").
	 * @param rhs Operand on the right hand side ('this' is the left-hand-side-operand).
	 * @return
	 */
	public Set createUnionSet(Set rhs);

	/**
	 * Creates an intersection set (conjunction, "Schnittmenge").
	 * @param rhs Operand on the right hand side ('this' is the left-hand-side-operand).
	 * @return
	 */
	public Set createIntersectionSet(Set rhs);
	
	/**
	 * Creates the cartesian product of 'this' and 'rhs' ("Kartesisches Produkt").
	 * @param rhs Operand on the right hand side ('this' is the left-hand-side-operand).
	 * @return
	 */
	public Set createCartesianProduct(Set rhs);
	
	/**
	 * Creates the complement set of 'this'  ("Komplement").
	 * The basic set without 'this'.
	 * Remark: For a sharp set this always results to the empty set (since a basic set is not given).
	 * @return
	 */
	public Set createComplementSet();

	/**
	 * Creates the complement set of 'this' in relation to another set ("Restmenge, Differenz, Komplement").
	 * "'this' without 'rhs'"
	 * @return
	 */
	public Set createComplementSet(Set rhs);

	/**
	 * Creates the symmetric difference of 'this' and 'rhs' ("symmetrische Differenz").
	 * @param rhs Operand on the right hand side ('this' is the left-hand-side-operand).
	 * @return
	 */
	public Set createSymmetricComplementSet(Set rhs);

}
