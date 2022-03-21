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
	 */
	@Override
	protected int countCartesiansInQuadrant(OrderedArray<Point> cartesians, int q) {

		return 0;
	}

	/** Leave this function unchanged. */
	public static void main(String[] args) {
		new QuadrantCounting().runTrial();
	}
}
