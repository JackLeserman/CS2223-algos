package jleserman.hw4;
import java.util.*;
import java.io.*;
import algs.hw4.map.HighwayMap;
import algs.hw4.map.Information;
import algs.maze.Position;
import edu.princeton.cs.algs4.*;
import edu.princeton.cs.algs4.Queue;

import javax.annotation.processing.SupportedSourceVersion;
import java.awt.*;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * Copy this class into USERID.hw4 and make changes.
 */
public class MapSearch {
	
	/**
	 * This method must create a copy of the graph, which you can do by creating a new graph with 
	 * the same number of vertices as the original one, BUT you only add an edge to the copy
	 * if the edge in the original graph IS NOT involved in the M25.
	 * 
	 * For example, in the data set you will see two nodes:
	 * 
	 *      E13@6a(M1)&E30@21(M25)&M1@6a&M25@21 51.716288 -0.385208
	 * 		E30/M25@+M25(X14) 51.713257 -0.421343
	 * 
	 * These lines correspond to vertex #114 (the first one) and vertex #1196 (the second one).
	 * Because the label for both of these vertices includes "M25" this edge must not appear in 
	 * the copied graph, since it is a highway segment involving the M25.
	 * 
	 * Note that the edge is eliminated even when only one of the nodes involves M25.
	 */
	private static final int INFINITY = Integer.MAX_VALUE;
	private static boolean[] marked;  // marked[v] = is there an s-v path
	private static int[] edgeTo;      // edgeTo[v] = w means (w,v) on s-v path
	private static int[] distTo;      // distTo[v] = number of edges shortest s-v path
	static double maxNorthLON = Integer.MIN_VALUE;
	static double maxSouthLON = Integer.MAX_VALUE;
	static double maxWestLAT = Integer.MIN_VALUE;
	static double maxEastLAT = Integer.MAX_VALUE;
	static int maxNorth;
	static int maxSouth;
	static int maxWest;
	static int maxEast;

	public static void BreadthFirstPaths(Graph G, int s, int target) {
		marked = new boolean[G.V()];
		distTo = new int[G.V()];
		edgeTo = new int[G.V()];
		//bfs(G, s, target);
	}

	private void bfs(Graph G, int s, int target) {
		Queue<Integer> q = new Queue<Integer>();
		for (int v = 0; v < G.V(); v++) { distTo[v] = INFINITY; }
		distTo[s] = 0;
		marked[s] = true;
		q.enqueue(s);

		while (!q.isEmpty()) {
			int v = q.dequeue();
			for (int w : G.adj(v)) {
				if (!marked[w]) {
					edgeTo[w] = v;
					distTo[w] = distTo[v] + 1;
					marked[w] = true;
					q.enqueue(w);
					if (v == target) {
						return;
					}
				}
			}
		}
	}



	static Information remove_M25_segments(Information info) {
		
		edu.princeton.cs.algs4.Graph g = info.graph;
		edu.princeton.cs.algs4.Graph copy = new edu.princeton.cs.algs4.Graph(g.V());
		
		Information newInfo = new Information(copy, info.positions, info.labels);
		return newInfo;
	}
	
	/** 
	 * This helper method returns the western-most data point in the Information, given its latitude and
	 * longitude.
	 * 
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 * 
	 */
	public static int westernMostVertex(Information info) {
		SeparateChainingHashST<Integer, algs.hw4.map.GPS> pos = info.positions;
		int v = info.graph.V();
		for(int i = 0; i<v-1; i++){
			double lat = pos.get(i).latitude;
			if(lat > maxWest){
				maxWestLAT = lat;
				maxWest = i;
			}
		}
		return maxWest;
	}

	/** 
	 * This helper method returns the western-most data point in the Information, given its latitude and
	 * longitude.
	 * 
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 * 
	 */
	public static int easternMostVertex(Information info) {
		SeparateChainingHashST<Integer, algs.hw4.map.GPS> pos = info.positions;
		int v = info.graph.V();
		for(int i = 0; i<v-1; i++){
			double lat = pos.get(i).latitude;
			if(lat < maxEast){
				maxEastLAT = lat;
				maxEast = i;
			}
		}
		return maxEast;
	}
	
	/** 
	 * This helper method returns the southern-most data point in the Information, given its latitude and
	 * longitude.
	 * 
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 * 
	 */
	public static int southernMostVertex(Information info) {
		throw new RuntimeException("Student Completes");
	}
	
	/** 
	 * This helper method returns the northern-most data point in the Information, given its latitude and
	 * longitude.
	 * 
	 * https://en.wikipedia.org/wiki/Latitude
	 * https://en.wikipedia.org/wiki/Longitude
	 * 
	 */
	public static int northernMostVertex(Information info) {
		throw new RuntimeException("Student Completes");
	}

	public static void main(String[] args) {
		Information info = HighwayMap.undirectedGraph();
		int west = westernMostVertex(info);
		int east = easternMostVertex(info);

		//int south = southernMostVertex(info);
		//int north = northernMostVertex(info);

		System.out.println("BreadthFirst Search from West to East:");
		BreadthFirstPaths(info.graph, west, east);
		String nameWest = info.labels.get(west);
		String nameEast = info.labels.get(east);

		System.out.println("BFS: " + nameWest + "(" + maxWest + ") to " + nameEast + "(" + maxEast + ") has " +edgeTo.length + " edges.");

		System.out.println("\nBreadthFirst Search from South to North:");
		// DO SOME WORK HERE
		System.out.println("BFS: " + "SOMEPLACE" + "(" + 999 + ") to " + "SOMEPLACE" + "(" + 999 + ") has " + 9999 + " edges.");
		
		System.out.println("\nDepthFirst Search from West to East:");
		// DO SOME WORK HERE
		System.out.println("DFS: " + "SOMEPLACE" + "(" + 999 + ") to " + "SOMEPLACE" + "(" + 999 + ") has " + 9999 + " edges.");
		
		System.out.println("\nDepthFirst Search from South to North:");
		// DO SOME WORK HERE
		System.out.println("DFS: " + "SOMEPLACE" + "(" + 999 + ") to " + "SOMEPLACE" + "(" + 999 + ") has " + 9999 + " edges.");
		
		System.out.println("\nNow without M25 edges...\n");
		System.out.println("WEST to EAST");
		Information info_no_m25 = remove_M25_segments(info);
		
		// DO SOME WORK HERE
		System.out.println("BFS: " + "SOMEPLACE" + "(" + 999 + ") to " + "SOMEPLACE" + "(" + 999 + ") has " + 9999 + " edges.");
		
		System.out.println("\nNORTH to SOUTH");
		// DO SOME WORK HERE
		System.out.println("BFS: " + "SOMEPLACE" + "(" + 999 + ") to " + "SOMEPLACE" + "(" + 999 + ") has " + 9999 + " edges.");
				
	}
	
}
