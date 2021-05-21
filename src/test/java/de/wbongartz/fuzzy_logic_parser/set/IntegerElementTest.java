package de.wbongartz.fuzzy_logic_parser.set;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class IntegerElementTest {
	
	private IntegerElement a, a_, b;
	
	@BeforeEach
	public void setUp() throws Exception {
		a  = new IntegerElement(1);
		a_ = new IntegerElement(1);
		b  = new IntegerElement(2);
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
	}
	
}
