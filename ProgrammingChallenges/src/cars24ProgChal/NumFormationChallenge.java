package cars24ProgChal;

import java.util.*;
/**
 * 
 * @author Mrigendra
 * How many k-digit numbers can be formed using N digits in the array. Numbers can not start with 0.
 * like 001, 010 is not allowed but 100 is fine.
 */
/*
5
1 1 0 1 0
3

 */
public class NumFormationChallenge {

	public static HashMap<Integer, Double> cache = new HashMap<>();
	
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
		// Read <N, Array, K>
		int N = s.nextInt();
		int A[] = new int[N];
		for( int i=0; i<N; i++ ) {
			A[i] = s.nextInt();
		}
		int K = s.nextInt();
		double result = computePossibleNumbers(N, K, A) % 720720;
		// double to long then long to integer
		System.out.println( Math.round( Math.round(result) ) );
		s.close();
	}

	/**
	 * 
	 * @param n length of array
	 * @param k number of digit in the number to be made using elements in array A
	 * @param A array with integers 0-9
	 * @return
	 */
	private static double computePossibleNumbers(int n, int k, int[] A) {
		double possibleNumbers = 0;
		if( n >= k) {
			// nCr = n!/(r!*(n-r)!)
			for( int i=0; i<n-k+1; i++ ) {
				if( A[i] == 0 ) {
					possibleNumbers -= computeComb( n-(i+1), k-1 );
				}
			}
			possibleNumbers += computeComb( n, k );
			
		}
		return possibleNumbers;
	}

	/**
	 * Compute nCk
	 * @param n
	 * @param k
	 * @return combinatorial result of nCk
	 */
	private static double computeComb(int n, int k) {
		return fact(n)/(fact(k) * fact(n-k));
	}

	/**
	 * Compute Factorial
	 * @param n
	 * @return
	 */
	private static double fact(int n) {
		if( n == 0 )
			return 1;
		else if ( cache.containsKey(n) ) {
			return cache.get(n);
		}
		else {
			cache.put( n, n*fact(n-1) );
			return cache.get(n);
		}
	}

}
