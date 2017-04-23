
import edu.princeton.cs.algs4.*;
import java.util.*;
public class PointSET {
    
    private SET<Point2D> set;
    public         PointSET()                               // construct an empty set of points 
    {
        set = new SET<Point2D>();
    }
    public           boolean isEmpty()                      // is the set empty? 
    {
        return set.isEmpty();
    }
    public               int size()                         // number of points in the set 
    {
        return set.size();
    }
    public              void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    {
        if(p==null) throw new java.lang.NullPointerException("null arg");
        set.add(p);
    }
    public           boolean contains(Point2D p)            // does the set contain point p? 
    {
        if(p==null) throw new java.lang.NullPointerException("null arg");
        return set.contains(p);
    }
    public              void draw()                         // draw all points to standard draw 
    {
        for(Point2D pp : set){
            pp.draw();
//        StdOut.print(pp.toString());   
        }
//        StdDraw.show();
        
        
    }
    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle 
    {
        if(rect==null) throw new java.lang.NullPointerException("null arg");
        LinkedList<Point2D> list = new LinkedList<Point2D>();
        for(Point2D pp : set){
            if(rect.contains(pp)) list.add(pp);
        }
        
        return list;
    }
    public           Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty 
    {
        if(p==null) throw new java.lang.NullPointerException("null arg");
        if(this.size() == 0) return null;
        double minDist = Double.POSITIVE_INFINITY;
        double dist;
        Point2D minP = null;
        for(Point2D pp : set){
            dist = pp.distanceTo(p);
            if(minDist > dist) {
                minDist = dist;
                minP = pp; 
            }
        }
        
        return minP;
    }
    
    public static void main(String[] args)                  // unit testing of the methods (optional) 
    {
        PointSET ps = new PointSET();
//       Point2D p1 = new Point2D(0,0);
//       ps.insert(p1);
        for(int i=0; i < 100; i++)
        {
            
            ps.insert(new Point2D(i,i));
        }
//        Point2D minP = ps.nearest(new Point2D(12.1,100.1));
//        StdOut.print(minP.toString());
//        
//        RectHV rect= new RectHV(2.5,2.5,5.5,5.5);
//        for(Point2D ppp : ps.range(rect))
//            StdOut.print(ppp.toString());
        
//         Point2D p2 = new Point2D(1,1);
//       StdOut.print(ps.contains(p2));
//       ps.draw();
        
    }
}