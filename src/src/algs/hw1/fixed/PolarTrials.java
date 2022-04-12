package algs.hw1.fixed;

/**
 * This class initiates a number of random trials so you can observe difference performance based
 * upon the structure of the data.
 * 
 */
public class PolarTrials {
	
	/** 
	 * Naive search through entire array using brute force approach. Your subclass will override with more efficient implementation
	 * knowing that the points are sorted according to {@link Sorting#comparePolarByTheta}. 
	 */
	protected boolean existsThetaOrdered (OrderedArray<PolarPoint> points, PolarPoint p) {
		for (int i = 0; i < points.length(); i++) {
			PolarPoint pp = points.get(i);
			if (pp.equals(p)) { return true; }
		}
		
		return false;
	}
	
	/** 
	 * Naive search through entire array using brute force approach. Your subclass will override with more efficient implementation
	 * knowing that the points are sorted according to {@link Sorting#comparePolarByTheta}. 
	 */
	protected int countBetweenThetaOrdered (OrderedArray<PolarPoint> points, int min, int max) {
		int count = 0;
		for (int i = 0; i < points.length(); i++) {
			PolarPoint pp = points.get(i);
			if (pp.theta >= min && pp.theta <= max) { count++; }
		}
		
		return count;
	}
	
	/** Execute a series of trials. */
	public void runTrial() {
		RandomPointGenerator rp = new RandomPointGenerator(1);
		System.out.println("N\t#Found\tExists-I\t#Betw.\tBetween-I");
		for (int n = 256; n <= 65536; n *= 2) {
			PolarPoint[] points = rp.randomPolars(n-1, 500);
			
			// conduct 65536 searches for a random point
			OrderedArray<PolarPoint> oa1 = new OrderedArray<PolarPoint>(points, Sorting.comparePolarByTheta);
			int existFound = 0;
			for (int i = 0; i < 65536; i++) {
				PolarPoint p = rp.randomPolar(500);
				if (existsThetaOrdered(oa1, p)) { existFound++; }
			}
			long existInspect = oa1.getNumInspections();
			
			// conduct 60 searches (of 6-degrees each) 
			OrderedArray<PolarPoint> oa2 = new OrderedArray<PolarPoint>(points, Sorting.comparePolarByTheta);
			int betweenFound = 0;
			for (int degree = 0; degree <= 354; degree += 6) {
				betweenFound += countBetweenThetaOrdered (oa2, degree, degree + 5);
			}
			long betweenInspect = oa2.getNumInspections();
			
			System.out.println((n-1) + "\t" + existFound + "\t" + existInspect + "\t" + betweenFound + "\t" + betweenInspect);
		}
	}

	
	/** Do not change this function. Just execute it. */
	public static void main(String[] args) {
		System.out.println("Naive Brute-force Implementation results.");
		new PolarTrials().runTrial();
		System.out.println();
		System.out.println("Your challenge is to develop code that outperforms these naive implementations.");
	}
	
}
