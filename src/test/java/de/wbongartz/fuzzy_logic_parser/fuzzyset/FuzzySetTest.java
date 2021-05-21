package de.wbongartz.fuzzy_logic_parser.fuzzyset;

import de.wbongartz.fuzzy_logic_parser.set.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.fail;

public class FuzzySetTest {


	public static FuzzyElement convert2element(String s) {
		s = s.substring(1, s.length()-1);
		s = s.trim();

		String[] parts = s.split(",");
		SetElement element;
		BigDecimal value;

		element = SharpSetTest.convert2element(parts[0].trim());
		value = new BigDecimal(parts[1].trim());

		return new FuzzyElement(element, value);
	}

	public static FuzzySet createSet(String elements) {
		String[] es = elements.split(";");
		FuzzySet list = new FuzzySet();
		for(String s: es) {
			list.addElement(convert2element(s.trim()));
		}
		return new FuzzySet(list);
	}


	@BeforeEach
	public void setUp() throws Exception {
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testFuzzySet() {
		try {
			FuzzySet f = new FuzzySet();
			if(f.getCardinalNumber()>0) fail();
		}
		catch (Exception ex) {
			fail();
		}
	}

	@Test
	public void testFuzzySetSharpSet() {
		SharpSet sharpSet;
		FuzzySet expected;
		FuzzySet check;

		try {
			sharpSet = SharpSetTest.createSet("9,0,-1");
			expected = createSet("(9,1.0);(0,1.0);(-1,1.0)");
			check = new FuzzySet(sharpSet);
			if(!check.equals(expected)) fail();

			sharpSet = SharpSetTest.createSet("A,B,C,D,");
			expected = createSet("(A,1.0);(B,1.0);(C,1.0);(D,1.0)");
			check = new FuzzySet(sharpSet);
			if(!check.equals(expected)) fail();

		} catch (Exception ex) {
			fail("Exception not expected! " + ex);
		}

	}

	@Test
	public void testFuzzySetBasicSetFloatArray() {
		SharpSet naturalNumberSet = SharpSetTest.createSet("9,0,-1");

		try {
			BigDecimal[] f = {BigDecimal.ONE, new BigDecimal("0.85"), new BigDecimal("0.27")};
			new FuzzySet(null, f);
			fail("IllegalArgumentException expected!");
		}
		catch (IllegalArgumentException ex) {
		}

		try {
			BigDecimal[] f = {BigDecimal.ONE, new BigDecimal("0.85")};
			new FuzzySet(naturalNumberSet, f);
			fail("IllegalArgumentException expected!");
		}
		catch (IllegalArgumentException ex) {
		}

		try {
			BigDecimal[] v = {BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE};
			FuzzySet fs = new FuzzySet(naturalNumberSet,v);
			if( ! fs.equals(naturalNumberSet)) fail();
		}
		catch (Exception ex) {
			fail("Exception not expected!");
		}

	}

	@Test
	public void testGetCardinalNumber() {
		try {
			FuzzySet fs = createSet("(9.0,1.0);(0.0,0.5);(-1.0,0.0)");
			if(fs.getCardinalNumber()!=2) fail("Value is not correct!");

			FuzzySet empty = new FuzzySet();
			if( empty.getCardinalNumber()!= 0 ) fail("Cardinal number of an empty set is 0!");
		} catch (Exception ex) {
			fail("Exception not expected! " + ex);
		}
	}


	@Test
	public void testEqualsObject() {
		try {
			FuzzySet fs1, fs2, a, b;

			a = new FuzzySet();
			b = new FuzzySet();
			if(!a.equals(b)) fail();

			a = createSet("(A,0.2);(B,0.6);(C,0.1);(D,0.2)");
			b = new FuzzySet();
			if(a.equals(b)) fail();
			if(b.equals(a)) fail();

			a = createSet("(A,0.2);(B,0.6);(C,0.1);(D,0.2)");
			b = createSet("(A,0.9);(B,0.6);(C,0.3);(D,0.67)");
			if(a.equals(b)) fail();
			if(b.equals(a)) fail();

			a = createSet("(A,0.0);(B,0.0);(C,0.0);(D,0.0)");
			b = createSet("(A,0.0);(B,0.0);(C,0.0);(D,0.0)");
			if(!a.equals(b)) fail();
			if(!b.equals(a)) fail();

			a = createSet("(A,0.2);(B,0.6);(C,0.1);(D,0.2)");
			b = createSet("(A,0.2);(B,0.6);(C,0.1);(D,0.2)");
			if(!a.equals(b)) fail();
			if(!b.equals(a)) fail();

			a = createSet("(A,0.2);(B,0.6);(C,0.1);(D,0.2)");
			b = createSet("(A,0.2);(B,0.6);(D,0.2)");
			if(a.equals(b)) fail();
			if(b.equals(a)) fail();

			fs1 = createSet("(9.0,1.0);(0.0,1.0);(-1.0,1.0)");
			fs2 = createSet("(9.0,1.0);(0.0,1.0);(-1.0,1.0)");
			if(!fs1.equals(fs2)) fail();
			if(!fs2.equals(fs1)) fail();

			fs1 = createSet("(9.0,1.0);(0.0,1.0);(-1.0,1.0)");
			fs2 = createSet("(9,1.0);(0,1.0);(-1,1.0)");
			if(fs1.equals(fs2)) fail();
			if(fs2.equals(fs1)) fail();

			try {
				fs1 = createSet("(A,1.0);(B,1.0);(C,1.0)");
				fs2 = createSet("(9,1.0);(0,1.0);(-1,1.0)");
				fs1.equals(fs2);
				fs2.equals(fs1);
				fail("Exception expected: Comparing sets of different type!");
			} catch (Exception ex) {
			}

			fs1 = createSet("(A,0.2);(B,0.6);(C,0.1);(D,0.2)");
			fs2 = createSet("(A,0.9);(B,0.6);(C,0.3);(D,0.67)");
			if(fs1.equals(fs2)) fail();
			if(fs2.equals(fs1)) fail();

			fs2 = createSet("(A,0.2);(B,0.6);(C,0.1);(D,0.2)");
			if(!fs1.equals(fs2)) fail();
			if(!fs2.equals(fs1)) fail();

			fs1 = new FuzzySet(SharpSetTest.createSet("A,B,C,D"));
			fs2 = new FuzzySet(SharpSetTest.createSet("A,C,B"));
			if(fs1.equals(fs2)) fail();
			if(fs2.equals(fs1)) fail();

			fs1 = new FuzzySet(SharpSetTest.createSet("A,B,C"));
			fs2 = new FuzzySet(SharpSetTest.createSet("A,C,B"));
			if(!fs1.equals(fs2)) fail();
			if(!fs2.equals(fs1)) fail();

			SharpSet sharp = SharpSetTest.createSet("A,B,C");
			BigDecimal[] v = {BigDecimal.ONE, BigDecimal.ONE, BigDecimal.ONE};
			FuzzySet fs = new FuzzySet(sharp,v);
			if( ! fs.equals(sharp)) fail();

		} catch (Exception ex) {
			fail("Exception not expected!");
		}
	}

	@Test
	public void testFuzzySetBasicSetMappingFunction() {

		MappingFunction mf = (ordinalValue, element) -> {
			if( ! (element instanceof IntegerElement) ) throw new IllegalArgumentException();
			IntegerElement current = (IntegerElement) element;
			BigDecimal ten = new BigDecimal("10");
			BigDecimal value = current.asBigDecimal();
			if(value.abs().compareTo(ten)>0) {
				return new DecimalElement(BigDecimal.ONE);
			} else {
				return new DecimalElement(value.abs().divide(ten));
			}
		};

		try {
			SharpSet someNumbers = SharpSetTest.createSet("9,0,-1,45,2,1,-99");
			FuzzySet expectedResult = createSet("(9,0.9);(0,0.0);(-1,0.1);(2,0.2);(1,0.1);(-99,1.0);(45,1.0)");

			FuzzySet fs = new FuzzySet(someNumbers, mf);
			if( fs.getCardinalNumber() != 6 ) fail();
			if( ! fs.equals(expectedResult)) fail();
		} catch(Exception ex) {
			fail();
		}
	}

	//	@Test
	//	public void testGetElement() {
	//		FuzzySet fs = createSet("(A,1.0);(B,0.5);(C,0.0);(D,1.0)");
	//
	//		if( ! fs.getElement(0).equals(new FuzzyElement(new GeneralPurposeElement("A"), BigDecimal.ONE)) ) fail();
	//		if( ! fs.getElement(1).equals(new FuzzyElement(new GeneralPurposeElement("B"), new BigDecimal("0.5"))) ) fail();
	//		if( ! fs.getElement(2).equals(new FuzzyElement(new GeneralPurposeElement("C"), BigDecimal.ZERO)) ) fail();
	//		if( ! fs.getElement(3).equals(new FuzzyElement(new GeneralPurposeElement("D"), BigDecimal.ONE)) ) fail();
	//		
	//		try {
	//			fs.getElement(5);
	//			fail("Exception expected!");
	//		} catch (Exception ex) {
	//		}
	//	}
	//	

	//	@Test
	//	public void testGetElementSetElement() {
	//		FuzzySet checkSet;
	//		SetElement element;
	//		FuzzyElement result;
	//
	//		try {
	//			checkSet = new FuzzySet();
	//			element = new GeneralPurposeElement("A");
	//			result = checkSet.getElement(element);
	//			if(result!=null) fail();
	//
	//			checkSet = createSet("(A,0.7);(B,0.5);(C,0.0);(D,0.4);(E,1.0)");
	//			element = new GeneralPurposeElement("A");
	//			result = checkSet.getElement(element);
	//			if(!result.get_element().equals(element)) fail();
	//
	//			element = null;
	//			result = checkSet.getElement(element);
	//			if(result!=null) fail();
	//
	//			element = new GeneralPurposeElement("X");
	//			result = checkSet.getElement(element);
	//			if(result!=null) fail();
	//
	//			element = new GeneralPurposeElement("C");
	//			result = checkSet.getElement(element);
	//			if(result!=null) fail();
	//		} catch (Exception ex) {
	//			fail("Unexpected exception occured: " + ex.getMessage());
	//		}
	//	}

	@Test
	public void testIsEmptySet() {
		FuzzySet checkSet;

		FuzzySet empty = new FuzzySet();
		if( ! empty.isEmptySet() ) fail();

		checkSet = createSet("(A,0.0);(B,0.0);(C,0.0);(D,0.0);(E,0.0)");
		if( ! checkSet.isEmptySet() ) fail();

		checkSet = createSet("(A,0.0);(B,0.0);(C,0.1);(D,0.0);(E,0.0)");
		if( checkSet.isEmptySet() ) fail();

		checkSet = createSet("(A,0.0);(B,0.0);(C,0.0);(D,0.0);(E,0.1)");
		if( checkSet.isEmptySet() ) fail();

		checkSet = createSet("(A,0.1);(B,0.0);(C,0.0);(D,0.0);(E,0.0)");
		if( checkSet.isEmptySet() ) fail();
	}

	@Test
	public void testIsSubset() {
		try {

			FuzzySet checkSet, isSubset, isNotSubset;

			checkSet = createSet("(A,0.7);(B,0.5);(C,0.3);(D,0.4);(E,1.0)");
			isSubset = createSet("(A,0.0);(B,0.2);(C,0.3);(D,0.0);(E,0.3)");
			isNotSubset = createSet("(A,0.0);(B,0.6);(C,0.3);(D,0.0);(E,0.3)");

			if(! isSubset.isSubset(checkSet) ) fail();
			if( isNotSubset.isSubset(checkSet) ) fail();

			FuzzySet itself = new FuzzySet(SharpSetTest.createSet("A,B,C,D,"));
			if( ! itself.isSubset(itself)) fail();

		} catch (Exception ex) {
			fail("Unexpected exception occured: " + ex.getMessage());
		}
	}

	@Test
	public void testIsIntersectionSet() {
		FuzzySet f_a, f_b, isIntersectionSet, isNotIntersectionSet;

		f_a                  = createSet("(A,0.7);(B,0.5);(C,0.3);(D,0.4);(E,0.0)");
		f_b                  = createSet("(A,0.0);(B,0.2);(C,0.4);(D,0.0);(E,0.3)");
		isIntersectionSet    = createSet("(A,0.0);(B,0.2);(C,0.3);(D,0.0);(E,0.0)");
		isNotIntersectionSet = createSet("(A,0.8);(B,0.2);(C,0.3);(D,0.0);(E,0.0)");

		try {
			if( ! isIntersectionSet.isIntersectionSet(f_a, f_b) ) fail();
			if( isNotIntersectionSet.isIntersectionSet(f_a, f_b) ) fail();
		} catch(Exception ex) {
			fail("Unexpected exception occured: " + ex.getMessage());
		}
		
		f_a                  = createSet("(A,0.1);(B,0.75)");
		f_b                  = createSet("(A,0.9);(B,0.25)");
		isIntersectionSet    = createSet("(A,0.1);(B,0.25)");

		try {
			if( ! isIntersectionSet.isIntersectionSet(f_a, f_b) ) fail();
		} catch(Exception ex) {
			fail("Unexpected exception occured: " + ex.getMessage());
		}
	}

	@Test
	public void testIsUnionSet() {
		FuzzySet f_a, f_b, unionSet;

		f_a      = createSet("(A,0.7);(B,0.5);(C,0.3);(D,0.4);(E,0.0)");
		f_b      = createSet("(A,0.0);(B,0.2);(C,0.4);(D,0.0);(E,0.3)");
		unionSet = createSet("(A,0.7);(B,0.5);(C,0.4);(D,0.4);(E,0.3)");

		try {
			if( ! unionSet.isUnionSet(f_a, f_b) ) fail();
		} catch(Exception ex) {
			fail("Unexpected exception occured: " + ex.getMessage());
		}
	}

	@Test
	public void testIsComplementSetFuzzySet() {
		FuzzySet f_a, expectedResult;

		f_a            = createSet("(A,0.7);(B,0.5);(C,0.3);(D,0.4);(E,0.0)");
		expectedResult = createSet("(A,0.3);(B,0.5);(C,0.7);(D,0.6);(E,1.0)");

		try {
			if( ! f_a.isComplementSet(expectedResult) ) fail();
		} catch(Exception ex) {
			fail("Unexpected exception occured: " + ex.getMessage());
		}
	}

	@Test
	public void testIsComplementSetFuzzySetFuzzySet() {
		FuzzySet f_a, expectedResult;

		f_a            = createSet("(A,0.7);(B,0.5);(C,0.3);(D,0.4);(E,0.0)");
		expectedResult = createSet("(A,0.3);(B,0.5);(C,0.7);(D,0.6);(E,1.0)");

		try {
			if( ! f_a.isComplementSet(expectedResult) ) fail();
		} catch(Exception ex) {
			fail("Unexpected exception occured: " + ex.getMessage());
		}

	}

	@Test
	public void testIsSymmetricComplementSet() {
		FuzzySet f_a, f_b, expectedResult;

		f_a            = createSet("(A,0.7);(B,0.5);(C,0.3);(D,0.4);(E,0.0)");
		f_b            = createSet("(A,0.3);(B,0.5);(C,0.7);(D,0.4);(E,1.0)");
		expectedResult = createSet("(A,0.4);(B,0.0);(C,0.4);(D,0.0);(E,1.0)");

		try {
			if( ! expectedResult.isSymmetricComplementSet(f_a, f_b) ) fail();
		} catch(Exception ex) {
			fail("Unexpected exception occured: " + ex.getMessage());
		}
	}

	@Test
	public void testCreateUnionSet() {
		FuzzySet f_a, f_b, f_c=null, expectedResult;

		f_a            = createSet("(A,0.7);(B,0.5);(C,0.3);(D,0.4);(E,0.0)");
		f_b            = createSet("(A,0.0);(B,0.2);(C,0.4);(D,0.0);(E,0.3)");
		expectedResult = createSet("(A,0.7);(B,0.5);(C,0.4);(D,0.4);(E,0.3)");

		try {
			f_c = (FuzzySet) f_a.createUnionSet(f_b);
			if( ! f_c.equals(expectedResult) ) fail();
		} catch(Exception ex) {
			fail("Unexpected exception occured: " + ex.getMessage());
		}
	}

	@Test
	public void testCreateIntersectionSet() {
		FuzzySet f_a, f_b, f_c=null, expectedResult;

		f_a            = createSet("(A,0.7);(B,0.5);(C,0.3);(D,0.4);(E,0.0)");
		f_b            = createSet("(A,0.0);(B,0.2);(C,0.4);(D,0.0);(E,0.0)");
		expectedResult = createSet("(A,0.0);(B,0.2);(C,0.3);(D,0.0);(E,0.0)");

		try {
			f_c = (FuzzySet) f_a.createIntersectionSet(f_b);
			if( ! f_c.equals(expectedResult) ) fail();
		} catch(Exception ex) {
			fail("Unexpected exception occured: " + ex.getMessage());
		}
	}

	@Test
	public void testCreateCartesianProduct() {
		FuzzySet f_a, f_b, expectedResult;
		Set result;

		try {
			f_a            = createSet("(a,0.7);(b,0.3);(c,0.3)");
			f_b            = createSet("(a,0.0);(b,0.2);(c,0.4)");
			expectedResult = createSet("(a:a,0.0); (a:b,0.2); (a:c,0.4); (b:a,0.0); (b:b,0.2); (b:c,0.3); (c:a,0.0); (c:b,0.2); (c:c,0.3)");
			result = f_a.createCartesianProduct(f_b);
			if( ! result.equals(expectedResult) ) fail();

			f_a            = createSet("(af,0.1);(bf,0.75);(cf,1.0)");
			f_b            = createSet("(bf,0.7);(gb,0.1);(cf,0.4);(af,0.2);(gf,0.8)");
			expectedResult = createSet("(af:bf,0.1);(af:gb,0.1);(af:cf,0.1);(af:af,0.1);(af:gf,0.1);(bf:bf,0.7);(bf:gb,0.1);(bf:cf,0.4);(bf:af,0.2);(bf:gf,0.75);(cf:bf,0.7);(cf:gb,0.1);(cf:cf,0.4);(cf:af,0.2);(cf:gf,0.8)");
			result = f_a.createCartesianProduct(f_b);
			if( ! result.equals(expectedResult) ) fail();
		} catch(Exception ex) {
			fail("Unexpected exception occured: " + ex.getMessage());
		}
	}

	@Test
	public void testContainsElementSetElement() {
		StringElement inSet, notInSet, notEvenInBaseSet;
		SharpSet base;
		FuzzySet set;

		inSet            = new StringElement("A");
		notInSet         = new StringElement("B");
		notEvenInBaseSet = new StringElement("C");

		{
			TreeSet<SetElement>  elements = new TreeSet<SetElement> ();
			elements.add(inSet);
			elements.add(notInSet);
			base = new SharpSet(elements);
		}

		{
			BigDecimal[] elements = { new BigDecimal("0.7"), BigDecimal.ZERO };
			set = new FuzzySet(base, elements);
		}

		try {
			if( ! set.containsElement(inSet) ) fail();
			if( set.containsElement(notInSet) ) fail();
			if( set.containsElement(notEvenInBaseSet) ) fail();
		} catch(Exception ex) {
			fail("Unexpected exception occured: " + ex.getMessage());
		}
	}

	@Test
	public void testContainsElementFuzzyElement() {
		FuzzySet set;
		FuzzyElement a, b, c, d, e, f;
		ArrayList<FuzzyElement> set_elements = new ArrayList<FuzzyElement>();

		a = new FuzzyElement(new StringElement("A"), new BigDecimal("0.7"));
		b = new FuzzyElement(new StringElement("B"), new BigDecimal("0.5"));
		c = new FuzzyElement(new StringElement("C"), new BigDecimal("0.3"));
		d = new FuzzyElement(new StringElement("D"), new BigDecimal("1.0"));
		e = new FuzzyElement(new StringElement("E"), new BigDecimal("0.0"));
		f = new FuzzyElement(new StringElement("F"), new BigDecimal("1.0"));

		set_elements.add(a);
		set_elements.add(b);
		set_elements.add(c);
		set_elements.add(d);
		set_elements.add(e);
		set = new FuzzySet(set_elements);

		try {
			if( ! set.containsElement(a) ) fail();
			if( ! set.containsElement(b) ) fail();
			if( ! set.containsElement(c) ) fail();
			if( ! set.containsElement(d) ) fail();
			if( set.containsElement(e) ) fail();
			if( set.containsElement(f) ) fail();
		} catch(Exception ex) {
			fail("Unexpected exception occured: " + ex.getMessage());
		}
	}

	@Test
	public void testCreateComplementSet() {
		FuzzySet f_a, f_c=null, expectedResult;

		f_a = createSet("(A,0.7);(B,0.5);(C,0.3);(D,0.4);(E,0.0)");
		expectedResult = createSet("(A,0.3);(B,0.5);(C,0.7);(D,0.6);(E,1.0)");

		try {
			f_c = (FuzzySet) f_a.createComplementSet();
			if( ! f_c.equals(expectedResult) ) fail();
		} catch(Exception ex) {
			fail("Unexpected exception occured: " + ex.getMessage());
		}
	}

	@Test
	public void testCreateComplementSetFuzzySet() {
		try {
			FuzzySet f_a, complete_set, incomplete_set, expectedResult;
			Set result, complement;

			complete_set   = createSet("(A,1.0);(B,1.0);(C,1.0);(D,1.0);(E,1.0);(F,1.0)");
			f_a            = createSet("(A,0.7);(B,0.5);(C,0.3);(D,0.4);(E,0.0)");

			result = f_a.createComplementSet(f_a);
			if( !result.isEmptySet() ) fail();

			expectedResult = createSet("(A,0.3);(B,0.5);(C,0.7);(D,0.6);(E,1.0);(F,1.0)");
			result = complete_set.createComplementSet(f_a);
			if( ! result.equals(expectedResult) ) fail();

			f_a            = createSet("(A,0.7);(B,0.5);(C,0.3);(D,0.4);(E,0.0)");
			incomplete_set = createSet("(A,1.0);(B,1.0);(C,1.0);(D,1.0);(E,1.0)");
			result = incomplete_set.createComplementSet(f_a);
			complement = f_a.createComplementSet();
			if( ! complement.equals(complement) ) fail();

			result = f_a.createComplementSet(complete_set);
			if( !result.isEmptySet() ) fail();

			f_a            = createSet("(A,0.7);(B,0.5);(C,0.3);(D,0.4);(E,0.0)");
			incomplete_set = createSet("(B,0.3);(C,0.5);(E,0.7);(F,0.6)");
			expectedResult = createSet("(A,0.7);(B,0.2);(C,0);(D,0.4);(E,0.0)");
			result = f_a.createComplementSet(incomplete_set);
			if( ! result.equals(expectedResult ) ) fail();

		} catch(Exception ex) {
			fail();
		}
	}

	@Test
	public void testCreateSymmetricComplementSet() {
		FuzzySet f_a, f_b, expectedResult;
		Set result;

		try {
			f_a            = createSet("(A,1.0);(B,1.0);(C,1.0);(D,1.0);(E,1.0)");
			result = f_a.createSymmetricComplementSet(f_a);
			if( ! result.isEmptySet() ) fail();

			f_a            = createSet("(A,1.0);(B,1.0);(C,1.0);(D,1.0);(E,1.0)");
			f_b            = createSet("(A,1.0);(B,1.0);(E,1.0);(D,1.0);(Y,1.0)");
			expectedResult = createSet("(C,1.0);(Y,1.0)");
			result = f_a.createSymmetricComplementSet(f_b);
			if( ! result.equals(expectedResult) ) fail();

			f_a            = createSet("(A,0.7);(B,0.5);(C,0.3);(D,0.4);(E,0.0)");
			f_b            = createSet("(A,0.3);(B,0.5);(C,0.7);(D,0.4);(E,1.0)");
			expectedResult = createSet("(A,0.4);(B,0.0);(C,0.4);(D,0.0);(E,1.0)");
			result = (FuzzySet) f_a.createSymmetricComplementSet(f_b);
			if( ! result.equals(expectedResult) ) fail();
		} catch(Exception ex) {
			fail("Unexpected exception occured: " + ex.getMessage());
		}
	}

	@Test
	public void testGetAbsoluteValue() {
		FuzzySet f_a, f_b, f_c;

		f_a = createSet("(A,0.7);(B,0.5);(C,0.3);(D,0.4);(E,0.0)");
		f_b = createSet("(A,0.3);(B,0.5);(C,0.7);(D,0.4);(E,1.0)");
		f_c = createSet("(A,0.3);(B,0.5);(C,0.7);(D,0.6);(E,1.0)");

		try {
			if( f_a.getAbsoluteValue().compareTo(new BigDecimal("1.9"))!=0 ) fail();
			if( f_b.getAbsoluteValue().compareTo(new BigDecimal("2.9"))!=0 ) fail();
			if( f_c.getAbsoluteValue().compareTo(new BigDecimal("3.1"))!=0 ) fail();
		} catch(Exception ex) {
			fail("Unexpected exception occured: " + ex.getMessage());
		}
	}

	@Test
	public void testGetAverageValue() {
		FuzzySet f_a, f_b, f_c;

		f_a = createSet("(A,0.7);(B,0.5);(C,0.3);(D,0.4);(E,0.0)");
		f_b = createSet("(A,0.3);(B,0.5);(C,0.7);(D,0.4);(E,1.0)");
		f_c = createSet("(A,0.3);(B,0.5);(C,0.7);(D,0.6);(E,1.0)");

		try {
			if( f_a.getAverageValue().compareTo( new BigDecimal("0.475") ) != 0 ) fail();
			if( f_b.getAverageValue().compareTo( new BigDecimal("0.58") ) != 0 ) fail();
			if( f_c.getAverageValue().compareTo( new BigDecimal("0.62") ) != 0 ) fail();
		} catch(Exception ex) {
			fail("Unexpected exception occured: " + ex.getMessage());
		}	}

}
