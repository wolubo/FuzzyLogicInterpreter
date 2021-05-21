package de.wbongartz.fuzzy_logic_parser.fuzzyset;

import java.math.BigDecimal;

import de.wbongartz.fuzzy_logic_parser.set.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;


public class FuzzyElementTest {

	@BeforeEach
	public void setUp() throws Exception {
	}

	@AfterEach
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testFuzzyElementBasicSetElementFloat() throws Exception {
		try {
			new FuzzyElement(null, BigDecimal.ONE);
			fail("IllegalArgumentException expected (parameter 'element' is null)!");
		} catch(IllegalArgumentException ex) {
		}

		try {
			new FuzzyElement(new IntegerElement(1), new BigDecimal("2.0"));
			fail("IllegalArgumentException expected (parameter 'value' is greater than 1.0)!");
		} catch(IllegalArgumentException ex) {
		}

		try {
			new FuzzyElement(new IntegerElement(1), BigDecimal.ONE.negate());
			fail("IllegalArgumentException expected (parameter 'value' is less than 0.0)!");
		} catch(IllegalArgumentException ex) {
		}

		try {
			new FuzzyElement(new IntegerElement(1), BigDecimal.ONE);
		} catch(Exception ex) {
			fail("Unexpected exception occured: " + ex.getMessage());
		}
	}

	@Test
	public void testEqualsObject() {
		try {
			FuzzyElement a = new FuzzyElement(new IntegerElement(1), BigDecimal.ONE);
			FuzzyElement b = new FuzzyElement(new IntegerElement(2), BigDecimal.ONE);
			FuzzyElement c = new FuzzyElement(new IntegerElement(1), BigDecimal.ONE);
			FuzzyElement d = new FuzzyElement(new IntegerElement(1), new BigDecimal("0.5"));
			if( ! a.equals(a) ) fail("These elements are not equal!");
			if( ! d.equals(d) ) fail("These elements are not equal!");
			if( ! a.equals(c) ) fail("These elements are not equal!");

			if( a.equals(b) ) fail("These elements are not equal!");
			if( ! a.equals(d) ) fail("These elements are equal!");
			if( b.equals(d) ) fail("These elements are not equal!");
			if( ! c.equals(d) ) fail("These elements are equal!");
		} catch(Exception ex) {
			fail("Unexpected exception occured: " + ex.getMessage());
		}
	}

}
