/* Arnav Shirodkar
as9086@bard.edu
9/4/2018
CMSC 201: Lab6&7 – Bounding Box & Convex Hull

Assignment: Use the given code to create 2 client classes, BoundingBox.Java and ConvexHull.java as well as 2
other classes MinPQMultiway.java and Stack2.java. Amongst these classes, make sure to use composition and
inheritance at least once.

Collaboration Statement: I worked on these with help from my brother back home. I had a bug in my MinPQMultiway
class that I could not find and he taught me how to use the debugger to step through my code line by line to
eventually find it. I was also confused regarding how to implement composition into the Bounding Box class
using bags and got his help.

*/
import edu.princeton.cs.algs4.*;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MinPQMultiway<Key> implements Iterable<Key> {

    private Key[] pq;                    // store items at indices 1 to n
    private int n;                       // number of items on priority queue
    private Comparator<Key> comparator;  // optional comparator
    private int type;

    public MinPQMultiway(int initCapacity) { // constructs PQ with initial capacity
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
        type = 2;
    }

    public MinPQMultiway() { // creates empty PQ with capacity 1
        this(2,1);
    }

    public MinPQMultiway(int initCapacity, Comparator<Key> comparator) { //creates PQ with a given comparator
        this.comparator = comparator;
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
        type = 2;
    }

    public MinPQMultiway(Comparator<Key> comparator) { //initializes empty PQ with comparator
        this(2,1, comparator);
    }

    public MinPQMultiway(int tier, int initCapacity, Comparator<Key> comparator) {
        pq = (Key[]) new Object[initCapacity + 1];
        this.comparator = comparator;
        n = 0;
        type = tier;
    }

    public MinPQMultiway(int tier, int initCapacity) {
        pq = (Key[]) new Object[initCapacity + 1];
        n = 0;
        type = tier;
    }

//        public MinPQMultiway(Key[] keys) { //creates a PQ from a given array
//            n = keys.length;
//            pq = (Key[]) new Object[keys.length + 1];
//            for (int i = 0; i < n; i++)
//                pq[i+1] = keys[i];
//            for (int k = n/2; k >= 1; k--)
//                sink(k);
//            assert isMinHeap(); // test to check if heap order is satisfied
//        }

    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        return pq[1]; // first element is the smallest
    }

    // helper function to double the size of the heap array
    private void resize(int capacity) {
        assert capacity > n; //make sure new capacity larger than the old capacity
        Key[] temp = (Key[]) new Object[capacity];
        for (int i = 1; i <= n; i++) {
            temp[i] = pq[i];
        }
        pq = temp;
    }

    public void insert(Key x) {
        // double size of array if necessary
        if (n == pq.length - 1) resize(2 * pq.length);
        // add x, and percolate it up to maintain heap invariant
        pq[++n] = x;
        swim(n);
        assert isMinHeap();
    }

    public Key delMin() {
        if (isEmpty()) throw new NoSuchElementException("Priority queue underflow");
        Key min = pq[1];
        exch(1, n--);
        sink(1);
        pq[n + 1] = null;     // to avoid loitering and help with garbage collection
        if ((n > 0) && (n == (pq.length - 1) / 4)) resize(pq.length / 2);
        assert isMinHeap();
        return min;
    }

    // Functions to help ensure heap order

    private void swim(int k) {
        while (k > 1 && greater(this.findParent(k), k)) {
            exch(k, this.findParent(k));
            k = this.findParent(k);
        }
    }

    private void sink(int k) {

        while (((k*type) - (type-2)) <= n) { //do not run sink for parents without children
            int j = this.findMinChild(k);
            if (!greater(k, j)) break;
            exch(k, j);
            k = j;
        }
    }

    //Functions to help facilitate comparisons and swaps
    private int findParent(int childIndex) {
        return ((childIndex + (type-2))/type);
    }

    private int findFirstChild(int parentIndex){
        return ((parentIndex*type) - (type-2));
    }


    private int findMinChild(int parentIndex) {
        int first = this.findFirstChild(parentIndex);
        int min = first;
        for(int i = first; i < first + type; i++){
            if(i<n && greater(min,i)) min = i;
        }
        return min;
    }

    private boolean greater(int i, int j) {
        if (comparator == null) {
            return ((Comparable<Key>) pq[i]).compareTo(pq[j]) > 0;
        } else {
            return comparator.compare(pq[i], pq[j]) > 0;
        }
    }

    private void exch(int i, int j) {
        Key swap = pq[i];
        pq[i] = pq[j];
        pq[j] = swap;
    }

    // is pq[1..n] a min heap?
    private boolean isMinHeap() {

        for (int i = 1; i <= n; i++) { // check that there are no null entries within the heap before last element
            if (pq[i] == null) return false;
        }
        for (int i = n + 1; i < pq.length; i++) { // check that all entries after the last are null
            if (pq[i] != null) return false;
        }
        if (pq[0] != null) return false; //first entry should be null
        return isMinHeapOrdered(1);
    }

    // is subtree of pq[1..n] rooted at k a min heap?
    private boolean isMinHeapOrdered(int k) {
        if (k > n) return true;

        int[] childIndexes = new int [type];
        for(int i=0;i<type;i++){
            childIndexes[i] = this.findFirstChild(k) + i;
        }
        for(int i=0;i<type;i++) {
            if (childIndexes[i] <= n && greater(k, childIndexes[i])) {
                return false;
            }
        }
        boolean ordered = true;
        for(int i =0;i<type;i++){
            ordered = ordered && isMinHeapOrdered(childIndexes[i]);
        }
        return ordered;
    }

    public Iterator<Key> iterator() {
        return new HeapIterator();
    }

    private class HeapIterator implements Iterator<Key> {
        // create a new pq
        private MinPQ<Key> copy;

        // add all items to copy of heap
        // takes linear time since already in heap order so no keys move
        public HeapIterator() {
            if (comparator == null) copy = new MinPQ<Key>(size());
            else copy = new MinPQ<Key>(size(), comparator);
            for (int i = 1; i <= n; i++)
                copy.insert(pq[i]);
        }

        public boolean hasNext() {
            return !copy.isEmpty();
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

        public Key next() {
            if (!hasNext()) throw new NoSuchElementException();
            return copy.delMin();
        }
    }

    public static void main(String[] args) {
        MinPQ<String> pq = new MinPQ<String>();
        while (!StdIn.isEmpty()) {
            String item = StdIn.readString();
            if (!item.equals("-")) pq.insert(item);
            else if (!pq.isEmpty()) StdOut.print(pq.delMin() + " ");
        }
        StdOut.println("(" + pq.size() + " left on pq)");
    }

}

