package IB;

import java.util.*;
import java.io.*;

public class sudokuTest {
    public static void main(String[] args) {  
        try {
            Random rand = new Random();
            Scanner in = new Scanner(System.in);
            char[][] board = {{' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' '},
                              {' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' '},
                              {' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' '},
                              {' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' '},
                              {'-', '-', '-', '-', '|', '-', '-', '-', '-'},
                              {' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' '},
                              {' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' '},
                              {' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' '},
                              {' ', ' ', ' ', ' ', '|', ' ', ' ', ' ', ' '},};


            for (char[] row : board) {
                for (char col : row) {
                    System.out.print(col);
                }
                System.out.println();
            }

            System.out.println();

            int r = 0;
            int c = 0;
            int num;

            for (int i = 0; i < rand.nextInt(16) + 4; i++) {
                while (r == 4 || c == 4) {
                    r = rand.nextInt(board.length);
                    c = rand.nextInt(board.length);
                }
                if (board[r][c] == '\0') {
                    num = rand.nextInt(8) + 1;
                    board[r][c] = (char)num;
                }
            }

            for (char[] row : board) {
                for (char col : row) {
                    System.out.print(col);
                }
                System.out.println();
            }
        } catch (Exception e) {
            // TODO: handle exception
        }
    }
}
