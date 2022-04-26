package algs.spreadsheet.graph;

/** Use this class as is. I NEED THIS COPY TO SUPPORT removeEdge. */
public class Digraph  {
    final int V;           // number of vertices in this digraph
    int E;                 // number of edges in this digraph
    Bag<Integer>[] adj;    // adj[v] = adjacency list for vertex v
    
    /**
     * Initializes an empty digraph with <em>V</em> vertices.
     *
     * @param  V the number of vertices
     * @throws IllegalArgumentException if V < 0
     */
    public Digraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        
        adj = (Bag<Integer>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<Integer>();
        }
    }

    /** Number of vertices and edges. */
    public int V() { return V; }
    public int E() { return E; }

    /** Adds the directed edge v->w to this digraph. */
    public void addEdge(int v, int w) {
        adj[v].add(w);
        E++;
    }
    
    /** Remove an edge (v->w) from this digraph. */
    public void removeEdge(int v, int w) {
    	if (adj[v].remove(w)) {
    		E--;
    	}
    }
    
    /** Return degree of v (which lets caller know if there are edges coming out of v. */
    public int degree(int v) { return adj[v].size; }

    /** Returns the vertices adjacent from vertex <tt>v</tt> in this digraph. */
    public Iterable<Integer> adj(int v) { return adj[v]; }

    /**
     * Returns a string representation of the graph.
     *
     * @return the number of vertices <em>V</em>, followed by the number of edges <em>E</em>,  
     *         followed by the <em>V</em> adjacency lists
     */
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append(V + " vertices, " + E + " edges " + "\n");
        for (int v = 0; v < V; v++) {
            s.append(String.format("%d: ", v));
            for (int w : adj[v]) {
                s.append(String.format("%d ", w));
            }
            s.append("\n");
        }
        return s.toString();
    }
}