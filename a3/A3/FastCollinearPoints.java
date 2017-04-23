import edu.princeton.cs.algs4.*;
import java.util.*;

public class FastCollinearPoints {
    private int nSeg;
    private int nP;
    private ArrayList<LineSegment> seg;
    private Point[] pt;
    private ArrayList<Point[] > minMaxSegmentPoints;
    private Point[] minMaxSegment(Point[] points)
    {
//       Point[] point = arrPoint.toArray();
        Point max,min;
        if(points[0].compareTo(points[1])>0)
        {
            max = points[0];
            min = points[1];
        }
        else 
        {
            min = points[0];
            max = points[1];
        }
        for(int i=2; i < points.length; i++){
            if(min.compareTo(points[i]) >0) min = points[i];
            if(max.compareTo(points[i]) <0) max = points[i];
        }
        Point[] minMax = new Point[2];
        minMax[0] = min; 
        minMax[1] = max;
        return minMax;
//       if(min.compareTo(points[r]) >0) min = points[r];
//       if(min.compareTo(points[s]) >0) min = points[s];
//       if(max.compareTo(points[r]) <0) max = points[r];
//       if(max.compareTo(points[s]) <0) max = points[s];   
    }
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
    public FastCollinearPoints(Point[] points)     // finds all line segments containing 4 or more points
    {
        nP = points.length;
        nSeg = 0;
        if(points ==null) throw new java.lang.NullPointerException("input null");
        for(int i=0; i < nP; i++)
        {
            if(points[i] == null) throw new java.lang.NullPointerException("input i is null");
//           pointList.add(points[i]);
        }
//        pt = points;
//        for(int i=0; i < nP; i++){    
//            StdOut.print(pt[i].toString() + "\n");
//        }
        seg = new ArrayList<LineSegment>();
        minMaxSegmentPoints = new ArrayList<Point[]>();
        if(hasRepeatedPoint(points)) throw new java.lang.IllegalArgumentException("repeated point");
        
//       for(int i=0; i < nP; i++){
//         Collections.sort(pointList);  
        
        
       
        Point[] ptCopy = new Point[nP];
        pt = new Point[nP];
        for(int i=0; i < nP; i++){
           pt[i] = points[i];
           ptCopy[i] = pt[i];
        }
//        
//        for(int i=0; i < nP; i++){    
//            StdOut.print(pt[i].toString() + "\n");
//        }
//        StdOut.print("\n");
//        
        double curSlope,prevSlope = Math.sqrt(-1);
        double nextSlope=Math.sqrt(-1);
        int count = 0;
        
        for(int j = 0; j < nP; j++)
        {
             Arrays.sort(pt,ptCopy[j].slopeOrder());
            
//           StdOut.print("j = " + j + " " + ptCopy[j].toString()+ "\n");
//            for(int i=0; i < nP; i++){    
//                 StdOut.print(pt[i].toString() + " "+ ptCopy[j].slopeTo(pt[i]) + "\n");
//             }
//             StdOut.print("\n");
            
            
            ArrayList<Point> candPts = new ArrayList<Point>();
            candPts.add(ptCopy[j]);
            
            for(int i=0; i < nP; i++){ 
                
//                if (i > 1 && i < nP-2)
//                {
//                 curSlope = nextSlope;   
//                }
//                else
//                {
                curSlope =  ptCopy[j].slopeTo(pt[i]);
//                }
//                 StdOut.print("i*:" + i + " cs:" + curSlope + " ns:" + nextSlope + "\n");
                
                if((i>0)&&(prevSlope == curSlope)  )
                {
                    count++;
                    candPts.add(pt[i-1]);
                    if( i < nP-1)
                        nextSlope = ptCopy[j].slopeTo(pt[i+1]);
                    else nextSlope = Math.sqrt(-1);
                  
//                    StdOut.print("i:" + i + " cs:" + curSlope + " ns:" + nextSlope + "\n");
                    if(count > 1  && (curSlope != nextSlope))
                    {
                        candPts.add(pt[i]);                
                        Point[] pp = new Point[candPts.size()];
                        candPts.toArray(pp);
                        
                        Point[] minMax = new Point[2];
                        minMax = minMaxSegment(pp);
                        
                       boolean alreadyInList = false;
//                        for(int k=0; k < nSeg; k++)
//                        {
//                            
//                            Point[] check = minMaxSegmentPoints.get(k);
////                            if((check[0].slopeTo(minMax[0]) == Double.NEGATIVE_INFINITY) && (check[1].slopeTo(minMax[1]) == Double.NEGATIVE_INFINITY))
//                             if( (check[0].compareTo(minMax[0])==0) && (check[1].compareTo(minMax[1])==0))
//                            {
////                               StdOut.print("ALREADY HAS SEGMENT\n");
//                               alreadyInList = true;
//                               break;
//                            }
//                        }
                        
                        LineSegment LS = new LineSegment(minMax[0],minMax[1]);
                        for(LineSegment l : seg){
                            if( l.toString().equals(LS.toString())){
                                alreadyInList = true;
                                break;
                            }
                        }
                        
                       if(!alreadyInList)//                        if(!(seg.contains(LS)))
                        {
                            
                            seg.add(LS);
//                        seg.add(new LineSegment(minMax[0],minMax[1]));
                        
//                        minMaxSegmentPoints.add(minMax);
                        nSeg++;
                        
//                        StdOut.print("Added Segment" + minMax[0] + "->" + minMax[1] + "\n");
                        }
                    }
                }
                else{
                    count = 0;
                    candPts.clear();
                    candPts.add(ptCopy[j]);
                }
                
//                StdOut.print(pt[i].toString() + " " + curSlope+ "\n");
                
                prevSlope = curSlope;
            } 
            
        }
//       }
        
        
        
        
    }
    public           int numberOfSegments()        // the number of line segments
    {return nSeg;
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
        
        
        if(true){
            
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
        FastCollinearPoints collinear = new FastCollinearPoints(points);
        StdOut.print("\n" + collinear.numberOfSegments()+ "\n");
//        for(int i=0; i < collinear.numberOfSegments(); i++)
//            StdOut.print(collinear.segments()[i].toString()+ "\n");
//    
        
        
        if(true){
            for (LineSegment segment : collinear.segments()) {
                StdOut.println(segment);
                segment.draw();
            }
            StdDraw.show();
        }
    }
    
    
    
}