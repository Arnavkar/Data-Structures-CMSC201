/*
Arnav Shirodkar
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
public class ConvexHull {
    public final static int W = 800;
    public final static int H = 800;
    public final static int N = 50;

    public static void main(String[] args) {
        Stopwatch timer = new Stopwatch();
        MinPQMultiway<Point2D> yPoints = new MinPQMultiway<Point2D>(8, 1);
        StdDraw.setCanvasSize(W, H);
        StdDraw.setXscale(0, W);
        StdDraw.setYscale(0, H);
        StdDraw.setPenColor(StdDraw.GREEN);

        for (int i = 0; i < N; i++) {
            Point2D p = new Point2D(StdRandom.gaussian(W / 2, W / 8),
                    StdRandom.gaussian(H / 2, H / 8));
            yPoints.insert(p);
            StdDraw.filledCircle(p.x(), p.y(), 3);
        }
        Point2D p0 = yPoints.delMin();

        MinPQMultiway<Point2D> pPoints = new MinPQMultiway<Point2D>(8, 1, p0.polarOrder());
        while (!yPoints.isEmpty()) {
            Point2D check = yPoints.delMin();
            pPoints.insert(check);
        }

        Stack2<Point2D> s = new Stack2<Point2D>();

        s.push(p0);
        s.push(pPoints.delMin());
        s.push(pPoints.delMin());


       /* StdOut.println(s.pop());   makes sure inherited functions for stack2 work properly
        StdOut.println(s.pop());
        StdOut.println(s.pop()); */

//        while (!pPoints.isEmpty()) {  // test to ensure pPoints is popping min properly
//            StdOut.println(pPoints.delMin());
//        }
//    }

//       while (!pPoints.isEmpty()) {
//            Point2D pi = pPoints.delMin();
//            StdDraw.line(p0.x(), p0.y(), pi.x(), pi.y());
//        }
//    }


        while (!pPoints.isEmpty()) {
            Point2D pi = pPoints.delMin();
            while (s.size() > 1 && Point2D.ccw(s.sneakpeek(), s.peek(), pi) == -1) {
                s.pop();
            }
            s.push(pi);
        }
        drawHull(s);
        StdOut.println(timer.elapsedTime()); // find elapsed time
    }
    public static void drawHull(Stack2<Point2D> s) {
        StdDraw.setPenColor(StdDraw.ORANGE);
        Point2D first = s.pop();
        Point2D last = first;
        while (!s.isEmpty()) {
            Point2D q = s.pop();
            StdDraw.line(last.x(), last.y(), q.x(), q.y());
            last = q;
        }
        StdDraw.line(first.x(), first.y(), last.x(), last.y());
    }
}