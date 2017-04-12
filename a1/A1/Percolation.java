import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation{
    private boolean[][] statusOpen; // 0 closed, 1 open
    private int nOpen; //number of open sites
    private int size;
    private WeightedQuickUnionUF myUF ;
    private WeightedQuickUnionUF auxUF ;
    private int top, bottom;
    private boolean qPerc;
    
    public Percolation(int n)                // create n-by-n grid, with all sites blocked
    {
        
          if (n<= 0 ) throw new IllegalArgumentException("n nonnegative") ;
        //       assert(false);
       nOpen = 0;
       size = n;
       statusOpen = new boolean[n+1][n+1];
       for(int i=0; i < n+1; i++){
           for(int j=0;j < n+1; j++){
        statusOpen[i][j] = false;
           }
       }
       
       myUF = new WeightedQuickUnionUF(n*n+2);//top = n^2, bottom = n^2+1
       auxUF = new WeightedQuickUnionUF(n*n+2);//top = n^2
       top = n*n;
       bottom = n*n+1;
       qPerc = false;
    }
    private int TwoDToOneDUnionIndex(int row,int col)
    {
        return (row-1)*size+(col-1);
    }
    
    public    void open(int row, int col)    // open site (row, col) if it is not open already
    {
        if (row <= 0 || row > size) throw new IndexOutOfBoundsException("row index i out of bounds");
        if (col <= 0 || col > size) throw new IndexOutOfBoundsException("col index i out of bounds");
        if (statusOpen[row][col] == false)
        {
            statusOpen[row][col] = true;
            nOpen++;
         
            if(col+1 <= size)
            {
                if(isOpen(row,col+1)){//right
                   myUF.union(TwoDToOneDUnionIndex(row,col),TwoDToOneDUnionIndex(row,col+1));
                 auxUF.union(TwoDToOneDUnionIndex(row,col),TwoDToOneDUnionIndex(row,col+1));
                }
            }
//            else System.out.println("right");
            if(col-1 > 0)
            {
                if(isOpen(row,col-1)){//right
                   myUF.union(TwoDToOneDUnionIndex(row,col),TwoDToOneDUnionIndex(row,col-1));
               auxUF.union(TwoDToOneDUnionIndex(row,col),TwoDToOneDUnionIndex(row,col-1));
                }
            }
//            else System.out.println("left");
            if(row+1 <= size)
            {
                if(isOpen(row+1,col)){
                  myUF.union(TwoDToOneDUnionIndex(row,col),TwoDToOneDUnionIndex(row+1,col));
                  auxUF.union(TwoDToOneDUnionIndex(row,col),TwoDToOneDUnionIndex(row+1,col));
                }
            }
            else
            {
//                 System.out.println("bot");
               myUF.union(TwoDToOneDUnionIndex(row,col),bottom);
//                auxUF.union(TwoDToOneDUnionIndex(row,col),bottom);
            }
            if(row-1 > 0)
            {
                if(isOpen(row-1,col)){
                  myUF.union(TwoDToOneDUnionIndex(row,col),TwoDToOneDUnionIndex(row-1,col));
                  auxUF.union(TwoDToOneDUnionIndex(row,col),TwoDToOneDUnionIndex(row-1,col));
                }
            }
            else
            {
//                System.out.println("top");
                myUF.union(TwoDToOneDUnionIndex(row,col),top);
                auxUF.union(TwoDToOneDUnionIndex(row,col),top);
            }
            
        }
    }    
    public boolean isOpen(int row, int col)  // is site (row, col) open?
    {
         if (row <= 0 || row > size) throw new IndexOutOfBoundsException("row index i out of bounds");
         if (col <= 0 || col > size) throw new IndexOutOfBoundsException("col index i out of bounds");
//        System.out.println("r:"+row);
//                System.out.println("col:"+col);
//                        System.out.println(statusOpen[0].length);
        return statusOpen[row][col];
    }    
    public boolean isFull(int row, int col)  // is site (row, col) full?
    {
         if (row <= 0 || row > size) throw new IndexOutOfBoundsException("row index i out of bounds");
         if (col <= 0 || col > size) throw new IndexOutOfBoundsException("col index i out of bounds");
//         System.out.println(row + ":" + col + " = " + myUF.connected(TwoDToOneDUnionIndex(row,col),top) );
    
//       if(!qPerc){
//            return myUF.connected(TwoDToOneDUnionIndex(row,col),top);
//       }
//           else{
                
        return auxUF.connected(TwoDToOneDUnionIndex(row,col),top);
//            }
    }    
    public     int numberOfOpenSites()       // number of open sites
    {
        return nOpen;
    }    
    public boolean percolates()              // does the system percolate?
    {
        qPerc = true;
  //       return auxUF.connected(top,bottom);
   return myUF.connected(top,bottom);
    }

    public static void main(String[] args)   // test client (optional)
    {
//        open(1,1);
//        open(1,2);
        Percolation perc = new Percolation(20);
//        System.out.println(perc.TwoDToOneDUnionIndex(20,20));
        
        System.out.println(perc.top + ":" + perc.bottom);
    }
}