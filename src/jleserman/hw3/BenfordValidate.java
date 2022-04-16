package jleserman.hw3;

import edu.princeton.cs.algs4.In;

// Copy this file into your USERID.hw3 package
public class BenfordValidate {
	static Integer countFeet;
	static Integer countMeters;
	static Integer totalCounts;
	public static void printTable(BST treeFeet, BST treeMeters){
		System.out.println(totalCounts);
		for(int N = 0; N<=9; N++){
			int valFeet = treeFeet.get(Integer.toString(N));
			int valMeters = treeMeters.get(Integer.toString(N));
			long percFeet = (treeFeet.get(Integer.toString(N))/(totalCounts)); //TODO wtf
			long percMeters = (treeMeters.get(Integer.toString(N))/totalCounts);
			System.out.println(N + "\t" + valFeet + "\t" + percFeet + "\t" + valMeters + "\t" + percMeters);
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
		System.out.println("\t \t \t FEET \t \t   || \t \t \t METERS");
		System.out.println("==========================================================");
		System.out.println("Leading Digit \t Count \t % || Leading Digit \t Count \t % ");
		System.out.println(totalCounts);
		printTable(treeFeet, treeMeters);
		}
	}