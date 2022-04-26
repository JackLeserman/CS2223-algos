package algs.days.day19.expr;

public class Factory {
	
	public static boolean isUnary(String op) {
		if (op.equals("sqrt")) { return true; }
		if (op.equals("log2")) { return true; }
		
		return false;
	}
	
	public static Expression parse(String op, Expression inner) {
		if (op.equals("sqrt")) {
			return new Sqrt(inner);
		} else if (op.equals("log2")) {
			return new Log2(inner);
		}
		
		throw new RuntimeException("Cannot understand unary operation: " + op);
	}
	
	public static Expression parse(String op, Expression left, Expression right) {
		if (op.equals ("+")) { 
			return new Add(left, right);
		} else if (op.equals ("-")) { 
			return new Subtract(left, right);
		} else if (op.equals ("*")) { 
			return new Multiply(left, right);
		} else if (op.equals ("/")) { 
			return new Divide(left, right);
		} 
		
		throw new RuntimeException("Cannot understand binary operation: " + op);
	}
}
