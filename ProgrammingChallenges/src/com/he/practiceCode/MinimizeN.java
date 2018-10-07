package com.he.practiceCode;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;

/*
 * Given a number n, count minimum steps to minimize it to 1 according to the following criteria:
 *	If n % 2 == 0 then n = n/2.
 *	If n % 3 == 0 then n = n/3.
 *	n--.
 *	===================================================================================== 
 *  If n = a*b; n = max(a,b) 
 *  else n--
 */

public class MinimizeN {
	static Map<Integer, Long> cache1 = new HashMap<>();
	static Map<Integer, Long> cache2 = new HashMap<>();

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int num = scan.nextInt();
		
		System.out.println( "Number is: " + num);
		System.out.println( reduceF1(num) );
		System.out.println( reduceF2(num) );
		
		System.out.println("=================================================================");
		// Print the Map
//		Iterator<Map.Entry<Integer, Long>> iter = cache1.entrySet().iterator();
//		iter.forEachRemaining( o -> System.out.println( o.getKey() + " " + o.getValue() ));
//		System.out.println("=================================================================");
//		iter = cache2.entrySet().iterator();
//		iter.forEachRemaining( o -> System.out.println( o.getKey() + " " + o.getValue() ));
		
		scan.close();

	}

	private static long reduceF2(int n) {
		if ( n <= 0 )
			return -1;
		else if ( n == 1 )
			// reducing n to 0
			return 1;
		else if ( cache2.containsKey(n) )
			return cache2.get(n);
		
		long minSteps = reduceF2( n-1 );
		minSteps = Math.min( minSteps, factor(n) );
		
		cache2.put( n, minSteps+1 );
		return cache2.get( n );
	}

	private static long factor(int n) {
		for( int i = 2; i <= Math.sqrt(n); i++ )
			if( n%i == 0 )
				return n/i;
		return Long.MAX_VALUE;
	}

	private static long reduceF1(int n) {
		if ( n <= 0 )
			return -1;
		else if ( n == 1 )
			return 0;
		else if ( cache1.containsKey(n) )
			return cache1.get(n);
		
		long minSteps = reduceF1( n-1 );
		if ( n%2 == 0 )
			minSteps = Math.min(minSteps, reduceF1(n/2));
		if ( n%3 == 0 )
			minSteps = Math.min(minSteps, reduceF1(n/3));
		
		cache1.put( n, minSteps+1 );
		return cache1.get( n );
	}

}
