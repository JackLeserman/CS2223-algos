package algs.hw1.fixed;

import java.awt.Point;

/**
 * For this question, you are asked to write efficient count method that computes the number of points in each quadrant.
 * 
 *              ^
 *       II     |     I
 *              | 
 * <-------------------------->
 *              |
 *       III    |     IV 
 *              V
 *               
 * 
 */
public class CartesianTrials {

	/** 
	 * Naively search through entire structure to see which points belong to the desired quadrant. Your subclass will override with more
	 * efficient implementation knowing that the points are sorted according to {@link Sorting#compareCartesianByQuadrant}.
	 *
	 */ 
	protected int countCartesiansInQuadrant(OrderedArray<Point> points, int q) {
		int count = 0;
		for (int i =  0; i < points.length(); i++) {
			Point p = points.get(i);

			if (q == 1) {
				if (p.x > 0 && p.y > 0) { count++; }
			} else if (q == 2) {
				if (p.x < 0 && p.y > 0) { count++; }	
			} else if (q == 3) {
				if (p.x < 0 && p.y < 0) { count++; }	
			} else {
				if (p.x > 0 && p.y < 0) { count++; }	
			}
		}
		
		return count;
	}
	
	/** Leave this alone. */
	protected void runTrial() {

		System.out.println();
		System.out.println("N\tQ1\tQ2\tQ3\tQ4\tQAll\t#Inspections");
		for (int n = 256; n <= 65536; n *= 2) {
			RandomPointGenerator trial = new RandomPointGenerator(n);  // This is the seed, so it can be repeatable.
			
			Point[] points = trial.randomCartesians(n-1, 1000);
			
			// ordered by special quadrant-sorting comparator.
			OrderedArray<Point> cartesians = new OrderedArray<Point>(points, Sorting.compareCartesianByQuadrant);
			
			int c1 = countCartesiansInQuadrant(cartesians, 1);
			int c2 = countCartesiansInQuadrant(cartesians, 2);
			int c3 = countCartesiansInQuadrant(cartesians, 3);
			int c4 = countCartesiansInQuadrant(cartesians, 4);
			
			System.out.println(String.format("%d\t%d\t%d\t%d\t%d\t%d\t%d", points.length, c1, c2, c3, c4, (c1+c2+c3+c4), cartesians.getNumInspections()));
		}
	}
	
	/** Leave this function unchanged. */
	public static void main(String[] args) {
		System.out.println("Naive Brute-force implementation results.");
		new CartesianTrials().runTrial();
		System.out.println();
		System.out.println("Your challenge is to develop code that outperforms these naive implementations.");
	}
}
