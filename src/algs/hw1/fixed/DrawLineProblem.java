package algs.hw1.fixed;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import javax.swing.JFrame;
import javax.swing.JPanel;

import algs.hw1.LineProblem;

/**
 * Visualizes the Sylvester line Problem: https://en.wikipedia.org/wiki/Sylvester%E2%80%93Gallai_theorem
 * 
 * In every finite set of points in the Euclidean plane, there is a line that either (a) passes through 
 * exactly two of the points; or (b) it passes through all of them.
 *
 */
public class DrawLineProblem extends JFrame {
	private static final long serialVersionUID = 1L;  // to keep eclipse happy

	public static final int width = 800;
	public static final int height = 800;
	
	/** Points to draw. */
	Point[] points;
	
	/** Refer to the student's solver. */
	LineProblem solver;
	
	/** Cartesian has origin in middle, but Java Swing has origin in upper left corner, and must flip vertically. */
	public static Point cartesianToSwing(Point cartesian) {
		return new Point((width/2) + cartesian.x, (height/2)-cartesian.y);
	}
	
	/** Convert from Swing Point into Cartesian point. */
	public static Point swingToCartesian(Point p) {
		return new Point(p.x - width/2, height/2 - p.y);
	}

	public DrawLineProblem (Point[] points, LineProblem solver) {
		super();
		this.points = points;
		this.solver = solver;
		setTitle("Sylvester Liner Problems.");
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

		@Override
		public void paintComponent(Graphics g) {
			
			for (Point p : points) {
				Point pj = cartesianToSwing(p);         // make sure to convert into Swing
				g.drawOval(pj.x - 2, pj.y - 2, 4, 4);   // since (0,0) is in the upper left corner.
			}
			
			if (solver == null) {
				g.setColor(Color.red);
				g.drawString("No Solver Available: Be sure to pass in with constructor.", 50, 50);
				g.setColor(Color.black);
				return;
			}
			
			// compute Sylvester line
			Solution s = solver.compute(points);
			if (s == null) {
				g.drawString("All Collinear", 50, 50); // at top left
			} else {
				Point p1 = cartesianToSwing(s.p1), p2 = cartesianToSwing(s.p2);
				g.setColor(Color.red);
				g.fillOval(p1.x - 2, p1.y - 2, 4, 4);
				g.fillOval(p2.x - 2, p2.y - 2, 4, 4);
				
				// now draw a line that cuts plane through these two points.
				int dy = s.p1.y - s.p2.y;
				int dx = s.p1.x - s.p2.x;
				
				if (dx == 0) {
					// horizontal line
					p1 = cartesianToSwing(new Point(s.p1.x, -height));
					p2 = cartesianToSwing(new Point(s.p1.x, +height));
				} else {
					// Pick either point and compute Y values at distant left and right
					p1 = cartesianToSwing(new Point(s.p1.x - width, s.p1.y - (width*dy)/dx));
					p2 = cartesianToSwing(new Point(s.p1.x + width, s.p1.y + (width*dy)/dx));
				}
				
				g.drawLine(p1.x, p1.y, p2.x, p2.y);
			}
		}
	}

	public static void main(String[] args) {
		RandomPointGenerator rnd = new RandomPointGenerator();
		Point[] sample = rnd.randomCartesians(300, width/2);  // only stick to middle, so the lines stand out
		
		DrawLineProblem frame = new DrawLineProblem(sample, null);
		frame.setVisible(true);
	}
}
