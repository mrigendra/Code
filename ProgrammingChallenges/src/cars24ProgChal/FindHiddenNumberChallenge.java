package cars24ProgChal;

import java.util.Scanner;

/**
 * 
 * @author Mrigendra
 * You are given an array  of  integers. Now there is another integer  which is unknown but you know its properties.
 * The properties of the unknown number are:
 * - It is an integer.
 * - If you subtract all the elements of the array  individually from the number  and add all the differences then it adds to x.
 * Find X
 */

/*
Test Case
1
3
1 2 3
2
 */

public class FindHiddenNumberChallenge {

	public static void main(String[] args) {
		
		Scanner s = new Scanner(System.in);
		int T = s.nextInt();
		for( int i=0; i<T; i++ ) {
			int n = s.nextInt();
			double sum = 0;
			for( int j=0; j<n; j++ ) {
				sum += s.nextInt();
			}
			double result = sum/n;
			if( result%1 == 0 && n >= 1 )
				System.out.println( Math.round(Math.round(result)) );
			else
				System.out.println( -1 );
		}
		s.close();
	}

}
