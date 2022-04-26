package algs.days.day21;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class FindTriangle {
	/** Comments below are for WORST case when every edge in graph exists. */ 
	static int triangleSearchRepetitions (Graph g) {
		
		// find triangles. Assume no self loops, thus u can never be in g.adj(u)
		int count = 0;
		for (int u = 0; u < g.V(); u++) {             // for all vertices u...
			for (Integer v : g.adj(u)) {              //   go find a neighbor v
				if (v < u) { continue; }
				for (Integer w : g.adj(v)) {     	  //   then find neighbor w to v
					if (w < v) { continue; }		  // ensure that #u < #v < #w
					for (Integer x : g.adj(u)) { 
						if (x.equals(w)) {            // DEFECT WAS IN USING == here
							count++;                  // HAH!
						}
					}
				}
			}
		}

		return count;    // make sure to avoid over-counting: given 3 elements, there are 3! permutations
	}

	/**
	 * Build complete graphs and search
	 */
	static void completeGraphTriangleSearch(int k) {
		Graph g = new Graph(k);
		for (int i = 0; i < k-1; i++) {
			for (int j = i+1; j < k; j++) {
				g.addEdge(i, j);
			}
		}

		int count = triangleSearchRepetitions(g);
		StdOut.println(k + "\t" + count);
	}

	public static void main(String[] args) {
		Graph g;
		int countT;
			
		if (args.length != 0) {
			In in = new In(args[0]);
			g = GraphLoader.load(in);
			countT = triangleSearchRepetitions(g);
			StdOut.println(g.V() + "\t" + countT);
			return;
		}

		StdOut.println("# Triangles in Complete Graph");
		StdOut.println("N\tcount\ta\tb\tc\td\te");
		for (int i = 3; i <= 7; i++) {
			completeGraphTriangleSearch(i);
		}
		StdOut.println();

		StdOut.println("This is going to take awhile!");
		StdOut.println("Also: results do not match published expectation.");
		StdOut.println("https://www.sciencedirect.com/science/article/pii/S0012365X0400370X");
		StdOut.println("N\tE\tCountE\tCountT\tEst.");
		int N = 8;
		while (N <= 1024) {
			g = new Graph(N);

			// with probability 1/2, generate each possible edge. Note this means
			// that the number of edges will be ~ (1/2) C(N,2) or N^2/4-N/4 
			for (int i = 0; i < N-1; i++) {
				for (int j = i+1; j < N; j++) {
					if (StdRandom.uniform() < 0.5) {
						g.addEdge(i,j);
					}
				}
			}
			long countE = N*(N-1)/4;

			countT = triangleSearchRepetitions(g);
			// Expected number of triangles is (1/8) C(N,3) 
			// http://www.sciencedirect.com/science/article/pii/S0012365X0400370X
			// however, this doesn't pan out. Curious to see the number of triangles
			// remain mostly constant...
			//			N		E		Count	Est.
			//			64		985		4833	5208.0
			//			128		4045	42112	42672.0
			//			256		16339	173600	345440.0	* off by x2
			//			512		65264	689410	2779840.0	* off by x4
			//			1024	261563	2763238	2.2304128E7	* off by x8

			float estimate = N*(N-1)*(N-2)/48.0f;
			StdOut.println(N + "\t" + g.E() + "\t" + countE + "\t" + countT + "\t" + estimate);
			N *= 2;
		}
	}
}
