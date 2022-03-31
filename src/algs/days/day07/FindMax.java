package algs.days.day07;

public class FindMax {
	
	static int findMax(int[] A, int lo, int hi) {
		// Base Case: Only one element!
		if (lo == hi) {
			return A[lo];
		}
		
		// Recursive Case: More than one element
		int mid = (lo+hi)/2;
		
		// Find L, max of left side, and R, max of right side
		int L = findMax(A, lo, mid);
		int R = findMax(A, mid+1, hi);
		
		// return larger of the two
		if (L > R) { return L; } else { return R; }
	}
	
	public static void main(String[] args) {
		int[] vals = new int[] { 15, 21, 20, 2};
		
		System.out.println(findMax(vals, 0, vals.length-1));
	}
}
