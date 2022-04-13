package algs.hw3.bonus;

/**
 * It's Back!
 * 
 * A SimpleRange is is one to which you can only ADD values, and each value that is 
 * added is GREATER THAN or equal to any other value previously added.
 * 
 * Can you modify a BST that contains integer values to add a method ranges() that returns
 * A SimpleRangeList to represent the range of values in the BST in compressed form?
 * 
 * HINT: if you ensure that you only call add() with values that are greater than or equal
 * to the largest value in the SimpleRangeList, the implementation is quite simple.
 * 
 * BONUS QUESTION.
 */
public class SimpleRangeList {

	/** Linked list node to use. */
	class Node {
		int low;
		int high;
		Node next;

		Node (int lo, int hi) {
			this.low = lo;
			this.high = hi;
		}
	}

	Node head = null;
	Node last = null;
	
	/**
	 * Add the value, but ALSO make sure that this function is only called with values that are 
	 * already larger than or equal to all existing values in the SimpleRangeList.
	 * 
	 * Doing so ensures this is a simple implementation...
	 * 
	 */
	public boolean add(int value) {
		throw new RuntimeException ("COMPLETE IMPLEMENTATION.");
	}

	/** Produce string representation of ranges, in order from least to greatest. */
	public String toString() {
		return "COMPLETE THIS IMPLEMENTATION...";
	}
}
