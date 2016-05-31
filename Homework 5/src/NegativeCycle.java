import java.util.Scanner;

public class NegativeCycle {
	
	static GraphMatrix  graph;
	static int noOfVertices;
	static Scanner s = new Scanner(System.in);
	static int dist[];
	static int parent[];
	static int src1 = -1, src2;
	static int vertices[];

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		getInput();
		System.out.println(hasNegativeEdge());
	}
	
	public static void getInput(){
		noOfVertices = s.nextInt();
		int noOfEdges = s.nextInt();
		graph = new GraphMatrix(noOfVertices, true);
		for(int  i= 0; i<noOfEdges; i++){
			int sc = s.nextInt(),ds = s.nextInt(), wt = s.nextInt();
			if(wt < 0 && src1 == -1 )
				src1 = ds;
			else if(wt < 0)
				src2 = ds;
			graph.addEdge(sc, ds, wt);
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
		String op = "";
		dist = new int[noOfVertices];
		boolean mark[] = new boolean[noOfVertices];
		int next=src1, min = Integer.MAX_VALUE;
		for(int i = 0; i < noOfVertices; i++){			
			dist[i] = graph.getEdge(src1, i);
		}
		mark[src1] = true;
		for(int i = 0; i< noOfVertices-1; i++){
			min = Integer.MAX_VALUE;
			for(int k =0; k < noOfVertices; k++){
				if(dist[k] < min && !mark[k]){
					min = dist[k];
					next = k;
				}
			}
			mark[next] = true;
			for(int j = 0; j<noOfVertices; j++){
				int disti = min;
				int distij = graph.getEdge(next, j);
				if(!(disti == Integer.MAX_VALUE || distij == Integer.MAX_VALUE))
					if( dist[j] > disti + distij){
						dist[j] = disti + distij;
				}
			}
		}
		if(dist[src1] < 0)
			op += "YES";
		else
			op+="NO";
		System.out.println(dist[src1]);
		System.out.println(src2);
		next = src2;
		mark = new boolean[noOfVertices];
		for(int i = 0; i < noOfVertices; i++){			
			dist[i] = graph.getEdge(src2, i);
			System.out.println(dist[i]);
		}
		mark[src2] = true;
		for(int i = 0; i< noOfVertices-1; i++){
			min = Integer.MAX_VALUE;
			for(int k =0; k < noOfVertices; k++){
				if(dist[k] < min && !mark[k]){
					min = dist[k];
					next = k;
				}
			}
			mark[next] = true;
			for(int j = 0; j<noOfVertices; j++){
				int disti = min;
				int distij = graph.getEdge(next, j);
				if(!(disti == Integer.MAX_VALUE || distij == Integer.MAX_VALUE))
					if( dist[j] > disti + distij){
						dist[j] = disti + distij;
						System.out.println(dist[j]);
				}
			}
		}
		if(dist[src2] < 0)			
			op += "YES";
		else
			op+="NO";
		System.out.println(dist[src2]);
		return op;
	}
}