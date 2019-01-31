package projects;

import java.util.Queue;
import java.util.LinkedList;

/**
 * Represents a graph with edges and vertices.
 * 
 * @author John Litscher
 */
public class Graph {
	
	private int[][] matrix;
	
	private Vertex[] vertices;
	
	public Graph(int numv) {
		matrix = new int[numv][numv];
		
		vertices = new Vertex[numv];
		
		//set each entry in the matrix to 0
		for(int i = 0; i < numv; i++) {
			for(int j = 0; j < numv; j++) {
				matrix[i][j] = 0;
			}
		}
	}
	
	public void addDirectedEdge(int v1, int v2) {
		addDirectedEdgeWithWeight(v1, v2, 1);
	}
	
	/**
	 * Adds a directed edge to the graph
	 */
	public void addDirectedEdgeWithWeight(int v1, int v2, int weight) {
		this.matrix[v1][v2] = weight;
	}
	
	public void addUndirectedEdge(int v1, int v2, int weight) {
		this.matrix[v1][v2] = weight;
		this.matrix[v2][v1] = weight;
	}
	
	public void makeAllUnvisited() {
		for(int i = 0; i < vertices.length; i++) {
			vertices[i].setUnvisited();
		}
	}
	
	/**
	 * Returns a string representation of the graph vertices traversed 
	 *  in bredth first order from the specified starting vertex.
	 * 
	 * @param start the starting vertex
	 * @return A string containing bfs path
	 */
	public String bfs(int start){
		
		String output = "";

        // Use a queue to store the nodes that are waiting to be visited.
        // Queue is an interface. We can declare a variable of type Queue
        // then create a LinkedList object for that Queue.
        Queue<Integer> q = new LinkedList<Integer>();

        // Initialize all vertices to unvisited
        makeAllUnvisited();

        // Enqueue the start vertex
        q.add(start);
        vertices[start].setWaiting();

        while(!q.isEmpty()) {

            // Dequeue the vertex at the front of the queue and add its information
            // to the output string. Mark that vertex as visited.
            int dqv = q.remove();
            vertices[dqv].setVisited();
            output += "[" + vertices[dqv].getName() + ", " + dqv + "]; ";

            // Enqueue all adjacent, unvisited vertices to dqv.
            // Loop through dqv's edgeList. Add unvisited vertices to the
            // queue and set those vertices to the waiting state.
			for (int i = 0; i < vertices.length; i++) {
				if (matrix[dqv][i] != 0 && vertices[i].isUnvisited()) {
					q.add(i);
					vertices[i].setWaiting();
				}
			}
		}
		return output;
	}
	
	/**
	 * Returns a string representation of the graph vertices traversed in depth
	 * first order from the specified starting vertex.
	 * 
	 * @param start starting vertex
	 * @return A string containing dfs path
	 */
	public String dfs(int start) {
		if (start < 0 || start >= vertices.length) {
			throw new IllegalArgumentException("start is not a valid vertex: " + start);
		}
		String output = "";

		// Use a stack to keep track of vertices that still need to be checked
		// for the depth traversal
		LinkedList<Integer> stack = new LinkedList<Integer>();

		// Initialize all vertices to unvisited
		makeAllUnvisited();

		// Add the starting vertex to the stack
		stack.push(start);

		// Visit the starting vertex
		output += "[" + vertices[start].getName() + ", " + start + "]; ";
		vertices[start].setVisited();

		// Keep visiting nodes in depth first order
		while (!stack.isEmpty()) {
			// Peek at the top of the stack
			int peeked = stack.peek();

			// Get the edgeList for the vertex at the top of the stack
			// LinkedList<Edge> edges = edgeList.get(peeked);

			boolean found = false; // if an unvisited adjacent vertex is found
			int i = 0; // counter

			while (i < vertices.length && !found) {
				for (int k = 0; k < vertices.length; k++) {
					if (matrix[peeked][k] != 0 && vertices[k].isUnvisited()) {
						found = true;
						stack.push(k);
						output += "[" + vertices[k].getName() + ", " + k + "]; ";
						vertices[k].setVisited();
						break;
					}
					i++;
				}

			}

			if (!found) {
				// the current vertex has no unvisited adjacent vertices
				// pop the current vertex off the stack
				stack.pop();
			}
		}
		return output;

	}
	
	/**
	 * Returns a string that contains information about the shortest path from the
	 *  given node to every node in the graph,
	 *  including the path from the given node to itself.
	 *  
	 * @param start starting vertex
	 * @return A string containing shortest path
	 */
	public String shortestPath(int start) throws IllegalArgumentException{
		if(start < 0 || start >= vertices.length){
			throw new IllegalArgumentException("start is not a valid vertex: " + start);
		}
		
		int dist[] = new int[vertices.length]; //the output array. dist[i] will hold the 
		//shortest distance from start to i
		
		//sptSet[i] returns true if vertex i is included in shortest 
		//path tree or shortest distance from start to i is finalized
		Boolean sptSet[] = new Boolean[vertices.length];
		
		//create an array of strings that hold the output for each path
		String[] path = new String[vertices.length];
		//initialize all the strings to hold the name of the starting vertex
		for(int i = 0; i < vertices.length; i++){
			path[i] = vertices[start].getName() + " " + start;
		}
		
		//initialize all distances as INFINITE and stpSet[] as false
		for(int i = 0; i < vertices.length; i++){
			dist[i] = Integer.MAX_VALUE;
			sptSet[i] = false;
		}
		
		//distance of source vertex from itself is always 0
		dist[start] = 0;
		
		//find shortest path for all vertices
		for(int count = 0; count < vertices.length - 1; count++){
			//pick the minimum distance vertex from the set of vertices 
			//not yet processed. u is always equal to start in first 
			//iteration
			int u = minDistance(dist, sptSet);
			 
			sptSet[u] = true;
			
			//Update dist value of the adjacent vertices of the 
			//picked vertex
			for(int k = 0; k < vertices.length; k++){
				//update dist[v] only if is not in sptSet, there is an 
				//edge from u to v, and total weight of the path from start to v
				//through u is smaller than current value of dist[v]
				if(!sptSet[k] && matrix[u][k] != 0 && dist[u] != Integer.MAX_VALUE 
						&& dist[u] + matrix[u][k] < dist[k]){
					dist[k] = dist[u] + matrix[u][k];
					path[k] = path[u] + " to " + vertices[k].getName() + " " + k;
				}
			}
			
		}
		return printShortestPathString(dist, vertices.length, start, path);
	}
	
