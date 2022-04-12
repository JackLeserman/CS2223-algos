package algs.hw1.fixed;

import java.awt.Point;

/**
 * A solution to the Sylvester Line Problem.
 * 
 * Two points define a line, and number is the number of other points (number >= 2) also on line.
 * 
 * The reason a number is included here is to determine when ALL points are collinear, a special
 * case that must be identified.
 * 
 * https://en.wikipedia.org/wiki/Sylvester%E2%80%93Gallai_theorem
 */
public class Solution {

	public final Point p1;
	public final Point p2;
	public final int   number;
	
	public Solution(Point p1, Point p2, int number) {
		this.p1 = p1;
		this.p2 = p2;
		this.number = number;
	}
	
}
