
public class GraphMatrix {
	
	int[][] adjacencyMatrix;
	int noOfVertices;
	boolean directed;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public GraphMatrix(int noOfVertices, boolean directed){
		this.noOfVertices = noOfVertices;
		this.directed = directed;
		adjacencyMatrix = new int[noOfVertices][noOfVertices];
		for(int i = 0; i < noOfVertices; i++){	
			for(int j = 0; j < noOfVertices; j++){	
				if(i==j)
					adjacencyMatrix[i][j] = 0;
				else
				adjacencyMatrix[i][j] = (int) Double.POSITIVE_INFINITY;
			}
		}
		/*for(int i = 0; i< noOfVertices; i++){
			for(int j =0; j<)
		}*/
	}
	
	public void addEdge(int src, int dest, int weight){
		adjacencyMatrix[src][dest] = weight;
		if(!directed)
			adjacencyMatrix[dest][src] = weight;
	}
	
	public int getEdge(int src, int dest){
		return adjacencyMatrix[src][dest];
	} 

}
