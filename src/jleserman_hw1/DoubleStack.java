package jleserman_hw1;

import algs.days.day03.FixedCapacityStack;
import edu.princeton.cs.algs4.In;

/**
 * For this assignment, you are to complete this DoubleStack class that uses a single array to keep
 * track of two independent stacks.
 * 
 * One grows from the left, upwards into the array. The other grows from the right, downwards
 * into the array.
 * 
 * For example, after creating a DoubleStack of size 7, storage looks like the following:
 * 
 * [ --, --, --, --, --, --, 7] >>
 * 
 * Now issue the following commands:
 * 
 * (a) pushLeft(5); (b) pushLeft(3); (c) pushRight(7); (d) pushRight(2); (e) pushLeft(1)
 * 
 * The storage changes to the following:
 * 
 * [ 5, 3, 1, --, --, 2, 7] >> [5 3 1 2 7]
 * 
 * For simplicity, only 'int' values are stored.
 * 
 * Any attempt to pop an empty stack (left or right) or push to a full stack must throw
 * an IllegalStateException.
 * 
 * Complete all methods and make sure that Existing TestCases pass.
 */
public class DoubleStack {
	
	FixedCapacityStack<Integer> globalStack;
	int globalN;
	int leftNums;
	int rightNums;
	/** Construct a DoubleStack that can store n integers. */
	public DoubleStack(int n) {
		FixedCapacityStack dubStack = new FixedCapacityStack<Integer>(n);
		globalStack = dubStack;
		globalN = n;
		leftNums = 0;
		rightNums = 0;
	}

	/** Determines if Double Stack is full. */
	public boolean isFull() {
		if(sizeLeft() + sizeRight() == globalN){
			return true;
		}else{
			return false;
		}
	}

	/** Returns the number of int values in the left stack. */
	public int sizeLeft() {
		return leftNums;
	}
	/** Returns the number of int values in the right stack. */
	public int sizeRight() {
		return rightNums;
	}

	/** If DoubleStack is not full, push value onto the left stack. */
	public void pushLeft(int v) {
		if (!isFull()) {
			globalStack.push(v);
			leftNums = leftNums + 1;
		}else{
			throw new IllegalStateException("The stack is full, you idiot, lol");
		}
	}

	/** If DoubleStack is not full, push value onto the right stack. */
	public void pushRight(int v) {
		if (!isFull()) {
			FixedCapacityStack<Integer> tempStack = new FixedCapacityStack(globalN);
			while (globalStack.isEmpty() == false) {
				tempStack.push(globalStack.pop());
			}
			globalStack.push(v);
			while (tempStack.isEmpty() == false) {
				globalStack.push(tempStack.pop());
			}
			rightNums = rightNums + 1;
		}else{
			throw new IllegalStateException("The stack is full, you idiot, lol");
		}
	}
	
	/**
	 * If left and right contain at least one value, exchange the top values found on both stacks.
	 * If either the left or right side is empty, throw new IllegalStateException.
	 */
	public void exchange() {
		if(leftNums > 0 && rightNums > 0){
			FixedCapacityStack<Integer> tempStack = new FixedCapacityStack(globalN);
			int left = globalStack.pop();
			int right;
			while (globalStack.isEmpty() == false) {
				tempStack.push(globalStack.pop());
			}
			right = tempStack.pop();
			tempStack.push(left);
			while (tempStack.isEmpty() == false) {
				globalStack.push(tempStack.pop());
			}
			globalStack.push(right);
		}else {
			throw new IllegalStateException("You cant swap stuff if theres nothing to swap");
		}
	}
	
	/** Pop and return the topmost value from left stack. */
	public int popLeft() {
		int leftVal;
		if(leftNums > 0) {
			leftVal = globalStack.pop();
			leftNums = leftNums - 1;
			return leftVal;
		}
		throw new IllegalStateException("There isn't anything in the left stack, you moron!");
	}

	/** Pop and return the topmost value from right stack. */
	public int popRight() {
		FixedCapacityStack<Integer> tempStack = new FixedCapacityStack(globalN);
		if(rightNums > 0) {
			while (globalStack.isEmpty() == false) {
				tempStack.push(globalStack.pop());
			}
			int rightVal = tempStack.pop();
			while (tempStack.isEmpty() == false) {
				globalStack.push(tempStack.pop());
			}
			rightNums = rightNums - 1;
			return rightVal;
		}
		throw new IllegalStateException("Next time, check if there is anything on the right before doing something stupid");
	}

	public static void main(String[] args) {
		try{
			DoubleStack dubStack = new DoubleStack(6);
			dubStack.pushLeft(5);
			dubStack.pushLeft(3);
			dubStack.pushRight(7);
			dubStack.pushRight(2) ;
			dubStack.pushLeft(1);
		}catch (IllegalStateException exp){
			System.out.println("you suck lol");
		}
	}
}
