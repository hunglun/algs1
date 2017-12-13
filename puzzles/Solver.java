// Mathematical fact 1
/*To detect such situations, use the fact that boards are divided into two equivalence classes with respect to reachability: (i) those that lead to the goal board and (ii) those that lead to the goal board if we modify the initial board by swapping any pair of blocks (the blank square is not a block). 
 * 
 */

public class Solver {
  public Solver(Board initial) {}          // find a solution to the initial board (using the A* algorithm)
  public boolean isSolvable()  {return true;}          // is the initial board solvable?
  public int moves()                  {return 0;}   // min number of moves to solve initial board; -1 if unsolvable
  public Iterable<Board> solution()  {return null;}    // sequence of boards in a shortest solution; null if unsolvable
  public static void main(String[] args) {}// solve a slider puzzle (given below)
}
