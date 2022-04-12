package jleserman.hw3.bonus;

public class BST {

	class Node {
		Integer   value;          
		Node      left, right;  // left and right subtrees

		public Node(Integer value) {
			this.value = value;
		}

		// here for debugging
		public String toString() { return "[" + value + "]"; }
	}

	Node root;               // root of the tree

	/** Invoke put on parent, should it exist. */
	public void add(Integer value) { 
		root = add(root, value);
	}

	private Node add(Node parent, Integer value) {
		if (parent == null) return new Node(value);

		int cmp = value.compareTo(parent.value);
		if      (cmp <= 0) { parent.left  = add(parent.left,  value); }
		else if (cmp > 0)  { parent.right = add(parent.right, value); }
		
		return parent;
	}
	
	public SimpleRangeList ranges() {
		throw new RuntimeException ("COMPLETE ME (and helper recursive method)");
	}

}
