package com.he.apostekJava;

import java.util.Scanner;
// Best Index: i such that S[i] is the max number where
// S[i] = a[i]+(a[i+1]+a[i+2])+(a[i+3]+a[i+4]+a[i+5])+...

/*
 6
-3 2 3 -4 3 1
->3
10
2 1 3 9 2 4 -10 -9 1 3
->9
5
1 3 1 2 5
->8
 */

public class arraySpecialSum {

	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
		int n = 0; 
		if( scan.hasNextLine()) {
			n = scan.nextInt();
		}
		if( n!=0) {
			double max = Double.MIN_VALUE;
			double[] newSum = new double[n];
			double[] in = new double[n];
			for( int i=0; i<n; i++) {
				in[i] = scan.nextInt();
			}
			
			int i,j,k,curr;
			for( i=n-1; i>=0; i--) {
				// loop through nth element to 1st element
				// curr is variable to keep track of the index being added to the sum
				// k is the size of the subset which increments as 2,3,4,...
				curr = i;
				newSum[i] = in[i];
				for( k=2; curr+k < n; k++) {
					for( j=curr+1; j<=curr+k; j++) {
						newSum[i] += in[j];
					}
					curr=j-1;
				}
				if ( newSum[i] > max )
					max = newSum[i];
			}
			System.out.println((long)(max));
		}
	}
}
