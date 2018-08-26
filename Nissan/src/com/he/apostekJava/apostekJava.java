package com.he.apostekJava;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

// Perform following operations on following bit array
// 0 i: In this type of query you have to find the nearest left and nearest right element from position i in the array whose value is 1.
// 1 i:  In this type of query you need to change the value at position i to 1 if its previous value is 0 or else leave it unchanged.


/*
7 4
1 0 0 0 1 0 1
0 1
0 5
1 2
0 2

Sample Output
0 4
4 6
0 4
ip
5 4
0 0 0 0 0
0 2
1 2
1 4
0 3
op
-1 -1
2 4
ip
6 2
1 0 1 0 1 1
0 0
0 4

op
-1 2
2 5
 */

public class apostekJava {
	static ArrayList<Integer> list;
	
	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);
		int n=0, q=0;
		n = scan.nextInt();
		q = scan.nextInt();
        
		if( n!=0 && q!=0) {
	        list = new ArrayList<>(n);
	        
	        for( int i = 0; i < n; i++) {
	        	list.add( scan.nextInt() );
	        }
//	        for( int i = 0; i < n; i++) {
//	        	System.out.print(list.get(i));
//	        }
//	        System.out.println();
	        
	        
	        for( int i = 0; i < q; i++ ) {
	        	int command = scan.nextInt();
	        	int index = scan.nextInt();
	//        	System.out.println();
	//        	System.out.println(command + "   " + index);
	        	if ( command == 0 )
	        		getLRSetBits(index);
	        	else if ( command == 1 )
	        		convert(index);
	        }
	        for( int i = 0; i < n; i++) {
	        	System.out.print(list.get(i));
	        }
		}
        scan.close();
	}

	private static void getLRSetBits(int index) {
		int l = -1;
		int r = -1;
		if ( index >= 0 && index < list.size() ) {
			for( int i = index-1; i >= 0; i--) {
				if (list.get(i) == 1 ) {
					l = i;
					break;
				}
			}
			for( int i = index+1; i < list.size(); i++) {
				if (list.get(i) == 1 ) {
					r = i;
					break;
				}
			}
		}
		System.out.println(l + " " + r);
	}

	private static void convert(int index) {
		if( index >= 0 && index < list.size() && list.get(index - 1) == 0 )
			list.set( index, 1 );
	}

}
