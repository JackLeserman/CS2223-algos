package algs.days.day09;

import java.util.Arrays;

// yet another one
public class FindMaxRecursively {

	static int findMaximum(int[] a, int lo, int hi) {
		/** Base Case: One Element. */
		if (lo == hi) {
			return a[lo];
		}
		
		/** Recursive case: compute largest of remaining objects. One additional comparison. */
		int mid = (lo + hi)/2;
		int left = findMaximum (a, lo, mid);
		int right = findMaximum (a, mid+1, hi);
		
		// post processing
		if (left < right) {
			return right;
		} else {
			return left;
		}
	}
	
	public static void main(String[] args) {
		int[] sample = { 3, 5, 9, 1, 2, 7, 9, 4 };
		System.out.println("max of " + Arrays.toString(sample) + " is " + findMaximum(sample, 0, sample.length-1) );
		
	}
}
