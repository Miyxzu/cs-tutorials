package assessmentfinal;

import java.util.*;

public class Player extends Board {
    private int health;
    private int score;
    private String name;
    private boolean isAlive;
    private boolean currentTurn;
    private Board board;

    public static void main(String[] args) {
        Player player = new Player("Player1");
        Scanner in = new Scanner(System.in);

        player.initializeBoard();
        player.board.getInternalBoard();

        System.out.print("Enter your guess (x y): ");
        int x = in.nextInt();
        int y = in.nextInt();
        player.Guess(x, y);
        player.displayBoard();
    }

    public Player(String name) {
        board = new Board();
        this.name = name;
        this.health = 100;
        this.score = 0;
        this.isAlive = true;
        this.currentTurn = false;
    }

    public void initializeBoard() {
        board.addCreaturesToBoard();
        displayBoard();
    }

    public void displayBoard() {
        System.out.println("\n=== YOUR OCEAN VIEW ===");
        for (int i = 0; i < board.InternalBoard.length; i++) {
            for (int j = 0; j < board.InternalBoard[i].length; j++) {
                if (board.PlayerBoard[i][j]) {
                    // Revealed - show what's there
                    String cell = board.InternalBoard[i][j] != null ? board.InternalBoard[i][j] : "X";
                    System.out.print(cell + " ");
                } else {
                    // Not revealed yet - show fog
                    System.out.print("~ ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    public void Guess(int x, int y) {
        if (board.PlayerBoard[x][y]) {
            System.out.println("Already guessed this position!");
            return;
        }

        board.PlayerBoard[x][y] = true; // Mark as revealed

        if (board.InternalBoard[x][y] != null) {
            // Hit! Show what creature it is
            System.out.println("Hit! You found: " + board.InternalBoard[x][y]);
            score += 5;
        } else if (board.InternalBoard[x][y] == "b") {
            System.out.println("Oh no! You hit a bomb!");
            health -= 10;
        }else if (board.InternalBoard[x][y] == "j") {
            System.out.println("Oh no! You hit a jellyfish!");
            score -= 2;
        } else {
            // Miss - mark with X
            System.out.println("Miss!");
            board.InternalBoard[x][y] = "X";
        }
    }
}
