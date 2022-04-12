package algs.days.day13;

public class CarefulWithRecursion {
	class Node {
		int val;
		Node next;
		
		Node (int v, Node n) {
			this.val  = v;
			this.next = n;
		}
	}
	
	Node head;
	
	void trial() {
		// construct list of 80,000 entries
		for (int i = 0; i < 80000; i++) {
			head = new Node(i, head);
		}
		
		// THIS should work (can you explain why?)
		System.out.println(contains(head, 75000));
		
		// THIS FAILS (can you explain why?)
		System.out.println(contains(head, 80));
	}
	
	/** Does the linked list, starting at n, contain the value v. */
	boolean contains(Node n, int v) {
		// base case
		if (n.val == v) { 
			return true;
		}
		
		// recursive case
		return contains(n.next, v);
	}
	
	
	public static void main(String[] args) {
		new CarefulWithRecursion().trial();
	}
}
