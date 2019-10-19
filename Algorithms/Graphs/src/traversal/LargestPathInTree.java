package traversal;

import java.util.HashSet;
import java.util.Set;

public class LargestPathInTree<T> extends Graph<T> {

	public static void main(String[] args) {
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
