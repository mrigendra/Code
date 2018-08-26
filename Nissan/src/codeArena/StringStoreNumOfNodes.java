package codeArena;

import java.util.*;


class Words {
    String word;
    int len;
    Words(String word, int len) {
        this.word = word;
        this.len = len;
    }
}


public class StringStoreNumOfNodes {
    public static void main(String args[] ) throws Exception {
        Scanner s = new Scanner(System.in);

        int n = s.nextInt();
        s.nextLine();
        ArrayList<Words> w = new ArrayList<>(n);
        
        for( int i=0; i<n; i++) {
            String tWord = s.nextLine();
            w.add( new Words( tWord, tWord.length() ));
        }
        Collections.sort( w, (o1, o2) -> o2.len-o1.len );
        
        int maxWordSize = w.get(0).len;
        
        ArrayList<HashSet<Character>> rows = new ArrayList<HashSet<Character>>(maxWordSize);
        for( int i=0; i<maxWordSize; i++) {
        	rows.add(i, new HashSet<Character>() );
        }
        
        System.out.println(w.size());
        
        for( Words wordOb : w ) {
        	System.out.println(wordOb.word);
            for (int j=0; j < wordOb.len; j++) {
            	rows.get(j).add( wordOb.word.charAt(j) );
            }
        }
        
        System.out.println();
        for( HashSet<Character> set : rows ) {
        	System.out.println(set);
        }
        System.out.println();
        
        long numberOfNodes = 1;
        
        for( HashSet<Character> s1 : rows ) {
            numberOfNodes += s1.size();
        }
        
        System.out.println(numberOfNodes);

    }
}

