package com.company;

import java.lang.reflect.Array;
import java.util.*;

public class Solver {
    private final ArrayList<Board> frontiers = new ArrayList<>();
    private final ArrayList<String> used = new ArrayList<>();

    public Board solve(Board boardToSolve) {
        if (boardToSolve.isSolvable()!=true){
            System.out.println("Instance of puzzle is unsolvable!");
            System.exit(0);
        }
        frontiers.add(boardToSolve);
        Board board = null;
        int i=0;
        while(i < Integer.MAX_VALUE) {
            int minVal = Integer.MAX_VALUE;
            for (int iter = 0; iter < frontiers.size(); iter++) {
                Board tmp = frontiers.get(iter);
                if (tmp.manhatten()< minVal) {
                    minVal = tmp.manhatten();
                    board = tmp;
                }
            }
            used.add(Arrays.deepToString(board.getPuzzle()));
            for (int iter = 0; iter < 4; iter++) {
                if (board.isSolved()) {
                    return board;
                }
                Board newBoard = new Board(board);
                if (newBoard.canMove(iter) && board.getMove()!=iter) {
                    newBoard.move(iter);
                    newBoard.setForbidenMove(iter);
                    if (used.contains(Arrays.deepToString(newBoard.getPuzzle()))!=true)
                        frontiers.add(newBoard);
                }

            }
            frontiers.remove(board);
            i++;
        }
        return null;
    }

    public void playSolution(Board boardToSolve, ArrayList solution, int time)

            throws InterruptedException {
        Iterator<String> itr = solution.iterator();
        while (itr.hasNext()) {
            String direction = itr.next();
            switch (direction) {
                case "\u2B63":
                    boardToSolve.move(0);
                    break;
                case "\u2B61":
                    boardToSolve.move(3);
                    break;
                case "\u2B62":
                    boardToSolve.move(2);
                    break;
                case "\u2B60":
                    boardToSolve.move(1);
                    break;
            }
            System.out.println(boardToSolve);
                Thread.sleep(time*1000);
            }
        }
    }

