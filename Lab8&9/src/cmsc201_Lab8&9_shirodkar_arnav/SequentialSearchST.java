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
public class SequentialSearchST<Key,Value extends Comparable<Value>> {
    // we extend comparable for value to use compareTo in the mergesort implementation
    private Node first;
    int N;

    SequentialSearchST() {
        N = 0;
        first = null;
    }

    private class Node {
        Key key;
        Value val;
        Node next;

        public Node(Key key, Value val, Node next) {
            this.key = key;
            this.val = val;
            this.next = next;
        }
    }

    public void put(Key key, Value val) { // Sequential put
        if (isEmpty()) { // create first Node,
            first = new Node(key, val, null);
            N = 1;
        }

        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) { //if search hit
                x.val = val; //overwrite value
                return;
            }
        }
        first = new Node(key, val, first);
        N++; // create new node in the list
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.val;
            }
        }
        return null;
    }

    public void delete(Key key) { //somehow could not implement this with a for loop, kept getting null errors
        if (key.equals(first.key)) {
            first = first.next;
            N--; //case for deleting first key
        }

        Node current = first;
        Node previous = null;
        while (current != null && key.equals(current.key) == false) { //move forward till search hit
            previous = current; //move both pointers down the list together
            current = current.next;
        }

        if (current == null) return; // if hit the end of the list, stop
        previous.next = current.next; //if the while loop exits on a search hit, make the current node inaccessible
        N--; //decrement N
    }

    public boolean contains(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return true;
            }
        }
        return false;
    }

    public boolean isEmpty() {
        if (size() == 0) {
            return true;
        }
        return false;
    }

    public int size() {
        return N;
    }

    Iterable<Key> keys() {
        Queue<Key> q = new Queue<Key>();
        for (Node x = first; x != null; x = x.next) {
            q.enqueue(x.key);
        }
        return q;
    }

    private Node merge(Node x, Node y) { //X and Y may not be single nodes, but the heads of sublists
        Node temp = new Node(null, null, null); //create temp Node
        Node finalList = temp; //create reference to temp

        while (x != null && y != null) {
            if (x.val.compareTo(y.val) > 0) { // compares the first nodes of the list and add them to the back of temp accordingly
                temp.next = x;
                x = x.next; //if x or y moves behind temp, move the respective point down by one
            } else {
                temp.next = y;
                y = y.next;
            }
            temp = temp.next;
        }

        if (x == null) {
            temp.next = y;
        } else if (y == null) { // if any of the sub lists return null/are empty,
            temp.next = x; // attach the rest of the sublist at the back
        }

        return finalList.next; // return the head of the final list

    }

    private Node findMiddle(Node x) {
        if (x == null) return null;
        Node slow = x;
        Node fast = x.next; // create two pointers that move down the list such that one moves twice as quick

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next; //when the fast pointer reaches the end, the slow pointer will be halfway through
        }
        return slow;
    }

    private Node mergesort(Node node) {
        if (node == null || node.next == null) {
            return node; //case for empty list/N = 1
        }

        Node middle = findMiddle(node);
        Node secondList = middle.next;
        middle.next = null; // delete the link between middle and secondList
        return merge(mergesort(node), mergesort(secondList)); //recursively sort and merge
    }

    public void mergesort() {
        this.first = this.mergesort(this.first);
    }
//apply mergesort by taking the head of the presorted list, reassign first once mergesort is complete
}
