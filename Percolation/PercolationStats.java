/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

public class PercolationStats {
    // perform independent trials on an n-by-n grid
    // private StdStats stats;
    private double mean = 1;
    private double moe;
    private double T;
    private double stddev = 1;
    // private static double[] pArray;

    public PercolationStats(int n, int trials) {
        if ((n <= 0) || (trials <= 0)) {
            throw new IllegalArgumentException("Age must be greater than zero");
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return mean;
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        // return StdStats.stddev(pArray);
        return stddev;

    }

    // low endpoint of 95% confidence interval
    public double confidenceLo() {
        return mean - moe;
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return mean + moe;
    }

    public static void main(String[] args) { // args[0] n = 200 // args[1] T = 100
        // int n = Integer.parseInt(args[0]);
        // int T = Integer.parseInt(args[1]);
        int n = 20;
        int T = 100;
        double z = 1.96; // CI95%
        // PercolationStats percStats = new PercolationStats(Integer.parseInt(args[0]),
        //                                                   Integer.parseInt(args[1]));
        PercolationStats percStats = new PercolationStats(n, T);
        double[] pArray = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                // open random cell
                percolation.open(StdRandom.uniform(1, n + 1),
                                 StdRandom.uniform(1, n + 1));
            }
            pArray[i] = ((double) percolation.numberOfOpenSites() / (double) (n * n));
        }
        percStats.stddev = StdStats.stddev(pArray);
        percStats.mean = StdStats.mean(pArray);
        // percStats.mean = percStats.mean();
        percStats.moe = z * percStats.stddev() / Math.sqrt(n);
        System.out.println("mean = " + percStats.mean);
        System.out.println("stddev = " + percStats.moe);
        System.out.println(
                "95% confidence interval = [" + (percStats.mean - percStats.moe) + "," + (
                        percStats.mean + percStats.moe) + "]");
    }
}
