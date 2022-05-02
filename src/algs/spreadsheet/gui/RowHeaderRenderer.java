package algs.spreadsheet.gui;

import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.MouseEvent;

import javax.swing.AbstractListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JTable;
import javax.swing.ListCellRenderer;
import javax.swing.ListModel;
import javax.swing.UIManager;
import javax.swing.event.MouseInputAdapter;
import javax.swing.table.JTableHeader;

/**
 * Helper class to allow for row headers. 
 * 
 * Adapted from https://stackoverflow.com/questions/1434933/jtable-row-header-implementation.
 * 
 * No Need To Change
 */
public class RowHeaderRenderer extends JLabel implements ListCellRenderer {

	/** Keep Eclipse happy. */
	private static final long serialVersionUID = 1L;

	private static final int MIN_ROW_HEIGHT = 12;
	
    private JTable table;

    RowHeaderRenderer(JTable table) {
        this.table = table;
        JTableHeader header = this.table.getTableHeader();
        setOpaque(true);
        setBorder(UIManager.getBorder("TableHeader.cellBorder"));
        setHorizontalAlignment(CENTER);
        setForeground(header.getForeground());
        setBackground(header.getBackground());
        setFont(header.getFont());
        setDoubleBuffered(true);
    }

    public Component getListCellRendererComponent(JList list, Object value,
                                                  int index, boolean isSelected, boolean cellHasFocus) {
        setText((value == null) ? "" : value.toString());
        setPreferredSize(null);
        setPreferredSize(new Dimension((int) getPreferredSize().getWidth(), table.getRowHeight(index)));
        //trick to force repaint on JList (set updateLayoutStateNeeded = true) on BasicListUI
        list.firePropertyChange("cellRenderer", 0, 1);
        return this;
    }
    

	/** Helper method to construct the row headers. */
	public static JList buildRowHeader(final JTable table) {
	   
	    ListModel lm = new AbstractListModel() {

	        public int getSize() {
	            return table.getRowCount();
	        }

	        public Object getElementAt(int index) {
	            return "" + (index+1);
	        }
	    };

	    final JList rowHeader = new JList(lm);
	    rowHeader.setOpaque(false);
	    rowHeader.setFixedCellWidth(50);
	    
	    MouseInputAdapter mouseAdapter = new MouseInputAdapter() {
	        Cursor oldCursor;
	        Cursor RESIZE_CURSOR = Cursor.getPredefinedCursor(Cursor.S_RESIZE_CURSOR);
	        int index = -1;
	        int oldY = -1;

	        @Override
	        public void mousePressed(MouseEvent e) {
	            super.mousePressed(e);
	        }

	        @Override
	        public void mouseMoved(MouseEvent e) {
	            super.mouseMoved(e);
	            int previ = getLocationToIndex(new Point(e.getX(), e.getY() - 3));
	            int nexti = getLocationToIndex(new Point(e.getX(), e.getY() + 3));
	            if (previ != -1 && previ != nexti) {
	                if (!isResizeCursor()) {
	                    oldCursor = rowHeader.getCursor();
	                    rowHeader.setCursor(RESIZE_CURSOR);
	                    index = previ;
	                }
	            } else if (isResizeCursor()) {
	                rowHeader.setCursor(oldCursor);
	            }
	        }

	        private int getLocationToIndex(Point point) {
	            int i = rowHeader.locationToIndex(point);
	            if (!rowHeader.getCellBounds(i, i).contains(point)) {
	                i = -1;
	            }
	            return i;
	        }

	        @Override
	        public void mouseReleased(MouseEvent e) {
	            super.mouseReleased(e);
	            if (isResizeCursor()) {
	                rowHeader.setCursor(oldCursor);
	                index = -1;
	                oldY = -1;
	            }
	        }

	        @Override
	        public void mouseDragged(MouseEvent e) {
	            super.mouseDragged(e);
	            if (isResizeCursor() && index != -1) {
	                int y = e.getY();
	                if (oldY != -1) {
	                    int inc = y - oldY;
	                    int oldRowHeight = table.getRowHeight(index);
	                    if (oldRowHeight > 12 || inc > 0) {
	                        int rowHeight = Math.max(MIN_ROW_HEIGHT, oldRowHeight + inc);
	                        table.setRowHeight(index, rowHeight);
	                        if (rowHeader.getModel().getSize() > index + 1) {
	                            int rowHeight1 = table.getRowHeight(index + 1) - inc;
	                            rowHeight1 = Math.max(12, rowHeight1);
	                            table.setRowHeight(index + 1, rowHeight1);
	                        }
	                    }
	                }
	                oldY = y;
	            }
	        }

	        private boolean isResizeCursor() {
	            return rowHeader.getCursor() == RESIZE_CURSOR;
	        }
	    };
	    rowHeader.addMouseListener(mouseAdapter);
	    rowHeader.addMouseMotionListener(mouseAdapter);
	    rowHeader.addMouseWheelListener(mouseAdapter);
	    
	    rowHeader.setCellRenderer(new RowHeaderRenderer(table));
	    rowHeader.setBackground(table.getBackground());
	    rowHeader.setForeground(table.getForeground());
	    return rowHeader;
	}
	
}