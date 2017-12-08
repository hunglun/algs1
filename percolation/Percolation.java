/******************************************************************************
 *  Compilation:  javac Percolation.java
 *  Execution:    java Percolation input.txt
 *  Dependencies: Percolation.java
 *
 *  Edit History
 *  Wed Dec  6 23:41:59 +08 2017 - created initial version
 *
 *  This program estimates the value of the percolation threshold via Monte Carlo simulation
 *
 ******************************************************************************/
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    // n x n grid to model percolation system
    private int n;
    private int count; // count of open sites
    private boolean grid[][];
    private WeightedQuickUnionUF uf;
    /**
     * create n-by-n grid, with all sites blocked
     * @param n n-by-n grid
     * @throws IllegalArgumentException if n is 0 or less
     */
    public Percolation(int n){
      if (n <= 0)
        throw new IllegalArgumentException("n is 0 or less");
      uf = new WeightedQuickUnionUF(n*n+2);
      this.count = 0;
      this.n = n;
      
      this.grid = new boolean[n][n]; // comply with the convention of col and row indices
      for(int i=0; i<n; i++){
        for(int j=0; j<n; j++){
          grid[i][j] = false; // false : blocked, true : open
        }
      }
      
      // connect top virtual site with the top row
      // let n*n be the top virtual site
      for(int i=0; i<n; i++){
        uf.union(n*n,i);
      }
      // connect bottom virtual site with the bottom row
      // let n*n+1 be the bottom virtual site
      for(int i=0; i<n; i++){
        uf.union(n*n+1,n*(n-1)+i);
      }
    }
    public    void open(int row, int col) {    // open site (row, col) {} if it is not open already
      row = row - 1;
      col = col - 1;
      if (this.grid[row][col] == false){
        this.grid[row][col] = true;
        this.count++;
      }
      // union with neighbouring sites
      for(int k=-1;k<2;k++){
        for(int i=-1;i<2;i++){
          if (row+i < 0 || col+k <0) continue;
          if (row+i > this.n-1 || col+k >this.n-1) continue;
          if(this.grid[row+i][col+k] && this.grid[row][col]){
            // Check what happens with union(m,m) ?
            uf.union(row*this.n+col, (row+i)*this.n+(col+k));
          }
        }
      }
      // uf.union(row,col);
      // uf.find(0);
      // uf.connected(0,1);
      // uf.count();
    }
    public boolean isOpen(int row, int col) {   // is site (row, col) open?
      row = row - 1;
      col = col - 1;
      return this.grid[row][col];
    }
    public boolean isFull(int row, int col) {  // is site (row, col) full?
      if (isOpen(row,col)){
        row = row - 1;
        col = col - 1;
        return uf.connected(n*n,row*this.n+col);
      }
      return false;
    }
    public int numberOfOpenSites() {       // number of open sites
      return this.count;
    }
    public boolean percolates() {              // does the system percolate?
      return uf.connected(n*n,n*n+1);
    }
    public static void main(String[] args) { // this is optional
      Percolation perc = new Percolation(5);
      for(int k=-1;k<2;k++){
        StdOut.println(k);
      }
      assert(perc.numberOfOpenSites() == 0);
      assert(perc.isFull(1,1) == false);
      perc.open(1,1);
      assert(perc.isFull(1,1));
      
      assert(perc.isFull(2,1) == false);
      perc.open(2,1);
      assert(perc.isOpen(2,1));
      assert(perc.isFull(2,1));
      
      perc.open(3,1);
      perc.open(4,1);
      perc.open(5,1);
      assert(perc.isFull(5,1));
      
   
      assert(perc.percolates());
      StdOut.println("End Percolation Program");
    }
}
