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
  private Item removeFirst(){
    Item item = first.item;
        first = first.next;
        n--;
        if (isEmpty()) last = null;   // to avoid loitering
        StdOut.println("Random number is 0");
        StdOut.println("Removed item is "  + item);
        return item;
  }
  public Item dequeue()                    // remove and return a random item
  {
    if (isEmpty()) throw new NoSuchElementException("Queue underflow");
    
    Node<Item> discarded;
    Node<Item> prev = null;
    int randNum = StdRandom.uniform(0,size()); // get a random number between 0 and size - 1
    if (randNum == 0) return removeFirst();
    // use iterator and find randNum th element in the queue
    // remove it
    StdOut.println("Random number is " + randNum);
    discarded = first;
    for(Node<Item> node=first; randNum>0; node = node.next){
 //     StdOut.println(node.item);
      prev = node;
      discarded = node.next;
      randNum--;
    }
    
    Item item = discarded.item; 
    if (prev != null)
      prev.next = discarded.next;
    discarded.next = null;
    n--;
    if (isEmpty()){ 
      last = null;   // to avoid loitering
      first = null;
    }
    
//    StdOut.println();
    StdOut.println("Removed item is "  + item);
    return item;
    
  }
  public Item sample()                     // return a random item (but do not remove it)
  {
    return null;
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
    private Node<Item> current;
    
    public ListIterator(Node<Item> first) {
      current = first;
    }
    
    public boolean hasNext()  { return current != null;                     }
    public void remove()      { throw new UnsupportedOperationException();  }
    
    public Item next() {
      if (!hasNext()) throw new NoSuchElementException();
      Item item = current.item;
      current = current.next; 
      return item;
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

