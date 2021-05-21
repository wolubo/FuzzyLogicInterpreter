package de.wbongartz.fuzzy_logic_parser.set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.fail;

/**
 * @author Wolfgang Bongartz
 *
 */
public class SharpSetTest {

	public static SetElement convert2element(String s) {
		if(s.contains(":")) {
			// Its a pair-element.
			String[] p = s.split(":");
			SetElement l = convert2element(p[0].trim());
			SetElement r = convert2element(p[1].trim());
			return new PairElement(l,r);
		} else {
			try {
				Integer aNaturalNumber = Integer.parseInt(s);
				return new IntegerElement(aNaturalNumber);
			} catch(NumberFormatException ex) {
				try {
					BigDecimal aRealNumber = new BigDecimal(s);
					return new DecimalElement(aRealNumber);
				} catch(NumberFormatException ex2) {
					return new StringElement(s);
				}
			}
		}
		//		return null;
	}
	
	public static SharpSet createSet(String elements) {
		String[] es = elements.split(",");
		ArrayList<SetElement> list = new ArrayList<SetElement>();
		for(String s: es) {
			list.add(convert2element(s.trim()));
		}
		return new SharpSet(list);
	}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	public void setUp() throws Exception {
	}

	/**
	 * @throws java.lang.Exception
	 */
	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testEquals() {
		SharpSet a, b;
		
		a = new SharpSet();
		b = new SharpSet();
		if(!a.equals(b)) fail();
		
		a = createSet("A,B,C,D");
		b = new SharpSet();
		if(a.equals(b)) fail();
		if(b.equals(a)) fail();
		
		a = createSet("A,B,C,D");
		b = createSet("D,B,A,C");
		if(!a.equals(b)) fail();
		if(!b.equals(a)) fail();

		a = createSet("C,A,G,V,B");
		b = createSet("G,C,A,B");
		if(a.equals(b)) fail();
		if(b.equals(a)) fail();
	}

	@Test
	public void testGetCardinalNumber() {
		SharpSet naturalNumberSet = createSet("9,0,-1");
		SharpSet realNumberSet = createSet("9.0,0.0,-1.0");
		SharpSet c=createSet("C");
		
		if(naturalNumberSet.getCardinalNumber()!=3) {
			fail("Return value not correct! (naturalNumberSet)");
		}
		if(realNumberSet.getCardinalNumber()!=3) {
			fail("Return value not correct! (realNumberSet)");
		}
		if(c.getCardinalNumber()!=1) {
			fail("Return value not correct! (mixedSet)");
		}
	}

	@Test
	public void testEqualsObject() {
		SharpSet naturalNumberSet = createSet("9,0,-1");
		SharpSet realNumberSet = createSet("9.0,0.0,-1.0");
		SharpSet a=createSet("A,B");
		SharpSet b=createSet("A,B,C");
		SharpSet c=createSet("C");
		
		if( ! naturalNumberSet.equals(naturalNumberSet) ) {
			fail("Sets are equal! (naturalNumberSet)");
		}
		if( ! realNumberSet.equals(realNumberSet) ) {
			fail("Sets are equal! (realNumberSet)");
		}
		if( ! a.equals(a) ) {
			fail("Sets are equal! (a)");
		}
		if( naturalNumberSet.equals(realNumberSet) ) {
			fail("Sets are not equal! (naturalNumberSet==realNumberSet)");
		}
		if( a.equals(b) ) {
			fail("Sets are not equal!");
		}
		if( b.equals(c) ) {
			fail("Sets are not equal!");
		}
	}

//	@Test
//	public void testGetElement() {
//		GeneralPurposeElement element = new GeneralPurposeElement("A");
//		SharpSet set = createSet("A");
//		SetElement a = (SetElement) set.getElement(0);
//		if( ! a.equals(element) ) fail();
//	}
//
	@Test
	public void testIsEmptySet() {
		SharpSet a=createSet("A,B");

		SharpSet e = new SharpSet();
		if( ! e.isEmptySet() ) fail("e is empty!");
		if(a.isEmptySet()) fail("a is not empty!");
	}

	@Test
	public void testIsSubset() {
		SharpSet a=createSet("A,B");
		SharpSet b=createSet("A,B,C");

		if( ! a.isSubset(b) ) fail("a IS a subset of b!");
		if( b.isSubset(a) ) fail("b IS NOT a subset of a!");
	}

