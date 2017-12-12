import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.ResizingArrayStack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BruteCollinearPoints {
  private int n;
  private LineSegment segments[];
  
  public BruteCollinearPoints(Point[] points){
    // sanity check
    if (points == null) throw new IllegalArgumentException("null points array");
    for(Point p : points) {
      if (p == null) throw new IllegalArgumentException("array contains null point");
    }
    // sort points array
    
      StdOut.println("Before sorting.");
    for(Point p : points) {
      StdOut.println(p);
    }
    Arrays.sort(points,(new Point(0,0)).slopeOrder());
    
        StdOut.println("After sorting.");
    for(Point p : points) {
      StdOut.println(p);
    }
    
    Point prev = null;    
    for(Point p : points) {
      if (prev != null){
         if(p == prev) throw new IllegalArgumentException("array contains repeated points");
      }
      prev = p;    
    }
    
    ResizingArrayStack<LineSegment> s = new ResizingArrayStack<LineSegment>();
    // add all segments to stack.
    
    for(int i=0;i<points.length;i++){
      
      for(int j=i+1;j<points.length;j++){
        
        for(int k=j+1;k<points.length;k++){
          
          for(int l=k+1;l<points.length;l++){
            if(points[i].slopeTo(points[j]) == points[i].slopeTo(points[k]) 
                 &&
               points[i].slopeTo(points[j]) == points[i].slopeTo(points[l])){
              StdOut.println("Add a new segment");
              StdOut.println(points[i]);
              StdOut.println(points[j]);
              StdOut.println(points[k]);
              StdOut.println(points[l]);
              
              s.push(new LineSegment(points[i],points[l]));
            }
            
          }}}
    }
    
    // populate segments array
    n = s.size();
    segments = new LineSegment[n];
    for(int i=0;i<n;i++){
      segments[i] = s.pop();
    }
    assert(s.isEmpty());
    
  }    // finds all line segments containing 4 points
  
  public           int numberOfSegments() {return n;}       // the number of line segments
  
  public LineSegment[] segments()                // the line segments
  {
    return segments;
  }
  
  public static void main(String[] args) {
    
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
}
