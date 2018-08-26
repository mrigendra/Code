package com.he.practiceCode;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/*
 * Calculate max average of integers in an array of k numbers.
 * Array acts as an stack and can convert of queue one time
 * to give integers from the end of the array.
 */

public class BitFlip {

	public static void main(String[] args) throws IOException {
		Scanner s = new Scanner(System.in);
		int n = s.nextInt();

		Double[] numbers = new Double[n];
		ArrayList<Double> num = new ArrayList<>(n);
		ArrayList<Double> avg = new ArrayList<>(n);
		for( int i=0; i<n; i++) {
			numbers[i] = s.nextDouble();
			num.add(numbers[i]);
		}
		System.out.println(num);
//		Collections.sort(num);

		double sum = 0;
		for(int i=0; i<n; i++) {
			sum += num.get(i);
			avg.add(sum/(i+1));
		}

		System.out.println(avg);
		int q = s.nextInt();
		double avg1;
		System.out.println(q);
		int j = 0;
		for( int i=0; i<q; i++) {
			avg1 = s.nextDouble();
			if ( avg1 > avg.get(n-1)  ) {
				System.out.println(n);;
				continue;
			}
			for(j=0; j<n; j++) {
				if ( avg.get(j) >= avg1 ){
					System.out.println("...." + (j));
					break;
				}
			}
			if( j==n ){
				System.out.println("...." + n);
			}
		}

		s.close();
	}

}
