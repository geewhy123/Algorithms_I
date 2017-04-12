
import edu.princeton.cs.algs4.StdIn;

import edu.princeton.cs.algs4.StdOut;
public class Permutation {
   private int numStrings; 

   public static void main(String[] args)
   {
         
       RandomizedQueue<String> q = new RandomizedQueue<String>();
     
       String s;
       while(!(StdIn.isEmpty())){  
           s = StdIn.readString();
//           StdOut.print(s);
           q.enqueue(s);
       }
       int k = Integer.parseInt(args[0]);
//       StdOut.print(k);

       
       for(int i=0; i < k; i++)
           StdOut.print(q.dequeue() + "\n");
       
   }
}