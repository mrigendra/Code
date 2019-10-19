package cars24ProgChal;

import java.util.*;
/**
 * 
 * @author Mrigendra
 * Return the nodes from the tree which will form the biggest cycle when connected.
 * Similar to problem of computing largest path in a tree.
 */
/*
7
1 2
1 3
2 4
2 5
3 6
3 7

 */
public class LargestCycleInTreeChallenge {

	public static HashMap<Integer, Integer> m = new HashMap<>();
	public static HashMap<Integer, ArrayList<Integer>> tree = new HashMap<>();
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		
		int n = s.nextInt();
		if( n == 2 ) {
			System.out.println( s.nextInt() + " " + s.nextInt() );
		}
		else if( n > 2 ) {
			int n1, n2; 
			Pair[] p = new Pair[n];
			
			for( int i=0; i<n-1; i++ ) {
				n1 = s.nextInt();
				n2 = s.nextInt();
				p[i] = new Pair(n1, n2);
				
				if( m.containsKey(n1)) {
					m.put(n1, m.get(n1)+1);
				} else {
					m.put(n1, 1);
				}
				
				if( m.containsKey(n2)) {
					m.put(n2, m.get(n2)+1);
				} else {
					m.put(n2, 1);
				}
			}
			
			Iterator<Integer> iter = m.keySet().iterator();
			int rootNode = iter.next();
			
			while( iter.hasNext() ) {
				int key = iter.next();
				if( m.get( key ) != 1 && m.get(rootNode) > m.get( key ) ) {
					rootNode = key;
				}
			}
//			System.out.println(rootNode);
			
		}
		s.close();
	}
}

class Pair{
    int start;
    int end;
    
    Pair(int start, int end){
        this.start = start;
        this.end = end;
    }
}

class Tree {
	
}

class Node {
    private final String data;
    private List<Node> children = new ArrayList<>();

    public Node(String data){
        this.data = data;
    }

    public void addChild(Node node){
        children.add(node);
    }

    public List<Node> getChildren(){
        return children;
    }

    public String getData(){
        return data;
    }
}

