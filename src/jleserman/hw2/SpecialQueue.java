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
		Node prev;
		Node (int val) {
			this.value = val;
		}
	}
	Node head = null; //first
	Node tail = null; //last
	int size = 0;

	/** 
	 * Enqueue val at end of the queue.
	 * Performance must be O(1) 
	 */
	public void enqueue(int val) { //
		size++;
		Node node = new Node(val);
		if(tail==null && head==null){ //If this is the FIRST
			head = tail = node;
			tail.prev = null; //todo
		}else {
			tail.next = node;
			node.prev = tail;
			tail = node; //
		}
	}
	
	/** 
	 * Dequeue and return the first value in the queue.
	 * Performance must be O(1) 
	 */
	public int dequeue() {
		size--;
		Node node = head;
		head = node.next;
		if(node == tail){
			node.prev = null;
		}
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
		Node checkMe = head;
		Node largestNode = null;
		int largestInt = 0;

		if (isEmpty()) {
			throw new RuntimeException("Queue Empty");
		}
		size--;
		while (checkMe.next != null) {
			int largest = checkMe.value;
			if (largest > largestInt) {
				largestNode = checkMe;
				largestInt = largest;
			}
			checkMe = checkMe.next;
		}
		int largest = checkMe.value;
		if (largest > largestInt) {
			largestNode = checkMe;
			largestInt = largest;
		}
		if(largestNode == null){
			throw new RuntimeException("Queue Empty");
		}

		if(largestNode == tail){
			tail = largestNode.prev;
			tail.next = null;
			return largestInt;
		}
		if(largestNode == head){
			head = largestNode.next;
			head.prev = null;
			return largestInt;

		}
		Node beforeLargest = largestNode.prev;
		Node afterLargest = largestNode.next;
		beforeLargest.next = afterLargest;
		afterLargest.prev = beforeLargest;
		return largestInt;
	}

	public int dequeueLargestOLD() {
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
			return true;
			}
		return false;
	}
	
	/** 
	 * Determine the number of values in the queue.
	 * Performance must be O(1)
	 */
	public int size() {
		return size;
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
		if (size < 2) {
			return;
		}

		if(size == 2){
			Node newTail = head;
			head = tail;
			head.next = newTail;
			tail = newTail;
			tail.next = null;
			tail.prev = head;
			return;
		}
		Node b4tail = tail.prev;
		Node afterHead = head.next;
		Node newHead = tail;
		Node newTail = head;

		newTail.prev = b4tail;
		newTail.next = null;
		b4tail.next = newTail;

		newHead.prev = null;
		newHead.next = afterHead;
		afterHead.prev = newHead;

		head = newHead;
		tail = newTail;


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
