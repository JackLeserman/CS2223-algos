package jleserman.hw4;

import edu.princeton.cs.algs4.StdRandom;

/** Copy to your USERID.hw4 package and make changes. */
public class Counting {

	/**
	 * This skeletal structure needs to be modified to solve this problem. Feel free to add code anywhere...
	 */

	/***
	 * Adds a node to the RBT if key does not exist, if it does exist the value is updated
	 */
	public static void main(String[] args) {
		System.out.println("N\tMaxAVHt\tMaxAVDp\tMaxAVZr\tAVZero%\tMaxRBHt\tMaxRBDp\tMaxRBZr\tRBZero%");
		for (int N = 32; N <= 262144; N*= 2) {

			int MaxAVHt = Integer.MIN_VALUE;
			int MaxAVDf = Integer.MIN_VALUE;
			int MaxAVZr = Integer.MIN_VALUE;

			int MaxRBHt = Integer.MIN_VALUE;
			int MaxRBDf = Integer.MIN_VALUE;
			int MaxAVZrRB = Integer.MIN_VALUE;

			int NUMTRIAL= 100;
			for (int T = 0; T < NUMTRIAL; T++) {
//------AVL-----------------------------------------
				AVL avlTree = new AVL();
				RedBlackBST rbt = new RedBlackBST();
				// This constructs the array of N-1 values (where N is a power of 2) and 
				// it uses StdRandom.setSeed() to ensure all students will get the same result
				int[] vals = new int[N-1];
				for (int i = 0; i < N-1; i++) {
					vals[i] = i+1;
				}
				StdRandom.setSeed(T);
				StdRandom.shuffle(vals);

				for(int v : vals){
					avlTree.insert(v);
					rbt.put(v,0);
				}
				int avHeight = avlTree.height();
				int mindepth = avlTree.minDepth();
				System.out.println((mindepth));
				int depthDiff = avHeight - mindepth;
				int numZeros  = avlTree.getZeroCounts();
				if(avHeight > MaxAVHt){ MaxAVHt = avHeight;}
				if(depthDiff > MaxAVDf){ MaxAVDf = depthDiff;}
				if(numZeros > MaxAVZr){ MaxAVZr = numZeros;}
//------RBT-----------------------------------------
				int rbtHeight = rbt.height();
				int minDepth = rbt.minDepth();
				int depthDiffR = rbtHeight - minDepth;
				int numZeroR = rbt.getZeroCounts();

				if(rbtHeight > MaxRBHt){MaxRBHt = rbtHeight;}
				if(depthDiffR > MaxRBDf){MaxRBDf = depthDiffR;}
				if(numZeroR > MaxAVZrRB){MaxAVZrRB = numZeroR;}
			}
			double AVZeroPerc = ((double) MaxAVZr / (double) (N-1)) * 100;
			String AVLStr = (N + "\t" + MaxAVHt + "\t" + MaxAVDf + "\t" + MaxAVZr + "\t" + Math.round(AVZeroPerc) + "%"); //AVL
			double RBZeroPerc = ((double) MaxAVZrRB / (double) (N-1)) * 100;
			String RBTstr = (MaxRBHt + "\t" + MaxRBDf + "\t" + MaxAVZrRB + "\t" +  Math.round(RBZeroPerc) + "%");//RBT
			System.out.println(AVLStr + "\t" + RBTstr);
		}
	}
}


