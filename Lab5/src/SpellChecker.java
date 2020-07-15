/*
Arnav Shirodkar CMSC201:Data Structures Lab 5
Email: as9086@bard.edu
Date: 12/03/2020

Assignment Description: Create a spellchecker program that can check if words supplied are
present in a dictionary supplied by us. Create a jumble program that is able to take jumbled words
as arguments and return the actual word by searching for it from the dictionary. Explore different methods of
doing so

Collaboration Statement: I got help from Tina while completing the lab, specifically to
 fix a bug in my Jumble class where the perm2 method had == instead of .equals()
*/

import edu.princeton.cs.algs4.*;

public class SpellChecker{
    String[] words;

    public SpellChecker(String dictFile) {
        words = In.readStrings(dictFile);
    }

    public SpellChecker() {
        this("words");
    }

    public boolean check(String word){
        // return scheck(word);
        return bcheck(word, 0, words.length-1);
     }
         
    private boolean scheck(String word){
    	for (int i =0; i<words.length ;i++){
    		if(words[i].equals(word)){ return true; }
    	}
        return false;
    }

    
    private boolean bcheck(String word, int low, int high){
    	//low = 0 and high = words.length-1

    	int lo = low;
    	int hi = high;

    	while (lo <= hi) {
            // Key is in a[lo..hi] or not present.
            int mid = lo + (hi - lo) / 2;
            int cmp = word.compareTo(words[mid]);

            if(cmp<0) {
            	hi = mid -1;
            } else if (cmp>0) {
            	lo = mid+1;
            } else if(cmp == 0){
            	return true;
            }
        }
        return false;
	}

	public int dictCount(){
        return words.length;
    }

    public String getWord(int i){
        return words[i];
    }

    public static void main(String[] args){
        String[] testWords = {"beard", "jackalope", "liger", "zebra","woodwork", "bob" };
        In in = new In ("words");
        SpellChecker sp = new SpellChecker("words");
        // if (args.length > 0) testWords = args;
        for (String word:testWords){
            if (sp.check(word)){
                StdOut.println(word);
            }
        }
    }
}