	/**
	 * Returns a string that contains information about the shortest path from the 
	 * given node to every node in the graph,
	 * including the path from the given node to itself.
	 * 
	 * @param start the starting vertex
	 * @return A String containing the shortest path
	 */
	public String dijkstras(int start) throws IllegalArgumentException {
		if(start < 0 || start >= vertices.length){
			throw new IllegalArgumentException("start is not a vaild vertex: " + start);
		}
		int dist[] = new int[vertices.length]; // The output array. dist[i] will hold
	                                 // the shortest distance from src to i
	        
	    // sptSet[i] will true if vertex i is included in shortest
	    // path tree or shortest distance from src to i is finalized
	    Boolean sptSet[] = new Boolean[vertices.length];
	        
	    //create an array of stings that hold the output for each path
	    String path[] = new String[vertices.length];
	        
	    //initializes all the Strings to hold the name of the starting vertex
	    for(int i = 0; i < vertices.length; i++){
	       dist[i] = Integer.MAX_VALUE;
	        sptSet[i] = false;
	     }
	 
	     // Initialize all distances as INFINITE and stpSet[] as false
	     for (int i = 0; i < vertices.length; i++) {
	        dist[i] = Integer.MAX_VALUE;
	        sptSet[i] = false;
	      }
	 
	      // Distance of source vertex from itself is always 0
	      dist[start] = 0;
	 
	      // Find shortest path for all vertices
	      for (int count = 0; count < vertices.length-1; count++) {
	         // Pick the minimum distance vertex from the set of vertices
	         // not yet processed. u is always equal to src in first
	         // iteration.
	         int u = minDistance(dist, sptSet);
	 
	         // Mark the picked vertex as processed
	         sptSet[u] = true;
	 
	         // Update dist value of the adjacent vertices of the
	         // picked vertex.
	         for (int k = 0; k < vertices.length; k++){
	            	
	             // Update dist[v] only if is not in sptSet, there is an
	             // edge from u to v, and total weight of path from src to
	             // v through u is smaller than current value of dist[v]
	             if (!sptSet[k] && matrix[u][k]!=0 && dist[u] != Integer.MAX_VALUE &&
	                		dist[u]+ matrix[u][k] < dist[k]){
	                 dist[k] = dist[u] + matrix[u][k];
	                 path[k] = path[u] + " to " + vertices[k].getName() + " " + k;
	             }
	          }
	     }
	 
	     // print the constructed distance array
	     return printString(dist, start, path);
	}
	
	/**
	 * Find the vertex with minimum distance value, from the set of vertices not
	 * yet included in shortest path tree
	 * 
	 * @param dist int array of current distances
	 * @param sptSet boolean array of if included in shortest path tree
	 * @return int of vertex with min distance value
	 */
	private int minDistance(int dist[], Boolean sptSet[]){
		//initialize min value
		int min = Integer.MAX_VALUE, min_index = -1;
		for(int k = 0; k < vertices.length; k++){
			if(sptSet[k] == false && dist[k] <= min){
				min = dist[k];
				min_index = k;
			}
		}
		return min_index;
	}
	
	/**
	 * Formats the return output
	 * 
	 * @param dist the array holding distances
	 * @param start the index of the starting vertex
	 * @param path the path path of the vertices
	 * @return
	 */
	private String printString(int dist[], int start, String[] path){
		String output = "";
		for(int i = 0; i < vertices.length; i++){
			output += ("Minimum weight path from " + vertices[start].getName() + " " + start + " to " + vertices[i].getName() + " "
					+ i + " is " + dist[i] + " -------- Path: " + path[i] + "\n");
		}
		return output;
	}
	
	/**
	 * Formats the return output 
	 * 
	 * @param dist the array holding distances
	 * @param n length of vertex
	 * @param start the index of the starting vertex
	 * @param path the path of the vertices
	 * @return formatted string for the method to return
	 */
	private String printShortestPathString(int dist[], int n, int start, String[] path){
		String output = "";
		for(int i = 0; i < vertices.length; i++){
			output += (vertices[start].getName() + " " + start + " to " + vertices[i].getName() + " "
				+ i + " is " + dist[i] + " -------- Path: " + path[i] + "\n");
		}
		return output;
	}
	
	/**
	 * Adds a vertex with name and value to traverse the list
	 * 
	 * @param v value used to traverse the list
	 * @param n name associated with the vertex
	 */
	public void addVertex(int v, String n){
		this.vertices[v] = new Vertex(n);
		this.vertices[v].setUnvisited();
	}
	
	
}
