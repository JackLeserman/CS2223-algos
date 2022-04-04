package jleserman.hw2;

import edu.princeton.cs.algs4.In;

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
	Node head = null;
	Node tail = null;
	/** 
	 * Enqueue val at end of the queue.
	 * Performance must be O(1) 
	 */
	public void enqueue(int val) {
		Node node = new Node(val);
		node.next = head;
		if(tail == null && head == null) {
			tail = node;
			head = node;
		}else{
			head = node;
		}
	}
	
	/** 
	 * Dequeue and return the first value in the queue.
	 * Performance must be O(1) 
	 */
	public int dequeue() {
		Node node = head;
		node.next = head;
		return node.value;
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
		int largestInt = Integer.MIN_VALUE;
		Node largestNode = null;
		Node previousNode = null;
		Node previousNodeLargest = null;
		Node checkMe = head;
		if (isEmpty()) {
			throw new RuntimeException("Queue Empty");
		}
		while (checkMe.next != null) {
			int largest = checkMe.value;
			if (largest > largestInt) {
				largestNode = checkMe;
				largestInt = largest;
				previousNodeLargest = previousNode;
			}
			previousNode = checkMe;
			checkMe = checkMe.next;
		}
		int largest = checkMe.value;
		if (largest > largestInt) {
			largestNode = checkMe;
			largestInt = largest;
			previousNodeLargest = previousNode;
		}
		if(previousNodeLargest == null){
			head = largestNode.next;
			return largestInt;
		}else{
			previousNodeLargest.next = largestNode.next;
		}
		return largestInt;
	}
	
	/**
	 * Determine if queue is empty.
	 * Performance must be O(1)
	 */
	public boolean isEmpty() {
		if(head == null && tail == null){
			if(head.next == null){
				return true;
			}
		}
		return false;
	}
	
	/** 
	 * Determine the number of values in the queue.
	 * Performance must be O(1)
	 */
	public int size() {
		Node checkMe = head;
		int counter = 0;
		while(checkMe != tail) {
			counter = counter + 1;
			checkMe = checkMe.next;
		}
		counter = counter + 1;
		return counter;
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
		Node headNext = head.next;
		Node tailb4 = null; //todo add global
		Node checkMe = head;
		while(checkMe != tail) {
			tailb4 = checkMe;
			checkMe = checkMe.next;
		}
		if(tailb4 == null){
			return;
		}
		tailb4.next = head;
		head = tail;
		head.next = headNext;
		return;
	}

	public void printQueue() {
		Node checkMe = head;
		String str = "";
		while(checkMe.next != null){
			int val = checkMe.value;
			String num = Integer.toString(val);
			str = str + num + " ";
			checkMe = checkMe.next;
		}
		int val = checkMe.value;
		String num = Integer.toString(val);
		str = str + num + " ";
		System.out.println(str);
		return;
	}
	
}
