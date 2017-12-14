import edu.princeton.cs.algs4.ResizingArrayStack;
import edu.princeton.cs.algs4.StdOut;
public class Board {
  private final int n;
  private final int[][] tiles;
  public Board(int[][] blocks)           // construct a board from an n-by-n array of blocks
  { 
    n = blocks.length;
    tiles = new int[n][n];
    for(int i=0; i<blocks.length;i++){
      for(int k=0; k<blocks.length;k++){
        tiles[i][k] = blocks[i][k];
      }
    }
  }       // (where blocks[i][j] = block in row i, column j)
  public int dimension() {return n;}                // board dimension n
  public int hamming()             { // number of blocks out of place
    int sum=0;
    for(int i=0; i<n; i++){
      for(int j=0; j<n; j++){
        int x = tiles[i][j];
        if(x == 0) continue; 
        if(tiles[i][j]!=i*n+j+1) sum++;
      }
    }
    return sum;
  }      
  public int manhattan() {
    int sum=0;
    for(int i=0; i<n; i++){
      for(int j=0; j<n; j++){
        
        int x = tiles[i][j];
        if(x == 0) continue; 
        int col = (x - 1) % n; 
        int row = (x - 1) / n; 
        sum += Math.abs(col-j) + Math.abs(row-i);
      }
    }
    return sum;
  }                // sum of Manhattan distances between blocks and goal
  public boolean isGoal()  {return hamming()==0;}              // is this board the goal board?
  public Board twin() { 
   
    // create a new board
    Board board = new Board(tiles);
   
    if(board.tiles[0][0] == 0 || board.tiles[0][1] == 0  ){
      // swap the second row
      int tmp= board.tiles[1][1];
      board.tiles[1][1] = board.tiles[1][0];
      board.tiles[1][0] = tmp; 
    }else{
      // swap the first row
      int tmp= board.tiles[0][1];
      board.tiles[0][1] = board.tiles[0][0];
      board.tiles[0][0] = tmp;
    }
    return board;
  }                   // a board that is obtained by exchanging any pair of blocks
  @Override
  public boolean equals(Object other)    {
    
    if (other == this) return true;
    if (other == null) return false;
    if (other.getClass() != this.getClass()) return false;
    Board that = (Board) other;
    
    for(int i=0; i<n; i++){
      for(int j=0; j<n; j++){
        if(tiles[i][j]!=that.tiles[i][j]) return false;
      }
    }
    
    return true;
    
  }    // does this board equal y?
  
  //swap modify board
  // x1,y1 indicate the empty square.
  // x2,y2 indicate a neighbouring square of the empty square.
  private boolean move(int board[][], int x1, int y1, int x2, int y2){
    if(x2<0 || x2 > 2) return false;
    if(y2<0 || y2 > 2) return false;
    board[x1][y1] = board[x2][y2] ;
    board[x2][y2] = 0;
    return true;
  }
  
  public Iterable<Board> neighbors()  {
    // identify the location of empty square
    int x=0;
    int y=0;
    
    int board[][] = new int[n][n];
    // create a new board
    for(int i=0; i<n; i++){
      for(int j=0; j<n; j++){
        board[i][j] = tiles[i][j];
        if(tiles[i][j]== 0) {
          x = i;
          y = j;
        };
      }
    }
    
    ResizingArrayStack<Board> stack = new ResizingArrayStack<Board>();
    // add neighbours
    Board b = new Board(board);
    if (move(b.tiles,x,y,x-1,y))
      stack.push(b);
    b = new Board(board);  
    if (move(b.tiles,x,y,x+1,y))
      stack.push(b);
    b = new Board(board);  
    if (move(b.tiles,x,y,x,y-1))
      stack.push(b);
    b = new Board(board);  
    if (move(b.tiles,x,y,x,y+1))
      stack.push(b);
    
    return stack;
  }   // all neighboring boards
  
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
  {
    int a[][]= {
      {8,1,3},
      {4,0,2},
      {7,6,5}
    };
    Board board = new Board(a);
    StdOut.println(board);
    for(Board n : board.neighbors()){
      StdOut.println(n);
      
    }
    
    StdOut.println("Twin " + board.twin());
    assert(board.equals(board.twin()) == false);
    assert(board.equals(new Board(board.tiles)));
    assert(board.hamming() == 5);
    assert(board.manhattan() == 10);
    
    
  }
}
