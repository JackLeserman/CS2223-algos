package algs.hw1;

import java.awt.Point;

import algs.hw1.fixed.RandomPointGenerator;
import algs.hw1.fixed.Solution;
import edu.princeton.cs.algs4.StopwatchCPU;

/**
 * Solves the Sylvester Line Problem: https://en.wikipedia.org/wiki/Sylvester%E2%80%93Gallai_theorem
 * 
 * In every finite set of points in the Euclidean plane, there is a line that either (a) passes through 
 * exactly two of the points; or (b) it passes through all of them.
 * 
 * Complete this class. Find inspiration in ThreeSum (p. 173 of book, in Section 1.4). You will find
 * the GCD computation useful.
 */
public class LineProblem {
	/** Computes greatest common divisor of two non-negative integers (p. 4, Chapter 1). */
	public static int gcd (int p, int q) {
		if (q == 0) { return p; }
		int r = p % q;
		return gcd(q, r);
	}

	/** 
	 * Helper method to determines whether points i,j,k are all on the same line.
	 *
	 * This method will be useful for both problems.
	 */
	public boolean onSameLine(Point[] points, int i, int j, int k) {
		return false; // COMPLETE ME
	}

	/**
	 * Solves the Sylvester Line Problem
	 * 
	 * This method returns a Solution object it has found, where the number is either 2 (a valid solution)
	 * or points.length to reflect all collinear.
	 */
	public Solution compute(Point[] points) {
		// Complete this solution....
		return null;
	}

	/**
	 * Solves a slightly different problem: Count the number of lines that pass through exactly two points.
	 * 
	 * This method returns the number of such lines that it finds.
	 */
	public static int findAllJustTwo(Point[] points) {
		return -1;
	}

	/** Execute trials. DO NOT MODIFY. */
	public void trial() {
		System.out.println("This might take up to a minute to complete. Be patient!");
		RandomPointGenerator rp = new RandomPointGenerator();
		System.out.println("N\tTime\tTimMax\tFound");
		for (int n = 32; n <= 2048; n *= 2) {
			Point[] sample = rp.randomCartesians(n, 256);
			StopwatchCPU watch = new StopwatchCPU();
			Solution sol = compute(sample);
			double timing = watch.elapsedTime();

			watch = new StopwatchCPU();
			int max = findAllJustTwo(sample);
			double maxTiming = watch.elapsedTime();
			System.out.println(String.format("%d\t%.3f\t%.3f\t%d",  n, timing, maxTiming, max));
		}
	}
	
	/**
	 * Execute all trials. YOU DO NOT NEED TO MODIFY THIS METHOD.
	 */
	public static void main(String[] args) {
		new LineProblem().trial();
	}
}
