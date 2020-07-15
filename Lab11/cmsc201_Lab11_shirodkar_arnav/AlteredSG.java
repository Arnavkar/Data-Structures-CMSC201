/*      Arnav Shirodkar
        CMSC 201 – Lab 11
        as9086@bard.edu
        15/5/20

        Assignment: Using the java files provided and the IMDB database, answer the following
        questions about Kevin Bacon’s Hollywood presence and continue exploring the data set as much as possible.

        Collaboration Statement: I worked on this alone

*/
import edu.princeton.cs.algs4.*;
public class AlteredSG {
    private ST<String, Integer> st;  // string -> index
    public static ST<Integer, Integer> tracker;// track actor index and if it has been checked
    private ST<String,Integer> costar; // st containing co stars
    private String[] keys;           // index  -> string
    private Graph graph;             // the underlying graph
    private ST<String, Integer> maxLog;

    public AlteredSG(String filename, String delimiter) {
        st = new ST<String, Integer>();
        maxLog = new ST<String, Integer>();
        tracker = new ST<Integer, Integer>();

        // First pass builds the index by reading strings to associate
        // distinct strings with an index
        In in = new In(filename);
        // while (in.hasNextLine()) {
        while (!in.isEmpty()) {
            String[] a = in.readLine().split(delimiter);
            st.put(a[0],st.size()); // put movie name and index into st separately
            for (int i = 1; i < a.length; i++) {
                if (!st.contains(a[i])){
                    st.put(a[i], st.size());
                    tracker.put(st.size()-1,0);
                    // add actor index to ST and store value = 0 as unchecked
                    // (-1 since put was executed first, increasing size by 1)
                }  
            }
        }

        // inverted index to get string keys in an array
        keys = new String[st.size()];
        for (String name : st.keys()) {
            keys[st.get(name)] = name;
        }

        // second pass builds the graph by connecting first vertex on each
        // line to all others
        graph = new Graph(st.size());
        in = new In(filename);
        while (in.hasNextLine()) {
            String[] a = in.readLine().split(delimiter);
            int v = st.get(a[0]);
            for (int i = 1; i < a.length; i++) {
                int w = st.get(a[i]);
                graph.addEdge(v, w);
            }
        }
    }

    public boolean contains(String s) {
        return st.contains(s);
    }

    @Deprecated
    public int index(String s) {
        return st.get(s);
    }

    public int indexOf(String s) {
        return st.get(s);
    }

    @Deprecated
    public String name(int v) {
        validateVertex(v);
        return keys[v];
    }

    public String nameOf(int v) {
        validateVertex(v);
        return keys[v];
    }

    @Deprecated
    public Graph G() {
        return graph;
    }

    public Graph graph() {
        return graph;
    }

    // throw an IllegalArgumentException unless {@code 0 <= v < V}
    private void validateVertex(int v) {
        int V = graph.V();
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public void storeCoStarMax(int actor){ // find costar most frequently worked with
        costar = new ST<String, Integer>();
        int s = actor;
        for(int v : graph.adj(s)){ // check actor's adjacent movies
            for(int x : graph.adj(v)){ //check the actor's adjacent to these's movies
                if(!costar.contains(this.name(x)) && (int)tracker.get(x) == 0 && x != actor){
                    //don't repeat for names already in the ST, checked actors and the argument actor themselves
                    costar.put(this.name(x),1);
                } else if (costar.contains(this.name(x))){
                    costar.put(this.name(x),costar.get(this.name(x))+1); //update count
                }
            }
        }
        tracker.put(actor,1); // mark this actor as checked

        int max = 1; // rationale in line 126
        String pal = " ";
        for(String x : costar){
            if(costar.get(x) > max) { //
                max = costar.get(x); //store value for the most number of times worked with one co-star
                pal = x; // store their name
            }
        }
        if(max>1) { // max is very likely to be >1 in this large set, so only add to maxLog if max is infact > 1
            pal = this.nameOf(actor) + "/" + pal; //concatenate names
            maxLog.put(pal, max);
        }
    }

    public void findMaxPair(){ //find pair that worked together most frequently
    	int max = 0;
    	for (String x : maxLog){
    		if(maxLog.get(x)>max){
    			max = maxLog.get(x); // update max
    		}
    	}

    	StdOut.println(max);

    	for (String x : maxLog) {
            if (maxLog.get(x) == max) {
                StdOut.println(x); // print names for all keys that hold max, in case there is more than one pair with max value
            }
        }

    }

    public static void main(String[] args) {
        //String filename  = args[0];
        //String delimiter = args[1];
        AlteredSG sg = new AlteredSG("movies2017-309K.txt", "==");
        Graph graph = sg.graph();

            for(Integer x : tracker){
            	sg.storeCoStarMax(x);
            }

            sg.findMaxPair();

            /*if (sg.contains(source)) {
               StdOut.println( sg.findCoStars(source));
                int s = sg.index(source); //find the index associated with our input
                for (int v : graph.adj(s)) {
                    StdOut.println("   " + sg.name(v));
                }
            }
                else {
                StdOut.println("input not contain '" + source + "'");
            }*/
    }
}

