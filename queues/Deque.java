import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
  private Node<Item> first;    // beginning of queue
  private Node<Item> last;     // end of queue
  private int n;               // number of elements on queue
  
  // helper linked list class
  private static class Node<Item> {
    private Item item;
    private Node<Item> next;
      //   private Node<Item> prev;
  }
  public Deque()                           // construct an empty deque
  {
    first = null;
    last  = null;
    n = 0;
  }
  public boolean isEmpty()                 // is the deque empty?
  { 
    return n == 0;
  }
  public int size()                        // return the number of items on the deque
  {
    return n;
  }
  public void addFirst(Item item)          // add the item to the front
  {
    if(item == null){
      throw new IllegalArgumentException("item is null");
    }
  }
  public void addLast(Item item)           // add the item to the end
  {
    if(item == null){
      throw new IllegalArgumentException("item is null");
    }
    Node<Item> oldlast = last;
    last = new Node<Item>();
    last.item = item;
    last.next = null;
    if (isEmpty()) first = last;
    else   {
        oldlast.next = last;
	//	last.prev = oldlast;
    }
    n++;

  }
  public Item removeFirst()                // remove and return the item from the front
  { 
    if (first == null){
      throw new NoSuchElementException("Queue underflow");
    }
    Item item = first.item;
    first = first.next;
    n--;
    if (isEmpty()) last = null;   // to avoid loitering
    return item;
  }
  public Item removeLast()                 // remove and return the item from the end
  { 
    if (last == null){
      throw new NoSuchElementException("Queue underflow");
    }
    return null;
  }
  public Iterator<Item> iterator()         // return an iterator over items in order from front to end
  { 
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

  }
}
