package assessmentfinal;

public class Player extends Board {
    private int health;
    private int score;
    private String name;
    private boolean isAlive;
    private boolean currentTurn;
    private boolean won;
    protected Board board;

    public Player(String name) {
        board = new Board();
        this.name = name;
        this.health = 100;
        this.score = 0;
        this.isAlive = true;
        this.currentTurn = false;
        this.won = false;
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
            String cell = board.InternalBoard[x][y];

            if (!cell.equals("b") && !cell.equals("j")) {
                // Hit! Show what creature it is
                System.out.println("Hit! You found: " + board.InternalBoard[x][y]);
                score += 5;
            } else if (cell.equals("b")) {
                System.out.println("Oh no! You hit a bomb!");
                health -= 25;
            } else if (cell.equals("j")) {
                System.out.println("Oh no! You hit a jellyfish!");
                score -= 2;
            }

            creaturesLeft--;
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

    public boolean overrideCurrentBoards(String[][] internalBoard, boolean[][] playerBoard, int creaturesLeft) {
        boolean flag = true;

        if (internalBoard != null && internalBoard.length > 0) {
            this.board.InternalBoard = internalBoard;
            if (playerBoard != null && playerBoard.length > 0) {
                this.board.PlayerBoard = playerBoard;
            } else {
                this.board.PlayerBoard = new boolean[16][16];
            }
            this.board.creaturesLeft = creaturesLeft;
        } else {
            flag = false;
        }

        return flag;
    }

    public int getCreaturesLeft() {
        return board.creaturesLeft;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
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
        return String.format("%-25s%-10s%-8s%-8s", name, currentTurn, health, score);
    }
}
