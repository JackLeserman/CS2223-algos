package jleserman.hw2;

import com.sun.xml.internal.bind.v2.TODO;
import edu.princeton.cs.algs4.In;
import org.w3c.dom.ranges.Range;

import java.util.LinkedList;

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
	public RangeList() {

	}

	/**
	 * Return the number of distinct ranges in the RangeList.
	 * Performance must be O(N)
	 */
	public int numberRanges() {
		Node checkMe = head;
		int counter = 0;
		if(checkMe.next == null){
			counter = counter + 1;
		}
		while(checkMe.next != null) {
			counter = counter + 1;
			checkMe = checkMe.next;
		}
		counter = counter + 1;
		return counter;
	}

	/** 
	 * Return the number of distinct values in the RangeList.
	 * Performance must be O(1) 
	 */
	public int numberValues() {
		Node checkMe = head;
		int counter = 0;
		while (checkMe.next != null) {
			int high = checkMe.high;
			int low = checkMe.low;
			if (high == low) {
				counter = counter + 1;
			} else {
				for (int i = low; i < high; i++) {
					counter = counter + 1;
				}
			}
			checkMe = checkMe.next;
		}
		int high = checkMe.high;
		int low = checkMe.low;
		if (high == low) {
			counter = counter + 1;
		} else {
			for (int i = low; i < high; i++) {
				counter = counter + 1;
			}
		}
		return counter;
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
		LinkedList<Integer> curr = new LinkedList();
		LinkedList<Integer> master = new LinkedList();
		Node checkMeCurr = head;
		Node checkMeMaster = rl.head;

		while(checkMeCurr.next != null){
			int high = checkMeCurr.high;
			int low = checkMeCurr.low;
			if(high==low){
				curr.add(high);
			}else {
				for (int i = low; i < high; i++) {
					curr.add(i);
				}
			}
			checkMeCurr = checkMeCurr.next;
		}
		int high = checkMeCurr.high;
		int low = checkMeCurr.low;

		if(high==low){
			curr.add(high);
		}else {
			for (int i = low; i < high; i++) {
				curr.add(i);
			}
		}

		while(checkMeMaster.next != null){
			int high2 = checkMeMaster.high;
			int low2 = checkMeMaster.low;
			if(high2==low2){
				master.add(high2);
			}else {
				for (int i = low2; i < high2; i++) {
					master.add(i);
				}
			}
			checkMeMaster = checkMeMaster.next;
		}

		int high2 = checkMeMaster.high;
		int low2 = checkMeMaster.low;

		if(high2==low2){
			master.add(high2);
		}else {
			for (int i = low2; i < high2; i++) {
				master.add(i);
			}
		}

		int listLen = curr.size();

		for(int i=0; i<listLen; i++){
			int num = curr.get(i);
			if(!master.contains(num)){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * Determine if the current RangeList contains the same values as the parameter list.
	 * Performance: O(N)
	 */
	public boolean equals(Object o) {
		RangeList oList = (RangeList) o;
		LinkedList<Integer> curr = new LinkedList();
		LinkedList<Integer> master = new LinkedList();
		Node checkMeCurr = head;
		Node checkMeMaster = oList.head;

		while(checkMeCurr.next != null){
			int high = checkMeCurr.high;
			int low = checkMeCurr.low;
			if(high==low){
				curr.add(high);
			}else {
				for (int i = low; i < high; i++) {
					curr.add(i);
				}
			}
			checkMeCurr = checkMeCurr.next;
		}
		int high = checkMeCurr.high;
		int low = checkMeCurr.low;

		if(high==low){
			curr.add(high);
		}else {
			for (int i = low; i < high; i++) {
				curr.add(i);
			}
		}

		while(checkMeMaster.next != null){
			int high2 = checkMeMaster.high;
			int low2 = checkMeMaster.low;
			if(high2==low2){
				master.add(high2);
			}else {
				for (int i = low2; i < high2; i++) {
					master.add(i);
				}
			}
			checkMeMaster = checkMeMaster.next;
		}

		int high2 = checkMeMaster.high;
		int low2 = checkMeMaster.low;

		if(high2==low2){
			master.add(high2);
		}else {
			for (int i = low2; i < high2; i++) {
				master.add(i);
			}
		}

		int listLen = curr.size();

		if(listLen != master.size()){
			return false;
		}

		for(int i=0; i<listLen; i++){
			int numCurr = curr.get(i);
			int numMaster = master.get(i);
			if(numCurr != numMaster){
				return false;
			}
		}
		return true;
	}
	
	
	/**
	 * Returns true if the value is contained in the given RangeList.
	 * Performance must be O(N)
	 */
	public boolean contains(int value) {
		Node checkMe = head;
		while(checkMe.next != null) {
			int high = checkMe.high;
			int low = checkMe.low;
			if (high == low) {
				if (low == value) {
					return true;
				}
			} else {
				for (int i = low; i < high; i++) {
					if (i == value) {
						return true;
					}
				}
			}
			checkMe = checkMe.next;
		}
		int high = checkMe.high;
		int low = checkMe.low;
		if(high==low){
			if(low==value){
				return true;
			}
		}else {
			for (int i = low; i < high; i++) {
				if (i == value) {
					return true;
				}
			}
		}
		return false;
	}
	
	/**
	 * If value does not already exist in RangeList, add it and return true, otherwise return false.
	 * 
	 * This method must guarantee the GAP property and the ASCENDING property of RangeList
	 * Performance must be O(N)
	 */
	public boolean add(int value) {
		Node checkMe = head;
		//TODO add case where 2 is added to 345
		//for case where list is empty
		if(checkMe == null){
			head = new Node(value, value, null);
			return true;
		}

		while(checkMe.next != null) {
			//check if the value exists
			if(checkMe.low < value && checkMe.high > value) {
				System.out.println("ALREADY EXISTS");
				return false;
			}

			//check if the value is between this node and the next one
			if(checkMe.high < value && checkMe.next.low > value){
				Node plusOne = checkMe.next;

				if(checkMe.high == value - 1){
					checkMe.high = value;
					return true;
				}
				if(checkMe.low == value + 1){
					checkMe.low = value;
					return true;
				}

				Node addNode = new Node(value, value, plusOne);
				checkMe.next = addNode;
				return true;
			}

			if(checkMe.low > value && checkMe.high > value){
				if(checkMe.high == value - 1){
					checkMe.high = value;
					return true;
				}
				if(checkMe.low == value + 1){
					checkMe.low = value;
					return true;
				}

				Node addNode = new Node(value, value, checkMe);
				head = addNode;
				return true;
			}
			//primes the next node
			checkMe = checkMe.next;
		}

		//edge case if checkMe is the node of concern
		//checks if value is between low and high of tail
		if(checkMe.low < value && checkMe.high > value) {
			System.out.println("ALREADY EXISTS");
			return false;
		}

		//if the node we want to add is going to go at the end,
		//where there is no "next" set the next to null
		//searches for next as null
		if(checkMe.high < value && checkMe.next == null){
			if(checkMe.high == value - 1){
				checkMe.high = value;
				return true;
			}
			if(checkMe.low == value + 1){
				checkMe.low = value;
				return true;
			}

			Node addNode = new Node(value, value, null);
			checkMe.next = addNode;
			return true;
		}

		//handles case where we add BEFORE a node
		if(checkMe.low > value && checkMe.high > value){
			if(checkMe.high == value - 1){
				checkMe.high = value;
				return true;
			}
			if(checkMe.low == value + 1){
				checkMe.low = value;
				return true;
			}

			Node addNode = new Node(value, value, checkMe);
			head = addNode;
			return true;
		}
		throw new RuntimeException("Something went terribly wrong and you should cry");
	}

	/**
	 * Remove value from RangeList if it exists (and return true) otherwise return false.
	 * 
	 * This method must guarantee the GAP property and the ASCENDING property of RangeList
	 * Performance must be O(N)
	 */

	public boolean remove(int value) {
		Node checkMe = head;
		Node checkMePrev = null; //EDGE
		if (checkMe == null) {
			return false;
		}
		while (checkMe.next != null) {
			int high = checkMe.high;
			int low = checkMe.low;
			if(low < value && value < high){
				if(checkMePrev == null ){
					Node upper = new Node(value+1, high, head);
					Node lower = new Node(low, value-1, upper);
					head = lower;
					return true;
				}
				Node upper = new Node(value+1, high, checkMe);
				Node lower = new Node(low, value-1, upper);
				checkMePrev.next=lower;
				return true;
			}
			if(low == high) {
				if (low == value) {
					if(checkMePrev == null){
						head = checkMe.next;
						return true;
					}
					checkMePrev.next = checkMe.next;
					return true;
				}
			}
			checkMePrev = checkMe;
			checkMe = checkMe.next;
		}
		int high = checkMe.high;
		int low = checkMe.low;
		if(checkMePrev == null ){
			Node upper = new Node(value+1, high, head);
			Node lower = new Node(low, value-1, upper);
			head = lower;
			return true;
		}
		if(low < value && value < high){
			Node upper = new Node(value+1, high, checkMe);
			Node lower = new Node(low, value-1, upper);
			checkMePrev.next=lower;
			return true;
		}
		if(low == high) {
			if (low == value) {
				checkMePrev.next = null;
				return true;
			}
		}
		return false;
	}

	/** 
	 * Produce string representation of ranges, in order from least to greatest.
	 * Performance must be O(N) 
	 */


	public String toString() {
		Node checkMe = head;
		String str = "";

		while(checkMe.next != null){
			int high = checkMe.high;
			int low = checkMe.low;
			if(high == low){
				String intStr = Integer.toString(low);
				str = str + intStr + ",";
			}else{
				String intStr = Integer.toString(low) + "-" + Integer.toString(high);
				str = str + intStr + ",";
			}
			checkMe = checkMe.next;
		}
		int high = checkMe.high;
		int low = checkMe.low;
		if(high == low){
			String intStr = Integer.toString(low);
			str = str + intStr;
		}else {
			String intStr = Integer.toString(low) + "-" + Integer.toString(high);
			str = str + intStr + ",";
		}
		return str;
	}

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
