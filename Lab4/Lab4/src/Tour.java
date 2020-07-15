
/*
Arnav Shirodkar CMSC201:Data Structures Lab 4
Email: as9086@bard.edu
Date: 08/03/2020

Assignment Description: Create a Tour class that finds the length of the distance travelled between 1000 points
in a circular space (Travelling salesman problem)

Collaboration Statement: I got help from Tina while completing the lab, specifically to make sure all
my other functions were working before I implemented the heuristics
*/

import edu.princeton.cs.algs4.*;
import java.util.NoSuchElementException;

public class Tour {
	private int n;
	private Node first;
	private Node last;

	private class Node {
		private Point point;
		private Node next;
	}

	public Tour() {
		first = null;
		last = null; // last is left as a null reference till getLast is called
		n = 0;
	}

	private boolean isEmpty() {
		return first == null;
	}

	public void show() {
		for (Node x = first; x != null; x = x.next) {
			System.out.println(x.point);
		}
	}

	public void draw() {
		for (Node x = first; x != null; x = x.next) {
			x.point.draw();
			if (x.next != null) { // exclude last null node
				x.point.drawTo(x.next.point);
			}
		}

	}

	public int size() {
		return n;
	}

	public double distance() {
		double total = 0;
		if (isEmpty()) throw new NoSuchElementException();
		for (Node x = first; x != null; x = x.next) {
			if (x.next != null) { // exclude last null node
				total = total + x.point.distanceTo(x.next.point);
			}
		}
		return total;
	}

	private Node getLast() {
		for (Node x = first; x != null; x = x.next) {
			last = x;
		}
		return last;
	}

	public void insertNearest(Point P) {
		if (isEmpty()) {
			first = new Node();
			first.point = P;
			first.next = null;
			n = 1;
		} else {
			double dist = first.point.distanceTo(P);// set first distance value against first node
			Node listChoice = first; // set choice as first node
			for (Node x = first; x != null; x = x.next) {
				double temp = x.point.distanceTo(P);
				if (temp < dist) {
					dist = temp;
					listChoice = x;
				}
			}
			Node addition = new Node();
			addition.point = P;
			addition.next = listChoice.next; // the node that followed from list choice is now added behind the inserted node
			listChoice.next = addition;// connect the inserted node behind listChoice
			n++;
		}
	}

	public void insertSmallest(Point P){
		if (isEmpty()) { // case for 1st node
			first = new Node();
			first.point = P;
			n++;

		} else if (n == 1) { // case for the starting 2 nodes for the first distance value
			Node addition = new Node();
			addition.point = P;
			addition.next = first.next;
			first.next = addition;
			n++;

		} else {
			// set distIncrease to the first possible increase value between the first two nodes
			double distIncrease = (first.point.distanceTo(P) + P.distanceTo(first.next.point)) - (first.point.distanceTo(first.next.point));
			Node listChoice = first; // set choice as first node before iterating
			for (Node x = first; x.next!= null; x = x.next) { // we have to check x.next!=null because the last null Node does not contain a point
				double temp = (x.point.distanceTo(P) + P.distanceTo(x.next.point)) - (x.point.distanceTo(x.next.point));
				if (temp < distIncrease){
					distIncrease = temp;
					listChoice = x;
				}
			}
			double lastOption = (this.getLast().point.distanceTo(P));
			if(lastOption < distIncrease){
				listChoice = this.getLast();
			}
			Node addition = new Node();
			addition.point = P;
			addition.next = listChoice.next; // the node that followed from list choice is now added behind the inserted node
			listChoice.next = addition;// connect the inserted node behind listChoice
			n++;
		}
	}
}





