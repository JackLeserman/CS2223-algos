package algs.spreadsheet;

import java.util.StringTokenizer;

import edu.princeton.cs.algs4.Queue;

/**
 * A Cell in a spreadsheet is identified by its reference (i.e., "D3" or "AB7")
 * 
 * Note that the Cell object does not contain the contents of the cell; it is here merely as a placeholder.
 * You will not have to modify this class.
 */
public class Cell {
	public final String reference;
	public final int row;
	public final int col;
	
	public static final int maxRow = 256;
	public static final int maxColumn = 256;

	/**
	 * Properly compute (col, row) for cell.
	 * 
	 * @param ref   Cell reference
	 */
	public Cell(String ref) {
		this.reference = ref;
		
		int[] isref = isReference(ref);
		if (isref != null) {
			col = isref[0];
			row = isref[1];
		} else {
			throw new IllegalArgumentException (ref + " is not a valid Cell reference.");
		}
	}
	
	/**
	 * Need a hashcode method, which is based solely on string reference.
	 */
	@Override
	public int hashCode() {
		return reference.hashCode();
	}
	
	/**
	 * Reasonable implementation of equals() method for Cell
	 */
	@Override
	public boolean equals (Object o) {
		if (o == null) { return false; }
		if (o instanceof Cell) {
			Cell other = (Cell) o;
			
			return other.reference.equals(reference);
		}
		
		return false;
	}
	
	/**
	 * Returns the unique vertex identifier for this reference, or returns -1 if 
	 * it isn't a reference.
	 * 
	 * Note that 
	 * 
	 * @param ref
	 */
	public static int getId(String ref) {
		int[] colAndRow = isReference(ref);  // both col/row are 1-indexed.
		if (colAndRow == null) { return -1; }
		
		return (colAndRow[1]-1)*maxColumn + (colAndRow[0]-1);
	}
	
	/**
	 * Return the String reference (i.e. "B6") for a unique identifier.
	 * 
	 * @param id   unique cell identifier.
	 */
	public static String getRef(int id) {
		int row = 1 + (id / 256);
		int col = 1 + (id % 256);
		
		if (col <= 26) {
			return "" + (char)('A' + col - 1) + row;
		}
		
		char let = (char) ('A' -1 + (col-1)/26);
		int end = (col-1)%26;
		return "" + let + (char)('A' + end) + row;
	}

	
	/**
	 * Given a space-separated string, such as "( 5 + ( B2 + ( 8 + A8 ) ) )", return
	 * a queue of references (in this case "B2" and "A8").
	 * 
	 * If no references are found in the string, then an empty queue is returned.
	 * 
	 * @param s
	 * @return
	 */
	public static Queue<String> getAllReferences(String s) {
		Queue<String> q = new Queue<String>();
		StringTokenizer st = new StringTokenizer(s, " ");
		while (st.hasMoreTokens()) {
			String tok = st.nextToken();
			if (isReference(tok) != null) {
				q.enqueue(tok);
			}
		}
		
		return q;
	}
	
	/**
	 * Returns array [] of {col, row} if ref is a real reference; otherwise returns null.
	 * 
	 * Thus, calling Cell.isReference("B7") would return { 2, 7 } since this is the 
	 * second column and the 7th row.
	 * 
	 * Note that the returned col and row are 1-indexed.
	 * 
	 * @param ref   Cell reference
	 */
	public static int[] isReference(String ref) {
		int last = ref.length()-1;
		while (last > 0 && Character.isDigit(ref.charAt(last))) {
			last--;
		}
		
		int c;
		if (last == 0) {
			c = ref.charAt(0) - 'A' + 1;
		} else {
			int head = ref.charAt(0) - 'A' + 1;
			int rest = ref.charAt(1) - 'A' + 1;
			
			c = 26*head + rest;
		}

		try {
			int r = Integer.valueOf(ref.substring(last+1));
			
			return new int[] { c, r };
		} catch (Exception e) {
			return null;
		}
	}
}
