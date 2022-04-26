package algs.days.day21;

import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class FindTriangleRaw {
	
	/** Comments below are for WORST case when every edge in graph exists.  */ 
	static int triangleSearchRepetitions (int[][] edges) {
			
		// find triangles. Assume no self loops, thus u can never be in g.adj(u)
		int count = 0;
		for (int u = 0; u < edges.length; u++) {            // for all vertices u...
			for (int v = u+1; v < edges.length; v++) {        //   go find a neighbor v
				if (edges[u][v] == 0) { continue; }
				for (int w = v+1; w < edges.length; w++) {    	  //   then find neighbor w to v
					if (edges[v][w] == 0) { continue; }			  // ensure that #u < #v < #w
					
					if (edges[u][w] == 1) { count++; }
				}
			}
		}

		return count;    // make sure to avoid over-counting: given 3 elements, there are 3! permutations
	}

	public static void main(String[] args) {
		int countT;

		StdOut.println("This is going to take awhile!");
		StdOut.println("Also: results do not match published expectation.");
		StdOut.println("https://www.sciencedirect.com/science/article/pii/S0012365X0400370X");
		StdOut.println("N\tE\tCountE\tCountT\tEst.");
		int N = 8;
		while (N <= 1024) {
			// Create 2-dimensional array to store an edge. If edges[i][v] == 1, there
			// is an edge from vertex #i to vertex #j
			int[][] edges = new int[N][N];

			// with probability 1/2, generate each possible edge. Note this means
			// that the number of edges will be ~ (1/2) C(N,2) or N^2/4-N/4
			int E = 0;
			for (int i = 0; i < N-1; i++) {
				for (int j = i+1; j < N; j++) {
					if (StdRandom.uniform() < 0.5) {
						edges[i][j] = edges[j][i] = 1; 
						E++;
					}
				}
			}
			long countE = N*(N-1)/4;

			countT = triangleSearchRepetitions(edges);
			// Expected number of triangles is (1/8) C(N,3) 
			float estimate = N*(N-1)*(N-2)/48.0f;
			StdOut.println(N + "\t" + E + "\t" + countE + "\t" + countT + "\t" + estimate);
			N *= 2;
		}
	}
}
