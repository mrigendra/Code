package com.he.practiceCode;

import java.util.HashMap;

public class BarclaySpecialBit {

	public static void main(String[] args) {
		
		/*
		 * Problem 1: Check if the Integer is Special Bit number.
		 * i.e. Number which have two consecutive '1's eg 6(110), 7(111) etc
		 */
		Integer in = 11;
		while( in > 1 ) {
			if( in%2 != 0 && (in >> 1)%2 != 0)
				System.out.println( true );
			in = in >> 1;
		}
		
		/*
		 * Problem 2: Compute the smallest string possible by removing all instances of any one char
		 * String should be the smallest one possible.
		 */
		String str = "";
		HashMap<Character, Integer> map = new HashMap<>();
		char maxChar = 'a';
		int maxCount = 0, newCount = 0;
		for( char c : str.toCharArray() ) {
			if( map.containsKey(c) ) {
				map.put(c, map.get(c) + 1 );
				
			}
			else {
				map.put(c, 1);
			}
			newCount = map.get(c);
			if( newCount > maxCount ) {
				maxCount = newCount;
				maxChar = c;
			}
		}
		
		/*
		 * Problem 3: Calculate total of substrings of S containing 
		 * both Strings A and B 
		 */
		
		String[] newStr = str.split("c");
		String resultStr = "";
		for( String tStr : newStr ) {
			resultStr.concat( tStr );
		}
		System.out.println( resultStr );
		
		
		String a = "c", b = "", s = "";
		int totalCount = 0;
		for( int i = 0; i < a.length(); i++ ) {
			for( int j=i+1; j < b.length(); j++ ) {
				String newSubStr =  s.substring(i, j);
				if (  newSubStr.contains(a) && newSubStr.contains(b)  )
					totalCount++;
			}
		}
		System.out.println(totalCount);

	}

}

