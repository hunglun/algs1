import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.StdOut;
//import edu.princeton.cs.algs4.SET;
import edu.princeton.cs.algs4.MinPQ;
import java.util.TreeSet;
public class PointSET {
  private TreeSet<Point2D> set;
  public         PointSET()                               // construct an empty set of points
  {
    set = new TreeSet<Point2D>();
  }
  public           boolean isEmpty()                      // is the set empty?
  {
    return set.size() == 0;
  }
  
  public               int size()                         // number of points in the set
  {
    return set.size();
  }
  
  public              void insert(Point2D p)              // add the point to the set (if it is not already in the set)
  {
    if (p == null)
      throw new IllegalArgumentException("null point");
    set.add(p);
  }
  public           boolean contains(Point2D p)            // does the set contain point p?
  {
    if (p == null)
      throw new IllegalArgumentException("null point");
    return set.contains(p);
  }
  
  public              void draw()                         // draw all points to standard draw 
  {
    for(Point2D p : set) p.draw();
  }
  public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle (or on the boundary) 
  {
    if (rect == null)
      throw new IllegalArgumentException("null rect");
    TreeSet<Point2D> result = new TreeSet<Point2D>();
    
    for(Point2D p : set){
       if(rect.contains(p)) result.add(p);
    }
    return result;
  }
  
  // Search Node 
  private class SearchNode implements Comparable<SearchNode> {
    Point2D point, query;
    public SearchNode(Point2D point, Point2D query){
      this.point = point;
      this.query = query;
    }
    
    public int compareTo(SearchNode that) {
      double thisOne = this.point.distanceSquaredTo(query);
      double thatOne = that.point.distanceSquaredTo(query);
      if(thisOne > thatOne) return 1;
      if(thisOne < thatOne) return -1;
      return 0;
    }
  }
  public           Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty 
  {
    if (p == null)
      throw new IllegalArgumentException("null point");
    MinPQ<SearchNode> pq = new MinPQ<SearchNode>();
    for(Point2D point : set) pq.insert(new SearchNode(point, p));
    return pq.delMin().point;
  }
  public static void main(String[] args)                  // unit testing of the methods (optional)
  {
  }
}
