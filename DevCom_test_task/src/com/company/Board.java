package com.company;


import java.util.Arrays;

public class Board {
    public enum DIRECTION {UP, DOWN, LEFT, RIGHT}
    private int forbidenMove;
    private final int[][] puzzle;
    private int[][] correctPuzzle;
    private String path = "";
    private int zeroX, zeroY;
    public Board(int[][] puzzle) {
        this.puzzle = puzzle;
        this.correctPuzzle = generateCorrectPuzzle(4,4);
        findZeroTile();
    }
    public Board(Board newBoard) {
        puzzle = new int[newBoard.puzzle.length][newBoard.puzzle[0].length];
        for (int i = 0; i < puzzle.length; i++) {
            puzzle[i] = Arrays.copyOf(newBoard.puzzle[i], puzzle[i].length);
        }
        correctPuzzle = newBoard.correctPuzzle;
        zeroX = newBoard.zeroX;
        zeroY = newBoard.zeroY;
        path = newBoard.path;
    }
    public boolean isSolved() {
        for (int y = 0; y < puzzle.length; ++y) {
            for (int x = 0; x < puzzle[y].length; ++x) {
                if (puzzle[y][x] != correctPuzzle[y][x]) {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean isSolvable() {
        int[] toCheck = new int[15];
        int row=0;
        int k=0;
        for (int y = 0; y < 4; ++y) {
            for (int x = 0; x < 4; ++x) {
                if (getTile(y,x)!=0)
                    toCheck[k]=getTile(y,x);
                else {
                    row = 4 - y;
                    k--;
                }
            k++;
            }
        }
        int countInversions = 0;
        for (int i = 0; i < 15; i++) {
            for (int j=i+1;j<15;j++) {
                if (toCheck[i] > toCheck[j])
                    countInversions++;
            }
        }
        int chaos=row+countInversions;
        if (chaos % 2 ==0)
            return false;
        else
            return  true;
    }

    public boolean canMove(DIRECTION direction) {
        switch (direction) {
            case UP:
                if (zeroY != 0) {
                    return true;
                }
                break;
            case DOWN:
                if (zeroY != puzzle.length - 1) {
                    return true;
                }
                break;
            case LEFT:
                if (zeroX != 0) {
                    return true;
                }
                break;
            case RIGHT:
                if (zeroX != puzzle[zeroY].length - 1) {
                    return true;
                }
                break;
        }
        return false;
    }
    public void move(DIRECTION direction) {
        switch (direction) {
            case UP:

                path += swap(zeroY, zeroX, (zeroY - 1), zeroX)+"\u2B63"+" ";
                break;
            case DOWN:

                path += swap(zeroY, zeroX, (zeroY + 1), zeroX)+"\u2B61"+" ";
                break;
            case LEFT:

                path += swap(zeroY, zeroX, zeroY, (zeroX - 1))+"\u2B62"+" ";
                break;
            case RIGHT:

                path += swap(zeroY, zeroX, zeroY, (zeroX + 1))+"\u2B60"+" ";
                break;
        }
    }
    private int swap(int y1, int x1, int y2, int x2) {
        int previous = getTile(y1, x1);
        setTile(y1, x1, getTile(y2, x2));
        setTile(y2, x2, previous);
        zeroY = y2;
        zeroX = x2;
        return getTile(y1,x1);
    }
    private void setTile(int y, int x, int tile) {
        puzzle[y][x] = tile;
    }
    private int getTile(int y, int x) {
        return puzzle[y][x];
    }
    private void findZeroTile() {
        for (int y = 0; y < puzzle.length; ++y) {
            for (int x = 0; x < puzzle[y].length; ++x) {
                if (puzzle[y][x] == 0) {
                    zeroY = y;
                    zeroX = x;
                }
            }
        }
    }
    public String getPath() {
        return path;
    }
    public void zeroPath(){
        path = "";
    }

    public void setForbidenMove(int i) {
        forbidenMove = 3-i;
    }

    public int getMove(){
        return forbidenMove;
    }

    private static int[][] generateCorrectPuzzle(int xSize, int ySize) {
        int[][] correctPuzzle = new int[ySize][xSize];
        int counter = 1;
        for (int y = 0; y < ySize; ++y) {
            for (int x = 0; x < xSize; ++x) {
                correctPuzzle[y][x] = counter;
                ++counter;
            }
        }
        correctPuzzle[ySize - 1][xSize - 1] = 0;
        return correctPuzzle;
    }

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        for (int y = 0; y < puzzle.length; ++y) {
            for (int x = 0; x < puzzle[y].length; ++x) {
                output.append(puzzle[y][x]).append(" ");
            }
            output.append(System.lineSeparator());
        }
        return output.toString();
    }
}