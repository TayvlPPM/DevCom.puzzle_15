package com.company;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class BoardLoader {
    private String spliter = " ";

    public int[][] load(String fileName) {
        int[][] board;
        try {
            Scanner scanner = new Scanner(new File(fileName));
            board = new int[4][4];
            int boardLine = 0;
            while (scanner.hasNextInt()) {
                for (int i = 0; i < board[0].length; ++i) {
                    board[boardLine][i] = scanner.nextInt();
                }
                ++boardLine;
            }
            scanner.close();
            return board;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void shuffled(String fileName, String content){
        try(PrintWriter writer = new PrintWriter(fileName))
        {
                writer.println(content);
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void solution(String fileName, String content){
        try(FileWriter writer = new FileWriter(fileName, false))
        {
            String[] moves = content.split(spliter);
            for (int i=0; i<moves.length; i++) {
                writer.write(moves[i]);
                writer.write("\r\n");
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> loadSolution(String fileName) {
        BufferedReader br;
        String line;
        ArrayList<String> moves = new ArrayList<>();
        try {
            br = new BufferedReader(new FileReader(fileName));
            while ((line = br.readLine()) != null) {
                moves.add(String.valueOf(line.charAt(line.length()-1)));
            }
            br.close();
            return moves;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}