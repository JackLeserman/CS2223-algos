package jleserman.hw3;

import edu.princeton.cs.algs4.In;

// Copy this file into your USERID.hw3 package
public class BenfordValidate {
	static Integer countFeet;
	static Integer countMeters;
	static Integer totalCounts;
	public static void printTable(BST treeFeet, BST treeMeters){
		for(int N = 0; N<=9; N++){
			int valFeet = treeFeet.get(Integer.toString(N));
			int valMeters = treeMeters.get(Integer.toString(N));
			double percFeet = ((double) valFeet/(double)(totalCounts))*100;
			double percMeters = ((double)valMeters/(double)totalCounts)*100;
			double pBL =((double)(valFeet + valMeters)/(double)(totalCounts*2))*100;
			System.out.println(N + " || " + valMeters + " | " + (int)percMeters + "% ||| " + valFeet + " | " + (int)percFeet + "% |||| "+ (int) pBL + "%");
		}
	}

	public static void main(String[] args) {
		BST treeFeet = new BST();
		BST treeMeters = new BST();
		// sample code to show you how to process the values from the Table. You 
		// will need to re-envision how this output will be done. That is up to you
		totalCounts = 0;
		for(int N = 0; N<=9; N++){
			countFeet = 0;
			countMeters = 0;
			for (int i = 0; i < Table.heights.length; i++) {
				String meter = Table.heights[i][0];
				String feet = Table.heights[i][1];
				char charF = feet.charAt(0);
				char charM = meter.charAt(0);
				int nFeet = Integer.parseInt(String.valueOf(charF));
				int nMeters = Integer.parseInt(String.valueOf(charM));
				if (nFeet == N) {countFeet++;}
				if (nMeters == N) {countMeters++;}
			}
			treeFeet.put(Integer.toString(N),countFeet);
			treeMeters.put(Integer.toString(N),countMeters);
			totalCounts = totalCounts + countFeet;
			}
		System.out.println("             METERS        |||         FEET              ");
		System.out.println("Leading Digit || Count | % ||| Leading Digit | Count | % |||| BENFORDS LAW");
		System.out.println("==========================================================");
		System.out.println("");
		printTable(treeFeet, treeMeters);
		}
	}