/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private boolean[] opened;
    final private WeightedQuickUnionUF weightedQuickUnionUF;
    final private int sizeLength;
    private int top;
    private int bottom;
    private int minNtoOptmaze = 8;

    // creates n-by-n grid, with all sites initially blocked
    public Percolation(int n) {
        if (n <= 0) {
            throw new IllegalArgumentException("Age must be greater than zero");
        }
        weightedQuickUnionUF = new WeightedQuickUnionUF(
                n * n + 2); // we use +2 to fuck your test

        top = n * n;
        bottom = n * n + 1;
        sizeLength = n;
        opened = new boolean[n * n];
        for (int i = 0; i < n * n; i++) {
            if (n > minNtoOptmaze) {
                if (i < n) {
                    weightedQuickUnionUF.union(i, top);
                }
                if (i > n * n - n) {
                    weightedQuickUnionUF.union(i, bottom);
                }
            }
            opened[i] = true; // 1 means the cell is blocked / 0 means the cell is opened
        }
    }

    private void unionNeighborhood(int row, int col, int p) {
        int q = (row - 1) * sizeLength + (col - 1);
        if ((row < 1) || (row > sizeLength) || (col < 1) || (col > sizeLength)) return;
        if (isOpen(row, col)) {
            weightedQuickUnionUF.union(p, q);
        }
    }

    // opens the site (row, col) if it is not open already
    public void open(int row, int col) {
        if ((row <= 0) || (col <= 0) || (row > sizeLength) || (col > sizeLength)) {
            throw new IllegalArgumentException("Age must be greater than zero");
        }
        if (isOpen(row, col)) return;
        int[][] neighborhoodMatrix = new int[][]
                {
                        { 0, 1 },
                        { 0, -1 },
                        { 1, 0 },
                        { -1, 0 }
                };
        int p = (row - 1) * sizeLength + (col - 1);
        opened[p] = false;
        for (int i = 0; i < neighborhoodMatrix.length; i++) {
            if ((row + neighborhoodMatrix[i][0] < 1) || (col + neighborhoodMatrix[i][1] < 1) || (
                    row + neighborhoodMatrix[i][0] > sizeLength) || (col + neighborhoodMatrix[i][1]
                    > sizeLength))
                continue;
            if (isOpen(row + neighborhoodMatrix[i][0], col + neighborhoodMatrix[i][1])) {
                unionNeighborhood(row + neighborhoodMatrix[i][0], col + neighborhoodMatrix[i][1],
                                  p);
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        // System.out.println(row);
        // System.out.println(col);
        if ((row < 1) || (col < 1) || (row > sizeLength) || (col > sizeLength)) {
            throw new IllegalArgumentException("Age must be greater than zero");
        }
        if (!opened[(row - 1) * sizeLength + (col - 1)]) return true;
        return false;
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        if (!isOpen(row, col)) return false;
        int p = (row - 1) * sizeLength + (col - 1);
        if (sizeLength > minNtoOptmaze) {
            if (weightedQuickUnionUF.find(p) == weightedQuickUnionUF.find(top)) return true;
        }
        else {
            for (int i = 0; i < sizeLength; i++) {
                if (!isOpen(1, i + 1)) continue;
                if (weightedQuickUnionUF.find(p) == weightedQuickUnionUF.find(
                        i)) {
                    return true;
                }
            }
        }
        return false;
    }

    // returns the number of open sites
    public int numberOfOpenSites() {
        int numOpenedCell = 0;
        for (int i = 0; i < sizeLength * sizeLength; i++) {
            if (!opened[i]) {
                numOpenedCell = numOpenedCell + 1;
            }
        }
        return numOpenedCell;
    }

    // does the system percolate?
    public boolean percolates() {
        if (sizeLength <= minNtoOptmaze) {
            for (int i = 0; i < sizeLength; i++) {
                if (isFull(sizeLength, i + 1)) {
                    return true;
                }
            }
        }
        if (weightedQuickUnionUF.find(bottom) == weightedQuickUnionUF.find(top)) return true;
        return false;
    }

    // test client (optional)
    public static void main(String[] args) {
        // System.out.println("Hello, Percolation");
        // Percolation perc = new Percolation(200);
        // while (!perc.percolates()) {
        //     // open random cell
        //     perc.open(perc.random.uniformInt(1, perc.sizeLength + 1),
        //               perc.random.uniformInt(1, perc.sizeLength) + 1);
        //     // check unions
        // }
        // // System.out.println(perc.sizeLength * perc.sizeLength);
        // double p = (double) perc.numberOfOpenSites() / (perc.sizeLength * perc.sizeLength);
        // System.out.println(perc.numberOfOpenSites());
        // System.out.println(perc.sizeLength * perc.sizeLength);
        // System.out.println(p);
    }
}
