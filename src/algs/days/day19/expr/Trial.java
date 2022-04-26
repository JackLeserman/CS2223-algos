package algs.days.day19.expr;

public class Trial {
	
	// This was generated from:
	// 3 1 + 4 / 1 5 + 9 * 2 6 * - *
	public static void main(String[] args) {
		double x0 = 3.0;
		double x1 = 1.0;
		double x2 = x0 + x1;
		double x3 = 4.0;
		double x4 = x2 / x3;
		double x5 = 1.0;
		double x6 = 5.0;
		double x7 = x5 + x6;
		double x8 = 9.0;
		double x9 = x7 * x8;
		double x10 = 2.0;
		double x11 = 6.0;
		double x12 = x10 * x11;
		double x13 = x9 - x12;
		double x14 = x4 * x13;
		
		System.out.println("value is " + x14);

	}
}
