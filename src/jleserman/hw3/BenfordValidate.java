package jleserman.hw3;

import edu.princeton.cs.algs4.In;

// Copy this file into your USERID.hw3 package
public class BenfordValidate {
	static Integer countFeet;
	static Integer countMeters;

	public static void main(String[] args) {
		BST_SymbolTable treeFeet = new BST_SymbolTable();
		BST_SymbolTable treeMeters = new BST_SymbolTable();
		// sample code to show you how to process the values from the Table. You 
		// will need to re-envision how this output will be done. That is up to you
		for(int N = 0; N<=9; N++){
			countFeet = 0;
			countMeters = 0;

			for (int i = 0; i < Table.heights.length; i++) {
				String meter = Table.heights[i][0];
				String feet = Table.heights[i][1];
				Integer nFeet = Integer.valueOf(feet.charAt(0));
				Integer nMeters = Integer.valueOf(meter.charAt(0));
				if (nFeet == N) {countFeet++;}
				if (nMeters == N) {countMeters++;}
			}

			if(N==1){
				treeFeet.put(N, countFeet);
				treeMeters.put(N,countMeters);
			}else{

			}

		}
	}
}
