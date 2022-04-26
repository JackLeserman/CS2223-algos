package algs.days.day19.expr;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.Queue;

public class Divide extends Expression {
	Expression left;
	Expression right;
	
	public Divide(Expression left, Expression right) {
		this.left = left;
		this.right = right;
	}
	
	@Override
	public double eval() {
		return left.eval() / right.eval();
	}

	@Override
	public String format() {
		return String.format("(%s/%s)", left.format(), right.format());
	}
	
	@Override
	public String postfix() {
		return left.postfix() + " " + right.postfix() + " /";
	}
	
	@Override
	public void code(Queue<String> variables, BST<String,String> env) {
		left.code(variables, env);
		right.code(variables, env);
		variables.enqueue("double " + getOrCreate(this, env) + " = " + getOrCreate(left, env) + " / " + getOrCreate(right, env) + ";");
	}
}