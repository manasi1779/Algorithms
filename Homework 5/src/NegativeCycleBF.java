import java.util.Scanner;

public class NegativeCycleBF {

	static int noOfVertices, noOfEdges;
	static Graph<Integer> graph;
	static Scanner s = new Scanner(System.in);
	
	public static void main(String[] args) {
		getInput();
		System.out.println(hasNegativeEdge());
	}
	
	public static void getInput(){
		noOfVertices = s.nextInt();
		noOfEdges = s.nextInt();
		graph = new Graph<Integer>(noOfVertices, noOfEdges, true);
		for(int  i= 0; i<noOfEdges; i++){
			graph.addEdge(s.nextInt(), s.nextInt(), s.nextInt());
		}		
		/*Vertex[] arr = graph.vertices;
		for(int i = 0; i<arr.length; i++){
			List<Edge> connected = arr[i].connectedTo;
			for(int j = 0; j < connected.size; j++ ){
				System.out.println(arr[i].ID+ " connected to "+connected.get(j).ID);
			}
		}*/
	}
	

	public static String hasNegativeEdge(){
		boolean output = false;
		int dist[] = new int[noOfVertices];
		for(int i = 0; i < noOfVertices; i++){			
			dist[i] = Integer.MAX_VALUE;
		}
		Vertex[] arr = graph.vertices;
		Edge[] edges = graph.edges;
		System.out.println(noOfVertices);
		System.out.println(noOfEdges);
		dist[(int) arr[0].ID] = 0;
		for(int i = 1; i< noOfVertices; i++){
			for(int j = 0; j <noOfEdges;  j++){
				int src = edges[j].src, dest = edges[j].ID;
				if(dist[dest] > dist[src] + edges[j].weight){
					dist[dest] = dist[src] + edges[j].weight;
				}
				System.out.println("distance from "+src +" to "+ dest+" "+dist[dest]);
			}
		}
		for(int j = 0; j <noOfEdges;  j++){
			int src = edges[j].src, dest = edges[j].ID;
			if(dist[dest] > dist[src] + edges[j].weight){
				return "YES";
			}
		}
		return "NO";
	}
}
