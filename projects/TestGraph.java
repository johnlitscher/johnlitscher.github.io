package projects;

/**
 * Test class for graph.
 * 
 * @author John Litscher
 */
public class TestGraph {
	public static void main(String[] args) {
		Graph g = new Graph(8);
		g.addVertex(0, "Manassas");
		g.addVertex(1, "Haymarket");
		g.addVertex(2, "Ashburn");
		g.addVertex(3, "Fairfax");
		g.addVertex(4, "Washington DC");
		g.addVertex(5, "Woodbridge");
		g.addVertex(6, "Tysons");
		g.addVertex(7, "Arlington");
		
		g.addDirectedEdge(0, 1);
		g.addDirectedEdge(0, 2);
		g.addDirectedEdge(0, 5);
		g.addDirectedEdge(1, 7);
		g.addDirectedEdge(1, 2);
		g.addDirectedEdge(1, 6);
		g.addDirectedEdge(2, 3);
		g.addDirectedEdge(2, 4);
		g.addDirectedEdge(3, 7);
		g.addDirectedEdge(3, 2);
		g.addDirectedEdge(4, 6);
		g.addDirectedEdge(4, 7);
		g.addDirectedEdge(5, 2);
		g.addDirectedEdge(6, 4);
		g.addDirectedEdge(7, 3);
		
		String output = g.bfs(3);
		System.out.println("BFS starting at 3");
		System.out.println(output);

		output = g.dfs(3);
		System.out.println("DFS starting at 3");
		System.out.println(output);

		output = g.shortestPath(3);
		System.out.println("Shortest paths starting at 3");
		System.out.println(output);
	}
}
