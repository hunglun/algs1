import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.ResizingArrayStack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {

  private ResizingArrayStack<LineSegment> s;
  private Point mypoints[];
  public BruteCollinearPoints(Point[] points){
    // sanity check
    if (points == null) throw new IllegalArgumentException("null points array");
    for(Point p : points) {
      if (p == null) throw new IllegalArgumentException("array contains null point");
    }
    // sort points array
    
   /* StdOut.println("Before sorting.");
    for(Point p : points) {
      StdOut.println(p);
    }*/
    mypoints = new Point[points.length];
    for(int i=0; i<points.length;i++) {
      mypoints[i] = points[i];
    }
    Arrays.sort(mypoints); // sort by natural order.
    Arrays.sort(mypoints,(new Point(0,0)).slopeOrder());
    /*
    StdOut.println("After sorting.");
    for(Point p : points) {
      StdOut.println(p);
    }*/
   
    Point prev = null;    
    for(Point p : mypoints) {
      
      if (prev != null){
         if(p.compareTo(prev) == 0) throw new IllegalArgumentException("array contains repeated points");
       //  StdOut.println("current point " + p );
       //  StdOut.println("previous point " + prev );
      }
      prev = p;    
    }
    
    s = new ResizingArrayStack<LineSegment>();
    // add all segments to stack.
    
    for(int i=0;i<mypoints.length;i++){
      
      for(int j=i+1;j<mypoints.length;j++){
        
        for(int k=j+1;k<mypoints.length;k++){
          
          for(int l=k+1;l<mypoints.length;l++){
            if(mypoints[i].slopeTo(mypoints[j]) == mypoints[i].slopeTo(mypoints[k]) 
                 &&
               mypoints[i].slopeTo(mypoints[j]) == mypoints[i].slopeTo(mypoints[l])){
        /*      StdOut.println("Add a new segment");
              StdOut.println(points[i]);
              StdOut.println(points[j]);
              StdOut.println(points[k]);
              StdOut.println(points[l]);
          */   
              Point start = new Point(0,0);
              
              Point end = new Point(0,0);
              start = mypoints[i];
              end = mypoints[l];
              
              s.push(new LineSegment(start, end));
            }
            
          }}}
    }
    
 
    
  }    // finds all line segments containing 4 points
  
  public           int numberOfSegments() {return s.size();}       // the number of line segments
  
  public LineSegment[] segments()                // the line segments
  {
       // populate segments array
    
    LineSegment segments[] = new LineSegment[s.size()];
    int i=0;
    for(LineSegment seg : s){
      segments[i++] = seg;
    }
    return segments;
  }
  
  private static void test(String[] args) {
   // read the n points from a file
    In in = new In(args[0]);
    int n = in.readInt();
    Point[] points = new Point[n];
    for (int i = 0; i < n; i++) {
      int x = in.readInt();
      int y = in.readInt();
      points[i] = new Point(x, y);
    }
    
    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
      p.draw();
    }
    StdDraw.show();
    
    // print and draw the line segments
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }
  public static void main(String[] args) {
    test(args);
    /*Point p1 = new Point(24109,4792);
    Point p2 = new Point(23875,27729);
    Point p3 = new Point(21848,22151);
    Point p4 = new Point(23875,27729);
    Point a[] = {p1,p2,p3,p4};
    BruteCollinearPoints algs = new BruteCollinearPoints(a);
    */

   
  }
}
