package de.wbongartz.fuzzy_logic_parser.fuzzyset;

import de.wbongartz.fuzzy_logic_parser.set.DecimalElement;
import de.wbongartz.fuzzy_logic_parser.set.SetElement;

public interface MappingFunction {
	
	public abstract DecimalElement compute(int ordinalValue, SetElement element);

}
