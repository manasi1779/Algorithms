public class Graph{
	
	Vertex[] vertices;
	Edge[] edges;
	int edgesCount = 0;
	int current;
	boolean directed;
	
	public Graph(int noOfVertices, int noOfEdges, boolean directed){
		vertices = new Vertex[noOfVertices];
		edges = new Edge[noOfEdges];
		current = 0;
		directed = true;
	}

	//Not used while adding edges 
	public void addVertex(int value){
		vertices[current] = new Vertex(value);
		current++;
	}
	
	public void addEdge(int source, int dest, int weight){
		if(vertices[source] == null)
			vertices[source] = new Vertex(source);
		if(vertices[dest] == null)
			vertices[dest] = new Vertex(dest);
		edges[edgesCount++] = new Edge(source, dest, weight);
		Vertex src = vertices[source], dst = vertices[dest];
		src.addNeighbour(dest, weight);
		if(!directed)
			dst.addNeighbour(source, weight);		
	}
}


class Vertex{
	int ID;
	List<Edge> connectedTo;
	
	public Vertex(int ID) {
		this.ID = ID;
		connectedTo = new List<Edge>();
	}

	public void addNeighbour(int neighbour, int wt){
		connectedTo.add(new Edge(ID, neighbour, wt));
	}
	
	public List<Edge> getConnections(){
		return connectedTo;
	}
}

class Edge{
	int src;
	int ID;
	int weight;
	
	public Edge(int src, int ID, int wt){
		this.src = src;
		this.ID = ID;
		weight = wt;
	}
}

class List<T>{
	
	Node<T> front;
	int size;
	
	public void add(T value){
		if(front == null){
			front  = new Node<T>(value);
		}
		else{
			Node<T> temp = front; 
			while(temp.hasNext()){
				temp = temp.next;
			}
			temp.next = new Node<T>(value);
		}
		size++;
	}	
	
	public T get(int index){
		Node<T> temp = front; 
		for(int i =0; i < index; i++){
			temp = temp.next;
		}
		return temp.s;
	}
	
	public void remove(int index){
		Node<T> temp = front; 
		if(index==0){
			front = front.next;
		}
		else{
			for(int i =0; i < index-1; i++){
				temp = temp.next;
			}
			temp.next = temp.next.next;
		}
		size--;
	}
}

class Node<T>{
	T s;
	Node<T> next;
	
	public Node(T t){
		s=t;
	}	
	
	public boolean hasNext(){
		return next!=null;
	}
}