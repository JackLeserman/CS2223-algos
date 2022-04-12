package algs.hw1.fixed;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Stroke;
import java.util.Arrays;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Helper code to visualize PolarPoints being drawn in a plane.
 */
public class DrawPolarPoints extends JFrame {
	private static final long serialVersionUID = 1L;  // to keep eclipse happy
	public static final int width = 800;
	public static final int height = 800;
	
	/** Points to draw. */
	PolarPoint[] points;
	
	/** Cartesian has origin in middle, but Java Swing has origin in upper left corner, and must flip vertically. */
	public static Point cartesianToSwing(Point cartesian) {
		return new Point((width/2) + cartesian.x, (height/2)-cartesian.y);
	}
	
	/** Convert from Swing Point into Cartesian point. */
	public static Point swingToCartesian(Point p) {
		return new Point(p.x - width/2, height/2 - p.y);
	}

	public DrawPolarPoints (PolarPoint[] points) {
		super();
		this.points = points;
		setTitle("Polar Point Drawer.");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(0, 0, width, height);
		JPanel contentPane = new JPanel();
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		DrawingPanel panel = new DrawingPanel();
		panel.setBounds(0, 0, getWidth(), getHeight());
		contentPane.add(panel);
	}
	
	class DrawingPanel extends JPanel {
		private static final long serialVersionUID = 1L;  // to keep eclipse happy

		// draw axis
		Point top = cartesianToSwing(new Point(0, height/2));
		Point bottom = cartesianToSwing(new Point(0, -height/2));
		Point left = cartesianToSwing(new Point(-width/2, 0));
		Point right = cartesianToSwing(new Point(width/2, 0));
		Point origin = cartesianToSwing(new Point(0, 0));
					
		@Override
		public void paintComponent(Graphics g) {
			// draw axis lines
			g.drawLine(top.x, top.y, bottom.x, bottom.y);
			g.drawLine(left.x, left.y, right.x, right.y);
			
			// Create a copy of the Graphics instance for drawing dashed lines
			Graphics2D g2d = (Graphics2D) g.create();
			Stroke dashed = new BasicStroke(1, BasicStroke.CAP_BUTT, BasicStroke.JOIN_BEVEL, 0, new float[]{1,3,2}, 0);
			g2d.setColor(Color.red);
	        g2d.setStroke(dashed);
	        
	        int idx = 1;
	        Point lastPoint = null;
			for (PolarPoint p : points) {
				Point cartesian = p.asCartesianPoint();
				
				// since Java has (0,0) in upper left corner, need to adjust
				Point pj = cartesianToSwing(cartesian);
				g.drawOval(pj.x - 2, pj.y - 2, 4, 4);
				
				// draw radial lines from origin and enumerate each point
				g2d.drawLine(origin.x, origin.y, pj.x, pj.y);
				g.drawString("" + idx, pj.x, pj.y);
				idx++;
				
				if (lastPoint != null) {
					g.drawLine(lastPoint.x, lastPoint.y, pj.x, pj.y);
				}
				lastPoint = pj;
			}
			
			g2d.dispose();  // make sure to dispose otherwise waste resources...
		}
	}

	public static void main(String[] args) {
		RandomPointGenerator rnd = new RandomPointGenerator();      
		PolarPoint[] sample = rnd.randomPolars(100, width/2);
		
		Arrays.sort(sample, Sorting.comparePolarByTheta);   // sort so they are in counterclockwise order
		
		DrawPolarPoints frame = new DrawPolarPoints(sample);
		frame.setVisible(true);
	}
}
