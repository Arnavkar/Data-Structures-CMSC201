/* Arnav Shirodkar
CMSC 201
as9086@bard.edu
23/4/20

Assignment: Build a frequency counter and test Zipf’s law using test files. Also test the efficieny of a Sequential Search Symbol Table
with a Self-Organizing Symbol Table that puts the most recently accessed entry at the front of the queue.

Collaboration Statement: I worked on this myself apart from the implementation of mergesort for linked lists. For that I went online to
coding simplified and watched some videos to break down how the various functions to accomplish mergesort on a linked list would work before
implementing it into my own code for both the SequentialsearchST and the Selforganizing ST.
*/

import edu.princeton.cs.algs4.*;
public class Zipf {
    private Zipf() {
    }

    public static void main(String[] args) {
        Stopwatch timer = new Stopwatch();
        In in = new In("dna3.txt");

        int distinct = 0, words = 0;
        int minlen = 1; //set minimum length to one (includes hyphens and dashes etc.)
        //SequentialSearchST<String, Integer> st = new SequentialSearchST<>();
        SelfOrganizingST<String, Integer> st = new SelfOrganizingST<>();

        // compute frequency counts
        while (!in.isEmpty()) {
            String key = in.readString();
            if (key.length() < minlen) continue;
            if (st.contains(key)) {
                st.put(key, st.get(key) + 1);
            } else {
                st.put(key, 1);
            }
        }
        StdOut.println(timer.elapsedTime()); // find elapsed time after the linked list has been fully constructed

        distinct = st.size(); //Checks for distinct words by calling st.size rather than adding as a counter
        for (String word : st.keys()) {
            words = words + st.get(word); // checks for words by using the iterable function
        }
        StdOut.println("distinct = " + distinct);
        StdOut.println("words    = " + words);
        StdOut.println();
        st.mergesort(); //use mergesort to sort the linked list

        int count = 0;
        for (String word : st.keys()) {
           StdOut.println(word + "/" + st.get(word));
            count++;
            if (count == 50) break; // print the top 50 words
        }
    }
}



