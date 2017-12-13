public class Board {
  private final int n;
  private final int[][] tiles;
    public Board(int[][] blocks)           // construct a board from an n-by-n array of blocks
    { n = blocks.length;
      tiles = new int[n][n];
      for(int i=0; i<blocks.length;i++){
      for(int k=0; k<blocks.length;k++){
        tiles[i][k] = blocks[i][k];
      }
      }
    }       // (where blocks[i][j] = block in row i, column j)
    public int dimension() {return 0;}                // board dimension n
    public int hamming()             {return 0;}      // number of blocks out of place
    public int manhattan() {return 0;}                // sum of Manhattan distances between blocks and goal
    public boolean isGoal()  {return false;}              // is this board the goal board?
    public Board twin() { return null;}                   // a board that is obtained by exchanging any pair of blocks
    public boolean equals(Object y)    {return false;}    // does this board equal y?
    public Iterable<Board> neighbors()  {return null;}   // all neighboring boards
    public String toString()               // string representation of this board (in the output format specified below)
    { StringBuilder s = new StringBuilder();
      s.append(n + "\n");
      for (int i = 0; i < n; i++) {
        for (int j = 0; j < n; j++) {
          s.append(String.format("%2d ", tiles[i][j]));
        }
        s.append("\n");
      }
      return s.toString();
    }
    public static void main(String[] args) // unit tests (not graded)
    {}
}
