package jleserman_hw1;

import algs.hw1.fixed.CartesianTrials;
import algs.hw1.fixed.OrderedArray;
import algs.hw1.fixed.Sorting;

import java.awt.*;

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
	 *
	 * 255	68	56	65	66	255	1020
	 */

	//GOAL start = 67
	//end = 67 + 56
	//56 total entries in Q2
	@Override
	protected int countCartesiansInQuadrant(OrderedArray<Point> cartesians, int q) {
		int len = cartesians.length();
//============First Quadrant=============
		if (q == 1) {
			Point leftmost = new Point(0, Integer.MAX_VALUE);
			Point rightmost = new Point(Integer.MIN_VALUE, 0);
			int low = 0;
			int high = len - 1;

            while (low <= high) {
                int mid = (low + high) / 2;
                int sorted = Sorting.compareCartesianByQuadrant.compare(cartesians.get(mid), leftmost);
                if (sorted >= 0) {
                    high = mid - 1;
                } else {
                    low = mid + 1;
                }
            }
            int points = high + 1;
			return points;
		}
//============Second Quadrant=============
		else if (q == 2) {
			Point leftmost = new Point(0, Integer.MAX_VALUE);
			Point rightmost = new Point(Integer.MIN_VALUE, 0);
			int low = 0;
			int high = len - 1;
			//------left edge----------
			while (low <= high) {
				int mid = (low + high) / 2;
				int sorted = Sorting.compareCartesianByQuadrant.compare(cartesians.get(mid), leftmost);
				if (sorted >= 0) {
					high = mid - 1;
				} else {
					low = mid + 1;
				}
			}
			int leftEdge = high + 1;
			//------right edge----------
			low = 0;
			high = len - 1;
			while (low <= high) {
				int mid = (low + high) / 2;
				int sorted = Sorting.compareCartesianByQuadrant.compare(cartesians.get(mid), rightmost);
				if (sorted <= 0) {
					low = mid + 1;
				} else {
					high = mid - 1;
				}
			}

			int rightEdge = high;
			int points2 = rightEdge - leftEdge + 1;
			return points2;
		}
	//============Third Quadrant=============
		else if (q == 3) {
			Point leftmost = new Point(0,Integer.MIN_VALUE);
			Point rightmost = new Point(Integer.MIN_VALUE, 0);
			int low = 0;
			int high = len - 1;
			//------left edge----------
			while (low <= high) {
				int mid = (low + high) / 2;
				int sorted = Sorting.compareCartesianByQuadrant.compare(cartesians.get(mid), leftmost);
				if (sorted <= 0) {
					low = mid + 1;
				} else {
					high = mid - 1;
				}
			}
			int leftEdge = high + 1;
			//------right edge----------
			low = 0;
			high = len - 1;
			while (low <= high) {
				int mid = (low + high) / 2;
				int sorted = Sorting.compareCartesianByQuadrant.compare(cartesians.get(mid), rightmost);
				if (sorted <= 0) {
					low = mid + 1;
				} else {
					high = mid - 1;
				}
			}
			int rightEdge = high + 1;

			int points2 = leftEdge - rightEdge;
			return points2;
		}
	//============Fourth Quadrant=============
		else if (q == 4) {
			Point leftmost = new Point(Integer.MIN_VALUE, 0);
			Point rightmost = new Point(0,Integer.MIN_VALUE);
			int low = 0;
			int high = len - 1;
			//------left edge----------
			while (low <= high) {
				int mid = (low + high) / 2;
				int sorted = Sorting.compareCartesianByQuadrant.compare(cartesians.get(mid), rightmost);
				if (sorted <= 0) {
					low = mid + 1;
				} else {
					high = mid - 1;
				}
			}
			int leftEdge = high + 1;
			//------right edge----------
			low = 0;
			high = len - 1;
			while (low >= high) {
				int mid = (low + high) / 2;
				int sorted = Sorting.compareCartesianByQuadrant.compare(cartesians.get(mid), leftmost);
				if (sorted >= 0) {
					high = mid + 1;
				} else {
					low = mid - 1;
				}
			}

			int rightEdge = high;
			int points2 = rightEdge - leftEdge + 1;
			return points2;
		}
		return 9999999;
	}
	public static void main(String[] args) {
		new QuadrantCounting().runTrial();
	}
}
