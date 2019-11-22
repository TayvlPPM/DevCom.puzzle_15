package com.company;


import java.util.Scanner;

public class Main {
    public static void main(String[] args)
            throws InterruptedException {
        BoardLoader boardLoader = new BoardLoader();
        Board.DIRECTION[] strategy = {Board.DIRECTION.RIGHT, Board.DIRECTION.DOWN, Board.DIRECTION.UP, Board.DIRECTION.LEFT};
        Solver solver = new Solver();
        Shuffler shuffler = new Shuffler();
        int[][] puzzleToOperate = boardLoader.load("src/Puzzle15.txt");
        Board board = new Board(puzzleToOperate);
        System.out.println("Enter number of steps for shuffling");
        Scanner in = new Scanner(System.in);
        int movesToShuffle = in.nextInt();
        System.out.println("Before shuffling");
        System.out.println(board);
        Board shuffledBoard = shuffler.shuffle(board,strategy,movesToShuffle);
        System.out.println("After shuffling");
        System.out.println(shuffledBoard);
        boardLoader.shuffled("src/Puzzle15.txt",shuffledBoard.toString());
        System.out.println("------- INITIAL STATE -------");
        System.out.println(board);
        System.out.println("------- SOLUTION -------");
        Board solvedBoard = solver.solve(board, strategy);
        boardLoader.solution("src/solution.txt", solvedBoard.getPath());
        System.out.println("Enter time for showing  one step in sec.");
        int time = in.nextInt();
        solver.playSolution(board,boardLoader.loadSolution("src/solution.txt"),time);
    }


}
