

import java.util.ArrayList;
import java.util.Comparator;
public class Vertex implements Comparator<Vertex>,Comparable<Vertex>{
	private String word;
	private Vertex predecessor;
	private int distance;
	private ArrayList<Edge> edges; 
	
	public Vertex(String word, Vertex pre, int d) {
		this.word = word;
		this.predecessor = pre;
		this.distance = d; 
		this.edges = new ArrayList<Edge>();		
	}
	public void addEdges(Edge temp) {
		this.edges.add(temp);
	}
	public String toString() {
		return "w: "+this.word + " d: " +this.distance+" p: "+this.predecessor.word;
	}
	public void pred(Vertex v) {
		this.predecessor = v;
	}
	public void dist(int d) {
		this.distance = d;
	}
	public String getWord() {
		return this.word;
	}
	public int getDist() {
		return this.distance;
	}
	public Vertex getPred() {
		return this.predecessor;
	}
	public int compare(Vertex v1,Vertex v2) {
		// TODO Auto-generated method stub
		 return v1.compareTo(v2);
		
	}
	public void reset() {
		this.predecessor = null;
		this.distance = Integer.MAX_VALUE;
		this.edges = new ArrayList<Edge>();
		
	}
//	@Override
	public int compareTo(Vertex verte) {
		int d1 = this.getDist();
		int d2 = verte.getDist();
		if(d1>d2) {
			return 1;
		}
		if(d1<d2) {
			return -1;
		}
		return 0;
	}
	public ArrayList<Edge> getEdges(){
		return this.edges;
	}
	
	
	
	
	
	

}
