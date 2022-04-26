package algs.spreadsheet;

import edu.princeton.cs.algs4.*;

/**
 * Code from p. 129 of Sedgewick (4ed). Modified to work within Spreadsheet.
 * 
 * The only change with regards to HW1 is that you can embed spreadsheet references now...
 */
public class Evaluate {
	
	/**
	 * Evaluates an individual string as found within an expression.
	 * 
	 * If string is a cell reference (like "A5" or "B19") then return the contents
	 * of that cell.
	 */
	static double evalToken(Spreadsheet spreadsheet, String s) {
		try {
			double d = Double.valueOf(s);
			return d;
		} catch (Exception e) {
			// must be a string or a reference...
			int [] isRef = Cell.isReference(s);
			if (isRef == null) {
				return 0; // give up and treat like 0
			} else {
				String contents = spreadsheet.getValue(s);
				if (contents.equals("")) { return 0; }
				return Double.valueOf(contents);
			}
		}
	}
	
	/**
	 * Takes a string that needs to be evaluated. Here are the options:
	 * 
	 * @param s
	 * @return
	 */
	public static double eval(Spreadsheet spreadsheet, String s) {
		// any normal string is evaluated as self.
		if (!s.startsWith("=")) {
			return Double.valueOf(s);
		}
		
		// Strip off the "=" at the front and evaluate as infix operator string (like HW1)
		return evalExpression(spreadsheet, s.substring(1));
	}
	
	/**
	 * You are to modify this method.
	 */
	static double evalExpression(Spreadsheet spreadsheet, String input) {
		
		Stack<String> ops = new Stack<String>();
		Stack<Double> vals = new Stack<Double>();

		// must be space before all tokens
		for (String s : input.split(" ")) {
			// Read token. push if operator.

			if (s.equals ("(")) { /* do nothing */ }
			else if (s.equals ("")) { /* do nothing */ }
			else if (s.equals ("+")) { ops.push(s); }
			else if (s.equals ("-")) { ops.push(s); }
			else if (s.equals ("*")) { ops.push(s); }
			else if (s.equals ("/")) { ops.push(s); }
			else if (s.equals ("sqrt")) { ops.push(s); }
			else if (s.equals (")")) {
				// pop, evaluate, and push result if token is ")".
				String op = ops.pop();
				double v = vals.pop();
				if (op.equals("+")) { v = vals.pop() + v; }
				else if (op.equals("-")) { v = vals.pop() - v; }
				else if (op.equals("*")) { v = vals.pop() * v; }
				else if (op.equals("/")) { v = vals.pop() / v; }
				else if (op.equals("sqrt")) { v = Math.sqrt(v); }
				vals.push(v);
			} else {
				// Token no operator or paren; must be double value to push
				vals.push(evalToken(spreadsheet, s));
			}
		}
		
		return vals.pop();
	}
}
