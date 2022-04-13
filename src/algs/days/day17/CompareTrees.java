package algs.days.day17;

import edu.princeton.cs.algs4.StdRandom;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import algs.days.day16.BST;

public class CompareTrees {

	public static void main(String[] args) {
		ascending();
		random();
		try {
			constitution();
		} catch (FileNotFoundException e) {
			System.out.println("No constitution.txt file");
		}
		
		try {
			pi();
		} catch (IOException e) {
			System.out.println("No pi.txt file");
		}
	}
	
	static void ascending() {
		BST<Integer> bt = new BST<Integer>();
		AVL<Integer> avl = new AVL<Integer>();

		for (int i = 0; i < 100; i++) {
			bt.insert(i);
			avl.insert(i);
		}

		System.out.println("BST for ascending has height of " + bt.height());
		System.out.println("AVL for ascending has height of " + avl.height());
	}

	// random BST appears to be no worse than twice as bad.
	static void random() {
		BST<Double> bt = new BST<Double>();
		AVL<Double> avl = new AVL<Double>();

		for (int i = 0; i < 100000; i++) {
			double r = StdRandom.uniform();   // random value between 0 and 1.
			bt.insert(r);
			avl.insert(r);
		}

		System.out.println("BST for random has height of " + bt.height());
		System.out.println("AVL for random has height of " + avl.height());
	}


	// What about words from the Constitution
	// Can someone tell me what the word with the greatest depth is?
	// WRITE A PROGRAM TO DO THIS: Nice challenge problem.
	static void constitution() throws FileNotFoundException {
		BST<String> bt = new BST<String>();
		AVL<String> avl = new AVL<String>();

		// NOTE: DUPLICATE WORDS ARE ADDED AS SEPARATE NODES....
		Scanner sc = new Scanner(new File("constitution.txt"));
		while (sc.hasNext()) {
			String s = sc.next();			
			bt.insert(s);
			avl.insert(s);
		}

		System.out.println("BST for Constitution has height of " + bt.height());
		System.out.println("AVL for Constitution has height of " + avl.height());

		// see if you can write a program using this API....
		// System.out.println(bt.deepestKey());
	}

	// What about digits of pi?
	// Can someone tell me what the word with the greatest depth is?
	// WRITE A PROGRAM TO DO THIS: Nice challenge problem.
	static void pi() throws IOException {
		BST<String> bt = new BST<String>();
		AVL<String> avl = new AVL<String>();
		AlternateEqualBST<String> altbt = new AlternateEqualBST<String>();
		
		// NOTE: DUPLICATE Digits ARE ADDED AS SEPARATE NODES....
		// 100,000 digits in file.
		FileReader fr = new FileReader(new File("pi.txt"));
		while (fr.ready()) {
			char bte = (char) fr.read();
			String key = "" + bte;
			bt.insert(key);
			avl.insert(key);
			altbt.insert(key);
		}

		System.out.println("BST height for PI has height of " + bt.height());
		System.out.println("AVL height for PI has height of " + avl.height());
		System.out.println("ALT BST height for PI has height of " + altbt.height());
		System.out.println("ROOT of AVL tree is " + avl.root);
	}
	
}
