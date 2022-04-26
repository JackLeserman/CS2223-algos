package algs.spreadsheet.gui;

import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

import algs.spreadsheet.Cell;
import algs.spreadsheet.Spreadsheet;

/**
 * A Spreadsheet model takes a SpreadSheet and makes it part of a JTable viewer.
 * 
 * We need this class to work properly with JTable, otherwise you can see that it is a wrapper
 * class.
 * 
 * No need to modify this file.
 */
public class Model extends AbstractTableModel {
	/** Keep Eclipse happy. */
	private static final long serialVersionUID = 1L;
	
	Spreadsheet spreadsheet;	
	JTable table;
	
	public Model (Spreadsheet spreadsheet) {
		this.spreadsheet = spreadsheet;
	}
	
	public int getColumnCount() {
		return Cell.maxColumn;
	}

	public int getRowCount() {
		return Cell.maxRow;
	}

	// only need up to 256
	public String getColumnName(int col) {
		if (col < 26) {
			return "" + (char)('A' + col);
		}
		char let = (char) ('A' - 1 + (col/26));
		int end = col%26;
		return "" + let + (char)('A' + end);
	}
	
	/** Column and row are both 0-indexed. */
	public String getReference (int row, int col) {
		return getColumnName(col) + (row+1);
	}

	public Object getValueAt(int row, int col) {
		String ref = getReference (row, col);
		if (table.isCellSelected(row, col)) {
			return spreadsheet.getContents(ref);
		} else {
			return spreadsheet.getValue(ref);
		}
	}

	/** Can edit any cell. */
	public boolean isCellEditable(int row, int col) {
		return true;
	}

	/** Pass this update along to the inner spreadsheet. */
	public void setValueAt(Object value, int row, int col) {
		if (value instanceof String) {
			String str = (String) value;
			String ref = getReference (row, col);
			
			// nothing? Make it empty string.
			if (str.equals("=")) {
				str = "";
			}
			
			fireTableCellUpdated(row, col);
			spreadsheet.set(ref, str);
		}
	}

	/** So we can use during getValue requests. */
	public void setJTable(JTable table) {
		this.table = table;
	}
}

