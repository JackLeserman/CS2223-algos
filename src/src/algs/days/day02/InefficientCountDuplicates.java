package algs.days.day02;

/**
 * Question posed in class for using BinaryArraySearch to count number of repeated occurrences of value
 * in a sorted array.
 * 
 * 
 */
public class InefficientCountDuplicates {
	
	/** 
	 * Find the location of the value AND THEN sweep left and right counting how many
	 */
	public static int countDuplicates(int target, int[] a) {
		int lo = 0;
		int hi = a.length -1;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (a[mid] > target) {         // Greater than? Keep looking in left range
				hi = mid - 1;             
			} else if (a[mid] < target) {  // Smaller than? Keep looking in right range 
				lo = mid + 1;
			} else {
				// found target! Sweep left to find first copy, then right to find last copy
				lo = mid;
				while (lo >= 0 && a[lo] == target) {   // if still could be more, move left 
					lo--;  
				}
				hi = mid;
				while (hi <= a.length-1 && a[hi] == target) { // if still coudl be more, move right
					hi++;
				}
				// Now: lo is 1 less than first copy; hi is 1 greater than last copy
				return hi - lo - 1;   // count of values in this range.
			}
		}
		
		return 0;                          // None were found...
	}
	
	public static void main(String[] args) {
		int[] example = new int[] { 1, 3, 5, 5, 5, 5, 9};
		
		int target = 5;
		int count = countDuplicates(target, example);
		System.out.println(target + " occurs " + count + " times.");
		
	}
}
