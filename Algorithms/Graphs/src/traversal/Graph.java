package traversal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Graph implementation with adjacency list
 * @author Mrigendra
 *
 * @param <T>
 */
public abstract class Graph<T> {
	Map<T, List<T>> adjLst;
	HashSet<T> visitedSet;
	int numOfVertices = 0;
	int expectedAdjListSize; 
	
	Graph() {
		adjLst = new HashMap<>();
		visitedSet = new HashSet<>();
	}
	
	/**
	 * Undirected edge adds edges both ways
	 * @param n1 start Vertex
	 * @param n2 end Vertex
	 */
	public void addUndirectedEdge(T n1, T n2) {
		addEdge(n1, n2);
		addEdge(n2, n1);
	}
	
	/**
	 * Adding only one way edge.
	 * @param n1
	 * @param n2
	 */
	public void addDirectedEdge(T n1, T n2) {
		addEdge(n1, n2);
	}
	
	/**
	 * Add vertex to the graph and ending vertex to the adjacency list. 
	 * @param n1
	 * @param n2
	 */
	private void addEdge(T n1, T n2) {
		// If graph already contains the starting vertex
		if( adjLst.containsKey(n1) ) {
			if( adjLst.get(n1).contains(n2) )
				// If graph already has the adjacent vertex
				System.out.println("Edge already inserted.");
			else
				adjLst.get(n1).add(n2);
		} else {
			ArrayList<T> lst = new ArrayList<>();
			lst.add(n2);
			adjLst.put(n1, lst);
			numOfVertices ++;
		}
	}
	
	/**
	 * Just to add a node in to the graph.
	 * @param n1
	 */
	public void addNode(T n1) {
		if( adjLst.containsKey(n1) ) {
			System.out.println("Node already inserted.");
		} else {
			ArrayList<T> lst = new ArrayList<>();
			adjLst.put(n1, lst);
			numOfVertices ++;
		}
	}

	public void printNode(T node) {
		System.out.println(node);
	}
	
	public void printAdjList() {
		for( T el : adjLst.keySet() ) {
			System.out.println(el + "-->" + adjLst.get(el));
		}
		System.out.println("End of List.\n");
	}
	
	abstract public void printGraph(T node); 
}

/**
 * 
 * @param <T> data-type of the data in the node
 */
class Node<T> {
	T data;
	Node(T data) {
		this.data = data;
	}
}


