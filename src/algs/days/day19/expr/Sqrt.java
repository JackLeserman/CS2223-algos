package algs.days.day19.expr;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.Queue;

public class Sqrt extends Expression {
	Expression inner;
	
	public Sqrt(Expression inner) {
		this.inner = inner;
	}
	
	@Override
	public double eval() {
		return Math.sqrt(inner.eval());
	}

	@Override
	public String format() {
		return String.format("Sqrt(%s)", inner.format());
	}
	
	@Override
	public String postfix() {
		return inner.postfix() + " sqrt";
	}

	@Override
	public void code(Queue<String> variables, BST<String,String> env) {
		inner.code(variables, env);
		variables.enqueue("double " + getOrCreate(this, env) + " = Math.sqrt(" + getOrCreate(this, env) + ");");
	}
}