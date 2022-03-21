package algs.hw1;

import algs.hw1.fixed.OrderedArray;
import algs.hw1.fixed.PolarPoint;
import algs.hw1.fixed.Sorting;

/**
 * This might help you debug your code.
 * 
 * COPY this file into your USERID.hw1 package and when you run it on a proper 
 * implementation, the result is:
 * 
 * should be true:true
 * should be 3:3
 *
 */
public class DebugPolarPointTrials extends PolarPointTrials {
	
	public void runTrial() {
		PolarPoint[] points = new PolarPoint[] {
				new PolarPoint(4, 45),     // sits in quadrant 1
				new PolarPoint(5, 120),    // in quadrant 2
				new PolarPoint(6, 225),    // in quadrant 3
				new PolarPoint(7, 315)     // in quadrant 4
		};
			
		OrderedArray<PolarPoint> polars = new OrderedArray<PolarPoint>(points, Sorting.comparePolarByTheta);

		System.out.println("should be true:" + existsThetaOrdered(polars, points[0]));
		
		System.out.println("should be 3:" + countBetweenThetaOrdered(polars, 80,  320));
	}
	
	
	/** Leave this function unchanged. */
	public static void main(String[] args) {
		new DebugPolarPointTrials().runTrial();
	}
}
