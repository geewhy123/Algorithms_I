/**
 * Auto Generated Java Class.
 */
//import java.lang.Integer;
import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
  
   private int nTrials;
   private double []fracs;
   public PercolationStats(int n, int trials)    // perform trials independent experiments on an n-by-n grid
   {
        if (n<= 0 || trials <= 0) throw new IllegalArgumentException("n, trials nonnegative") ;
       
      fracs = new double[trials];
       nTrials = trials;
      for(int i=0; i < trials; i++){
          Percolation perc = new Percolation(n);
        
          while(!(perc.percolates())){
              int m1 = StdRandom.uniform(n)+1;
              int m2 = StdRandom.uniform(n)+1;
              perc.open(m1,m2);
          }
          double frac = (double)perc.numberOfOpenSites()/(n*n);
//          System.out.println(frac);
          fracs[i] = frac;
      }
       
   }
       public double mean()                          // sample mean of percolation threshold
   {
           return StdStats.mean(fracs);
   }
       public double stddev()                        // sample standard deviation of percolation threshold
   {
            return StdStats.stddev(fracs);
   }
       public double confidenceLo()                  // low  endpoint of 95% confidence interval
   {
            return mean()-1.96*stddev()/Math.sqrt(nTrials);
   }
       public double confidenceHi()                  // high endpoint of 95% confidence interval
   {
           return mean()+1.96*stddev()/Math.sqrt(nTrials);
   }
   public static void main(String[] args)        // test client (described below)
   {
      int n =  Integer.parseInt(args[0]);
      int T =  Integer.parseInt(args[1]);
        
   PercolationStats ps = new PercolationStats(n,T);
   System.out.println("mean \t\t  = " + ps.mean()); 
   System.out.println("stddev \t\t  = " + ps.stddev());
   System.out.println("95% confidence interval = [" + ps.confidenceLo()+", " + ps.confidenceHi()+"]");
   }
   
}

