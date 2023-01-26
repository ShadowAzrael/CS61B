package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;


public class Percolation {

    private boolean[][] grid;
    private int N;
    private int top;
    private int bottom;
    private WeightedQuickUnionUF uf;
    private WeightedQuickUnionUF ufExcludeBottom; // to avoid backwash
    private int numOpenSites = 0;
    private int[][] surroundings = new int[][]{{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    // transform (row, col) to 1D coordinate
    private int XYto1D(int row, int col) {
        return row * N + col + 1;
    }

    public void validate(int row, int col) {
        if (row < 0 || col < 0 || row > N || row > N) {
            throw new IndexOutOfBoundsException();
        }
    }

    // create N-by-N grid, with all sites initially blocked
    public Percolation(int N) {
        if (N <= 0) {
            throw new IllegalArgumentException();
        }
        this.N = N;
        top = 0;
        bottom = N * N + 1;
        uf = new WeightedQuickUnionUF(N * N + 2);
        ufExcludeBottom = new WeightedQuickUnionUF(N * N + 1);
    }

    // open the site (row, col) if it is not open already
    public void open(int row, int col) {
        validate(row, col);
        if (!isOpen(row, col)) {
            grid[row][col] = true;
            numOpenSites += 1;
        }
        if (row == 0) {
            uf.union(XYto1D(row, col), top);
            ufExcludeBottom.union(XYto1D(row, col), top);
        }
        if (row == N - 1) {
            uf.union(XYto1D(row, col), bottom);
            ufExcludeBottom.union(XYto1D(row, col), bottom);
        }
        for (int[] surrounding : surroundings) {
            int adjacentRow = row + surrounding[0];
            int adjacentCol = col + surrounding[1];
            if (adjacentRow >= 0 && adjacentRow < N) {
                if (isOpen(adjacentRow, adjacentCol)) {
                    uf.union(XYto1D(row, col), XYto1D(adjacentRow, adjacentCol));
                    ufExcludeBottom.union(XYto1D(row, col), XYto1D(adjacentRow, adjacentCol));
                }
            }
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col) {
        validate(row, col);
        return grid[row][col];
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col) {
        validate(row, col);
        return ufExcludeBottom.connected(XYto1D(row, col), top);
    }

    // number of open sites
    public int numberOfOpenSites() {
        return numOpenSites;
    }

    // does the system percolate?
    public boolean percolates() {
        return uf.connected(top, bottom);
    }

    // use for unit testing (not required)
    public static void main(String[] args) {

    }
}
