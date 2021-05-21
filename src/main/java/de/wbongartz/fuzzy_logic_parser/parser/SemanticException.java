package de.wbongartz.fuzzy_logic_parser.parser;

@SuppressWarnings("serial")
public class SemanticException extends Exception {
	
	@SuppressWarnings("unused")
	private SemanticException(String msg) {
		super(msg);
	}
	
	public SemanticException(String msg, int line, int col) {
		super(msg + " (Line: " + line + ", Col: " + col + ")");
	}

}
