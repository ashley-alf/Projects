public class Edge {
	private Vertex vertex;
	private int weight;
	
	public Edge(Vertex a, int w) {
		vertex = a; 
		weight = w; 
	}
	public int getW() {
		return weight;
	}
	public Vertex getVertex() {
		return this.vertex;
	}
	public String toString() {
		return this.vertex + " "+ this.weight;
	}
}
