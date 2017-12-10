import java.util.Iterator;
import java.util.NoSuchElementException;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
public class RandomizedQueue<Item> implements Iterable<Item> {
  private Node<Item> first;    // beginning of queue
  private Node<Item> last;     // end of queue
  private int n;               // number of elements on queue
  
  // helper linked list class
  private static class Node<Item> {
    private Item item;
    private Node<Item> next;
  }
  
  public RandomizedQueue()                 // construct an empty randomized queue
  {
    first = null;
    last  = null;
    n = 0;
  }
  /**
   * Returns true if this queue is empty.
   *
   * @return {@code true} if this queue is empty; {@code false} otherwise
   */
  public boolean isEmpty() {
    return first == null;
  }
  /**
   * Returns the number of items in this queue.
   *
   * @return the number of items in this queue
   */
  public int size() {
    return n;
  }
  
  /**
   * Adds the item to this queue.
   *
   * @param  item the item to add
   */
  public void enqueue(Item item) {
    Node<Item> oldlast = last;
    last = new Node<Item>();
    last.item = item;
    last.next = null;
    if (isEmpty()) first = last;
    else           oldlast.next = last;
    n++;
  }

  public Item dequeue()                    // remove and return a random item
  {
    if (isEmpty()) throw new NoSuchElementException("Queue underflow");
    
    Node<Item> discarded;
    Node<Item> prev = null;
    int randNum = StdRandom.uniform(0,size()); // get a random number between 0 and size - 1

    //    StdOut.println("Random number is " + randNum);
    discarded = first;
    for(Node<Item> node=first; randNum>0; node = node.next){

      prev = node;
      discarded = node.next;
      randNum--;
    }
    
    Item item = discarded.item; 
    if (discarded == first){
      first = first.next;
    }
    if (prev != null)
      prev.next = discarded.next;
    discarded.next = null;
    n--;
    if (isEmpty()){ 
      last = null;   // to avoid loitering
      first = null;
    }
    

    //    StdOut.println("Removed item is "  + item);
    return item;
    
  }
  public Item sample()                     // return a random item (but do not remove it)
  {
    int randNum = StdRandom.uniform(0,size()); // get a random number between 0 and size - 1
    //    StdOut.println("Random number is " + randNum);
    Node<Item> node = first;
    for(node=first; randNum>0; node = node.next){
      randNum--;
    }
    //    StdOut.println("Sample is " + node.item);
    return node.item;
  }
  public Item remove(){
    throw new UnsupportedOperationException();
  }
  /**
   * Returns an iterator that iterates over the items in this queue in FIFO order.
   *
   * @return an iterator that iterates over the items in this queue in FIFO order
   */
  public Iterator<Item> iterator()  {
    return new ListIterator<Item>(first);  
  }
  
  // an iterator, doesn't implement remove() since it's optional
  private class ListIterator<Item> implements Iterator<Item> {
    private RandomizedQueue<Item> queue;
    public ListIterator(Node<Item> first) {
      queue = new RandomizedQueue<Item>();
      for(Node<Item> node = first; node != null; node = node.next){
        queue.enqueue(node.item);
      }
      
      
    }
    
    public boolean hasNext()  { return !queue.isEmpty();                     }
    public void remove()      { throw new UnsupportedOperationException();  }
    
    public Item next() {
      if (!hasNext()) throw new NoSuchElementException();
      return queue.dequeue();
    }
  }
  public static void main(String[] args)   // unit testing (optional)
  {
    RandomizedQueue<String> queue = new RandomizedQueue<String>();
    queue.enqueue("0");
    queue.enqueue("1");
    queue.enqueue("2");
    queue.enqueue("3");
    queue.enqueue("4");
    
    queue.sample();
    queue.sample();
    queue.sample();
    queue.sample();
    queue.sample();
    for (String s: queue){
      StdOut.println("Iterator " + s );
    }
    queue.dequeue();
    queue.dequeue();
    queue.dequeue();
    queue.dequeue();
    queue.dequeue();
    assert(queue.isEmpty());
    /*
    for(String s : queue){
      StdOut.println(s);
    }
    queue.dequeue();
    
    for(String s : queue){
      StdOut.println(s);
    }
    queue.dequeue();
    for(String s : queue){
      StdOut.println(s);
    }
    queue.dequeue();
    for(String s : queue){
      StdOut.println(s);
    }
    queue.dequeue();
    for(String s : queue){
      StdOut.println(s);
    }*/
  }
}

