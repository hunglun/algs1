import java.util.Arrays;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.ResizingArrayStack;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class FastCollinearPoints {
    private ResizingArrayStack<LineSegment> s;
   public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
    {
    // sanity check
    if (points == null) throw new IllegalArgumentException("null points array");
    for(Point p : points) {
      if (p == null) throw new IllegalArgumentException("array contains null point");
    }
    // sort points array
  
    Point mypoints[] = new Point[points.length];
    for(int i=0; i<points.length;i++) {
      mypoints[i] = points[i];
    }
    Arrays.sort(mypoints); // sort by natural order.
    Arrays.sort(mypoints,(new Point(0,0)).slopeOrder());
   
    Point prev = null;    
    for(Point p : mypoints) {
      
      if (prev != null){
         if(p.compareTo(prev) == 0) throw new IllegalArgumentException("array contains repeated points");
      }
      prev = p;    
    }
    
    s = new ResizingArrayStack<LineSegment>();
    // add all segments to stack.
    
    for(int i=0;i<mypoints.length;i++){
      // sort mypoints array based on each point's slope wrt points[i].
      Arrays.sort(mypoints,points[i].slopeOrder());
      assert(mypoints[0].compareTo(points[i]) == 0);
      // check if any 3 or more adjacent points have equal slopes wrt points[i]
      for(int k=1;k+1<mypoints.length;k++){
        if(points[i].slopeTo(mypoints[k]) == points[i].slopeTo(mypoints[k+1]) 
             &&
           points[i].slopeTo(mypoints[k]) == points[i].slopeTo(mypoints[k+2])){
          Point start = new Point(0,0);           
          Point end = new Point(0,0);
          start = points[i];
          end = mypoints[k+2];              
          s.push(new LineSegment(start, end));
          break;
        }
      }
      
    }
   }
    
   
    
    
   
   
   public           int numberOfSegments()        // the number of line segments
    {
     return s.size();
    }
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
    FastCollinearPoints collinear = new FastCollinearPoints(points);
    for (LineSegment segment : collinear.segments()) {
      StdOut.println(segment);
      segment.draw();
    }
    StdDraw.show();
  }
  public static void main(String[] args) {
    test(args);
    /*
    Point p1 = new Point(0,0);
    Point p2 = new Point(1,1);
    Point p3 = new Point(2,2);
    Point p4 = new Point(3,3);
    Point a[] = {p1,p2,p3,p4};
    FastCollinearPoints algs = new FastCollinearPoints(a);
    for (LineSegment segment : algs.segments()) {
      StdOut.println(segment);
      
    }
*/
   
  }
}
