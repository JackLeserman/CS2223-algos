package algs.spreadsheet;

import javax.swing.JOptionPane;

import algs.spreadsheet.graph.Digraph;
import algs.spreadsheet.graph.Topological;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.SeparateChainingHashST;

/**
 * A spreadsheet mimics behavior of a standard Excel Spreadsheet which has 256 columns
 * and 256 rows.
 * 
 * 1. Each cell is referenced by a string like "A15" or "AB7".
 * 2. Each cell can contain a string value. If that value starts with "=" then it represents
 *    an expression, otherwise it is treated as a double. The expression must be represented
 *    in InFix using spaces between tokens and have parentheses for clarity.
 * 
 */
public class Spreadsheet {
	
	/** Whenever changes are required, this alerts outside on a cell-by-cell basis. */
	IUpdate listener;
	
	/** Once linked in, any changes are alerted to outside agent. */
	public void registerListener(IUpdate listener) {
		this.listener = listener;
	}
	
	/** 
	 * The content of all Cells are stored in this symbol table. 
	 * 
	 * Key is Cell (i.e., a Cell Reference such as new Cell("B5")) 
	 * Value is String (i.e., either a number like "2.343" or an expression of form "= ( 4 + 5 )"
	 * 
	 * 1. To change a value, for a given cell reference, call "cells.put(reference, cell)" where
	 *    cell is a Cell object 
	 */
	SeparateChainingHashST<String,String> cellContents = new SeparateChainingHashST<String,String>();
	
	/** 
	 * The value of all Cells are stored in this symbol table. 
	 * 
	 * Values are transitory and can change based on other cells.
	 */
	SeparateChainingHashST<String,Double> cellValues = new SeparateChainingHashST<String,Double>();
	
	/**
	 * Digraph has vertex for each possible cell reference.
	 */
	Digraph graph = new Digraph(Cell.maxRow*Cell.maxColumn);
	
	/**
	 * Set the value in spreadsheet column and row to the specified contents and 
	 * return an Iterable of Cells that have to be updated in response.
	 * 
	 * At a minimum, the Cell identified by 
	 * 
	 * For example, ss.set('B5', "= ( 4 + 5 )") would set the contents of cell B5 to
	 * be the expression "4 + 5" which is evaluated using infix notation as in HW1. 
	 * 
	 * Now, if the cell 'C6' contained the expression "= ( B5 * 2 )" then this method
	 * would have to return an Iterable that contains both Cell(B5) and Cell (C6) in 
	 * that order (which is the topological sorted order).
	 * 
	 * @param column
	 * @param row
	 * @param contents
	 * @return
	 */
	public void set(String reference, String contents) throws IllegalArgumentException {
		
		// remove all dependencies that reference has (since they might be overwritten)
		int v = Cell.getId(reference);
		if (v < 0) {
			throw new IllegalArgumentException (reference + " is an invalid Spreadsheet reference.");
		}
		
		String oldContents = null;
		if (cellContents.contains(reference)) {
			oldContents = cellContents.get(reference);
		}
		
		if (oldContents != null) {
			// remove old dependencies!
			if (oldContents.startsWith("=")) {
				// do something since this is an expression
				String expr = oldContents.substring(1);
				Queue<String> q = Cell.getAllReferences(expr);
				while (!q.isEmpty()) {
					String ref = q.dequeue();
					int w = Cell.getId(ref);
					graph.removeEdge(w, v);    // when vertex V changes, then W must also change
				}
			}
		}
		
		// no store the new contents
		cellContents.put(reference, contents);
		
		// update graph 
		Queue<Integer> queue = new Queue<Integer>();
		if (contents.startsWith("=")) {
			// do something since this is an expression
			String expr = contents.substring(1);
			Queue<String> q = Cell.getAllReferences(expr);
			while (!q.isEmpty()) {
				String ref = q.dequeue();
				int w = Cell.getId(ref);
				graph.addEdge(w, v);    // when vertex V changes, then W must also change
				queue.enqueue(w);       // keep track of in case we want to UNDO later
			}
		} else {
		
			double value;
			try {
				value = Double.valueOf(contents);
				cellValues.put(reference, value);
				// everything is great!
			} catch (Exception e) {
				cellContents.put(reference, oldContents);
				if (contents.length() > 0) {
					JOptionPane.showMessageDialog(null, "Invalid contents:" + contents);
				}
			}
		}
		
		// only need to detect topological ordering from a given vertex.
		Topological top = new Topological(graph);
		if (!top.hasOrder()) {
			while (!queue.isEmpty()) {
				graph.removeEdge(queue.dequeue(), v);
			}
			cellContents.put(reference, oldContents);
			JOptionPane.showMessageDialog(null, "Setting " + reference + " to " + "\"" + contents + "\" will cause a cycle in the spreadsheet.");
			return;
		}
		
		// everything looks great! Now recompute
		for (int w : top.order()) {
			String ref = Cell.getRef(w);
			compute(ref);
			Double val = cellValues.get(ref);
			if (val != null) {
				listener.update(ref, val);
			}
		}
	}
	
	/** Recompute the cell. This is only called when it is valid to recompute topologically. */
	void compute(String reference) {
		String oldRef = cellContents.get(reference);
		
		if (oldRef != null) {
			double d = Evaluate.eval(this, oldRef);
			cellValues.put(reference, d);
		}
		
	}
	
	/** All computed values are retrievable, otherwise return "" for non-existent computation. */
	public String getValue(String reference) {
		if (cellValues.contains(reference)) {
			return "" + cellValues.get(reference);
		}
		
		return "";
	}
	
	/**
	 * Converts a column # into a spreadsheet column string
	 * @param col
	 */
	public String getColumnName(int col) {
		if (col < 26) {
			return "" + (char)('A' + col);
		}
		char let = (char) ('A' - 1 + (col/26));
		int end = col%26;
		return "" + let + (char)('A' + end);
	}

	public String getContents(String ref) {
		return cellContents.get(ref);
	}
}
