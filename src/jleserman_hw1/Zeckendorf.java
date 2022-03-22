package jleserman_hw1;

import algs.days.day03.FixedCapacityStack;
import edu.princeton.cs.algs4.In;

import java.util.Scanner;  // Import the Scanner class
import java.awt.*;

/**
 * Use a Stack to compute the Zeckendorf representation for a number. 
 * 
 * The Zeckendorf representation for 1,000,000 is "10001010000000000010100000000"
 *                                                 |   | |           | |
 *                                                 |   | |           | 55
 *                                                 |   | |           |
 *                                                 |   | |           144
 *                                                 |   | 46368
 *                                                 |   |
 *                                                 |   121393
 *                                                 |
 *                                                 832040
 *
 * 1,000,000 = 832,040 + 121393 + 46368 + 144 + 55                                                            
 *
 * left-most digit is the most significant and the right-most is the least significant.
 */
public class Zeckendorf {
	public static FixedCapacityStack<Long> makeFibonacciStack(long n) {
		FixedCapacityStack<Long> stack = new FixedCapacityStack<Long>(100);
		int prev1 = 0;
		int prev2 = 1;
		int sum = 1;
		int i = 0;
		while (sum <= n) {
			stack.push((long) sum);
			prev1 = prev2;
			prev2 = sum;
			sum = prev1 + prev2;
		}
		return stack;
	}

	/**
	 * Compute the Zeckendorf representation for n, that is, the unique sequence of sums of Fibonacci numbers, where none are duplicated.
	 * <p>
	 * The first 7 Fibonacci numbers that make up the representation are: 1, 2, 3, 5, 8, 13, 21
	 * <p>
	 * The Zeckendorf representation for the numbers from 1 to 10 are:
	 * <p>
	 * 1  = 1     = 1
	 * 2  = 10    = 2
	 * 3  = 100   = 3
	 * 4  = 101   = 3 + 1
	 * 5  = 1000  = 5
	 * 6  = 1001  = 5 + 1
	 * 7  = 1010  = 5 + 2
	 * 8  = 10000 = 8
	 * 9  = 10001 = 8 + 1
	 * 10 = 10010 = 8 + 2
	 * ...
	 * <p>
	 * This function should use makeFibonacciStack() to construct a stack containing (in reverse order) the Fibonacci numbers from F2 to Fn
	 * where Fn is the largest Fibonacci number smaller than or equal to n.
	 */
	public static String zeckendorfRepresentation(long n) {
		FixedCapacityStack<Long> fibStack;
		String rep = "";
		long fibNum;
		fibStack = makeFibonacciStack(n);
		while (n > 0) {
			fibNum = fibStack.pop();
			if(n >= fibNum){
				n = n - fibNum;
				rep = rep + "1";
			}else{
				rep = rep + "0";
			}
		}
		while(fibStack.isEmpty() == false){
			fibStack.pop();
			rep = rep + "0";
		}
		return rep;
	}

	/** No need to modify below this line. */
	public static void main(String[] args) {
		System.out.println("N\tZeck. Repr.");
		for (int i = 1; i <= 20; i++) {
			System.out.println(i + "\t" + zeckendorfRepresentation((i)));
		}
	}
}
