package algs.days.day11;

import edu.princeton.cs.algs4.In;

public class BoundToFail {
	public static void main(String[] args) {
		In in = new In ("words.english.txt");
		String[] knownStrings = new String[10000019];     // change from 1000003 to 10000019 and see...
		int numCollisions = 0;
		while (!in.isEmpty()) {
			String word = in.readString(); 
			
			int location = (word.hashCode() & 0x7fffffff) % knownStrings.length;
			if (knownStrings[location] == null) {
				knownStrings[location] = word;
			} else {
				System.out.println("COLLISION between " + knownStrings[location] + " and " + word + " on " + location);
				numCollisions++;
			}
			
		}
		in.close();

		System.out.println(numCollisions + " total collisions.");
	}
}
