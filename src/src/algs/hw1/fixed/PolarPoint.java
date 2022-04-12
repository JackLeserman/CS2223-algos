package algs.hw1.fixed;

import java.awt.Point;

/**
 * Represents a polar coordinate, (r, theta) using integer values for R and Theta (between 0 and 359).
 */
public class PolarPoint {
	public static final double radianMult = Math.PI/180;   /** Constant to convert degrees to radians. */
	
	public final int r;
	public final int theta;
	
	public PolarPoint (int r, int t) {
		this.r = r;
		this.theta = t;
	}

	/** Standard equals method to ensure we compare by structure of class. */
	public boolean equals (Object o) {
		if (o == null) { return false; }
		if (o instanceof PolarPoint) {
			PolarPoint pp = (PolarPoint) o;
			return pp.r == r && pp.theta == theta;
		}
		
		return false;  // incomparable.
	}
	
	/** Convert PolarPoint into approximate Cartesian point. */
	public Point asCartesianPoint() {
		int x = (int)(r * Math.cos(theta*radianMult));
		int y = (int)(r * Math.sin(theta*radianMult));
		return new Point(x, y);
	}
	
	/** Convert from Cartesian Point into Approximate Polar Point (again, using integer coords). */
	public static PolarPoint cartesianToPolarPoint(Point cartesian) {
		int r = (int)(Math.sqrt(cartesian.x*cartesian.x + cartesian.y*cartesian.y));
		double numerator = cartesian.y;
		double denominator = cartesian.x;
		if (denominator == 0) {
			if (numerator >= 0) { return new PolarPoint(r, 90); }
			return new PolarPoint(r, 270); 
		}
		return new PolarPoint(r, (int)(Math.atan(numerator/denominator)/radianMult));
	}
}
