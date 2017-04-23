import edu.princeton.cs.algs4.*;
import java.util.*;


public class Board {
    private int n;
    private int[][] tiles;
//    private int[] spaceLoc;
    
    
    public Board(int[][] blocks)          // construct a board from an n-by-n array of blocks
        // (where blocks[i][j] = block in row i, column j)
    {
        if(blocks == null) throw new java.lang.NullPointerException("null arg");
        n = blocks.length;
        tiles = new int[n][n];
//        spaceLoc = new int[2];
        for(int i=0; i < n; i++)
        {
            for(int j=0; j < n; j++)
            {
                tiles[i][j] = blocks[i][j];
//                if(tiles[i][j] == 0)
//                {
//                    spaceLoc[0] = i;
//                    spaceLoc[1] = j;
//                }
            }
        }
    }
    public int dimension()                 // board dimension n
    {
        return n;
    }
    public int hamming()                  // number of blocks out of place
    {
        int count = 0;
        for(int i=0; i < n*n-1; i++)
        {
            int col = i%n;
            int row = i/n;
            if(tiles[row][col] != i+1) count++;
        }
        return count;
    }
    public int manhattan()                 // sum of Manhattan distances between blocks and goal
    {
      int count = 0;
      int val;
     for(int i=0; i < n; i++)
     {
      for(int j=0; j < n ; j++)
      {
       val = tiles[i][j];
       if(val != 0) count = count + Math.abs(i-((val-1)/n))+  Math.abs(j-((val-1)%n));
     
//      StdOut.print( Math.abs(i-((val-1)/n)) + " "+ Math.abs(j-((val-1)%n))+ "\n " );
//       StdOut.print(i + ":" + j + "=" +Math.abs(i-((val-1)/n)) +"," +  (j-((val-1)%n))+ " \n");
      }
     }
        return count;
    }
    public boolean isGoal()                // is this board the goal board?
    {
        for(int i=0; i < n; i++)
        {
            for(int j=0; j < n ; j++)
            {
                if((i == n-1) && (j == n-1))
                {
                    if(tiles[i][j] != 0) return false;
                }
                else if(tiles[i][j] != i*n+j+1) return false;
            }
        }
        return true;
    }
    public Board twin()                    // a board that is obtained by exchanging any pair of blocks
    {
        int[][] arr = new int[n][n];

        for(int i=0; i < n; i++)
        {
         for(int j=0; j < n; j++)
         {
            arr[i][j] = tiles[i][j];
         }
        }
         int row1 = n-1;
        int row2 = n-1;
        int col1 = n-1;
        int col2 = n-2;
        if(tiles[row1][col1] == 0)
        {
            row1 = n-2;
            col1 = n-2;
        }
        else if(tiles[row2][col2] ==0)
        {
            row2 = n-2;
            col2 = n-1;
        }
        int temp = arr[row1][col1];
        arr[row1][col1] = tiles[row2][col2];
        arr[row2][col2] = temp;
//         if(i == n-1 && j == n-3)
//             {
//                 arr[i][j] = tiles[i][j+1];
//             }
//             else if(i == n-1 && j == n-2)
//             {
//                 arr[i][j] = tiles[i][j-1];
//             }
//             else 
        
        
        return new Board(arr);
    }
    public boolean equals(Object y)        // does this board equal y?
    {
        if(y==this) return true;
        if(y==null) return false;
        if(y.getClass() != this.getClass()) return false;
        Board that = (Board) y;
        if(that.dimension() != this.dimension()) return false; 
        
       for(int i=0; i < n; i++)
        {
            for(int j=0; j < n ; j++)
            {
               if(tiles[i][j] != that.tiles[i][j])
                   return false;
            }
        }
        return true;
    }

