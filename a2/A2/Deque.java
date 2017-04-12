import java.util.Iterator;
//import java.io;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class Deque<Item> implements Iterable<Item> {
   private Node first;
   private Node last;
   private int N;
   private class Node{
    Item item;
    Node next;
    Node prev;    
   }
   
    public Deque()                          // construct an empty deque
    {
    }
   public boolean isEmpty()                 // is the deque empty?
   {
//       return first == null;
       return N==0;
   }
   public int size()                       // return the number of items on the deque
   {return N;}
   public void addFirst(Item item)         // add the item to the front
   {
       if(item == null) throw new java.lang.NullPointerException("add null");
       Node oldFirst = first;
       first = new Node();
       first.item = item;
       first.prev = null;
       first.next = null;
       if(isEmpty()){
           last = first;
       }
       else 
       {
          oldFirst.prev = first;
           first.next = oldFirst;
       }
       N++;
       
   }
   public void addLast(Item item)           // add the item to the end
   {
      if(item == null) throw new java.lang.NullPointerException("add null");
       Node oldLast = last;
       last = new Node();
       last.item = item;
       last.next = null;
       if(isEmpty()){
           first = last;
       }
       else 
       {
           oldLast.next = last;
          last.prev = oldLast;
       }
       N++;
   }
   public Item removeFirst()               // remove and return the item from the front
   {
       if(isEmpty()) throw new java.util.NoSuchElementException("rm empty");
       Item item = first.item;
       first = first.next;
//       first.prev = null;
//       if(isEmpty()) last = null;
       N--;
       if(isEmpty()) last = null;
       else first.prev = null;
       
       
       return item;
   }
   public Item removeLast()                // remove and return the item from the end
   { 
     if(isEmpty()) throw new java.util.NoSuchElementException("rm empty");
       Item item = last.item;
       last = last.prev;
//       last.next = null;
//       if(isEmpty()) first = null;
       N--;
       if(isEmpty()) first = null;
       else last.next = null;
       return item;

   }
   public Iterator<Item> iterator()         // return an iterator over items in order from front to end
   {return new ListIterator();}
   private class ListIterator implements Iterator<Item>
   {
    private Node current = first;
    public boolean hasNext()
    {return current != null;}
    public void remove(){ 
    throw new java.lang.UnsupportedOperationException("not supp");
    }
    public Item next()
    {
       if(!hasNext()) throw new java.util.NoSuchElementException("no next");
        Item item = current.item;
        current = current.next;
        return item;
    }
   
    
    
   }
   
   
   public static void main(String[] args)   // unit testing (optional)
   {
//   Deque <String> q = new Deque<String>();
   
//   while(!StdIn.isEmpty())
//   {
//        String item = StdIn.readString();
//        if(!item.equals("-"))
//            q.addLast(item);
//        else if (!q.isEmpty()) StdOut.print(q.removeFirst() + " ");
//   }
//   StdOut.println("(" + q.size() + " left on queue)");
//   
   
   
//   q.addLast("a");
//   q.addLast("b");
//   q.addLast("c");
//    q.addLast("d");
   
//       q.addFirst("a");
//       q.addLast("b");
////       q.removeLast();
//       StdOut.print(q.size());
//       StdOut.print("\n");
//       Iterator it = q.iterator();
//       while( it.hasNext() ) {
//           StdOut.print( it.next() );
//       }
//       
//   q.addLast("a");
//   q.addFirst("b");
   
       
//        q.addFirst("b");
//   int s = q.size();
//   for(int i=0; i < s;i++){
//   StdOut.print("##:" + q.removeFirst()+ "\n");
//   }
//   
//   
//   Deque <String> p = new Deque<String>();
//   
////          p.isEmpty();
//          p.addLast("a");
//          p.removeLast();
             
   
   }
}