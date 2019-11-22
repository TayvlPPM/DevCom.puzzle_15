package com.company;

public class Shuffler {
        public Board shuffle(Board boardToShuffle, Board.DIRECTION[] strategy, int numOfMoves) {
            for (int i = 0; i < numOfMoves; i++) {
                int m = (int)(Math.random() * 4);
                if (boardToShuffle.canMove(strategy[m])) {
                    boardToShuffle.move(strategy[m]);
                } else boardToShuffle.move(strategy[3-m]);
            }
            boardToShuffle.zeroPath();
            return boardToShuffle;
        }
}
