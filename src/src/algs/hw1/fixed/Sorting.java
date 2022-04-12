package algs.hw1.fixed;

import java.awt.Point;
import java.util.Comparator;

public class Sorting {
	
	/**
	 * A PolarPoint is defined by (R, Theta), where R is the distance from the (0, 0) origin, and 
	 * Theta is the angle (a double between 0 and 2*PI) representing the angle from the horizontal axis.
	 * 
	 * https://en.wikipedia.org/wiki/Polar_coordinate_system
	 * 
	 * This comparator sorts two polar points by Theta first, and then by R, the distance from origin.
	 * 
     * 
     *              ^
     *       4      |     2
	 *              | 3    
	 *        5     |    1 
	 * <-------------------------->
	 *   6          |   9         
	 *        7     |      
	 *              |    8
	 *              V
	 */
	public static Comparator<PolarPoint> comparePolarByTheta = new Comparator<PolarPoint>() {
		@Override
		public int compare(PolarPoint o1, PolarPoint o2) {
			if (o1.theta < o2.theta) { return -1; }
			if (o1.theta > o2.theta) { return +1; }
			double d = o1.r - o2.r;
			if (d < 0) { return -1; } else if (d > 0) { return +1; } else { return 0; }
		}		
	};
	
	/** 
	 * The Cartesian plane is commonly divided into FOUR quadrants:
	 * 
	 *              ^
	 *       II     |     I
	 *              | 
	 * <-------------------------->
	 *              |
	 *       III    |     IV 
	 *              V
	 *               
     * This comparator sorts points from RIGHT to LEFT, starting from Quadrant I into Quadrant II,
     * then from LEFT to RIGHT, from Quadrant III into Quadrant IV. The following
     * nine points are sorted in this way.
     * 
     *              ^
     *       5      |     1
	 *              | 3    
	 *        4     |    2 
	 * <-------------------------->
	 *   6          |   8         
	 *        7     |      
	 *              |    9
	 *              V
	 */
	public static Comparator<Point> compareCartesianByQuadrant = new Comparator<Point>() {
		@Override
		public int compare(Point c1, Point c2) {
			if (c1.y >= 0) {                           // c1 is in QI or QII
				if (c2.y < 0) { return -1; }           // c2 is in QIII or QIV, so before
				if (c1.x < c2.x) { return +1; }        // Order by X appropriately (right to left)
				if (c1.x > c2.x) { return -1; }
				return 0;                              // must be same
			}
			
			// c1 is in QIII or QIV (< 0)
			if (c2.y >= 0) { return +1; }              // c2 (QI or QI) comes before QIII or QIV
			if (c1.x < c2.x) { return -1; }            // Order by X appropriately (left to right)
			if (c1.x > c2.x) { return 1; }
			return 0;	                               // must be same
		}		
	};
}
