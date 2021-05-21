package de.wbongartz.fuzzy_logic_parser.set;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.fail;


public class DecimalElementTest {
	
	DecimalElement a, a_, b, b_;

	@BeforeEach
	public void setUp() throws Exception {
		a  = new DecimalElement(BigDecimal.ONE);
		a_ = new DecimalElement(BigDecimal.ONE);
		b  = new DecimalElement(new BigDecimal("2.0"));
		b_ = new DecimalElement(new BigDecimal("2.0000001"));
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	@Test
	public void testCompare() {
		if(a.equals(b)) {
			fail("These elements aren't equal!");
		}
		if(b.equals(a)) {
			fail("These elements aren't equal!");
		}
		if(!a.equals(a_)) {
			fail("These elements are equal!");
		}
		if(b.equals(b_)) {
			fail("These elements aren't equal!");
		}
	}

}
