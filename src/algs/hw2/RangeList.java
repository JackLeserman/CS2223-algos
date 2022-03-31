package algs.hw2;

/**
 * A RangeList contains a compact representation of integer sequences.
 * 
 * For example, given the integer values of { 1, 2, 3, 5, 7, 9, 10, 11, 12} a compact representation would be
 * 
 * { 1-3,5,7,9-12 }
 * 
 * Your assignment is to implement the following API for this data type.
 */
public class RangeList {

	/** Linked list node to use. */
	class Node {
		int low;
		int high;
		Node next;

		Node (int lo, int hi) {
			this.low = lo;
			this.high = hi;
		}

		Node (int lo, int hi, Node next) {
			this.low = lo;
			this.high = hi;
			this.next = next;
		}
	}

	Node head = null;

	public RangeList() { }
	
	/** 
	 * Return the number of distinct ranges in the RangeList.
	 * Performance must be O(N) 
	 */
	public int numberRanges() {
		throw new RuntimeException ("FINISH THIS IMPLEMENTATION");
	}
	
	/** 
	 * Return the number of distinct values in the RangeList.
	 * Performance must be O(1) 
	 */
	public int numberValues() {
		throw new RuntimeException ("FINISH THIS IMPLEMENTATION");
	}
	
	/**
	 * Determines whether the current RangeList is a subset of the parameter.
	 * 
	 * This object is a subset of rl if every value contained in this object also is found in rl.
	 * 
	 * Five points for correctness
	 * 
	 * If you have a solution that provides O(N^2) performance you get +5 points
	 * If you have a solution that provides O(N) performance you get +10 points
	 */
	public boolean subsetOf(RangeList rl) {
		throw new RuntimeException ("FINISH THIS IMPLEMENTATION");
	}
	
	/**
	 * Determine if the current RangeList contains the same values as the parameter list.
	 * Performance: O(N)
	 */
	public boolean equals(Object o) {
		throw new RuntimeException ("FINISH THIS IMPLEMENTATION");
	}
	
	
	/**
	 * Returns true if the value is contained in the given RangeList.
	 * Performance must be O(N)
	 */
	public boolean contains(int value) {
		throw new RuntimeException ("FINISH THIS IMPLEMENTATION");
	}
	
	/**
	 * If value does not already exist in RangeList, add it and return true, otherwise return false.
	 * 
	 * This method must guarantee the GAP property and the ASCENDING property of RangeList
	 * Performance must be O(N)
	 */
	public boolean add(int value) {
		throw new RuntimeException ("FINISH THIS IMPLEMENTATION");
	}

	/**
	 * Remove value from RangeList if it exists (and return true) otherwise return false.
	 * 
	 * This method must guarantee the GAP property and the ASCENDING property of RangeList
	 * Performance must be O(N)
	 */
	public boolean remove(int value) {
		throw new RuntimeException ("FINISH THIS IMPLEMENTATION");
	}

	/** 
	 * Produce string representation of ranges, in order from least to greatest.
	 * Performance must be O(N) 
	 */
	public String toString() {
		throw new RuntimeException ("FINISH THIS IMPLEMENTATION");	}

	// BONUS QUESTIONS
	// --------------------------------------------------
	/** Return a random integer from this RangeList uniformly over all values. */
	public int random() {
		throw new RuntimeException("ONLY COMPLETE IF DOING BONUS");
	}
	public java.util.Iterator<Integer> iterator() {
		throw new RuntimeException("ONLY COMPLETE IF DOING BONUS");
	}

}
