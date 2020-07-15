
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
import java.util.Arrays;

public class Jumble{

	public static void perm1(String s) {
		perm1("", s);
	}

	private static void perm1(String prefix, String s) {
		In in = new In ("words");
		SpellChecker sp = new SpellChecker("words");
		int n = s.length();
		if (n == 0){
			if(sp.check(prefix)){ StdOut.println(prefix); }
		}

		else {
			for (int i = 0; i < n; i++)
				perm1(prefix + s.charAt(i), s.substring(0, i) + s.substring(i + 1, n));
		}
	}

	private static void perm2(String s){
		In in = new In ("words");
		SpellChecker sp = new SpellChecker("words");

		char[] temp = s.toCharArray();
		Arrays.sort(temp);
		String arg = new String(temp);

		for (int i=0; i< sp.dictCount();i++) {
			char[] chars = sp.getWord(i).toCharArray();
			Arrays.sort(chars);
			String dictWord = new String(chars);
			if(arg.equals(dictWord)){
				StdOut.println(sp.getWord(i));
			}
		}
	}

	public static void main(String [] args){
		String[] testwords = {"nelir","gurpe","notair","bahcle"};
        for (String word:testwords){
        	perm2(word);
        }
	}
}
	
	

