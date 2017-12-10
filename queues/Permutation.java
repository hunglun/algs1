import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdIn;
public class Permutation {
    public static void main(String[] args) {
	RandomizedQueue<String> queue = new RandomizedQueue<String>();

	int k = Integer.parseInt(args[0]);
	while (!StdIn.isEmpty()) {
	    String item = StdIn.readString();
	    queue.enqueue(item);
	}
	while(k!=0){
	    k--;
	    StdOut.println(queue.dequeue());
	}
    }
}
