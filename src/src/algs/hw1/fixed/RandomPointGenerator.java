package algs.hw1.fixed;

import java.awt.Point;
import java.util.Random;

/**
 * Generator for random polar and Cartesian points, none of whom appear on the traditional X- and Y-axis.
 * 
 */
public class RandomPointGenerator {
	Random rnd;
	
	/** Use random generator without seed. */
	public RandomPointGenerator() {
		rnd = new Random();
	}
	
	/** Select random number generator seed to perform repeatable actions. */
	public RandomPointGenerator(long seed) {
		rnd = new Random(seed);		
	}
	
	/** Random Polar point (within max_radius) that (if converted to Cartesian) would not appear on X- or Y-axis. */
	public PolarPoint randomPolar(int max_radius) {
		int r = 0;
		int t = 0;
		while (r == 0 || t == 0 || t == 90 || t == 180 || t == 270) {
			r = Math.abs(rnd.nextInt() % max_radius);
			t = Math.abs(rnd.nextInt() % 360);
		}
		return new PolarPoint(r, t);
	}
	
	/** 
	 * Random Cartesian point (with +/- max_d for both X and Y) that would not appear on X- or Y-axis.
	 * 
	 * These points all have integer coordinates. Points range (x and y) from -max_d/2 up to max_d/2-1.
	 */
	public Point randomCartesian(int max_d) {
		
		int x = 0;
		int y = 0;
		while (x == 0 || y == 0) {
			x = Math.abs(rnd.nextInt() % max_d) - max_d/2;
			y = Math.abs(rnd.nextInt() % max_d) - max_d/2;
		}
		return new Point(x, y);
	}
	
	/** 
	 * Up to n unique random Cartesian points. 
	 * 
	 * If max_d is too small, relative to n, then might return fewer points than asked, since might 
	 * not be able to find n unique points...
	 */
	public Point[] randomCartesians(int n, int max_d) {
		Point[] target = new Point[n];
		for (int idx = 0; idx < n; idx++) {
			int trial = n; // fixed number of tries
			boolean duplicate = false;
			while (trial > 0) {
				target[idx] = randomCartesian(max_d);
				for (int j = 0; j < idx; j++) {
					if (target[j].equals(target[idx])) { duplicate = true; break; }
				}
				if (!duplicate) { break; }
				
				trial -= 1;         // try again
				duplicate = false;
			}
			
			if (duplicate) {
				// tried, but couldn't do it. Return what we have...
				Point[] partial = new Point[idx];
				System.arraycopy(target,  0, partial, 0, idx);
				return partial;
			}
		}
		
		return target;
	}
	
	/** 
	 * Up to n unique random Polar points. 
	 * 
	 * If max_d is too small, relative to n, then might return fewer points than asked, since might 
	 * not be able to find n unique points...
	 */
	public PolarPoint[] randomPolars(int n, int max_radius) {
		PolarPoint[] target = new PolarPoint[n];
		for (int idx = 0; idx < n; idx++) {
			int trial = n; // fixed number of tries
			boolean duplicate = false;
			while (trial > 0) {
				target[idx] = randomPolar(max_radius);
				for (int j = 0; j < idx; j++) {
					if (target[j].equals(target[idx])) { duplicate = true; break; }
				}
				if (!duplicate) { break; }
				
				trial -= 1;         // try again
				duplicate = false;
			}
			
			if (duplicate) {
				// tried, but couldn't do it. Return what we have...
				PolarPoint[] partial = new PolarPoint[idx];
				System.arraycopy(target,  0, partial, 0, idx);
				return partial;
			}
		}
		
		return target;
	}
}
