package algs.hw1;

import java.awt.Point;

import algs.hw1.fixed.CartesianTrials;
import algs.hw1.fixed.OrderedArray;
import algs.hw1.fixed.Sorting;

/**
 * For this question, you are asked to write efficient count that computes the number of Cartesian points in each quadrant.
 * 
 *              ^
 *       II     |     I
 *              | 
 * <-------------------------->
 *              |
 *       III    |     IV 
 *              V
 *
 *
 * You can take advantage of the fact that the points are already sorted by {@link Sorting#compareCartesianByQuadrant}. 
 */
public class QuadrantCounting extends CartesianTrials {
	
	/**
	 * Return the count of Cartesian points in the given quadrant, assuming that the points have already been sorted 
	 * in ascending order by {@link Sorting#compareCartesianByQuadrant}. Take advantage of this ordering to write a more
	 * efficient implementation than the naive, brute-force implementation
	 */
	@Override
	protected int countCartesiansInQuadrant(OrderedArray<Point> cartesians, int q) {
		// TODO Auto-generated method stub
		if (q == 1) {
			int leftEdge = 0;
		}
		
		if (q == 4) {
			int rightEdge = cartesians.length()-1;
		}
		
		if (q == 2) {
			// find left edge BETWEEN Q1 and Q2
			Point leftEdgePoint = new Point(0, Integer.MAX_VALUE);
			int leftEdge;
			int lo = 0;
			int hi = cartesians.length() -1;
			while (lo <= hi) {
				int mid = (lo + hi) / 2;
				Point midPoint = cartesians.get(mid);
				
				// int rc = Integer.compare(A[mid], target);
				int rc = Sorting.compareCartesianByQuadrant.compare(midPoint, leftEdgePoint);
				if (rc >= 0) {            // Greater than or equal? Still haven't found left edge
					hi = mid - 1;         // so reduce hi to below mid and keep looking
				} else {
					lo = mid + 1;
				}				
			}
			leftEdge = lo;
			
			Point rightEdgePoint = new Point(Integer.MIN_VALUE, 0);   
			
			// find right edge edge BETWEEN Q2 and Q3
			int rightEdge;
			lo = 0;
			hi = cartesians.length() -1;
			while (lo <= hi) {
				int mid = (lo + hi) / 2;
				Point midPoint = cartesians.get(mid);
				
				int rc = Sorting.compareCartesianByQuadrant.compare(midPoint, rightEdgePoint);
				if (rc <= 0) {             // Lesser than or equal? Still haven't found right edge
					lo = mid + 1;          // so increase lo to above mid and keep looking
				} else {
					hi = mid - 1;
				}				
			}
			
			rightEdge = hi;
			
			return rightEdge - leftEdge + 1;
		}
		
		return 0;
	}

	/** Leave this function unchanged. */
	public static void main(String[] args) {
		new QuadrantCounting().runTrial();
	}
}
