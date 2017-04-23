import edu.princeton.cs.algs4.*;
import java.util.*;
public class BruteCollinearPoints {
    
//   private 
   private int nP;
   private int nSeg;
   private ArrayList<LineSegment> seg;
   private boolean hasRepeatedPoint(Point[] points)
   {
       for(int i=0; i < nP; i++)
       {
           for(int j=i+1 ; j < nP; j++)   
           {
               if(points[i].slopeTo(points[j])== Double.NEGATIVE_INFINITY) return true; 
           }
       }
       return false;
   }
   public BruteCollinearPoints(Point[] points)    // finds all line segments containing 4 points
   {
       nSeg = 0;
       nP = points.length;
       if(points == null) throw new java.lang.NullPointerException("null input");
       if(hasRepeatedPoint(points)) throw new java.lang.IllegalArgumentException("repeated point");
       
       seg = new ArrayList<LineSegment>();
  
       for(int p=0; p < nP; p++)
       {
           for(int q=p+1; q < nP; q++)
           {
               for(int r=q+1; r < nP; r++)
               {
                   for(int s =r+1; s < nP; s++)
                   {
//                       StdOut.print(p + " " + q + " " + r + " " + s + "\n");
                       double s0 = points[p].slopeTo(points[q]);
                       double s1 = points[p].slopeTo(points[r]);
                       double s2 = points[p].slopeTo(points[s]);                       
                      if( (s0 == s1) && (s0 == s2))
                      {
//                          StdOut.print(p + " " + q + " " + r + " " + s + ":"+ points[p]+"," +  points[s]+"form a line \n");
                          
                          Point max,min;
                          if(points[p].compareTo(points[q])>0)
                          {
                              max = points[p];
                              min = points[q];
                          }
                          else 
                          {
                              min = points[p];
                              max = points[q];
                          }
                          if(min.compareTo(points[r]) >0) min = points[r];
                          if(min.compareTo(points[s]) >0) min = points[s];
                          if(max.compareTo(points[r]) <0) max = points[r];
                          if(max.compareTo(points[s]) <0) max = points[s];
                          
                          
                          seg.add(new LineSegment(min,max));
                          nSeg++;
                      }
                   }
               }
           }
       }
           
   }
   public           int numberOfSegments()        // the number of line segments
   {
       return nSeg;
   }
   public LineSegment[] segments()                // the line segments
   {
      return seg.toArray(new LineSegment[seg.size()]);
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
//        StdOut.print(x +" " + y + "\n");
    }

    
    if(false){
        
    // draw the points
    StdDraw.enableDoubleBuffering();
    StdDraw.setXscale(0, 32768);
    StdDraw.setYscale(0, 32768);
    for (Point p : points) {
        p.draw();
    }
    StdDraw.show();
    }
  
    // print and draw the line segments
    BruteCollinearPoints collinear = new BruteCollinearPoints(points);
    StdOut.print("\n" + collinear.numberOfSegments()+ "\n");
    for(int i=0; i < collinear.numberOfSegments(); i++)
        StdOut.print(collinear.segments()[i].toString()+ "\n");
    
    
    
   if(false){
    for (LineSegment segment : collinear.segments()) {
        StdOut.println(segment);
        segment.draw();
    }
    StdDraw.show();
    }
}
   
}