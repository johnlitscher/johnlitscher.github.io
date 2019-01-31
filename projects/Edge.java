package projects;

/**
 * Represents a location
 * 
 * @author John Litscher
 */
public class Edge {

	/** Destination of the edge 
	 */
	private int destination;
	
	/** Weight of the edge */
	private int weight;
	
	public Edge(int destination, int weight) {
		this.destination = destination;
		this.weight = weight;
	}
	
	public int getDestination() {
		return destination;
	}
	
	public int getWeight() {
		return weight;
	}
}
