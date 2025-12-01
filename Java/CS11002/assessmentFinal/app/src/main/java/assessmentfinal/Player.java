package assessmentfinal;

import java.util.*;

public class Player extends Board {
    private int health;
    private int score;
    private String name;
    private boolean isAlive;
    private boolean currentTurn;
    private boolean won;
    private Board board;

    public static void main(String[] args) {
        Player player = new Player("Player 1");
        Scanner in = new Scanner(System.in);

        player.initializeBoard();
        player.board.getInternalBoard();
        player.setCurrentTurn(true);

        System.out.print("Enter your guess (X, Y): ");
        String coords = in.nextLine();
        String[] parts = coords.split(", ");
        int x = Integer.parseInt(parts[0]);
        int y = Integer.parseInt(parts[1]);
        player.Guess(x, y);

        in.close();
    }

    public Player(String name) {
        board = new Board();
        this.name = name;
        this.health = 100;
        this.score = 0;
        this.isAlive = true;
        this.currentTurn = false;
        this.won = false;
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
            health -= 25;
        }else if (board.InternalBoard[x][y] == "j") {
            System.out.println("Oh no! You hit a jellyfish!");
            score -= 2;
        } else {
            // Miss - mark with X
            System.out.println("Miss!");
            board.InternalBoard[x][y] = "X";
        }

        if (health == 0) {
            isAlive = false;
            return;
        }
        
        displayBoard();
        currentTurn = false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isCurrentTurn() {
        return currentTurn;
    }

    public void setCurrentTurn(boolean currentTurn) {
        this.currentTurn = currentTurn;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    public boolean isWon() {
        return won;
    }

    public void setWon(boolean won) {
        this.won = won;
    }

    @Override
    public String toString() {
        return String.format("%-25s%-15s%-8.2f%-8.2f", name, health, score);
    }
}
