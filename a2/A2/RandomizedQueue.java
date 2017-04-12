import java.util.Iterator;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;
import java.util.NoSuchElementException;
public class RandomizedQueue<Item> implements Iterable<Item> {
    
    private Item[] q;       // queue elements
    private int n;          // number of elements on queue
//    private int first;      // index of first element of queue
//    private int last; 
    public RandomizedQueue()                 // construct an empty randomized queue
    {
        q = (Item[]) new Object[2];
        n = 0;
//        first = 0;
//        last = 0;
    }
    public boolean isEmpty()                 // is the queue empty?
    {return n==0;}
    public int size()                        // return the number of items on the queue
    {return n;}
    // resize the underlying array
    private void resize(int capacity) {
        assert capacity >= n;
        Item[] temp = (Item[]) new Object[capacity];
        for (int i = 0; i < n; i++) {
            temp[i] = q[i];//q[(first + i) % q.length];
        }
        q = temp;
//        first = 0;
//        last  = n;
    }
    
    public void enqueue(Item item)           // add the item
    {
        if (item == null) throw new java.lang.NullPointerException("NULL item");
        // double size of array if necessary and recopy to front of array
        if (n == q.length) resize(2*q.length);   // double size of array if necessary
//        q[last++] = item;                        // add item
//        if (last == q.length) last = 0;          // wrap-around
        q[n] = item;
        n++;
        
        
//        StdOut.print("ENQ-F:" + first + ":" + last+ "\n");
//        StdOut.print("Array is now ("+n+") ");
//           for(int i=0; i < n; i++)
//               StdOut.print(q[i] + " " );
//           StdOut.print("\n");
    }
    public Item dequeue()                    // remove and return a random item
    {
        if (isEmpty()) throw new NoSuchElementException("Queue underflow");
           int r = StdRandom.uniform(n);
           
           Item item = q[r];
           if(r != n-1 ){
           q[r] = q[n-1];
           q[n-1] = null;
//         last--;
           }
           else{
            q[r] = null;
            
//             last--;
           }

           n--;
 // shrink size of array if necessary
           if (n > 0 && n == q.length/4) resize(q.length/2); 
           
           
//           StdOut.print("DEQ-F:" + first + ":" + last+ "\n" + " r = " + r + " size = " + n + " ");
//           StdOut.print("Array is now ");
//           for(int i=0; i < n; i++)
//               StdOut.print(q[i] + " " );
//           StdOut.print("\n");
           return item;
           
           
//        Item item = q[first];
//        q[first] = null;                            // to avoid loitering
//        n--;
//        first++;
//        if (first == q.length) first = 0;           // wrap-around
//        // shrink size of array if necessary
//        if (n > 0 && n == q.length/4) resize(q.length/2); 
//        return item;
     
    }
    public Item sample()                     // return (but do not remove) a random item
    {
       if(isEmpty()) throw new NoSuchElementException();
       int r = StdRandom.uniform(n);
          return q[r];
    }
    public Iterator<Item> iterator()         // return an iterator over items in order from front to end
    { return new ArrayIterator();
    }
    private class ArrayIterator implements Iterator<Item> {
        private int i = 0;
        private int newN = n;
        private Item newArray[] = (Item[]) new Object[n];
        private ArrayIterator(){
            for(int i=0; i <newN; i++){
             newArray[i] = q[i];   
            }
        }
        public boolean hasNext()  { return i < newN;                               }
        public void remove()      { throw new UnsupportedOperationException();  }
        
        public Item next() {
//            StdOut.print(newN);
            
           if (!hasNext()) throw new NoSuchElementException();
//            Item item = q[i];//q[(i + first) % q.length];
//            i++;
//            return item;
           int k = StdRandom.uniform(newN);
           Item item = newArray[k];
           if(k != newN-1)
               newArray[k] = newArray[newN-1];
           newN--;
           newArray[newN] = null;
           return item;
        }
    }
    
    
    public static void main(String[] args)   // unit testing (optional)
    {
//    RandomizedQueue<String> q = new RandomizedQueue<String>();
        
   RandomizedQueue<Integer> q = new RandomizedQueue<Integer>();
//  q.size();
//         q.size()       ;
//         q.isEmpty()    ;
//         q.isEmpty()   ; 
        q.enqueue(14);
  q.enqueue(16);
   q.enqueue(18);
//        q.enqueue(20);
//         StdOut.print(q.dequeue() + "\n") ;
//   q.sample();
//         q.enqueue(22);
//         StdOut.print(q.dequeue()) ;
         
//    for(int i=0; i < s; i++)
//    StdOut.print(q.dequeue());
   
        for (Integer t : q){
            for(Integer tt:q){
            StdOut.print("A");
            }
        }
   }
}