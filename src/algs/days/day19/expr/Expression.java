package algs.days.day19.expr;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.Queue;

/** 
 * Every operator must extend this class. 
 * 
 * This is the abstract class that represents a Node in an Expression tree.
 * 
 * Unlike the BST and AVL example from class, there is no separate class to represent 
 * the tree itself. Instead, you will only be working with nodes that are subclasses
 * of this abstract type.
 */
public abstract class Expression {
	/** 
	 * Compute the result of an expression rooted at this node and return its double 
	 */
	public abstract double eval();
	
	/** 
	 * Generate a string equivalent to the Infix expression for this node. 
	 */
	public abstract String format();
	
	/** Postfix representation. */
	public abstract String postfix();
	
	/** Create resources to generate code. */
	public abstract void code(Queue<String> stmts,  BST<String,String> env);
	
	/** Get or create a new variable in the environment. */
	public String getOrCreate(Expression e, BST<String,String> env) {
		String key = e.toString();
		if (!env.contains(key)) {
			String id = "x" + env.size();
			env.put(key, id);
		}
		return env.get(key);
	}
	
}
