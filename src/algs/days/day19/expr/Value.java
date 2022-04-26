package algs.days.day19.expr;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.Queue;

/** 
 * Question 1. Represents a "leaf node" in the expression tree, whose value 
 * is the desired double.
 *
 * For example:
 * 
 *    Value lit1 = new Value(3);
 *    
 * represents the expression 3.0. When you call 'eval' on this object, it returns
 * its value. 
 */
public class Value extends Expression {

	final double value;
	
	public int height() {
		return 0;
	}
	
	public Value(double value) {
		this.value = value;
	}
	
	@Override
	public double eval() {
		return value;
	}

	@Override
	public String format() {
		if (value == (int)(value)) {
			return Integer.toString((int)value);
		} else {
			return Double.toString(value);
		}
	}
	
	@Override
	public String postfix() {
		return format();
	}

	@Override
	public void code(Queue<String> variables,  BST<String,String> env) {
		variables.enqueue("double " + getOrCreate(this, env) + " = " + value + ";");
	}
}
