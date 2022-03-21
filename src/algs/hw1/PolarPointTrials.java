package algs.hw1;

import algs.hw1.fixed.OrderedArray;
import algs.hw1.fixed.PolarPoint;
import algs.hw1.fixed.PolarTrials;

/**
 * Modify this class to complete the following operations:
 * 
 *  1. Determine if a PolarPoint belongs to an array
 */
public class PolarPointTrials extends PolarTrials {

	/** 
	 * Return true if p belongs to the OrderedArray. You can find the length of the ordered array
	 * and you can get any individual element by its index location (i.e., the 5th one, or the 19th one).
	 * 
	 * You know that the OrderedArray is already sorted by Sorting.comparePolarByTheta, which means you can
	 * take advantage of this ordering.
	 */
	@Override
	protected boolean existsThetaOrdered(OrderedArray<PolarPoint> polars, PolarPoint p) {
		return false;  // COMPLETE
	}
	
	/**
	 * Return the number of PolarPoints between angles [min, max], inclusive on both sides.
	 * 
	 * You know that the OrderedArray is already sorted by Sorting.comparePolarByTheta, which means you can
	 * take advantage of this ordering.
	 */
	@Override
	protected int countBetweenThetaOrdered(OrderedArray<PolarPoint> polars, int min, int max) {
		return -1;     // COMPLETE
	}
	
	/** Do not change this function. Just execute it. */
	public static void main(String[] args) {
		new PolarPointTrials().runTrial();    // FIXED DEFECT IN INITIAL RELEASE
	}
}
