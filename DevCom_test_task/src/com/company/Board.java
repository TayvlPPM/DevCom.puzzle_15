package com.company;

import java.util.Arrays;

public class Board {
    private final int[][] correctPuzzle = {{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,0}};
    private int forbiddenMove = 8;
    private int[][] puzzle;
    private String path = "";
    private int zeroX, zeroY;
    private int level=0;
    public Board(int[][] puzzle) {
        this.puzzle = puzzle;
        findZeroTile();
    }
    public Board(Board newBoard) {
        puzzle = new int[newBoard.puzzle.length][newBoard.puzzle[0].length];
        for (int i = 0; i < puzzle.length; i++) {
            puzzle[i] = Arrays.copyOf(newBoard.puzzle[i], puzzle[i].length);
        }
        findZeroTile();
        path = newBoard.getPath();
        level = newBoard.getLevel()+1;
    }
    public int manhatten(){
        int manh=0;
        int rx=0,ry=0;
        for (int y=0;y<4;y++){
            for (int x=0;x<4;x++){
            if (puzzle[y][x]!=correctPuzzle[y][x]){
                for (int i=0;i<4;i++){
                    for (int j=0;j<4;j++){
                        if (correctPuzzle[i][j]==puzzle[y][x]){
                            ry=i;
                            rx=j;
                        }
                    }
                }
                manh +=Math.abs(ry-y)+Math.abs(rx-x);
            }
            }
        }
        return manh;
    }

    public int rowConflict() {
        int test [][]=puzzle;
        test[zeroY][zeroX]=16;

        int confSum=0;

        for (int row = 0; row < 4; row++){
            switch(row){
                case 0:{
                    for (int col=0; col < 4; col++){
                        if (test[row][col]>0 && test[row][col]<5){
                            int val=test[row][col];
                            for (int col2=col;col2<4;col2++){
                                if (test[row][col2]>0 && test[row][col2]<5){
                                    if (test[row][col2]<val)
                                        confSum+=2;
                                }
                            }

                        }
                    }
                }
                case 1:{
                    for (int col=0; col < 4; col++){
                        if (test[row][col]>4 && test[row][col]<9){
                            int val=test[row][col];
                            for (int col2=col;col2<4;col2++){
                                if (test[row][col2]>4 && test[row][col2]<9){
                                    if (test[row][col2]<val)
                                        confSum+=2;
                                }
                            }

                        }
                    }
                }
                case 2:{
                    for (int col=0; col < 4; col++){
                        if (test[row][col]>8 && test[row][col]<13){
                            int val=test[row][col];
                            for (int col2=col;col2<4;col2++){
                                if (test[row][col2]>8 && test[row][col2]<13){
                                    if (test[row][col2]<val)
                                        confSum+=2;
                                }
                            }

                        }
                    }
                }
                case 3:{
                    for (int col=0; col < 4; col++){
                        if (test[row][col]>12 && test[row][col]<17){
                            int val=test[row][col];
                            for (int col2=col;col2<4;col2++){
                                if (test[row][col2]>12 && test[row][col2]<17){
                                    if (test[row][col2]<val)
                                        confSum+=2;
                                }
                            }

                        }
                    }
                }
            }

        }
        return confSum;
    }

    public boolean isSolved() {
        if (Arrays.deepEquals(puzzle, correctPuzzle))
        return true;
        else
            return false;
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

    public boolean canMove(int i) {
        switch (i) {
            case 0:     // UP
                if (zeroY != 0) {
                    return true;
                }
                break;
            case 1:     //RIGHT
                if (zeroX != puzzle[zeroY].length - 1) {
                    return true;
                }
                break;
            case 2:     //LEFT
                if (zeroX != 0) {
                    return true;
                }
                break;
            case 3:     // DOWN
                if (zeroY != puzzle.length - 1) {
                    return true;
                }
                break;
        }
        return false;
    }
    public void move(int i) {
        switch (i) {
            case 0:         //UP
                path += swap(zeroY, zeroX, (zeroY - 1), zeroX)+"\u2B63"+" ";
                break;
            case 1:         //RIGHT
                path += swap(zeroY, zeroX, zeroY, (zeroX + 1))+"\u2B60"+" ";
                break;
            case 2:         //LEFT
                path += swap(zeroY, zeroX, zeroY, (zeroX - 1))+"\u2B62"+" ";
                break;
            case 3:         //DOWN
                path += swap(zeroY, zeroX, (zeroY + 1), zeroX)+"\u2B61"+" ";
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
    public void setForbidenMove(int i) {
        forbiddenMove = 3-i;
    }
    public int getMove(){
        return forbiddenMove;
    }

    public int[][] getPuzzle(){
        return puzzle;
    }
    public int getLevel(){
        return level;
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