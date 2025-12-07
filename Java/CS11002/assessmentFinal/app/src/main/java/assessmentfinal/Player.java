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

    /**
     * Prints the player's board
     */
    public void displayBoard() {
        System.out.println("\n=== YOUR OCEAN VIEW ===");
        
        // Print column numbers (0-15)
        System.out.print("   "); // Offset for row numbers
        for (int col = 0; col < 16; col++) {
            System.out.printf("%2d ", col);
        }
        System.out.println();
        
        for (int i = 0; i < board.InternalBoard.length; i++) {
            // Print row number
            System.out.printf("%2d ", i);
            
            for (int j = 0; j < board.InternalBoard[i].length; j++) {
                if (board.PlayerBoard[i][j]) {
                    // Revealed - show what's there
                    String cell = board.InternalBoard[i][j] != null ? board.InternalBoard[i][j] : "X";
                    System.out.print(cell.toUpperCase() + "  ");
                } else {
                    // Not revealed yet - show fog
                    System.out.print("~  ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * Makes a guess on the board and updates state accordingly based on result
     * @param x X-coord
     * @param y Y-coord
     */
    public void Guess(int x, int y) {
        // Check bounds first
        if (x < 0 || x >= 16 || y < 0 || y >= 16) {
            System.out.println("Invalid coordinates! Must be between 0 and 15.");
            return;
        }

        if (board.PlayerBoard[x][y]) {
            System.out.println("Already guessed this position!");
            return;
        }

        board.PlayerBoard[x][y] = true; // Mark as revealed

        if (board.InternalBoard[x][y] != null) {
            String cell = board.InternalBoard[x][y];

            if (!cell.equals("b") && !cell.equals("j")) {
                // Hit! Show what creature it is
                System.out.println("Hit! You found: " + cell.toUpperCase() + "!");
                score += 5;

                // Checks if fully discovered
                if (isCreatureComplete(x, y, cell)) {
                    String creatureName = getCreatureName(cell);
                    score += 5;
                    System.out.println("Bonus! You fully discovered a " + creatureName + "!");
                }

            } else if (cell.equals("b")) { // Hit bomb: lose health
                System.out.println("Oh no! You hit a bomb!");
                health -= 25;
            } else if (cell.equals("j")) { // Hit jellyfish: lose score
                System.out.println("Oh no! You hit a jellyfish!");
                score -= 2;
            }

            creaturesLeft--;
        } else {
            // Miss - mark with X
            System.out.println("Miss!");
            board.InternalBoard[x][y] = "X";
        }

        if (health <= 0) { // Check if player is dead
            isAlive = false;
            return;
        }

        displayBoard();
        currentTurn = false;
    }

    /**
     * Overrides current boards with provided ones
     * @param internalBoard Previous internal board
     * @param playerBoard Previous player board
     * @param creaturesLeft Number of creatures left
     * @return boolean Returns whether override was successful
     */
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

    // Private methods
    /**
     * Checks if a creature is fully discovered
     * @param x X-coord
     * @param y Y-coord
     * @param type Creature type
     * @return boolean Returns whether creature is complete
     */
    private boolean isCreatureComplete(int x, int y, String type) {
        if (type.equals("c")) {
            return true;
        } else if (type.equals("f")) {
            return isFishComplete(x, y);
        } else if (type.equals("s")) {
            return isSharkComplete(x, y);
        } else if (type.equals("e")) {
            return isEelComplete(x, y);
        } else if (type.equals("m")) {
            return isMantaRayComplete(x, y);
        } else if (type.equals("a")) {
            return isAnenomeComplete(x, y);
        }

        return false;
    }

    /**
     * Creature completion checking
     */

    /**
     * @param x im not gonna explain this for the third time now
     * @param y see above
     * @return Returns whether fish is complete
     */
    private boolean isFishComplete(int x, int y) {
        int[][] directions = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };

        for (int[] dir : directions) {
            // Calculate new relative positions
            int newX = x + dir[0];
            int newY = y + dir[1];

            if (isValidCell(newX, newY) && 
                "f".equals(board.InternalBoard[newX][newY]) &&
                board.PlayerBoard[newX][newY]) {
                return true;
            }
        }
        return false;
    }

    /**
     * @param x
     * @param y
     * @return Returns whether shark is complete
     */
    private boolean isSharkComplete(int x, int y) {
        // Check horizontal possibilities
        if (checkSharkLine(x, y, 0, -1, 0, 1) || // center
                checkSharkLine(x, y, 0, 1, 0, 2) || // left end
                checkSharkLine(x, y, 0, -2, 0, -1)) { // right end
            return true;
        }

        // Check vertical possibilities
        if (checkSharkLine(x, y, -1, 0, 1, 0) || // center
                checkSharkLine(x, y, 1, 0, 2, 0) || // top end
                checkSharkLine(x, y, -2, 0, -1, 0)) { // bottom end
            return true;
        }

        return false;
    }

    /**
     * Extension of above to reduce redundancy
     * @return Returns whether shark is complete
     */
    private boolean checkSharkLine(int x, int y, int dirX1, int dirY1, int dirX2, int dirY2) {
        // Calculate new relative positions
        int newX1 = x + dirX1, newY1 = y + dirY1;
        int newX2 = x + dirX2, newY2 = y + dirY2;


        return isValidCell(newX1, newY1) && isValidCell(newX2, newY2)
                && "s".equals(board.InternalBoard[newX1][newY1]) && board.PlayerBoard[newX1][newY1]
                && "s".equals(board.InternalBoard[newX2][newY2]) && board.PlayerBoard[newX2][newY2];
        
    }

    /**
     * Check if coordinates are within board bounds
     */
    private boolean isValidCell(int x, int y) {
        return x >= 0 && x < 16 && y >= 0 && y < 16;
    }

    /**
     * Check if a 4-cell eel (zig-zag) is completely revealed
     */
    private boolean isEelComplete(int x, int y) {
        // Check all 4 possible zig-zag patterns
        int[][][] patterns = {
            // Pattern 1: Right-down-right-down
            { { 0, 0 }, { 0, 1 }, { 1, 1 }, { 1, 2 } },
            // Pattern 2: Down-right-down-right
            { { 0, 0 }, { 1, 0 }, { 1, 1 }, { 2, 1 } },
            // Pattern 3: Left-down-left-down
            { { 0, 0 }, { 0, -1 }, { 1, -1 }, { 1, -2 } },
            // Pattern 4: Up-right-up-right
            { { 0, 0 }, { -1, 0 }, { -1, 1 }, { -2, 1 } }
        };

        return checkCreaturePattern(x, y, "e", patterns);
    }

    /**
     * Check if a 3-cell manta ray (V-shaped) is completely revealed
     */
    private boolean isMantaRayComplete(int x, int y) {
        // Check all 4 V orientations
        int[][][] patterns = {
            // V pointing down
            { { 0, -1 }, { 1, 0 }, { 0, 1 } },
            // V pointing up
            { { 0, -1 }, { -1, 0 }, { 0, 1 } },
            // V pointing right
            { { -1, 0 }, { 0, 1 }, { 1, 0 } },
            // V pointing left
            { { -1, 0 }, { 0, -1 }, { 1, 0 } }
        };

        return checkCreaturePattern(x, y, "m", patterns);
    }

    /**
     * Check if a 4-cell anenome (2x2 square) is completely revealed
     */
    private boolean isAnenomeComplete(int x, int y) {
        // Check all 4 positions where the current cell could be in the 2x2 square
        int[][][] patterns = {
            // Current is top-left
            { { 0, 0 }, { 0, 1 }, { 1, 0 }, { 1, 1 } },
            // Current is top-right
            { { 0, 0 }, { 0, -1 }, { 1, 0 }, { 1, -1 } },
            // Current is bottom-left
            { { 0, 0 }, { 0, 1 }, { -1, 0 }, { -1, 1 } },
            // Current is bottom-right
            { { 0, 0 }, { 0, -1 }, { -1, 0 }, { -1, -1 } }
        };

        return checkCreaturePattern(x, y, "a", patterns);
    }

    /**
     * Generic pattern checker for multi-cell creatures
     */
    private boolean checkCreaturePattern(int x, int y, String creatureType, int[][][] patterns) {
        for (int[][] pattern : patterns) {
            boolean allRevealed = true;
            
            for (int[] offset : pattern) {
                int nx = x + offset[0];
                int ny = y + offset[1];
                
                if (!board.validPositioning(nx, ny) || 
                    !creatureType.equals(board.InternalBoard[nx][ny]) ||
                    !board.PlayerBoard[nx][ny]) {
                    allRevealed = false;
                    break;
                }
            }
            
            if (allRevealed) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the full name of a creature from its abbreviation
     */
    private String getCreatureName(String abbrev) {
        switch (abbrev) {
            case "c": return "crab";
            case "f": return "fish";
            case "s": return "shark";
            case "e": return "eel";
            case "m": return "manta ray";
            case "a": return "anenome";
            default: return abbrev;
        }
    }

    // Getters and Setters
    /**
     * @return int
     */
    public int getCreaturesLeft() {
        return board.creaturesLeft;
    }

    /**
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return int
     */
    public int getHealth() {
        return health;
    }

    /**
     * @param health
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @return int
     */
    public int getScore() {
        return score;
    }

    /**
     * @param score
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * @return boolean
     */
    public boolean isCurrentTurn() {
        return currentTurn;
    }

    /**
     * @param currentTurn
     */
    public void setCurrentTurn(boolean currentTurn) {
        this.currentTurn = currentTurn;
    }

    /**
     * @return boolean
     */
    public boolean isAlive() {
        return isAlive;
    }

    /**
     * @param isAlive
     */
    public void setAlive(boolean isAlive) {
        this.isAlive = isAlive;
    }

    /**
     * @return boolean
     */
    public boolean isWon() {
        return won;
    }

    /**
     * @param won
     */
    public void setWon(boolean won) {
        this.won = won;
    }

    @Override
    public String toString() {
        return String.format("%-20s%-15s%-8s%-8s", name, currentTurn, health, score);
    }
}
