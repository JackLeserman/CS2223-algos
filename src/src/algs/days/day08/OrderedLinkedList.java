package algs.days.day08;

public class OrderedLinkedList {
	class Node {
		int     value;
		Node    next;
		
		Node (int v) { 
			this.value = v; 
		}
		
		Node (int v, Node next) {
			this.value = v;
			this.next = next;
		}
		
		// helps debugging!
		public String toString() { return "[" + value + "]"; }
	}
	
	Node head;
	
	public OrderedLinkedList() {
		head = null;
	}
	
	public void insert(int value) {
		// 1. EMPTY CASE
		if (head == null) {    
			head = new Node(value);
			return;
		}
		
		// 2.1 new value is smaller than head's value
		if (value < head.value) {
			head = new Node(value, head);
			return;
		}
		
		// 2.2 New value will be inserted after prev, a node in the list
		// but you have to find the proper one...
		Node prev = head;
		Node n = head.next;
		while (n != null) {
			if (value < n.value) {   // Belongs AFTER prev and BEFORE n
				prev.next = new Node(value, n);
				return;
			}
			
			// advance 
			prev = n;
			n = n.next;
		}
		
		// 2.3 New Value is largest in the linked list, so add AFTER prev
		prev.next = new Node(value);		
	}
	
	public boolean delete (int value) {
		// EMPTY LIST? Nothing can be deleted
		if (head == null) { return false; }
		
		// Is HEAD the one to be deleted?
		if (head.value == value) {
			head = head.next;
			return true;
		}
		
		// Try to see if you can find a prev such that prev.next is the
		// one to be deleted.
		Node prev = head;
		Node n = head.next;
		while (n != null) {
			if (value == n.value) {   // This is one to be deleted
				prev.next = n.next;   // cut the link out
				return true;
			}
			
			// advance 
			prev = n;
			n = n.next;
		}
		
		return false; // didn't find it
	}
	
	public static void main(String[] args) {
		OrderedLinkedList list = new OrderedLinkedList();
		list.insert(7);
		list.insert(10);  // add one after last
		list.insert(5);   // add one before first
		list.insert(8);   // add one in the middle
		
		list.delete(10);  // show how to delete last
		list.delete(7);   // show how to delete one in middle
		list.delete(5);   // show how to delete first one		
	}
}
