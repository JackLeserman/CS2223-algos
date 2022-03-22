package jleserman_hw1;

import algs.days.day03.FixedCapacityStack;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

import java.util.Objects;

/**
 * Complete this implementation that takes a postfix expression and converts it into
 * an Infix Expression using a fixed Capacity stack. Also perform the necessary 
 * computation to produce its value
 * 
 * Using the postfix expression as input
 * 
 *     3 6 + 5 * 8 2 - /
 *  
 * should produce the following as output:
 * 
 *     (((3 + 6) * 5) / (8 - 2)) = 7.5
 *
 * Note that postfix expressions do not need parentheses, which is one of their
 * major selling points.
 */
public class PostFixToInfix {

	public static void main(String[] args) {
		FixedCapacityStack<String> exprs = new FixedCapacityStack<String>(100);
		FixedCapacityStack<Double> vals = new FixedCapacityStack<Double>(100);
		while (!StdIn.isEmpty()) {
			// Read token. push if operator.
			String s = StdIn.readString();
			if (!s.equals("+") || !s.equals("-") || !s.equals("/") || !s.equals("-") ) {
				vals.push(Double.parseDouble(s));
				exprs.push(s);
			} else if (s.equals("+")) {
				String n2 = exprs.pop();
				String n1 = exprs.pop();
				Double v2 = vals.pop();
				Double v1 = vals.pop();
				String chunk = ("( " + n1 + " + " + n2 + " )");
				System.out.println(chunk);
				exprs.push(chunk);
				vals.push((v2 + v1));
			} else if (s.equals("-")) {
				String n2 = exprs.pop();
				String n1 = exprs.pop();
				Double v2 = vals.pop();
				Double v1 = vals.pop();
				String chunk = ("( " + n1 + " - " + n2 + " )");
				System.out.println(chunk);
				exprs.push(chunk);
				vals.push((v2 - v1));
			} else if (s.equals("/")) {
				String n2 = exprs.pop();
				String n1 = exprs.pop();
				Double v2 = vals.pop();
				Double v1 = vals.pop();
				String chunk = ("( " + n1 + " / " + n2 + " )");
				System.out.println(chunk);
				exprs.push(chunk);
				vals.push((v2 / v1));
			} else if (s.equals("*")) {
				String n2 = exprs.pop();
				String n1 = exprs.pop();
				Double v2 = vals.pop();
				Double v1 = vals.pop();
				String chunk = ("( " + n1 + " * " + n2 + " )");
				System.out.println(chunk);
				exprs.push(chunk);
				vals.push((v2 * v1));
			}
			String finalExp = exprs.pop();
			Double answer = vals.pop();
			System.out.println(finalExp);
			System.out.println(answer);

		}
	}
}

