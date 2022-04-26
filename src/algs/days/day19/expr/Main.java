package algs.days.day19.expr;

import edu.princeton.cs.algs4.BST;
import edu.princeton.cs.algs4.Queue;

public class Main {
	public static void main(String[] args) {
		Expression one = new Multiply(new Value(2), new Value(3));
		Expression two = new Sqrt(one);
		System.out.println("one:" + one.format());
		System.out.println("two:" + two.format());
		System.out.println(new Sqrt(new Value(2)).format());
		System.out.println("two evaluates to " + two.eval());
		
		Queue<String> q = new Queue<>();
		BST<String,String> env = new BST<>();
		two.code(q, env);
		for (String stmt : q) {
			System.out.println(stmt);
		}
		System.out.println("System.out.println(\"value is \" + " + two.getOrCreate(two, env) + ");");
	}
}
