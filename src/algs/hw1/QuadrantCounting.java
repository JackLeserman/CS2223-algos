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
		int len = cartesians.length();

		if (q==2){
			Point leftmost = new Point(0, Integer.MAX_VALUE);
			Point rightmost = new Point(Integer.MIN_VALUE, 0);

			int low = 0;
			int high = len - 1;

			while (low <= high){
				int mid = (low + high) / 2;

				int sorted = Sorting.compareCartesianByQuadrant.compare(cartesians.get(mid), leftmost);
				System.out.println(sorted);
				if (sorted >=0){
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			}

		}


		return 0;
	}

	/** Leave this function unchanged. */
	public static void main(String[] args) {
		new QuadrantCounting().runTrial();
	}
}
