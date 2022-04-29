package jleserman.hw4;

import edu.princeton.cs.algs4.StdRandom;

/** Copy to your USERID.hw4 package and make changes. */
public class Counting {
	
	/**
	 * This skeletal structure needs to be modified to solve this problem. Feel free to add code anywhere...
	 */
	public static void main(String[] args) {
		System.out.println("N\tMaxAVHt\tMaxAVDp\tMaxAVZr\tAVZero%\tMaxRBHt\tMaxRBDp\tMaxRBZr\tRBZero%");
		for (int N = 32; N <= 262144; N*= 2) {
			
			int NUMTRIAL= 100;
			for (int T = 0; T < NUMTRIAL; T++) {
				
				// This constructs the array of N-1 values (where N is a power of 2) and 
				// it uses StdRandom.setSeed() to ensure all students will get the same result
				int[] vals = new int[N-1];
				for (int i = 0; i < N-1; i++) {
					vals[i] = i+1;
				}
				StdRandom.setSeed(T);
				StdRandom.shuffle(vals);
				
				// note: Insert the integers in vals into a new AVL or RedBlack Tree in order from left to right
				
				// MORE TO DO HERE...
			}
		}
	}
}


