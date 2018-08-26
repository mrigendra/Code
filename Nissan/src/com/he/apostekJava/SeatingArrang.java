package com.he.apostekJava;

import java.util.HashMap;
import java.util.PriorityQueue;

// N chairs in a row. Each person occupies middle of largest largest empty continuous sequence of unoccupied chairs and occupies the middle position. 
// They have a preference indicating whether they would choose the left or the right chair if there are two chairs at the middle to chose from 
// (else the preference does not matter, since there is only 1 chair at the center). If there are multiple largest empty sequences, then the person chooses 
// the sequence which appears first from left side. You are asked to answer Q queries. Determine which person has occupied the queried position.
// 1<= N <= 10^18
// 1<= K <= min(N,10^5)
// length(S) = K
// 

/*
5 3
RLR
5 1 2 3 4 5
Output :
2 -1 1 -1 3

Input :
3 3
RRL
3
1 3 2
Output :
2 3 1
 * 
 */

/*
 * This class keeps track of the sets of empty seats.
 * Of this always the middle seat is occupied by the new guest based on the preference.
 */
class EmptySeatSet {
	// position of left-most empty seat in this set
	long startPos;
	// number of empty eats in this set
	long emptySeats;
	
	public EmptySeatSet(long i, long n) {
		startPos = i;
		emptySeats = n;
	}
}
public class SeatingArrang {
	
	// posMap maps seat number (index of seat) -> the person number who sat on that seat  
	static HashMap<Long, Integer> posMap = new HashMap<>();
	
	// Max-Heap of empty seats Set. Comparator compares the number of empty seats in the set 
	// and the position of set in the row. 
	static PriorityQueue<EmptySeatSet> q = new PriorityQueue<EmptySeatSet>( (o1, o2) -> { 
		if( o1.emptySeats == o2.emptySeats ) {
			return (o1.startPos > o2.startPos ? 1 : -1);
		} else {
			return (o1.emptySeats > o2.emptySeats ? -1 : 1);
		}
	});

	public static void main(String[] args) {
		
         String inputString = "5 3";
         String s = "RLR";
         long[] Q = new long[] { 5, 1, 2, 3, 4, 5};
         
         String input[] = inputString.split("\\s+");
         long n = Long.parseLong(input[0]);
         int k = Integer.parseInt(input[1]);

         int[] out_ = solve(Q, k, s, n);
         for(int i_out_=0; i_out_<out_.length; i_out_++)
         	System.out.println(out_[i_out_]);
	}
	
	static int[] solve(long[] Q, int k, String s, long n){
		int res[] = new int[Q.length];
		q.add( new EmptySeatSet(0, n) );
		
		for( int i=0; i<s.length(); i++ ) {
			char c = s.charAt(i);
			update(c, i);
		}
		
        for(int i=0; i<Q.length; i++) {
        	if ( posMap.containsKey(Q[i]) ) {
        		res[i] = posMap.get(Q[i]);
        	} else {
        		res[i] = -1;
        	}
        }
        return res;
    }

	private static void update(char lr, int index) {
		
		// Get the set where the new person will sit. Provide him with a seat based on logic.
		// Reinsert the new empty seat sets created in the max-heap.
		EmptySeatSet e = q.poll();
		
		long midPos = (e.emptySeats)/2;
		if ( e.emptySeats % 2 == 0 ) {
			if ( lr == 'L' )
				midPos--;
			
			if ( midPos != 0 )
				q.add( new EmptySeatSet(e.startPos, midPos) );
			if ( midPos != e.emptySeats-1 )
				q.add( new EmptySeatSet(e.startPos + midPos + 1, e.emptySeats - midPos - 1) );
			
		} else {
			if ( e.emptySeats != 1 ) {
				q.add( new EmptySeatSet(e.startPos, midPos) );
				q.add( new EmptySeatSet(e.startPos + midPos + 1, midPos) );
			}
		}
//		System.out.println( (e.startPos + midPos + 1) + "   " + (index+1) );
		posMap.put( e.startPos + midPos + 1, index+1 );
	}

}