    private int[][] swap(int row1,int col1, int row2, int col2)
    {
        int [][] swapped = new int[n][n];
        for(int i=0; i < n; i++)
        {
            for(int j=0; j < n ; j++)
            {
                swapped[i][j] = tiles[i][j];   
            }
        }
        
        int temp = swapped[row1][col1];
        swapped[row1][col1] = tiles[row2][col2];
        swapped[row2][col2] = temp;
        return swapped;
    }
    private int[] spaceLocation()
    {
        int[] loc = new int[2];
        for(int i=0; i < n; i++)
        {
            for(int j=0; j < n; j++)
            {
                if(tiles[i][j] ==0) 
                {
                    loc[0] = i;
                    loc[1] = j;
                    return loc;
                }
            }
        }
        return null;
    }
    public Iterable<Board> neighbors()     // all neighboring boards
    {
       LinkedList <Board> neigh = new LinkedList<Board>();
//       StdOut.print(spaceLoc[0] + " " + spaceLoc[1] + "\n");
       int []loc = spaceLocation();
       int spaceRow = loc[0];
       int spaceCol = loc[1];
  
    

      if (spaceRow < n-1) neigh.add(new Board(swap(spaceRow,spaceCol,spaceRow+1,spaceCol)));
      if (spaceRow > 0) neigh.add(new Board(swap(spaceRow,spaceCol,spaceRow-1,spaceCol)));
      if (spaceCol < n-1) neigh.add(new Board(swap(spaceRow,spaceCol,spaceRow,spaceCol+1)));
      if (spaceCol > 0) neigh.add(new Board(swap(spaceRow,spaceCol,spaceRow,spaceCol-1)));
       
       
       
       
       return neigh;
    }
    public String toString()               // string representation of this board (in the output format specified below)
    {
//        String out = Integer.toString(n) + "\n";
//        for(int i=0; i < n; i++)
//        {
//            for(int j=0; j < n ; j++)
//            {
//                out = out + Integer.toString(tiles[i][j]) + "  ";
//            }
//            out = out + "\n";
//        }
//        return out;
        
    StringBuilder s = new StringBuilder();
    s.append(n + "\n");
    for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
            s.append(String.format("%2d ", tiles[i][j]));
        }
        s.append("\n");
    }
    return s.toString();

        
        
        
    }
    
    public static void main(String[] args) // unit tests (not graded)
    {
        int[][] arr = new int[3][3];
        arr[0][0] = 8;
        arr[0][1] = 4;
        arr[0][2] = 6;
        arr[1][0] = 5;
        arr[1][1] = 0;
        arr[1][2] = 3;
        arr[2][0] = 2;
        arr[2][1] = 7;
        arr[2][2] = 1;
        Board b = new Board(arr);
//        StdOut.print(b.toString());
//        StdOut.print(b.isGoal());
//        StdOut.print(b.hamming());
        
//        StdOut.print("\n"+b.manhattan()+ "\n");
        
        
//        StdOut.print(b.twin().toString());
//          int[][] arr2 = new int[3][3];
//        arr2[0][0] = 8;
//        arr2[0][1] = 7;
//        arr2[0][2] = 6;
//        arr2[1][0] = 5;
//        arr2[1][1] = 4;
//        arr2[1][2] = 3;
//        arr2[2][0] = 2;
//        arr2[2][1] = 2;
//        arr2[2][2] = 0;
//        Board c = new Board(arr2);
//        
//        StdOut.print(b.equals(c));
        
//        b.neighbors();
        
//        int[] loc1 = new int[2];
//        int[] loc2 = new int[2];
//        loc1[0] = 0;
//        loc1[1] = 0;
//        loc2[0] = 1;
//        loc2[1] = 1;
//        int[][] arr2 = b.swap(loc1,loc2);
//        Board c = new Board(arr2);
//            StdOut.print(c.toString());
        
        
//        Iterable <Board> neigh = b.neighbors();
//        for (Board board : b.neighbors())
//        {
//         StdOut.print(board.toString());   
//        }
        
       StdOut.print(b.twin().toString());
//        int[][]array = new int[2][2];
//        array[0][0] = 1;
//        array[0][1] = 2;
//        array[1][0] = 3;
//        array[1][1] = 0;
//        Board a = new Board(array);
//        StdOut.print(a.toString());
//        
//        StdOut.print(a.twin().toString());
        
        
    }
    
    
}