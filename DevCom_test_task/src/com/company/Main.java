package com.company;


import java.util.Scanner;

public class Main {
    public static void main(String[] args)
            throws InterruptedException {
        BoardLoader boardLoader = new BoardLoader();
        Solver solver = new Solver();
        Shuffler shuffler = new Shuffler();
        int[][] puzzleToShuffle = boardLoader.load("src/Puzzle15.txt");
        Board boardForShuffling = new Board(puzzleToShuffle);
        System.out.println("Enter number of steps for shuffling");
        Scanner in = new Scanner(System.in);
        int movesToShuffle = in.nextInt();
        System.out.println("Before shuffling");
        System.out.println(boardForShuffling);
        Board shuffledBoard = shuffler.shuffle(boardForShuffling,movesToShuffle);
        System.out.println("After shuffling");
        System.out.println(shuffledBoard);
        boardLoader.shuffled("src/Puzzle15.txt",shuffledBoard.toString());
        int[][] puzzleToSolve = boardLoader.load("src/Puzzle15.txt");
        Board boardToSolve = new Board(puzzleToShuffle);
        System.out.println("------- INITIAL STATE -------");
        System.out.println(boardToSolve);
        System.out.println("Enter time for showing  one step in sec.");
        int time = in.nextInt();
        System.out.println("------- SOLUTION -------");
        Board solvedBoard = solver.solve(boardToSolve);
        boardLoader.solution("src/solution.txt", solvedBoard.getPath());
        solver.playSolution(boardToSolve,boardLoader.loadSolution("src/solution.txt"),time);
    }


}
