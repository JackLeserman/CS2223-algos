package algs.days.day18;

import algs.hw3.sort.*;
import edu.princeton.cs.algs4.StdRandom;

public class ExchangeableVisualizer {
	
	/** This ensures an exact copy. DO NOT DO THIS FOR HW3. */
	public static Exchangeable[] duplicate(int[] in) {
		Exchangeable[] copy = new Exchangeable[in.length];
		for (int i = 0; i < in.length; i++) {
			copy[i] = new Exchangeable(in[i]);
		}
		return copy;
	}
	
	/** Report # of exchanges of each element, by position. */
	static void report (String name, Exchangeable[] vals) {
		System.out.print(name + "\t");
		for (Exchangeable v : vals) {
			System.out.print(String.format("%2d ", v.getExchangeCount()));
		}
		System.out.println();
	}
	
	public static void main(String[] args) {
		int[] base = new int[32];
		for (int i = 0; i < base.length; i++) {
			base[i] = StdRandom.uniform(100);
		}
		
		Exchangeable H[] = duplicate(base);
		Exchangeable M[] = duplicate(base);
		Exchangeable S[] = duplicate(base);
		Exchangeable Q[] = duplicate(base);
		Exchangeable I[] = duplicate(base);
		
		HeapSort.sort(H);
		Merge.sort(M);
		Selection.sort(S);
		Quick.sort(Q);
		Insertion.sort(I);
		
		report("Heap", H);
		report("Merge", M);
		report("Select", S);
		report("Quick", Q);
		report("Insert", I);
	}

}
