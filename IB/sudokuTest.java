import java.util.*;
import java.io.*;

public class sudokuTest {
    public static void main(String[] args) {  
        try {
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

    public static void placePiece(char[][] board, int pos1, int pos2, int num) {
        int r = 0;
        int c = 0;

        if(board[pos1][pos2] != '\0'){
            System.out.println("Error: Num already exists in this position");
            return;
        }

        for (int i = 0; i < board.length; i++) {
            if(i != pos1){
                r =+ board[i][pos1];
            } else {
                r =+ num;
            }
        }
        for (int i = 0; i < board.length; i++) {
            if(i != pos2){
                r =+ board[pos2][i];
            } else {
                r =+ num;
            }
        }
    }

    public static void validateBoard(char[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                
            }
        }
    }
}