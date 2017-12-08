import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdOut;
public class PercolationStats {
  private double x[];
  private int trials;
  public PercolationStats(int n, int trials){
    if (n <= 0 || trials <=0 )
      throw new IllegalArgumentException("n or trials not positive");
    this.trials=trials;
    this.x = new double[trials];
    for(int i=0;i<trials;i++){
      Percolation perc = new Percolation(n);
      while(perc.percolates() == false){
        int row = StdRandom.uniform(1,n+1);
        int col = StdRandom.uniform(1,n+1);
        perc.open(row,col);
      }
      this.x[i]=perc.numberOfOpenSites()/((double)n*n);
    }
  }    // perform trials independent experiments on an n-by-n grid
  public double mean(){ 
    double result = 0 ;
    for(int i=0; i<this.trials; i++){
      result = result + this.x[i];
    }
    return result / this.trials;
  }                          // sample mean of percolation threshold
  public double stddev(){
    double result = 0;
    double mean = mean();
    for (double i : this.x) {
      result += Math.pow(( i - mean), 2);
    }
    assert(this.x.length == this.trials);
    return Math.sqrt(result/this.trials);
  }                        // sample standard deviation of percolation threshold
  public double confidenceLo(){
    double mean = mean();
    double s = stddev();
    return mean - (1.96 * s)/Math.sqrt(this.trials);
  }                  // low  endpoint of 95% confidence interval
  public double confidenceHi(){
    double mean = mean();
    double s = stddev();
    return mean + (1.96 * s)/Math.sqrt(this.trials);
  } // high endpoint of 95% confidence interval
  
  public static void main(String[] args){
    int n = Integer.parseInt(args[0]);
    int trials = Integer.parseInt(args[1]);
    
    PercolationStats p = new PercolationStats(n,trials);    
    StdOut.println("mean = " + p.mean());
    StdOut.println("stddev = " + p.stddev());
    StdOut.println("95% confidence interval = [" + p.confidenceLo() + "," + p.confidenceHi() + "]");    
    
  }        // test client (described below)
}
