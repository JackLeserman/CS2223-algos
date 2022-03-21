package algs.hw1;

import java.awt.Point;

import algs.hw1.fixed.OrderedArray;
import algs.hw1.fixed.Sorting;

/**
 * This might help you debug your code.
 * 
 * COPY this file into your USERID.hw1 package and when you run it on a proper 
 * implementation, the result is:
 * 
 * N	Q1	Q2	Q3	Q4	QAll	#Inspections
 * 4	1	1	1	1	4		14
 *
 */
public class DebugQuadrantCounting extends QuadrantCounting {

	public void runTrial() {
		Point[] points = new Point[] {
				new Point(10, 10),       // this is in quadrant 1
				new Point(-10, 10),      // this is in quadrant 2
				new Point(-10, -10),     // this is in quadrant 3
				new Point(10, -1),       // this is in quadrant 4				
		};
				
		// ordered by special quadrant-sorting comparator.
		OrderedArray<Point> cartesians = new OrderedArray<Point>(points, Sorting.compareCartesianByQuadrant);
				
		int c1 = countCartesiansInQuadrant(cartesians, 1);
		int c2 = countCartesiansInQuadrant(cartesians, 2);
		int c3 = countCartesiansInQuadrant(cartesians, 3);
		int c4 = countCartesiansInQuadrant(cartesians, 4);
		
		System.out.println("N\tQ1\tQ2\tQ3\tQ4\tQAll\t#Inspections");
		System.out.println(String.format("%d\t%d\t%d\t%d\t%d\t%d\t%d", points.length, c1, c2, c3, c4, (c1+c2+c3+c4), cartesians.getNumInspections()));
	}
	
	/** Leave this function unchanged. */
	public static void main(String[] args) {
		new DebugQuadrantCounting().runTrial();
	}
}
