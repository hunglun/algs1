// Mathematical fact 1
/*To detect such situations, use the fact that boards are divided into two equivalence classes with respect to reachability: (i) those that lead to the goal board and (ii) those that lead to the goal board if we modify the initial board by swapping any pair of blocks (the blank square is not a block). 
 * 
 */
import edu.princeton.cs.algs4.ResizingArrayStack;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
public class Solver {
  private MinPQ<SearchNode> pq;
  private MinPQ<SearchNode> pqTwin;
  private SearchNode node, nodeTwin;
  
  private ResizingArrayStack<Board> solution; 
    
  public Solver(Board initial) { // find a solution to the initial board (using the A* algorithm)
    if(initial == null)
      throw new IllegalArgumentException("empty board");
    pq = new MinPQ<SearchNode>();
    pq.insert(new SearchNode(initial,null,0));  
    node = pq.delMin();
    SearchNode nextNode;
    
    //twin
    pqTwin = new MinPQ<SearchNode>();
    pqTwin.insert(new SearchNode(initial.twin(),null,0));
    nodeTwin = pqTwin.delMin();
    SearchNode nextNodeTwin;
     
    solution = new ResizingArrayStack<Board>();
    //solution.enqueue(initial);
    while(node.board.isGoal() == false && nodeTwin.board.isGoal() == false){
      
      
      for(Board nextBoard : node.board.neighbors()){
        // critical optimisation
        if (node.predecessor == null || nextBoard.equals(node.predecessor.board) == false){
          nextNode = new SearchNode(nextBoard,node,node.moves+1);
          pq.insert(nextNode);
        }
      }
      node = pq.delMin();
      
      
      //repeat the process for twin board
      for(Board nextBoard : nodeTwin.board.neighbors()){
        // critical optimisation
        if (nodeTwin.predecessor == null || nextBoard.equals(nodeTwin.predecessor.board) == false){
          nextNode = new SearchNode(nextBoard,nodeTwin,nodeTwin.moves+1);
          pqTwin.insert(nextNode);
        }
      }
      nodeTwin = pqTwin.delMin();
    }
    if(node.board.isGoal()){
      for(SearchNode n = node; n!=null; n = n.predecessor)
        solution.push(n.board);
    }
  }         
  // Search Node 
  private class SearchNode implements Comparable<SearchNode> {
    Board board;
    SearchNode predecessor;
    int moves;
    public SearchNode(Board board, SearchNode predecessor, int moves){
      
      this.board = board;
      this.predecessor = predecessor;
      this.moves = moves;
    }
    
    public int compareTo(SearchNode that) {
      if(this.moves + this.board.manhattan() < that.moves + that.board.manhattan()) return -1;
      if(this.moves + this.board.manhattan() > that.moves + that.board.manhattan()) return  1;
      return 0;
    }
  }
  public boolean isSolvable()  {
    return node.board.isGoal();
  }          // is the initial board solvable?

  public int moves()                  {
    return node.board.isGoal()?solution.size()-1:-1;
  }   // min number of moves to solve initial board; -1 if unsolvable
  
  public Iterable<Board> solution()  {
    if (isSolvable()){
      return solution;
    }
    else
      return null;
  
  }    // sequence of boards in a shortest solution; null if unsolvable
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
    
  }// solve a slider puzzle (given below)
}
