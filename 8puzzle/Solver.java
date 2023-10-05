/* *****************************************************************************
 *  Name:              Ada Lovelace
 *  Coursera User ID:  123456
 *  Last modified:     October 16, 1842
 **************************************************************************** */

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.StdOut;

import java.util.ArrayList;
import java.util.Comparator;


public class Solver {
    // static private int[][] board1 = {
    //         { 1, 0 },
    //         { 2, 3 }
    // };
    // static private int[][] board1 = {
    //         { 7, 8, 5 },
    //         { 4, 0, 2 },
    //         { 3, 6, 1 },
    //         };
    // Board goalBoard;
    private Board goalBoard;
    private int moves;
    private boolean isResolve;
    private ArrayList<Board> solution;
    private ArrayList<Board> visitedBoard;

    private class Node implements Comparable<Node> { // make comparable
        // private boolean visited;

        // public Node() {
        //     board = null;
        //     moves = 0;
        //     prevNode = null;
        //     visited = false;
        // }


        public Node(Board b) {
            this.board = b;
            this.prevNode = null;
            this.moves = 0;
            // this.visited = false;
        }

        public Node(Board b, Node prevNode) {
            this.board = b;
            this.prevNode = prevNode;
            // this.visited = false;
        }

        public int compareTo(Node other) {
            if (priority() < other.priority())
                return -1;
            if (priority() > other.priority())
                return 1;
            return 0;
        }

        Comparator<Node> comparator;
        Board board;
        int moves;

        int priority() {
            return this.board.manhattan() + moves;
        }

        Node prevNode;
    }

    // find a solution to the initial board (using the A* algorithm)
    public Solver(Board initial) {
        // int countMethodsBoard = 0;
        if (initial == null) throw new IllegalArgumentException();
        solution = new ArrayList<Board>();
        // check we have solution at the start
        if (initial.isGoal()) {
            solution.add(initial);
            isResolve = true;
            moves = 0;
            return;
        }
        solution.add(initial);
        visitedBoard = new ArrayList<Board>();
        Node curNode = new Node(initial);
        // System.out.println("initial board manhattan " + curNode.board.manhattan()); // infinite loop
        MinPQ<Node> priorityQueue = new MinPQ<Node>(curNode.comparator);
        priorityQueue.insert(curNode);
        while (!priorityQueue.isEmpty()) { //(priorityQueue.size()> 0) {
            curNode = priorityQueue.delMin();
            // System.out.println("priorityQueue.size " + priorityQueue.size());
            visitedBoard.add(curNode.board);
            for (Board b : curNode.board.neighbors()) {

                // boolean visitFlag = false;
                // for (Board visiteb : visitedBoard) {
                //     if (b.equals(visiteb)) {
                //         countMethodsBoard++;
                //         visitFlag = true;
                //     }
                // }
                // if (visitFlag) continue;
                // if (b.equals(initial)) continue;
                if (curNode.prevNode != null) {
                    if (b.equals(curNode.prevNode.board))
                        continue;
                }
                Node neighboorNode = new Node(b, curNode);

                // Find solve?
                // create solutin array
                if (b.isGoal()) {
                    //    countMethodsBoard++;
                    this.moves = curNode.moves + 1;
                    ArrayList<Board> bufferStackSolution = new ArrayList<Board>();
                    while (neighboorNode.prevNode != null) {
                        bufferStackSolution.add(neighboorNode.board);
                        neighboorNode = neighboorNode.prevNode;
                    }
                    isResolve = true;
                    while (!bufferStackSolution.isEmpty()) {
                        int index = bufferStackSolution.size() - 1;
                        solution.add(bufferStackSolution.remove(index));
                    }
                    //  System.out.println("count  " + countMethodsBoard);
                    return;
                }

                // do once more step
                neighboorNode.moves = curNode.moves + 1;
                priorityQueue.insert(neighboorNode);


            }
            // curNode = priorityQueue.min();
        }
        isResolve = false;
        moves = -1;
    }


    // is the initial board solvable? (see below)
    public boolean isSolvable() {
        return isResolve;
    }

    // min number of moves to solve initial board; -1 if unsolvable
    public int moves() {
        return moves;
    }

    // sequence of boards in a shortest solution; null if unsolvable
    public Iterable<Board> solution() {
        return solution;
    }

    // test client (see below)
    public static void main(String[] args) {

        // create initial board from file
        In in = new In(args[0]);
        int n = in.readInt();
        int[][] tiles = new int[n][n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                tiles[i][j] = in.readInt();
        Board initial = new Board(tiles);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }

        // Board initial = new Board(board1);
        // Solver solver = new Solver(initial);
        //
        // // print solution to standard output
        // if (!solver.isSolvable())
        //     System.out.println("No solution possible");
        // else {
        //     System.out.println("Minimum number of moves = " + solver.moves());
        //     for (Board board : solver.solution())
        //         System.out.println(board);
        // }


    }

}