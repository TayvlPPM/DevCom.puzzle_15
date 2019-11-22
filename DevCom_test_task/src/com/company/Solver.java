package com.company;

import java.util.*;

public class Solver {
    private final Queue<Board> frontiers = new LinkedList<>();

    public Board solve(Board boardToSolve, Board.DIRECTION[] strategy) {
        if (boardToSolve.isSolvable()!=true){
            System.out.println("Instance of puzzle is unsolvable!");
            System.exit(0);
        }
        frontiers.add(boardToSolve);
        while (!frontiers.isEmpty()) {
            Board board = frontiers.poll();
            if (board.isSolved()) {
                return board;
            }
            for (int i = 0; i < strategy.length; i++) {
                if (board.canMove(strategy[i])) {
                    Board newBoard = new Board(board);
                    newBoard.move(strategy[i]);
                    newBoard.setForbidenMove(i);
                    if (i!=board.getMove())
                        frontiers.add(newBoard);

                    }

                }
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
                    boardToSolve.move(Board.DIRECTION.UP);
                    break;
                case "\u2B61":
                    boardToSolve.move(Board.DIRECTION.DOWN);
                    break;
                case "\u2B62":
                    boardToSolve.move(Board.DIRECTION.LEFT);
                    break;
                case "\u2B60":
                    boardToSolve.move(Board.DIRECTION.RIGHT);
                    break;
            }
            System.out.println(boardToSolve);
                Thread.sleep(time*1000);
            }
        }
    }

