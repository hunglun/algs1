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

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Out;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {

    // n x n grid to model percolation system
    private int n;
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
	uf = new WeightedQuickUnionUF(n*n);

	this.n = n;
	// grid[0][x] and grid[x][0] are not used.
	this.grid = new boolean[n+1][n+1]; // comply with the convention of col and row indices
	for(int i=0; i<n+1; i++){
	    for(int j=0; j<n+1; j++){
		grid[i][j] = false; // false : blocked, true : open
	    }
	}
    }
    public    void open(int row, int col) {    // open site (row, col) {} if it is not open already
	// TODO remove these function calls. Just to check that they could compile
	uf.union(row,col);
	uf.find(0);
	uf.connected(0,1);
	uf.count();
    }
    public boolean isOpen(int row, int col) {   // is site (row, col) open?
	return true;
    }
    public boolean isFull(int row, int col) {  // is site (row, col) full?
	return true;
    }
    public     int numberOfOpenSites() {       // number of open sites
	return 0;
    }
    public boolean percolates() {              // does the system percolate?
	return true;
    }


    public static void main(String[] args) { // this is optional
    }
}