	@Test
	public void testIsIntersectionSet() {
		SharpSet a=createSet("A,B");
		SharpSet b=createSet("A,B,C");
		SharpSet c=createSet("C");
		SharpSet d=createSet("A,B");
		if( ! d.isIntersectionSet(a, b) ) fail("d IS the intersection set of a and b!");
		if( a.isIntersectionSet(a, c) ) fail("a IS NOT the intersection set of a and c!");
	}

	@Test
	public void testIsUnionSet() {
		SharpSet a=createSet("A,B");
		SharpSet b=createSet("A,B,C");
		SharpSet c=createSet("C");
		try {
			if( ! b.isUnionSet(a, c) ) fail("b is a union set of a and c!");
			if( c.isUnionSet(a, b) ) fail("c is not a union set of a and b!");
		} catch(Exception ex) {
			fail("Unexpected exception: " + ex.getMessage());
		}
	}

	@Test
	public void testIsSymmetricComplementSet() {
		SharpSet a=createSet("A,B");
		SharpSet b=createSet("A,B,C");
		SharpSet c=createSet("C");
		if( ! c.isSymmetricComplementSet(a, b) ) fail();
		if( ! b.isSymmetricComplementSet(a, c) ) fail();
	}

	@Test
	public void testCreateUnionSet() {
		SharpSet a=createSet("A,B");
		SharpSet b=createSet("A,B,C");
		SharpSet c=createSet("C");
		SharpSet u = (SharpSet) a.createUnionSet(b);
		if( ! u.equals(b)) fail();
		
		u = (SharpSet) a.createUnionSet(c);
		if( ! u.equals(b)) fail();
	}

	@Test
	public void testCreateIntersectionSet() {
		SharpSet a=createSet("A,B");
		SharpSet b=createSet("A,B,C");
		SharpSet i = (SharpSet) a.createIntersectionSet(b);
		if( ! i.equals(a)) fail();
	}

	@Test
	public void testCreateCartesianProduct() {
		SharpSet a=createSet("A,B");
		SharpSet b=createSet("A,B,C");
		SharpSet checkSet = createSet("A:A,A:B,A:C,B:A,B:B,B:C"); 
		Set cp = a.createCartesianProduct(b);
		if( ! cp.equals(checkSet) ) fail();
	}

	@Test
	public void testContainsElement() {
		SharpSet a=createSet("A,B");
		if( ! a.containsElement(new StringElement("A"))) fail("A is in a!");
		if( a.containsElement(new StringElement("X"))) fail("X is not in a!");
	}

	@Test
	public void testCreateComplementSetSet() {
		SharpSet a=createSet("A,B");
		SharpSet b=createSet("A,B,C");
		SharpSet c=createSet("C");
		Set d = a.createComplementSet(b);
		if( ! d.equals(c) ) fail("c IS the complement of a relative to b!");
	}

	@Test
	public void testIsComplementSetSetSet() {
		SharpSet a=createSet("A,B");
		SharpSet b=createSet("A,B,C");
		SharpSet c=createSet("C");
		if( ! c.isComplementSet(a, b) ) fail();
	}

	@Test
	public void testCreateSymmetricComplementSet() {
		SharpSet a=createSet("A,B");
		SharpSet b=createSet("A,B,C");
		SharpSet c=createSet("C");
		Set d = a.createSymmetricComplementSet(b);
		if( ! d.equals(c) ) fail();

		d = a.createSymmetricComplementSet(c);
		if( ! d.equals(b) ) fail();
	}

//	@Test
//	public void testGetOrdinalNumberOfElementBasicSetElement() {
//		SetElement a, b, c, d;
//		a = new NaturalNumberElement(9);
//		b = new NaturalNumberElement(0);
//		c = new NaturalNumberElement(-1);
//		d = new NaturalNumberElement(6);
//		SetElement[] e = { a, b, c	};
//		SharpSet set = new SharpSet( e );
//
//		try {
//			set.getOrdinalNumberOfElement(d);
//			fail("IllegalArgumentException expected!");
//		} catch (IllegalArgumentException ex) {
//		}
//		
//		try {
//			int x = set.getOrdinalNumberOfElement(c);
//			if(x!=2) fail();
//		} catch (Exception ex) {
//			fail("Unexpected Exception: " + ex.getMessage());
//		}
//	}
}
