package jleserman.hw2;

/**
 * A Queue that offers some special methods 
 */
public class SpecialQueue {

	/** Linked list node to use. You must ensure that the value attribute is final. */
	class Node {
		final int value;
		Node next;

		Node (int val) {
			this.value = val;
		}
	}
	
	/** 
	 * Enqueue val at end of the queue.
	 * Performance must be O(1) 
	 */
	public void enqueue(int val) {
		// Complete
	}
	
	/** 
	 * Dequeue and return the first value in the queue.
	 * Performance must be O(1) 
	 */
	public int dequeue() {
		throw new RuntimeException("Must Be Completed By Student.");
	}
	
	/**
	 * Remove from the queue and return the largest value contained.
	 * 
	 * If there are multiple occurrences of the maximum value in the queue, then the 
	 * closest one to the front is the one that is removed.
	 * 
	 * If queue is empty a RuntimeException is thrown.
	 * 
	 * Performance is O(N) where N is the number of values in the queue.
	 */
	public int dequeueLargest() {
		throw new RuntimeException ("Must Be Completed By Student.");
	}
	
	/**
	 * Determine if queue is empty.
	 * Performance must be O(1)
	 */
	public boolean isEmpty() {
		throw new RuntimeException ("Must Be Completed By Student.");
	}
	
	/** 
	 * Determine the number of values in the queue.
	 * Performance must be O(1)
	 */
	public int size() {
		throw new RuntimeException ("Must Be Completed By Student.");
	}
	
	/**
	 * Adjusts the queue so the first element becomes the last element, and the last element
	 * becomes the first. 
	 * 
	 * If queue is empty or contains only one integer, nothing happens. 
	 * 
	 * If you can implement this method in O(1) time, +4 points
	 */
	public void swapEndPoints() {
		throw new RuntimeException ("Must Be Completed By Student.");
	}
	
}
