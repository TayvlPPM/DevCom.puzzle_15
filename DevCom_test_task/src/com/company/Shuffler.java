package com.company;

public class Shuffler {
        public Board shuffle(Board boardToShuffle, int numOfMoves) {
            for (int i = 0; i < numOfMoves; i++) {
                int m = (int)(Math.random() * 4);
                if (boardToShuffle.canMove(m) && m!=boardToShuffle.getMove()) {
                    boardToShuffle.move(m);
                } else boardToShuffle.move(3-m);
            }
            boardToShuffle.zeroPath();
            return boardToShuffle;
        }
}
