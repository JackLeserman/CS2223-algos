package jleserman.hw3;
import edu.princeton.cs.algs4.StdOut;
import java.util.NoSuchElementException;

/**
 * Minimum implementation of Binary Search Tree (BST) as a Symbol Table<String, Integer>
 *
 * You need to copy this class into your USERID.hw3 and add methods to the end of this class.
 */
public class BST {

	Node root;               // root of the tree

	class Node {
		String    key;
		Integer   count;
		Node      left, right;  // left and right subtrees
		int       N;            // number of nodes in subtree

		public Node(String key, int ct, int N) {
			this.key = key;
			this.count = ct;
			this.N = N;
		}

		/** For Debugging */
		public String toString() { return "[" + key + "]"; }
	}

	public boolean isEmpty() { return size() == 0; }

	/** Return number of key-value pairs in ST. */
	public int size()                { return size(root); }

	// Helper method that deals with "empty nodes"
	private int size(Node node) {
		if (node == null) return 0;

		return node.N;
	}

	/** Search for key in BST. */
	public Integer get(String key)      { return get(root, key); }

	/** Helper method to search for key in BST rooted at parent. */
	private Integer get(Node parent, String key) {
		if (parent == null) return null;

		int cmp = key.compareTo(parent.key);

		if      (cmp < 0) return get(parent.left, key);
		else if (cmp > 0) return get(parent.right, key);
		else              return parent.count;
	}

	/** Invoke put on parent, should it exist. */
	public void put(String key, Integer val) {
		root = put(root, key, val);
	}

	/** Helper method to put (key, ct) pair into BST rooted at parent. */
	private Node put(Node parent, String key, Integer ct) {
		if (parent == null) return new Node(key, ct, 1);

		int cmp = key.compareTo(parent.key);
		if      (cmp < 0) parent.left  = put(parent.left,  key, ct);
		else if (cmp > 0) parent.right = put(parent.right, key, ct);
		else              parent.count = ct;

		parent.N = 1 + size(parent.left) + size(parent.right);
		return parent;
	}

	// traversal ideas
	// invoke an inorder traversal of the tree
	public void inorder() { inorder(root); }
	private void inorder(Node n) {
		if (n == null) { return; }

		inorder (n.left);
		StdOut.println (n.key);
		inorder (n.right);
	}

	/**
	 * Removes the specified key and its associated value from this symbol table
	 * (if the key is in this symbol table).
	 *
	 * @param  key the key
	 * @throws IllegalArgumentException if {@code key} is {@code null}
	 */
	public void delete(String key) {
		if (key == null) throw new IllegalArgumentException("calls delete() with a null key");
		root = delete(root, key);
	}

	/** Taken from Sedgewick algo. */
	private Node delete(Node x, String key) {
		if (x == null) return null;

		int cmp = key.compareTo(x.key);
		if      (cmp < 0) x.left  = delete(x.left,  key);
		else if (cmp > 0) x.right = delete(x.right, key);
		else {
			if (x.right == null) return x.left;
			if (x.left  == null) return x.right;
			Node t = x;
			x = min(t.right);
			x.right = deleteMin(t.right);
			x.left = t.left;
		}
		return x;
	}

	private Node min(Node x) {
		if (x.left == null) return x;
		else                return min(x.left);
	}

	/**
	 * Removes the smallest key and associated value from the symbol table.
	 *
	 * @throws NoSuchElementException if the symbol table is empty
	 */
	public void deleteMin() {
		if (isEmpty()) throw new NoSuchElementException("Symbol table underflow");
		root = deleteMin(root);
	}

	private Node deleteMin(Node x) {
		if (x.left == null) return x.right;
		x.left = deleteMin(x.left);
		return x;
	}

	// ------------------------------------------------------------------------------------------------
	// YOU WILL MODIFY METHODS BELOW. THERE IS NO NEED TO MODIFY CODE ABOVE.
	// ------------------------------------------------------------------------------------------------


	/** Remove the maximum KEY in the Symbol table BST (i.e., Greatest String Value). */
	int largestStrInt = 0;
	String largestStr = null;
	public void deleteMaxKeyHelper(Node n){
		if (n == null) {return;}
		int keyLen = n.key.length();
		if(keyLen > largestStrInt){
			largestStrInt = keyLen;
			largestStr = n.key;
		}
		Node leftN = n.left;
		Node rightN = n.right;
		deleteMaxKeyHelper(leftN);
		deleteMaxKeyHelper(rightN);
	}

	public void deleteMaxKey() {
		deleteMaxKeyHelper(root);
		delete(largestStr);
		return;
	}

	public BST copy() {
		BST newBST = new BST();
		Node newRoot = copy(root);
		newBST.root = newRoot;
		return newBST;
	}
	/**
	 * Helper method for copy()
	 */

	Node copy(Node n) {
		if (root == null) {
			return null;
		}
		Node root_copy = new Node(root.key, root.count, root.N);

		root_copy.left = copy(root.left);
		root_copy.right = copy(root.right);

		// return cloned root node
		return root_copy;
	}

	/**
	 * Return the key whose count is the greatest (that is, has the most 
	 * occurrences in the BST). If multiple keys have the same count, 
	 * then any of them can be returned as the most frequent.
 	 * If EMPTY, return null.
	 */

	int maxCount = 0;
	int currCount = 1;
	Node bestNode = root;
	Node previous = null;
	public String keyWithLargestCount() {
		if(root == null){return null;}
		String frequentCountKey = keyWithLargestCount(root,bestNode).key;
		return frequentCountKey;
	}
	/**
	 * This helper method looks unusual, but follow the logic. It is trying
	 * to find the node with greatest count. Let 'best' be the best Node
	 * in the tree found so far.
	 */
	Node keyWithLargestCount(Node n, Node best) {
		if (n == null) {
			return best;
		}
		keyWithLargestCount(n.left, best);
		if (n.count == best.count) {
			currCount = currCount + 1;
		} else {
			currCount = 1;
		}
		if (currCount > maxCount) {
			maxCount = currCount;
			bestNode = n;
		}
		keyWithLargestCount(n.right, best);
		return null;
	}



	
}
