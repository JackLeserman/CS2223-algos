package algs.days.day19.expr;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.Queue;

/** 
 * Question 1. Represents the binary expression (left + right) where 'left' and 'right'
 * are themselves expressions.  
 *
 * For example:
 * 
 *    Add add1 = new Add(new Value(3), new Value(5)) 
 *    
 * represents the expression (3.0 + 5.0). The following example:
 * 
 *    Add add2 = new Add(new Value(4), add1)
 *    
 * represents the expression (4.0 + (3.0 + 5.0))
 *
 */
public class Add extends Expression {

	Expression left;
	Expression right;
	
	public Add(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public double eval() {
		return left.eval() + right.eval();
	}

	@Override
	public String format() {
		return String.format("(%s+%s)", left.format(), right.format());
	}
	
	@Override
	public String postfix() {
		return left.postfix() + " " + right.postfix() + " +";
	}

	@Override
	public void code(Queue<String> variables, BST<String,String> env) {
		left.code(variables, env);
		right.code(variables, env);
		variables.enqueue("double " + getOrCreate(this, env) + " = " + getOrCreate(left, env) + " + " + getOrCreate(right, env) + ";");
	}
}
