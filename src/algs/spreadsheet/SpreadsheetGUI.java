package algs.spreadsheet;

/*
 * Copyright (c) 1995, 2008, Oracle and/or its affiliates. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Oracle or the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
 
/*
 * TableFTFEditDemo.java requires one other file:
 *   IntegerEditor.java
 */
 
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.TableColumn;

import algs.spreadsheet.gui.Model;
import algs.spreadsheet.gui.RowHeaderRenderer;

import java.awt.Dimension;
import java.awt.GridLayout;
 
/**
 * You do not have to modify this GUI class at all. Use as is.
 */
public class SpreadsheetGUI extends JPanel implements IUpdate {
	
	/** Contains the actual spreadsheet. */
	Spreadsheet spreadsheet;
	
	/** JTable model. */
	Model model;
	
	/** JTable view. */
	JTable table;
	
    public SpreadsheetGUI(Spreadsheet spreadsheet) {
        super(new GridLayout(1,0));
        this.spreadsheet = spreadsheet;
        spreadsheet.registerListener(this);
 
        setPreferredSize(new Dimension(606, 406));
        
        model = new Model(spreadsheet);
        table = new JTable(model);
        model.setJTable(table);
        
        for (int i = 0; i < table.getColumnCount(); i++) {
        	TableColumn column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(60);
        }
        
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
 
        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        
        table.setCellSelectionEnabled(true); // allow individual cells to be selected.
        
        scrollPane.setRowHeaderView(RowHeaderRenderer.buildRowHeader(table));
        
        // Add the scroll pane to this panel.
        add(scrollPane);
    }
 
   
    public static void main(String[] args) {
    	// Create and set up the window.
        JFrame frame = new JFrame("Spreadsheet Example");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 
        Spreadsheet spreadsheet = new Spreadsheet();
        
        //Create and set up the content pane.
        SpreadsheetGUI newContentPane = new SpreadsheetGUI(spreadsheet);
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
 
        // Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    /**
     * Whenever a cell has changed its value (because of a computation) this method is invoked,
     * and we just force a repaint of the required cell.
     */
	@Override
	public void update(String reference, double value) {
		int[] colAndRow = Cell.isReference(reference);
		model.fireTableCellUpdated(colAndRow[1], colAndRow[0]);

		System.out.println("DEBUG: refresh:" + reference + " to " + value);
		table.repaint(table.getCellRect(colAndRow[1]-1, colAndRow[0]-1, false));
	}
}