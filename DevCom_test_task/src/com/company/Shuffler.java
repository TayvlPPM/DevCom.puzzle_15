package com.company;

public class Shuffler {
        public Board shuffle(Board boardToShuffle, int numOfMoves) {
            for (int i = 0; i < numOfMoves; i++) {
                int m = (int)(Math.random() * 4);
                if (boardToShuffle.canMove(m) && m!=boardToShuffle.getMove()) {
                    boardToShuffle.setForbidenMove(m);
                    boardToShuffle.move(m);
                } else numOfMoves++;
            }
            return boardToShuffle;
        }
}
