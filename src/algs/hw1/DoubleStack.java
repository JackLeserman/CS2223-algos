package algs.hw1;

/**
 * For this assignment, you are to complete this DoubleStack class that uses a single array to keep
 * track of two independent stacks.
 * 
 * One grows from the left, upwards into the array. The other grows from the right, downwards
 * into the array.
 * 
 * For example, after creating a DoubleStack of size 7, storage looks like the following:
 * 
 * [ --, --, --, --, --, --, --]
 * 
 * Now issue the following commands:
 * 
 * (a) pushLeft(5); (b) pushLeft(3); (c) pushRight(7); (d) pushRight(2); (e) pushLeft(1)
 * 
 * The storage changes to the following:
 * 
 * [ 5, 3, 1, --, --, 2, 7]
 * 
 * For simplicity, only 'int' values are stored.
 * 
 * Any attempt to pop an empty stack (left or right) or push to a full stack must throw
 * an IllegalStateException.
 * 
 * Complete all methods and make sure that Existing TestCases pass.
 */
public class DoubleStack {
	
	
	/** Construct a DoubleStack that can store n integers. */
	public DoubleStack(int n) {
		
	}

	/** Determines if Double Stack is full. */
	public boolean isFull() {
		throw new IllegalStateException("Replace with actual implementation for isFull");
	}

	/** Returns the number of int values in the left stack. */
	public int sizeLeft() {
		return -1;  // Replace with actual implementation
	}

	/** Returns the number of int values in the right stack. */
	public int sizeRight() {
		return -1;  // Replace with actual implementation
	}

	/** If DoubleStack is not full, push value onto the left stack. */
	public void pushLeft(int v) {
		// Replace with actual implementation
	}

	/** If DoubleStack is not full, push value onto the right stack. */
	public void pushRight(int v) {
		// Replace with actual implementation
	}
	
	/**
	 * If left and right contain at least one value, exchange the top values found on both stacks.
	 * If either the left or right side is empty, throw new IllegalStateException.
	 */
	public void exchange() {
		throw new IllegalStateException("Replace with actual implementation for exchange."); 
	}
	
	/** Pop and return the topmost value from left stack. */
	public int popLeft() {
		throw new IllegalStateException("Replace with actual implementation for popLeft.");
	}

	/** Pop and return the topmost value from right stack. */
	public int popRight() {
		throw new IllegalStateException("Replace with actual implementation for popLeft.");
	}
}
