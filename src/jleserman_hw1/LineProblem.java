package jleserman_hw1;

import algs.days.day03.FixedCapacityStack;
import algs.hw1.fixed.RandomPointGenerator;
import algs.hw1.fixed.Solution;
import edu.princeton.cs.algs4.StopwatchCPU;

import java.awt.*;
import java.sql.SQLSyntaxErrorException;

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
		Point pI = points[i];
		Point pJ = points[j];
		Point pK = points[k];
		double ijSlope;
		double ikSlope;

		if((pJ.x - pI.x) == 0){  //edge case for divide by zero
			ijSlope = 0;
		}else{
			ijSlope = ((pJ.y - pI.y)/(pJ.x - pI.x));
		}

		if((pK.x - pI.x) == 0){ //edge case for divide by zero
			ikSlope = 0;
		}else{
			ikSlope = ((pK.y - pI.y)/(pK.x - pI.x));
		}
		if(ijSlope == ikSlope){
			return true;
		}else {
			return false;
		}
	}


	public Solution compute(Point[] points) {
		int lenPoints = points.length;
		int numPoints;
		int numPointsMAX;
		int maxI;
		int maxJ;
		numPointsMAX = 0;
		maxI = -1;
		maxJ=-1;
		for(int i = 0; i < lenPoints; i++){
			for(int j = i+1; j < lenPoints; j++){
				numPoints = 0;
				for(int k = j+1; k < lenPoints; k++) {
					if (onSameLine(points, i, j, k)) {
						numPoints = numPoints + 1;
					}
				}
				if(numPoints > numPointsMAX){
					numPointsMAX = numPoints;
					maxI = i;
					maxJ = j;
				}
			}
		}
		Solution sol = new Solution(points[maxI],points[maxJ],numPointsMAX);
		return sol;
	}

	/**
	 * Solves a slightly different problem: Count the number of lines that pass through exactly two points.
	 * 
	 * This method returns the number of such lines that it finds.
	 */
	public int findAllJustTwo(Point[] points) {
		int lenPoints = points.length;
		int counter;
		counter = 0;
		for(int i = 0; i < lenPoints; i++){
			for(int j = i+1; j < lenPoints; j++){
				for(int k = j+1; k < lenPoints; k++){
					if(onSameLine(points, i, j, k)){
						k = lenPoints;
					}
				}
				counter = counter + 1;
			}
		}
		return counter;
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
