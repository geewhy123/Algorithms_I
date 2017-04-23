import edu.princeton.cs.algs4.*;
//import java.util.*;

public class Solver {
    private class Move implements Comparable<Move>
    {
        private int priority;
        private Board board;
        private int nMoves;
        private Move prev;
        
        public Move(Board board, Move prev)
        {
            
            
            this.board = board;
            this.prev = prev;
            
            if(this.prev == null)
                this.nMoves = 0;
            else this.nMoves = prev.nMoves+1;

            
            
            
            this.priority = this.nMoves+ board.manhattan();
        }
        public int compareTo(Move m)
        {
            if(this.priority > m.priority) return 1;
            else if(this.priority < m.priority)return -1;
            else return 0;
            
        }
        
        
    }
    
    
    
    private boolean qSolvable;
    private Move lastMove;
    private int nMoves;
    public Solver(Board initial)           // find a solution to the initial board (using the A* algorithm)
    {
        if(initial == null) throw new java.lang.NullPointerException("null arg");
        MinPQ<Move> moveQ= new MinPQ<Move>();
        MinPQ<Move> moveQTwin= new MinPQ<Move>();
        
        Move initMove = new Move(initial,null);
        moveQ.insert(initMove);
        Move minMove = moveQ.delMin();
        
        // twin
        
        
        Move initMoveTwin = new Move(initial.twin(),null);
        moveQTwin.insert(initMoveTwin);
        Move minMoveTwin = moveQTwin.delMin();
        
//        int count = 0;
        nMoves = 0;
        while (!(minMove.board.isGoal()) && !(minMoveTwin.board.isGoal()))
        {
            for(Board board: minMove.board.neighbors())
            {
                if((minMove.prev == null) || !(board.equals(minMove.prev.board)))
                {
                    moveQ.insert(new Move(board,minMove));
                    
                }
            }
            minMove = moveQ.delMin();
//            StdOut.print(minMove.board.toString());
//            StdOut.print(minMove.prev+ " " + minMove.nMoves + " " + minMove.priority);
//            System.exit(0);
            
//            nMoves++;
            for(Board board: minMoveTwin.board.neighbors())
            {
                 if((minMoveTwin.prev == null) || !(board.equals(minMoveTwin.prev.board)))
                moveQTwin.insert(new Move(board,minMoveTwin));
            }
            minMoveTwin = moveQTwin.delMin();
            
            
            
//            count++;
        }
        
        if(minMove.board.isGoal()) 
        {
            qSolvable = true;
//            StdOut.print("found sol");
            lastMove = minMove;
            
//            nMoves++;
//            nMoves = 0;
            while(minMove.prev != null)
            {
//                StdOut.print(minMove.prev.board.toString());  
                minMove = minMove.prev;
                nMoves++;
            }
        }
        else 
        {
            nMoves = -1;
            qSolvable = false;
//            StdOut.print("search failed");
        }
//        StdOut.print(minMove.board.toString());
//        while(minMove.prev != null)
//        {
//        StdOut.print(minMove.prev.board.toString());  
//        minMove = minMove.prev;
//        }
        
        
        
        
    }
    public boolean isSolvable()            // is the initial board solvable?
    {
        return qSolvable;
    }
    public int moves()                     // min number of moves to solve initial board; -1 if unsolvable
    {
        if(!isSolvable()) return -1;
        return nMoves;
    }
    public Iterable<Board> solution()      // sequence of boards in a shortest solution; null if unsolvable
    {
        if(!isSolvable()) return null;
        Move minMove = lastMove;
        Stack <Board> sol = new Stack<Board>();
        sol.push(minMove.board);
         while(minMove.prev != null)
        {
//        StdOut.print(minMove.prev.board.toString());  
             sol.push(minMove.prev.board);
        minMove = minMove.prev;
        }
        
        return sol;
    }
    
    // solve a slider puzzle (given below)
    public static void main(String[] args) {
        
        
        
        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] blocks = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
            blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);
        
        // solve the puzzle
        Solver solver = new Solver(initial);
        
        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
         for (Board board : solver.solution())
                StdOut.println(board);
//        StdOut.print(solver.moves());
    }
}