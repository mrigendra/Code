package traversal;

import java.util.*;

/**
 * DFS Graph traversal
 * @author Mrigendra
 *
 */
public class DFS<T> extends Graph<T> {

	public static void main(String[] args) {
		DFS<Integer> g = new DFS<>();
		g.addUndirectedEdge(1, 2);
		g.addUndirectedEdge(1, 3);
		g.addUndirectedEdge(1, 4);
		g.addUndirectedEdge(2, 5);
		g.addUndirectedEdge(2, 6);
		g.addUndirectedEdge(4, 1);
		g.printAdjList();
		
		g.printGraph(4);
	}
	
	
	/**
	 * print DFS from starting node as input.
	 * @param node
	 */
	@Override
	public void printGraph(T node) {
		if( adjLst.containsKey(node) ) {
			Set<T> visitedSet = new HashSet<>();
			dfs(node, visitedSet);
		} else {
			System.out.println( "Starting Node not found: " + node );
		}
	}

	/**
	 * DFS recursive traversal with visited node set. 
	 * @param node
	 * @param visitedSet
	 */
	private void dfs(T node, Set<T> visitedSet) {
		visitedSet.add(node);
		printNode(node);
		for( T el : adjLst.get(node) ) {
			if( visitedSet.contains(el) == false )
				dfs( el, visitedSet );
		}
	}


}
