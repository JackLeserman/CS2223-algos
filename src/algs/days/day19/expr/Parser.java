package algs.days.day19.expr;

import algs.days.day03.FixedCapacityStack;
import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdIn;

public class Parser {
	/**
	 * Complete this implementation that takes a postfix expression string and converts it into
	 * an Expression node using a fixed Capacity stack. When done, an Expression node will
	 * be returned.
	 * 
	 * Using the postfix expression as input
	 * 
	 *     3 1 + 4 / 1 5 + 9 * 2 6 * - *
	 *  
	 * should produce the expression from the homework, namely
	 * 
	 *     (((3+1)/4 * (((1+5)*9)-(2*6)))
	 *
	 * Note that postfix expressions do not need parentheses, which is one of their
	 * major selling points.
	 * 
	 *    8 sqrt sqrt sqrt sqrt
	 */
	public static void main(String[] args) {

		// since everything IS an expression (even Values) you only need a single stack.
		FixedCapacityStack<Expression> exprs = new FixedCapacityStack<Expression>(100);

		while (!StdIn.isEmpty()) {
			// Read token. If value then push, otherwise process operators (unary and binary)
			String s = StdIn.readString();
			
			try {
				double d = Double.valueOf(s);
				exprs.push(new Value(d));
			} catch (NumberFormatException nfe) {
				if (Factory.isUnary(s)) {
					Expression inner = exprs.pop();
					exprs.push(Factory.parse(s, inner));
				} else {
					Expression right = exprs.pop();
					Expression left = exprs.pop();
					
					exprs.push(Factory.parse(s, left, right));
				}
			}
		}
		
		// Grab the result
		Expression exp = exprs.pop();
		
		//output some information
		System.out.println(exp.format() + " = " + exp.eval());
		System.out.println("PostFix:" + exp.postfix());
		
		Queue<String> q = new Queue<>();
		BST<String,String> env = new BST<>();
		exp.code(q, env);
		for (String stmt : q) {
			System.out.println(stmt);
		}
		System.out.println("System.out.println(\"value is \" + " + exp.getOrCreate(exp, env) + ");");
	}
}
