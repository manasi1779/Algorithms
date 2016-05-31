/**
 * @author Manasi Bharde and Paridhi Srivastava
 * Application calculates number of edges in graph which are not used 
 * to calculate shortest path between any two vertices. 
 */

import java.util.Scanner;

public class CountUseless {
	
	static Scanner s = new Scanner(System.in);
	static Edge[] graphEdges;
	static int n, m;
	static boolean edgeUsed[][];
	static Double shortestPaths[][][];
	static GraphMatrix graph;
	
	public static void main(String[] args) {
		getInput();
		getShortestPaths();
		System.out.println(getUnused());
	}
	
	public static void getInput(){
		n = s.nextInt();
		m = s.nextInt();
		graph = new GraphMatrix(n, true);
		graphEdges = new Edge[m];
		edgeUsed = new boolean[n][n];
		shortestPaths = new Double[n][n][n+1];
		for(int i =0; i < n; i++){
			for(int j =0; j < n; j++){
				edgeUsed[i][j] = true;
				shortestPaths[i][j][0] =  Double.MAX_VALUE;
				if(i==j)
					shortestPaths[i][j][0] = Double.MIN_VALUE;
			}
		}
		for(int i =0; i < m; i++){
			int j = s.nextInt(), k =s.nextInt();
			double wt = s.nextDouble();
			graph.addEdge(j,k,wt);
			if(j==k)
				edgeUsed[j][k] = false;
			shortestPaths[j][k][0] =  wt;
		}
	}
	
	/**
	 * Floyd Warshall algorithm to calculate all source shortest path
	 */
	public static void getShortestPaths(){
		for(int k =1; k< n+1; k++){
			for(int i =0; i< n; i++){
				for(int j =0; j < n; j++){
				//	System.out.println(shortestPaths[i][k][k-1]+" "+shortestPaths[k][j][k-1]);
					if(shortestPaths[i][k-1][k-1]!=Double.MAX_VALUE && shortestPaths[k-1][j][k-1] != Double.MAX_VALUE){
						if(shortestPaths[i][j][k-1] > shortestPaths[i][k-1][k-1]+shortestPaths[k-1][j][k-1]){
							shortestPaths[i][j][k] = shortestPaths[i][k-1][k-1]+shortestPaths[k-1][j][k-1];
							if(edgeUsed[i][j] && graph.getEdge(i, j) != Double.MAX_VALUE){
								edgeUsed[i][j] = false;
							}
						}
						else{
							shortestPaths[i][j][k] = shortestPaths[i][j][k-1];
						}
					}
					else{
						shortestPaths[i][j][k] = shortestPaths[i][j][k-1];
					}
				}
			}
		}
	}
	
	/**
	 * Calculated number of unused edges from dp array
	 * @return
	 */
	public static int getUnused(){
		int num = 0;
		for(int i =0; i< n; i++){
			for(int j =0; j< n; j++){
				if(!edgeUsed[i][j]){
					num++;
				}
			}
		}
		return num;
	}
}

class Edge{
	int src;
	int ID;
	double weight;
	
	public Edge(int src, int ID, double wt){
		this.src = src;
		this.ID = ID;
		weight = wt;
	}
}