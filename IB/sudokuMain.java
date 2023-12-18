package IB;

import java.util.*;
import java.io.*;

public class sudokuMain {
    public static void main(String[] args) {
        sudoku s = new sudoku();
        Scanner in = new Scanner(System.in);

        intro();

        System.out.println("Initializing board...");
        s.generateBoard();

        System.out.println();

        s.printBoard();

        System.out.println();

        while (!s.checkFilled()) {
            System.out.print("Choose an x coordinate: ");
            int x = in.nextInt();
            System.out.print("Choose a y coordinate: ");
            int y = in.nextInt();
            System.out.print("Choose a number: ");
            int num = in.nextInt();

            s.placePiece(x, y, num);

            System.out.println();

            s.printBoard();
        }

        if (s.checkWin()) {
            System.out.println("You win!");
        } else {
            System.out.println("You lose!");
            
        }
    }

    public static void intro() {
        System.out.println();
        
        System.out.println("Welcome to Sudoku!");
        System.out.println("The goal of the game is to fill in the board with numbers 1-4");
        System.out.println("such that each row and column contains each number exactly once.");
        System.out.println("You will be prompted to enter an x and y coordinate (Follows array indexing), as well as a number.");
        System.out.println("The number will be placed at the given coordinates.");
        System.out.println("Keep in mind that this is not a good example of the game, and there may be some bugs.");
        System.out.println("The board generation is also not very good, and may not be solvable.");

        System.out.println();
    }
}
