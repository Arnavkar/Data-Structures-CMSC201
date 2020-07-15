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
// composed using bag implementation

import edu.princeton.cs.algs4.*;
import java.util.Iterator;

public class BoundingBox implements Iterable {

    private Bag<Point2D> bag;
    Point2D north,south,west,east;
    double Xsum, Ysum;

    public final static int W = 800; // window dimensions
    public final static int H = 800;
    public final static int N = 150;

    BoundingBox(){
        bag = new Bag();
        north = new Point2D(400,0);
        south = new Point2D(400,800);
        west = new Point2D(800,400);
        east = new Point2D(0,400);
    }

    void add(Point2D item) {
            bag.add(item);

            // update the references to north,south,west,east as nodes are added
            if (item.x() < west.x()) { west = item;}
            if (item.x() > east.x()) { east = item;}
            if (item.y() < south.y()) { south = item;}
            if (item.y() > north.y()) { north = item;}
            Xsum += item.x(); //find sum of all X and Y coordinates
            Ysum += item.y();
        }

    boolean isEmpty(){ return bag.isEmpty(); }
    int size(){ return bag.size(); }

    Point2D top(){
        return north;
    }
    Point2D bottom(){
        return south;
    }
    Point2D left(){
        return west;
    }
    Point2D right(){
        return east;
    }

    Point2D centroid(){
        Point2D centroid = new Point2D(Xsum/bag.size(),Ysum/bag.size());
        return centroid;
    }

    public Iterator iterator()  { return bag.iterator(); }

    public static void main(String[] args){
        BoundingBox bb = new BoundingBox();

        StdDraw.setCanvasSize(W, H);
        StdDraw.setXscale(0, W);
        StdDraw.setYscale(0, H);
        StdDraw.setPenColor(StdDraw.GREEN);


        for (int i = 0; i < N; i ++){
            Point2D p = new Point2D(StdRandom.gaussian(W/2, W/8),
                    StdRandom.gaussian(H/2, H/8));
            bb.add(p);
            StdDraw.filledCircle(p.x(), p.y(), 3);
        }
        //Test to ensure Centroid function works

        StdDraw.setPenColor(StdDraw.BOOK_RED);
        StdDraw.filledCircle(bb.centroid().x(), bb.centroid().y(), 3);


        Point2D left = bb.left();
        Point2D right = bb.right();
        Point2D top = bb.top();
        Point2D bottom = bb.bottom();
        StdDraw.setPenColor(StdDraw.ORANGE);
        StdDraw.line(right.x(), bottom.y(), right.x(), top.y());
        StdDraw.line(left.x(), bottom.y(), left.x(), top.y());
        StdDraw.line(left.x(), bottom.y(), right.x(), bottom.y());
        StdDraw.line(left.x(), top.y(), right.x(), top.y());
    }
}

