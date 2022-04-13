package algs.days.day15;

public class TraversalExample {
	public static void main(String[] args) {

		// construct binary tree with hidden message and do pre-order traversal
		BST<Integer,String> bst = new BST<Integer,String>();

		bst.put(1, "S");
		bst.put(5, "C");
		bst.put(10, "T");
		bst.put(6, "R");
		bst.put(2, "E");
		bst.put(7, "E");

		bst.inorder();
		System.out.println("------------------------------------------");

		// construct binary tree with hidden message and do pre-order traversal
		bst = new BST<Integer,String>();

		bst.put(8, "S");
		bst.put(5, "E");
		bst.put(10, "T");
		bst.put(6, "R");
		bst.put(2, "C");
		bst.put(7, "E");

		bst.preorder();
		System.out.println("------------------------------------------");

		// construct binary tree with hidden message and do post-order traversal
		bst = new BST<Integer,String>();

		bst.put(5, "T");
		bst.put(2, "E");
		bst.put(3, "S");
		bst.put(9, "E");
		bst.put(7, "R");
		bst.put(8, "C");

		bst.postorder();
		System.out.println("------------------------------------------");

		// ditto for rev-oder (unusual, right?)
		bst = new BST<Integer,String>();
		bst.put(13, "E");
		bst.put(1, "T");
		bst.put(4, "R");
		bst.put(35, "S");
		bst.put(10, "C");
		bst.put(2, "E");		
		bst.revorder();
	}
}
