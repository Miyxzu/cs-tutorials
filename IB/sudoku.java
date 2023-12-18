package IB;

import java.util.*;
import java.io.*;

public class sudoku {
    private char[][] board;
    private Random rand;


    public sudoku() {
        rand = new Random();
        board = new char[5][5];

        board[0] = new char[]{' ', ' ', '|', ' ', ' '};
        board[1] = new char[]{' ', ' ', '|', ' ', ' '};
        board[2] = new char[]{'-', '-', '|', '-', '-'};
        board[3] = new char[]{' ', ' ', '|', ' ', ' '};
        board[4] = new char[]{' ', ' ', '|', ' ', ' '};
    }

    public void generateBoard() {
        List<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4));
        List<int[]> positions = new ArrayList<>();
    
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                if (board[i][j] == ' ') {
                    positions.add(new int[]{i, j});
                }
            }
        }
    
        Collections.shuffle(numbers);
        Collections.shuffle(positions);
    
        for (int i = 0; i < 4; i++) {
            int num = numbers.get(i);
            int[] pos = positions.remove(0);
    
            placePiece(pos[0], pos[1], num);
        }
    }

    public void initializeBoard() {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] != '|' && board[i][j] != '-') {
                    board[i][j] = ' ';
                }
            }
        }
    }

    public void placePiece(int pos1, int pos2, int num) {
        if(board[pos2][pos1] != ' '){
            System.out.println("Error: Num already exists in this position");
            return;
        }

        for (int i = 0; i < board[pos2].length; i++) {
            if (board[pos2][i] == num) {
                System.out.println("Error: Num already exists in this row");
                return;
            }
        }

        for (int i = 0; i < board.length; i++) {
            if (board[i][pos1] == num) {
                System.out.println("Error: Num already exists in this column");
                return;
            }
        }

        board[pos2][pos1] = (char) (num + '0');
    }

    public void printBoard() {
        for (char[] row : board) {
            for (char col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }

    public boolean checkFilled() {
        for (char[] row : board) {
            for (char col : row) {
                if (col == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    public boolean checkWin() {
        for (int i = 0; i < board.length; i++) {
            int r = 0;
            int c = 0;

            for (int j = 0; j < board[i].length; j++) {
                r += board[i][j];
                c += board[j][i];
            }

            if (r != 40 || c != 40) {
                return false;
            }
        }
        return true;
    }
}