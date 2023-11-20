package IB;

import java.util.*;
import java.io.*;

public class sudokuTest {
    public static void main(String[] args) {  
        try {
            Random rand = new Random();
            Scanner in = new Scanner(System.in);
            char[][] board = {{' ', ' ', '|', ' ', ' '},
                              {' ', ' ', '|', ' ', ' '},
                              {' ', ' ', '|', ' ', ' '},
                              {' ', ' ', '|', ' ', ' '},
                              {'-', '-', '|', '-', '-'},
                              {' ', ' ', '|', ' ', ' '},
                              {' ', ' ', '|', ' ', ' '},
                              {' ', ' ', '|', ' ', ' '},
                              {' ', ' ', '|', ' ', ' '},};

            System.out.println("board v1");
            printBoard(board);

            System.out.println();

            int r = 0;
            int c = 0;
            int num;
            int bound = rand.nextInt(5) + 3;

            for (int i = 0; i < bound; i++) {
                r = rand.nextInt(board.length);
                c = rand.nextInt(board.length);
                System.out.println(r);
                System.out.println(c);

                while (r == 2 || c == 2) {
                    r = rand.nextInt(board.length);
                    c = rand.nextInt(board.length);
                }

                System.out.println(r);
                System.out.println(c);
                System.out.println(bound);

                if (board[r][c] == '\0') {
                    num = rand.nextInt(4) + 1;
                    if(num == 5){
                        num = rand.nextInt(4) + 1;
                    }

                    System.out.println((char)num);
                    board[r][c] = (char)num;
                    System.out.println(board[r][c]);
                }

                printBoard(board);

                System.out.println();

                // for (int j = 0; j < board.length - 1; j++) {
                //     r =+ board[i][c];
                // }
            }

            printBoard(board);

            System.out.println();

            placePiece(board, 1, 1, 4);
            printBoard(board);


        } catch (Exception e) {

        }
    }

    public static void printBoard(char[][] b) {
        for (char[] row : b) {
            for (char col : row) {
                System.out.print(col + " ");
            }
            System.out.println();
        }
    }

    public static void placePiece(char[][] gb, int pos1, int pos2, int num) {
        int r = 0;
        int c = 0;

        if(gb[pos1][pos2] != '\0'){
            System.out.println("Error: Num already exists in this position");
            return;
        }

        for (int i = 0; i < gb.length; i++) {
            if(i != pos1){
                r =+ gb[i][pos1];
            } else {
                r =+ num;
            }
        }
        for (int i = 0; i < gb.length; i++) {
            if(i != pos2){
                r =+ gb[pos2][i];
            } else {
                r =+ num;
            }
        }

        if (r != 9 && c != 9) {
            System.out.println("Error: ");
            return;
        }

        
    }
}