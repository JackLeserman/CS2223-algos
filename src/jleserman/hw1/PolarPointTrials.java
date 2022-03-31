package jleserman.hw1;

import algs.hw1.fixed.OrderedArray;
import algs.hw1.fixed.PolarPoint;
import algs.hw1.fixed.PolarTrials;

/**
 * Modify this class to complete the following operations:
 * 
 *  1. Determine if a PolarPoint belongs to an array
 */
public class PolarPointTrials extends PolarTrials {

	@Override
	protected boolean existsThetaOrdered(OrderedArray<PolarPoint> polars, PolarPoint p) {
		for (int i = 0; i < polars.length(); i++) {
			if (polars.get(i) == p) {
				return true;
			}
		}
		return false;
	}

	@Override
	protected int countBetweenThetaOrdered(OrderedArray<PolarPoint> polars, int min, int max) {
		int counter  = 0;
		for (int i = 0; i < polars.length(); i++) {
			double thetaP = polars.get(i).theta;
			if(thetaP > min && thetaP < max){
				counter = counter + 1;
			}
		}
		return counter;
	}
	
	/** Do not change this function. Just execute it. */
	public static void main(String[] args) {
		new PolarPointTrials().runTrial();    // FIXED DEFECT IN INITIAL RELEASE
	}
}
