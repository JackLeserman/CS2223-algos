package algs.days.day19.expr;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.Queue;

public class Log2 extends Expression {
	Expression inner;
	
	public Log2(Expression inner) {
		this.inner = inner;
	}
	
	@Override
	public double eval() {
		return Math.log(inner.eval())/Math.log(2);
	}

	@Override
	public String format() {
		return String.format("Log2(%s)", inner.format());
	}
	
	@Override
	public String postfix() {
		return inner.postfix() + " log2";
	}
	
	@Override
	public void code(Queue<String> variables, BST<String,String> env) {
		inner.code(variables, env);
		variables.enqueue("double " + getOrCreate(this, env) + " = Math.log(" + getOrCreate(inner, env) + ")/Math.log(2);");
	}
}