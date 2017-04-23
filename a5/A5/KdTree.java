
import edu.princeton.cs.algs4.*;
import java.util.Comparator;

public class KdTree {
    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree
        private boolean isHoriz = false;
        
        public Node (Point2D p, RectHV rect,boolean isHoriz)
        {
         this.p = p;
         this.rect = rect;
         this.isHoriz = isHoriz;
        }
        
        private void print(){
            
          StdOut.print(p.toString() + " " + rect.toString() +" "+ isHoriz + "\n");
        if(lb != null) lb.print();
        if(rt != null) rt.print();
        }
    }
    
    private Node root;
    private int nSize;
    public         KdTree()                               // construct an empty set of points 
    {
        root = null;
        nSize = 0;
    }
    
    public           boolean isEmpty()                      // is the set empty? 
    {
        return root == null;
    }
    public               int size()                         // number of points in the set 
    {
        return nSize;
    }
    public              void insert(Point2D p)              // add the point to the set (if it is not already in the set)
    {
        if(p==null) throw new java.lang.NullPointerException("null arg");
        root = put(root,p,false,null,0);
    }
    
    private RectHV subdivide(RectHV rect,Point2D p, boolean isHorizontal,int c)
    {
        double xmin,xmax,ymin,ymax;
        xmin = rect.xmin();
        xmax = rect.xmax();
        ymin = rect.ymin();
        ymax = rect.ymax();
//       StdOut.print("c: " + c + "\n");
        if(isHorizontal)
        {
         if(c<0) xmax = p.x();
         else xmin = p.x();
        }
        else
        {
         if(c<0) ymax = p.y();
         else ymin = p.y();
        }
//        StdOut.print(xmin + " " + ymin + " " + xmax + " " + ymax + "\n");
     return new RectHV(xmin,ymin,xmax,ymax);   
    }
    private Node put(Node node, Point2D p, boolean isHorizontal,Node parent,int c)
    {
        if(node == null)
        {
            RectHV rect;
            if (parent ==null) rect = new RectHV(0,0,1,1);
            else{
               rect = new RectHV(0,0,1,1);
                rect = subdivide(parent.rect,parent.p,isHorizontal,c);
            }
            if(!(contains(p))){
                
            nSize++;
            return new Node(p,rect,isHorizontal);
            }
            else return null;
        }
     
      Comparator <Point2D> comparator;
     if(isHorizontal) comparator = Point2D.Y_ORDER;
     else comparator = Point2D.X_ORDER;
     int cmp = comparator.compare(p,node.p);
        
        
        
//        int cmp = p.compareTo(node.p);
//     double sp = isHorizontal? p.x(): p.y();
     
        if(cmp < 0) node.lb = put(node.lb,p,!isHorizontal,node,cmp);
        else if(cmp >=0) node.rt = put(node.rt,p,!isHorizontal,node,cmp);
//        else node.p = p;
//        node.nSize = node.lb.size()+ node.rt.size()+1;
        return node;
    }
    
    public           boolean contains(Point2D p)            // does the set contain point p? 
    {
        if(p==null) throw new java.lang.NullPointerException("null arg");
        

        return contains(root,p,false);
        
    }
    private boolean contains(Node node,Point2D p, boolean isH)
    {
     if(node == null) return false;
     if(node.p.equals(p)) return true;
     
     Comparator <Point2D> comparator;
     
     if(!isH) comparator = Point2D.X_ORDER;
     else comparator = Point2D.Y_ORDER;
     
     int comp = comparator.compare(p,node.p);
     
     if(comp < 0) return contains(node.lb,p,!isH);
     else return contains(node.rt,p,!isH);
     
     
    }
        
    
    public              void draw()                         // draw all points to standard draw 
    {
        root.print();
        draw(root,null);
//        draw(root.lb);
//        draw(root.rt);

    }
    private boolean qIsLeftNeighbor(Node node, Node parent)
    {
     if(parent.lb != null)
         if(parent.lb.equals(node)) return true;
     if(parent.rt != null)
         if(parent.rt.equals(node)) return false;
     return false;
        
    }
    private void draw(Node node,Node parent)
    {
        if(node == null) return;
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.02);
        StdDraw.point(node.p.x(),node.p.y());
        StdDraw.setPenRadius();
        if(!(node.isHoriz)) StdDraw.setPenColor(StdDraw.RED);
        else StdDraw.setPenColor(StdDraw.BLUE);
        
        if(parent == null)
        {
            if(!node.isHoriz) StdDraw.line(node.p.x(),0,node.p.x(),1);
            else StdDraw.line(0,node.p.y(),1,node.p.y());
        }
        else
        {
            if(!node.isHoriz){
//            double x0 = node.lb.rect.xmax();
//            double x1 = x0;
//            double y0 = node.lb.rect.ymin();
//            double y1 = node.lb.rect.ymax();
//            StdOut.print(x0 + "," + y0 + " : " + x1 + "," +y1 + "\n");
                double x0 = node.p.x();
                double x1 = x0;
                double y0,y1;
                if(qIsLeftNeighbor(node,parent) )
                {
                    y0 = 0;
                    y1 = parent.p.y();
                }
                else
                {
                    y1 = 1;
                    y0 = parent.p.y();                
                }
                
                StdDraw.line(x0,y0,x1,y1);
            }
            else
            {
//            double x0 = node.lb.rect.xmin();
//            double x1 = node.lb.rect.xmax();;
//            double y0 = node.lb.rect.ymax();
//            double y1 = y0;
                double x0,x1;
//                if(parent.lb == null ||parent.lb.equals(node))
                if(qIsLeftNeighbor(node,parent) )
                {
                    x0 = 0;
                    x1 = parent.p.x();
                }
                else
                {
                    
                    x1 = 1;
                    x0 = parent.p.x();
                }
                
                
                
                double y0 = node.p.y();
                double y1 = y0;
                
//            StdOut.print(x0 + "," + y0 + " : " + x1 + "," +y1 + "\n");
                StdDraw.line(x0,y0,x1,y1);
            }
        }
        draw(node.lb,node);
        draw(node.rt,node);
        
        
    }
        
    public Iterable<Point2D> range(RectHV rect)             // all points that are inside the rectangle 
    {
        if(rect == null) throw new java.lang.NullPointerException("null arg");
        Stack<Point2D> st = new Stack<Point2D>();
        
        if(root != null) range(root,rect,st);
        
        return st;
            
    }
    private void range(Node node, RectHV rect, Stack<Point2D> st)
    {
     if(rect.contains(node.p)) st.push(node.p); 
     
     if(node.lb != null && node.lb.rect.intersects(rect)) range(node.lb,rect,st);
     if(node.rt != null && node.rt.rect.intersects(rect)) range(node.rt,rect,st);
    }
    public           Point2D nearest(Point2D p)             // a nearest neighbor in the set to point p; null if the set is empty
    {
        if(p==null) throw new java.lang.NullPointerException("null arg");
        if(root == null) return null;
        return nearest(root,p,root.p);

    }
    private Point2D nearest(Node node, Point2D query, Point2D best)
    {
     if(node == null) return best;
     
     double d2n = query.distanceSquaredTo(best);
     double d2r = node.rect.distanceSquaredTo(query);
     
     if(d2n < d2r) return best;
     double d2p = query.distanceSquaredTo(node.p);
     
     if(d2p < d2n) best = node.p;
     
     if(node.lb == null && node.rt == null) return best;
     
     if(node.rt == null) return nearest(node.lb,query,best);
     if(node.lb == null) return nearest(node.rt,query,best);
     
     if(node.lb.rect.contains(query))
     {
      best = nearest(node.lb,query,best);
      best = nearest(node.rt,query,best);
     }
     else
     {
      best = nearest(node.rt,query,best);
      best = nearest(node.lb,query,best);
     }
     return best;
    }
    public static void main(String[] args)                  // unit testing of the methods (optional) 
    {
        KdTree kd = new KdTree();
//        kd.insert(new Point2D(0.7,0.2));
//        kd.insert(new Point2D(0.5,0.4));
//        kd.insert(new Point2D(0.2,0.3));
//        kd.insert(new Point2D(0.4,0.7));
//        kd.insert(new Point2D(0.9,0.6));
//////        
       kd.insert(new Point2D(0.206107,0.095492));
       kd.insert(new Point2D(0.975528,0.654508));
       kd.insert(new Point2D(0.024472 ,0.345492));
       kd.insert(new Point2D(0.793893 ,0.095492));
       kd.insert(new Point2D(0.793893 ,0.904508));
       kd.insert(new Point2D(0.975528 ,0.345492));
       kd.insert(new Point2D(0.206107 ,0.904508));
//       kd.insert(new Point2D(0.500000 ,0.000000));
//       kd.insert(new Point2D(0.024472 ,0.654508));
//       kd.insert(new Point2D(0.500000 ,1.000000));
        
//        kd.insert(new Point2D(0.85 ,0.4));
//       kd.insert(new Point2D(0.6,0.1));
//      kd.insert(new Point2D(0.8,0.4));
//        kd.insert(new Point2D(0.8,0.7));
//        StdOut.print(kd.contains(new Point2D(0.5,0.4)));
        kd.insert(new Point2D(0.2 ,0.9));
        kd.insert(new Point2D(0.793893 ,0.904508));
        kd.insert(new Point2D(0.793893 ,0.904508));
        
        kd.insert(new Point2D(0.793893 ,0.904508));
        StdOut.print("size = "+kd.size()+ "\n");
        StdOut.print(kd.contains(new Point2D(0.2 ,0.9)));
        
//        kd.draw();
        for(Point2D pt : kd.range(new RectHV(0.5,0.5,1,1)))
            StdOut.print(pt.toString()+ "\n");
                                      
        
//        
//        for(int i=0; i < 100; i++)
//        {
//            
//            kd.insert(new Point2D(i*.01,i*.01));
//        }
//       Point2D minP = kd.nearest(new Point2D(12.1*.01,100.1*.01));
//         StdOut.print(minP.toString());
        
    }
}