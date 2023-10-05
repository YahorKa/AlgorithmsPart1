/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.StdRandom;

import java.util.ArrayList;

public class Board {
    private int[][] board;
    final private int sizeBoard;
    final private int[][] neighborhoodMatrix = new int[][]
            {
                    { 0, 1 },
                    { 0, -1 },
                    { 1, 0 },
                    { -1, 0 }
            };
    private int[][] goalBoard;
    // static private int[][] board1 = {
    //         { 8, 1, 3 },
    //         { 4, 0, 2 },
    //         { 7, 6, 5 }
    // };
    // static private int[][] board1 = {
    //         { 1, 3 },
    //         { 0, 2 }
    // };

    // create a board from an n-by-n array of tiles,
    // where tiles[row][col] = tile at (row, col)
    public Board(int[][] tiles) {  // maby tiles passing by ref?? (Yse its true)
        if (tiles.length == 0) throw new IllegalArgumentException();
        if (!(tiles instanceof int[][])) throw new IllegalArgumentException();
        sizeBoard = (tiles.length);
        board = new int[tiles.length][tiles.length];
        goalBoard = new int[tiles.length][tiles.length];
        for (int i = 0; i < sizeBoard; i++) {
            for (int j = 0; j < sizeBoard; j++) {
                board[i][j] = tiles[i][j];
                goalBoard[i][j] = i * sizeBoard + j + 1;
            }
        }
        goalBoard[sizeBoard - 1][sizeBoard - 1] = 0;

    }

    // string representation of this board
    public String toString() {
        String sBoard = Integer.toString(sizeBoard);
        for (int[] row : board) {
            sBoard += "\r\n";
            for (int el : row) {
                sBoard += Integer.toString(el) + "   ";
            }
        }
        // for (int[] row : goalBoard) {
        //     sBoard += "\r\n";
        //     for (int el : row) {
        //         sBoard += Integer.toString(el) + "   ";
        //     }
        // }
        return sBoard;
    }

    // board dimension n
    public int dimension() {
        return sizeBoard;
    }

    // number of tiles out of place
    public int hamming() {
        int hamming = 0;
        for (int i = 0; i < sizeBoard; i++) {
            for (int j = 0; j < sizeBoard; j++) {
                if (board[i][j] != goalBoard[i][j]) {
                    if (board[i][j] != 0)
                        hamming++;
                }
            }
        }
        return hamming;
    }

    // sum of Manhattan distances between tiles and goal
    public int manhattan() {
        int manhattanDistance = 0;
        for (int i = 0; i < sizeBoard; i++) {
            for (int j = 0; j < sizeBoard; j++) {
                if (board[i][j] != goalBoard[i][j]) {
                    int posi = (board[i][j] - 1) / (sizeBoard);
                    int posj = (board[i][j] - 1) % (sizeBoard);
                    int dist = Math.abs(i - posi) + Math.abs(j - posj);
                    if (board[i][j] != 0) {
                        manhattanDistance += dist;
                    }
                }
            }
        }
        return manhattanDistance;
    }

    // is this board the goal board?
    public boolean isGoal() {
        for (int i = 0; i < sizeBoard; i++) {
            for (int j = 0; j < sizeBoard; j++) {
                if (board[i][j] != goalBoard[i][j]) {
                    return false;
                }
            }
        }
        return true;
    }

    // does this board equal y?
    public boolean equals(Object y) {
        if (y == null) return false;
        Board b = (Board) y;
        if (sizeBoard != b.sizeBoard) return false;
        for (int i = 0; i < sizeBoard; i++) {
            for (int j = 0; j < sizeBoard; j++) {
                if (board[i][j] != b.board[i][j]) {
                    return false;
                }
            }
        }
        return true;
        // return this.board == y;
    }

    // all neighboring boards
    public Iterable<Board> neighbors() {

        ArrayList<Board> neighborBoards = new ArrayList<Board>();
        /// find zerp position
        int zeroPositionRow = 0;
        int zeroPositionCol = 0;
        for (int i = 0; i < sizeBoard; i++) {
            for (int j = 0; j < sizeBoard; j++) {
                if (board[i][j] == 0) {
                    zeroPositionRow = i;
                    zeroPositionCol = j;
                }
            }
        }
        /// find  neighborhoods with zero position
        for (int i = 0; i < neighborhoodMatrix.length; i++) {
            if ((zeroPositionRow + neighborhoodMatrix[i][0] < 0) || (
                    zeroPositionCol + neighborhoodMatrix[i][1] < 0) || (
                    zeroPositionRow + neighborhoodMatrix[i][0] >= sizeBoard) || (
                    zeroPositionCol + neighborhoodMatrix[i][1]
                            >= sizeBoard)) {
                continue;
            }
            /// Create  neighborhood board (not working??)
            Board b = new Board(board);
            int copy = b.board[zeroPositionRow + neighborhoodMatrix[i][0]][zeroPositionCol
                    + neighborhoodMatrix[i][1]];
            b.board[zeroPositionRow + neighborhoodMatrix[i][0]][zeroPositionCol
                    + neighborhoodMatrix[i][1]] = b.board[zeroPositionRow][zeroPositionCol];
            b.board[zeroPositionRow][zeroPositionCol] = copy;
            neighborBoards.add(b);
        }
        return neighborBoards;
    }

    // a board that is obtained by exchanging any pair of tiles
    public Board twin() {
        // System.out.println("who are you" + this);
        ///exch any cell in table
        int raw = StdRandom.uniform(0, sizeBoard);
        int col = StdRandom.uniform(0, sizeBoard);
        // int copy = board[raw][col];
        Board b = new Board(board);
        // for (int i = 0; i < neighborhoodMatrix.length; i++) {
        //     if ((raw + neighborhoodMatrix[i][0] < 0) || (
        //             col + neighborhoodMatrix[i][1] < 0) || (
        //             raw + neighborhoodMatrix[i][0] >= sizeBoard) || (
        //             col + neighborhoodMatrix[i][1]
        //                     >= sizeBoard)) {
        //         continue;
        //     }
        //
        //     // b.board[raw + neighborhoodMatrix[i][0]][col + neighborhoodMatrix[i][1]]
        //     //         = b.board[raw][col];
        //     b.board[raw + neighborhoodMatrix[i][0]][col + neighborhoodMatrix[i][1]] = copy;
        // }

        // find 0
        for (int i = 0; i < sizeBoard; i++) {
            for (int j = 0; j < sizeBoard; j++) {
                if (board[i][j] != 0) {
                    int copy = board[i][j];
                    if (i + 1 < sizeBoard) {
                        b.board[i][j] = b.board[i + 1][j];
                        b.board[i + 1][j] = copy;
                        return b;
                    }
                    else if (i - 1 > 0) {
                        b.board[i][j] = b.board[i - 1][j];
                        b.board[i - 1][j] = copy;
                        return b;
                    }
                    if (j + 1 < sizeBoard) {
                        b.board[i][j] = b.board[i][j + 1];
                        b.board[i][j + 1] = copy;
                        return b;
                    }
                    else if (i - 1 > 0) {
                        b.board[i][j] = b.board[i][j - 1];
                        b.board[i][j - 1] = copy;
                        return b;
                    }
                }
            }
        }
        // System.out.println("my twin " + b);
        return null;
    }

    // unit testing (not graded)
    public static void main(String[] args) {
        // Board b = new Board(board1);
        //  System.out.println(b.toString() + "\r\n");
        // for (Object ob : b.neighbors()) {
        //     System.out.println(ob.toString());
        // }
        // System.out.println("manhatten =" + b.manhattan());
        // System.out.println(b.twin());

    }

}