
public class GraphMatrix {
	
	Double[][] adjacencyMatrix;
	int noOfVertices;
	boolean directed;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public GraphMatrix(int noOfVertices, boolean directed){
		this.noOfVertices = noOfVertices;
		this.directed = directed;
		adjacencyMatrix = new Double[noOfVertices][noOfVertices];
		for(int i = 0; i < noOfVertices; i++){	
			for(int j = 0; j < noOfVertices; j++){	
				if(i==j)
					adjacencyMatrix[i][j] = 0.0;
				else
				adjacencyMatrix[i][j] = Double.MAX_VALUE;
			}
		}
		/*for(int i = 0; i< noOfVertices; i++){
			for(int j =0; j<)
		}*/
	}
	
	public void addEdge(int src, int dest, double f){
		adjacencyMatrix[src][dest] = f;
		if(!directed)
			adjacencyMatrix[dest][src] = f;
	}
	
	public Double getEdge(int src, int dest){
		return adjacencyMatrix[src][dest];
	} 

}
