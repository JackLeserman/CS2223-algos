package algs.hw4.map;

import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.SeparateChainingHashST;

/**
 * Records a graph for highway segments together with GPS information for each vertex id.
 * 
 * DO NOT MODIFY. USE AS IS.
 */
public class Information {
	public final Graph graph;
	
	/** GPS locations associated with each vertex. */
	public final SeparateChainingHashST<Integer, GPS> positions;
	
	/** Labels for each vertex. */
	public final SeparateChainingHashST<Integer, String> labels;
	
	public Information (Graph g, SeparateChainingHashST<Integer, GPS> pos, SeparateChainingHashST<Integer, String> labels) {
		this.graph = g;
		this.positions = pos;
		this.labels = labels;
	}
}
