package excelforceJavaChallenge;

import java.util.*;

/**
 * Alice and Bob saw a challenging track which consists of  hurdles of variable heights . 
 * Now, Bob challenges Alice to complete this track with a given amount of jump power .
 * The rules for completing the challenge are as follows:
 * . Each time Alice jumps a hurdle, jump power  of Alice reduces by the height of the hurdle.
 * . Every even minute all the remaining hurdles having even heights will decay and their heights 
 * 	 reduce by  and every odd minute all the remaining hurdles having odd heights will decay and 
 *   their heights reduce by .
 *   
 * @author Mrigendra
 *
 */



public class ChallengingTrack {

	public static void main(String args[] ) throws Exception {
        //Scanner
        Scanner s = new Scanner(System.in);
        // Testcases
        int T = s.nextInt();
        
        for( int i=0; i<T; i++) {
        	// Input Array Count
        	int N = s.nextInt();
        	// Input Jump Power of athlete
            long P = s.nextLong();
            int currheight;
            int time = 0;
            int j=0;
            
            if( N >= 1 ) {
            	P = P-s.nextInt();
            	j++;
            }
            while( j < N && s.hasNextInt() ) {
//            for( ; j < N && P >= 0; j++ ) {
            	currheight = s.nextInt();
            	// update height of the hurdle based on time.
        		if( (currheight % 2) == 0 )
        			currheight = currheight - time++ - 1;
        		else {
        			currheight = currheight - time++;
        		}
        		
        		if( currheight < 0 ) {
        			currheight = 0;
            	}
            	// Update the height of the hurdle 
            	P = P - currheight;
            	j++;
//            	System.out.println(P);
            }
            if( P >= 0 && j == N )
            	System.out.println("Yes " + P);
            else
            	System.out.println("No");
        }
        s.close();
    }
}
/*
3
6 23
8 6 1 7 6 5
3 6
5 2 7
3 100
5 2 7


 */


