import java.util.*;
import java.io.*;

public class Driver {
	static HashMap<String, Vertex> mappy = new HashMap<String, Vertex>();
	static HashSet<Vertex> words_used;
	public static void main(String[]args) {
		File dict; 
		Scanner readboy;
		try {
			dict = new File("Dict.txt");
			readboy = new Scanner(dict);
			while (readboy.hasNextLine()) {
				String w = readboy.nextLine();
				w = w.strip();
				//add the length as a key 
				mappy.put(w, new Vertex(w, null, Integer.MAX_VALUE));
			}
			readboy.close();
		} catch (FileNotFoundException e) {
			System.out.println("An error occurred.");
			e.printStackTrace();
		}//catch

		boolean keep_asking = true;
		Scanner input = new Scanner(System.in);
		String start;
		String end;
		String answer;
		while(keep_asking) {
			System.out.print("Starting word: ");
			start = input.nextLine().strip().toLowerCase();
			System.out.print("Ending word: ");
			end = input.nextLine().strip().toLowerCase();
			words_used = new HashSet<>();
			
			shortestPath(start,end);
			System.out.println("\nWant to keep going?");
			System.out.print("Enter Y or N: ");
			answer = input.nextLine().strip().toUpperCase();
			if(answer.equals("N")) {
				keep_asking = false;
				System.out.println("Thank you for stopping. Bye");
			}
			reset();
		}


	}
	public static void shortestPath(String w1, String w2) {
		PriorityQueue<Vertex> q = new PriorityQueue<>();
		if(!mappy.containsKey(w1) || !mappy.containsKey(w2)) {
			System.out.println("Enter valid words");
			return;
		}
		Vertex starting = mappy.get(w1);
		Vertex ending = mappy.get(w2);
		if(starting.getWord().length() != ending.getWord().length()) {
			System.out.println("Words must be the same size.");
			return;
		}
		if(starting.getWord().equals(ending.getWord())) {
			System.out.println(w1 +" -> "+w2);
			return;
		}	
		words_used.add(starting);
		words_used.add(ending);

		starting.dist(0);
		starting.pred(starting);
		q.add(starting);
		Vertex ver;


		while(!q.isEmpty()) {
			ver = q.poll();
			getNeighbors(q,ver);
		}
		if(ending.getPred() == null) {
			System.out.println("No path found");
			return;
		}

		printPath(ending, w1, w2);

	}
	public static void printPath(Vertex ver, String s1, String s2) {
		Stack<Vertex> stacky = new Stack<>();  
		while(!ver.getWord().equals(s1)) {
			stacky.add(ver);
			ver = ver.getPred();
		}
		String path = s1+" -> ";
		while(stacky.size() !=1) {
			path += stacky.pop().getWord()+" -> ";
		}
		System.out.println(path + s2);

	}
	public static void getNeighbors(PriorityQueue<Vertex>q, Vertex v) {

		char [] word = v.getWord().toCharArray();
		char[] copy = word.clone();

		for(int i = 0; i <word.length;i++) {
			for(int j = 97; j<=122; j++) {

				if(word[i] != (char)j) {
					copy[i] = (char)j;
					String w = String.valueOf(copy);

					if(mappy.containsKey(w)) {
						Vertex neighbor = mappy.get(w);
						//get the weight 
						int weight = Math.abs(word[i] - j);
						v.addEdges(new Edge(neighbor, weight));
						//relax bro
						if(relaxEdge(v, neighbor, weight)) {
							neighbor.pred(v);
							q.add(neighbor);
							words_used.add(neighbor);
						}
					}
					//change copy[i] back 
					copy[i] = word[i];
				}
			}
		}

	}
	public static boolean relaxEdge(Vertex pred, Vertex v, int w) {
		int current_dist = pred.getDist() + w;
		if (current_dist < v.getDist()) {
			v.dist(current_dist);
			return true;
		}
		return false;
	}
	public static void reset() {
		for(Vertex v: words_used) {
			v.reset();
		}
	}







}

