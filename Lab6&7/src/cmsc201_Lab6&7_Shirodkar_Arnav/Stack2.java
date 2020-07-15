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

//inherits from Stack
import edu.princeton.cs.algs4.*;
public class Stack2<Item> extends Stack<Item> {

  public Stack2(){}
  public Item sneakpeek(){
      Item item1;
      Item item2;
      item1 = this.pop();
      item2 = this.peek();
      this.push(item1);
      return item2;
  }
}