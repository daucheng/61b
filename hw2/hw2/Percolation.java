package hw2;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int N;

    private boolean grids[][];
    //grid[][][0] open or closed
    //grid[][][1] full or not
    private int num_of_open;
    private WeightedQuickUnionUF unionset;
    private int virtual_top;
    private int virtual_bottom;


    /**
     *      0   1   2   3
     * 0  [1 ][2 ][3 ][4 ]
     * 1  [5 ][6 ][7 ][8 ]
     * 2  [9 ][10][11][12]
     * 3  [13][14][15][16]
     */
    //Constructor
    public Percolation(int N){
        this.N = N;
        num_of_open = 0;
        unionset = new WeightedQuickUnionUF(N*N + 2); // including virtual top and bottom
        virtual_top = 0;
        virtual_bottom = N*N + 1;
        grids = new boolean[N][N];
        // create N-by-N grid, with all sites initially blocked
        if (N <= 0){throw new java.lang.IndexOutOfBoundsException("Invalid number of number");}
        for (int row = 0; row < N; row++){
            for (int col = 0; col < N; col++){
                grids[row][col] = false;
            }
        }

        for (int col = 0; col < N; col++){
            unionset.union(virtual_top,xyTo1D(0,col));
            unionset.union(virtual_bottom,xyTo1D(N-1,col));
        }
    }
    public void open(int row, int col){
        if (row >= N || row <0 || col >= N || col < 0){
            throw new java.lang.IndexOutOfBoundsException("Invalid number of row or col");
        }
        // open the site (row, col) if it is not open already
        if (!isOpen(row,col)){
            grids[row][col] = true;
            num_of_open += 1;
        }
        //check top left right bottom is opened
        //union if opened
        check_top(row,col);
        check_bottom(row,col);
        check_left(row,col);
        check_right(row,col);
    }

    private void check_top(int row, int col){
        if (row != 0 && isOpen(row-1,col)){
            unionset.union(xyTo1D(row,col),xyTo1D(row-1,col));
        }
    }
    private void check_left(int row, int col){
        if (col != 0 && isOpen(row,col-1)){
            unionset.union(xyTo1D(row,col),xyTo1D(row,col-1));
        }
    }
    private void check_right(int row, int col){
        if (col != N-1 && isOpen(row,col+1)){
            unionset.union(xyTo1D(row,col),xyTo1D(row,col+1));
        }
    }
    private void check_bottom(int row, int col){
        if (row != N-1 && isOpen(row+1,col)){
            unionset.union(xyTo1D(row,col),xyTo1D(row+1,col));
        }
    }

    // is the site (row, col) open?
    public boolean isOpen(int row, int col){
        if (row >= N || row < 0 || col >= N || col < 0){
            throw new java.lang.IndexOutOfBoundsException("Invalid number of row or col");
        }
        return grids[row][col];
    }

    //helper function to compute the number of girds
    private int xyTo1D(int row, int col){
        return (N * row + col + 1);
    }

    // is the site (row, col) full?
    public boolean isFull(int row, int col){
        if (row >= N || row <0 || col >= N || col < 0){
            throw new java.lang.IndexOutOfBoundsException("Invalid number of row or col");
        }
        //check is grids connected with top OR bottom
        int num = xyTo1D(row, col);
        return (unionset.connected(virtual_top,num) || unionset.connected(virtual_bottom,num));
    }

    // number of open sites
    public int numberOfOpenSites(){
        return num_of_open;
    }

    // does the system percolate?
    public boolean percolates(){
        //check union connected the virtual top AND bottom
        return unionset.connected(virtual_top,virtual_bottom);
    }

    // use for unit testing (not required)
    public static void main(String[] args){
        Percolation test = new Percolation(5);
        test.open(3,3);
        test.open(1,3);
        test.open(1,1);
        System.out.println(test.numberOfOpenSites());
        test.open(0,1);
        System.out.println(test.isFull(1,3));
        test.open(1,2);
        test.open(4,3);
        System.out.println(test.isFull(3,3));
        test.open(2,3);
        System.out.println(test.numberOfOpenSites());
        System.out.print(test.percolates());
    }
}// end of percolation class
