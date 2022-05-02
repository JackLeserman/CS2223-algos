package algs.days.day22;

import algs.hw4.map.Information;
import edu.princeton.cs.algs4.Graph;
import edu.princeton.cs.algs4.Queue;

import java.awt.*;

public class BFSMapAnimation_ALL extends MapAnimation {
	int[] color;
	int[] edgeTo;

	public BFSMapAnimation_ALL() {
		super();
	}

	public BFSMapAnimation_ALL(Information info) {
		super(info);
	}
	
	@Override
	protected boolean explore(int s, int target) {
		Queue<Integer> queue = new Queue<Integer>();
		Graph g = info.graph;
		color = new int[g.V()];
		edgeTo = new int[g.V()];

		// positions in the Queue are Gray and under investigation.
		queue.enqueue(s);
		color[s] = Gray;
		while (!queue.isEmpty()) {
			Integer u = queue.dequeue();
			if (isVerbose()) { 
				outputNode(u);
			}
			
			checkPause();  // see if request pause by clicking mouse

			for (int v : g.adj(u)) {
				if (color[v] == White) {
					edgeTo[v] = u;
					color[v] = Gray;
					visualizer.highlightNode(v, Color.lightGray);
					
					queue.enqueue(v);
				}
			}

			color[u] = Black; // done with vertex
			visualizer.highlightNode(u, Color.black); 
			delayAndRefresh();
		}
		
		return false;
	}
	void showPath(int s, int target) {
		if (color[target] != Black) { return; }
		for (int x = target; x != s; x = edgeTo[x]) {
			System.out.println(x + " [" + info.labels.get(x) + "]");
			visualizer.highlightEdge(x, edgeTo[x], Color.red);
			delayAndRefresh();
		}
	}
	
	public static void main(String[] args) {
		new BFSMapAnimation_ALL().launch(2256, 389);
	}
}