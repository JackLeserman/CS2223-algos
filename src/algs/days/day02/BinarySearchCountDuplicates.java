package algs.days.day02;

/**
 * Question posed in class for using BinaryArraySearch to count number of repeated occurrences of value
 * in a sorted array.
 * 
 * Given:
 * 
 *    [1, 3, 5, 5, 5, 5, 9]
 *    
 * The target value of '5' appears four times, while the value '8' appears zero times.
 * 
 * key idea is to not stop search when '==' to the target value, but to keep pursuing, once for the left-edge of the
 * range, and a second time for the right-edge of the range. Thus:
 * 
 *    [1, 3, 5, 5, 5, 5, 9]
 *    lo        m        hi         Since m is >= to target of 5, keep searching LEFT
 *    
 *    lo  m  hi                     Since m is < target of 5, continue searching RIGHT
 *    
 *           lo
 *           hi                     Since m is >= to target of 5, keep searching LEFT
 *           m
 *           
 *        hi lo                     ENDS the search, and note that 'lo' points to the LEFT EDGE of the range.
 *        
 *  Now do again for right edge
 *  
 *   [1, 3, 5, 5, 5, 5, 9]
 *    lo       m       hi          Since m is <= to target of 5, keep searching RIGHT
 *    
 *                lo m  hi         Since m is <= to target of 5, keep searching RIGHT
 *                
 *                      lo
 *                      hi         Since m is > target of 5, keep searching LEFT
 *                      m
 *                      
 *                   hi lo         ENDS the search, and note that 'hi' points to the RIGHT EDGE of the range.
 *
 */
public class BinarySearchCountDuplicates {
	
	/** 
	 * Find the left-most occurrence of target, if it exists, otherwise index where 
	 * it would have been placed.
	 */
	public static int leftEdge(int target, int[] a) {
		int lo = 0;
		int hi = a.length -1;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (a[mid] >= target) {   // Greater than or equal? Still haven't found left edge
				hi = mid - 1;         // so reduce hi to below mid and keep looking
			} else {
				lo = mid + 1;
			}				
		}
		
		return lo;                    // lo points to edge.
	}
	
	/** 
	 * Find the right-most occurrence of target, if it exists, otherwise index BEFORE
	 * where it would have been placed.
	 */
	public static int rightEdge(int target, int[] a) {
		int lo = 0;
		int hi = a.length -1;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (a[mid] <= target) {    // Lesser than or equal? Still haven't found right edge
				lo = mid + 1;          // so increase lo to above mid and keep looking
			} else {
				hi = mid - 1;
			}				
		}
		
		return hi;                     // this time, remember 'hi' to be the edge.
	}
	
	public static void main(String[] args) {
		int[] example = new int[] { 1, 3, 5, 5, 5, 5, 9};
		
		int left = leftEdge(5, example);
		int right = rightEdge(5, example);
		int count = right - left + 1;
		System.out.println("5 occurs " + count + " times.");
		
		left = leftEdge(4, example);
		right = rightEdge(4, example);
		
		count = right - left + 1;
		System.out.println(left + "," + right);
		System.out.println("4 occurs " + count + " times.");
		
	}
}
