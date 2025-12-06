package assessmentfinal;

import java.util.*;

public class Board {
    private Random rand;
    protected String[][] InternalBoard;
    protected boolean[][] PlayerBoard;
    protected int creaturesLeft;
    private String lastFishOrientation = null;
    private String lastSharkOrientation = null;
    private final List<String> creatures = new ArrayList<>( // "c" = Crab, "f" = Fish, "s" = Shark,
                                                            // "e" = Eel, "m" = Manta Ray, "a" = Anenome,
                                                            // "b" = Mine, "j" = Jellyfish
            Arrays.asList("c", "c", "c", "c", "c", "c", "a", "m", "e", "b", "b", "b", "b", "j", "j", "j", "f", "f", "f", "s",
                    "s", "s"));

    public Board() {
        InternalBoard = new String[16][16];
        PlayerBoard = new boolean[16][16];
        creaturesLeft = creatures.size();
    }

    /**
     * Initializes the board with creatures placed randomly
     */
    protected void initializeBoard() {
        rand = new Random();
        int maxAttempts = 1000;
        int attempts = 0;

        for (int i = 0; i < creaturesLeft; i++) {
            if (attempts >= maxAttempts) {
                System.out.println("Warning: Could not place all creatures with buffer");
                break;
            }

            int x = rand.nextInt(14) + 1;
            int y = rand.nextInt(14) + 1;

            if (validPosBuffer(x, y, 1)) { // Use buffer check for single-cell
                int idx = rand.nextInt(creatures.size());
                boolean placed = false;

                if (creatures.get(idx).equals("c")) {
                    InternalBoard[x][y] = "c";
                    placed = true;
                } else if (creatures.get(idx).equals("b")) {
                    InternalBoard[x][y] = "b";
                    placed = true;
                } else if (creatures.get(idx).equals("j")) {
                    InternalBoard[x][y] = "j";
                    placed = true;
                } else if (creatures.get(idx).equals("f")) {
                    placed = placeFish(x, y);
                } else if (creatures.get(idx).equals("s")) {
                    placed = placeShark(x, y);
                } else if (creatures.get(idx).equals("e")) {
                    placed = placeEel(x, y);
                } else if (creatures.get(idx).equals("m")) {
                    placed = placeMantaRay(x, y);
                } else if (creatures.get(idx).equals("a")) {
                    placed = placeAnenome(x, y);
                }

                if (placed) {
                    creatures.remove(idx);
                    attempts = 0;
                } else {
                    i--;
                    attempts++;
                }
            } else {
                i--;
                attempts++;
            }
        }
    }

    /**
     * Displays the internal board (for debugging purposes)
     */
    protected void getInternalBoard() {
        System.out.println("\n=== INTERNAL BOARD VIEW ===");
        for (int i = 0; i < InternalBoard.length; i++) {
            for (int j = 0; j < InternalBoard[i].length; j++) {
                if (InternalBoard[i][j] == null) {
                    System.out.print("~ ");
                } else {
                    System.out.print(InternalBoard[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * You can figure this one out :)
     * 
     * @return int
     */
    public int getCreaturesLeft() {
        return creaturesLeft;
    }

    // Internal methods

    /**
     * Checks if a position is valid for placing a creature
     * 
     * @param x X-Coord
     * @param y Y-Coord
     * @return boolean Returns validity
     */
    protected boolean validPositioning(int x, int y) {
        if (x < 0 || x >= 16 || y < 0 || y >= 16) {
            return false;
        }
        return InternalBoard[x][y] == null;
    }

    /**
     * Checks if a position and its surrounding cells are valid (includes buffer)
     * 
     * @param bufferSize Number of cells to check around the position
     * @return boolean Returns validity
     */
    protected boolean validPosBuffer(int x, int y, int bufferSize) {
        // Check if the position itself is valid
        if (!validPositioning(x, y)) {
            return false;
        }

        // Check surrounding cells within buffer zone
        for (int dx = -bufferSize; dx <= bufferSize; dx++) {
            for (int dy = -bufferSize; dy <= bufferSize; dy++) {
                if (dx == 0 && dy == 0)
                    continue; // Skip the center cell

                int newX = x + dx;
                int newY = y + dy;

                // If within bounds and occupied, invalid
                if (newX >= 0 && newX < 16 && newY >= 0 && newY < 16) {
                    if (InternalBoard[newX][newY] != null) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Checks if all cells for a multi-cell creature have valid buffer zones
     */
    private boolean checkMultiCellBuffer(int[][] positions, int bufferSize) {
        // First check if all positions are valid
        for (int[] pos : positions) {
            if (!validPositioning(pos[0], pos[1])) {
                return false;
            }
        }

        // Check buffer around each position
        for (int[] pos : positions) {
            for (int dx = -bufferSize; dx <= bufferSize; dx++) {
                for (int dy = -bufferSize; dy <= bufferSize; dy++) {
                    int newX = pos[0] + dx;
                    int newY = pos[1] + dy;

                    // Skip if this is one of the creature's own cells
                    boolean isOwnCell = false;
                    for (int[] posCheck : positions) {
                        if (posCheck[0] == newX && posCheck[1] == newY) {
                            isOwnCell = true;
                            break;
                        }
                    }

                    if (isOwnCell)
                        continue;

                    // Check if buffer cell is occupied
                    if (newX >= 0 && newX < 16 && newY >= 0 && newY < 16) {
                        if (InternalBoard[newX][newY] != null) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }

    /**
     * @return boolean
     */
    private boolean placeFish(int x, int y) {
        // Try opposite orientation first
        if (lastFishOrientation == null || lastFishOrientation.equals("vertical")) {
            if (placeFishHori(x, y)) {
                lastFishOrientation = "horizontal";
                return true;
            } else if (placeFishVert(x, y)) {
                lastFishOrientation = "vertical";
                return true;
            }
        } else {
            if (placeFishVert(x, y)) {
                lastFishOrientation = "vertical";
                return true;
            } else if (placeFishHori(x, y)) {
                lastFishOrientation = "horizontal";
                return true;
            }
        }
        return false;
    }

    /**
     * @return boolean
     */
    private boolean placeShark(int x, int y) {
        if (lastSharkOrientation == null || lastSharkOrientation.equals("vertical")) {
            if (placeSharkHori(x, y)) {
                lastSharkOrientation = "horizontal";
                return true;
            } else if (placeSharkVert(x, y)) {
                lastSharkOrientation = "vertical";
                return true;
            }
        } else {
            if (placeSharkVert(x, y)) {
                lastSharkOrientation = "vertical";
                return true;
            } else if (placeSharkHori(x, y)) {
                lastSharkOrientation = "horizontal";
                return true;
            }
        }
        return false;
    }

    private boolean placeFishHori(int x, int y) {
        int[][] positions = { { x, y }, { x, y + 1 } };
        if (checkMultiCellBuffer(positions, 1)) { // 1-cell buffer
            InternalBoard[x][y] = "f";
            InternalBoard[x][y + 1] = "f";
            return true;
        }
        return false;
    }

    private boolean placeFishVert(int x, int y) {
        int[][] positions = { { x, y }, { x + 1, y } };
        if (checkMultiCellBuffer(positions, 1)) { // 1-cell buffer
            InternalBoard[x][y] = "f";
            InternalBoard[x + 1][y] = "f";
            return true;
        }
        return false;
    }

    private boolean placeSharkHori(int x, int y) {
        int[][] positions = { { x, y - 1 }, { x, y }, { x, y + 1 } };
        if (checkMultiCellBuffer(positions, 1)) { // 1-cell buffer
            InternalBoard[x][y - 1] = "s";
            InternalBoard[x][y] = "s";
            InternalBoard[x][y + 1] = "s";
            return true;
        }
        return false;
    }

    private boolean placeSharkVert(int x, int y) {
        int[][] positions = { { x - 1, y }, { x, y }, { x + 1, y } };
        if (checkMultiCellBuffer(positions, 1)) { // 1-cell buffer
            InternalBoard[x - 1][y] = "s";
            InternalBoard[x][y] = "s";
            InternalBoard[x + 1][y] = "s";
            return true;
        }
        return false;
    }

    // Eel placement - 4 cells zig-zag pattern
    private boolean placeEel(int x, int y) {
        // Try different zig-zag orientations
        // Pattern 1: Right-down-right-down (like stairs going right)
        int[][] zigzag1 = { { x, y }, { x, y + 1 }, { x + 1, y + 1 }, { x + 1, y + 2 } };
        if (checkMultiCellBuffer(zigzag1, 1)) {
            for (int[] pos : zigzag1) {
                InternalBoard[pos[0]][pos[1]] = "e";
            }
            return true;
        }

        // Pattern 2: Down-right-down-right (like stairs going down)
        int[][] zigzag2 = { { x, y }, { x + 1, y }, { x + 1, y + 1 }, { x + 2, y + 1 } };
        if (checkMultiCellBuffer(zigzag2, 1)) {
            for (int[] pos : zigzag2) {
                InternalBoard[pos[0]][pos[1]] = "e";
            }
            return true;
        }

        // Pattern 3: Left-down-left-down (mirror of pattern 1)
        int[][] zigzag3 = { { x, y }, { x, y - 1 }, { x + 1, y - 1 }, { x + 1, y - 2 } };
        if (checkMultiCellBuffer(zigzag3, 1)) {
            for (int[] pos : zigzag3) {
                InternalBoard[pos[0]][pos[1]] = "e";
            }
            return true;
        }

        // Pattern 4: Up-right-up-right (mirror of pattern 2)
        int[][] zigzag4 = { { x, y }, { x - 1, y }, { x - 1, y + 1 }, { x - 2, y + 1 } };
        if (checkMultiCellBuffer(zigzag4, 1)) {
            for (int[] pos : zigzag4) {
                InternalBoard[pos[0]][pos[1]] = "e";
            }
            return true;
        }

        return false;
    }

    // Manta Ray placement - V-shaped (3 cells)
    private boolean placeMantaRay(int x, int y) {
        // V pointing down
        int[][] vDown = { { x, y - 1 }, { x + 1, y }, { x, y + 1 } };
        if (checkMultiCellBuffer(vDown, 1)) {
            for (int[] pos : vDown) {
                InternalBoard[pos[0]][pos[1]] = "m";
            }
            return true;
        }

        // V pointing up
        int[][] vUp = { { x, y - 1 }, { x - 1, y }, { x, y + 1 } };
        if (checkMultiCellBuffer(vUp, 1)) {
            for (int[] pos : vUp) {
                InternalBoard[pos[0]][pos[1]] = "m";
            }
            return true;
        }

        // V pointing right
        int[][] vRight = { { x - 1, y }, { x, y + 1 }, { x + 1, y } };
        if (checkMultiCellBuffer(vRight, 1)) {
            for (int[] pos : vRight) {
                InternalBoard[pos[0]][pos[1]] = "m";
            }
            return true;
        }

        // V pointing left
        int[][] vLeft = { { x - 1, y }, { x, y - 1 }, { x + 1, y } };
        if (checkMultiCellBuffer(vLeft, 1)) {
            for (int[] pos : vLeft) {
                InternalBoard[pos[0]][pos[1]] = "m";
            }
            return true;
        }

        return false;
    }

    // Anenome placement - Circle/square shape (4 cells in 2x2)
    private boolean placeAnenome(int x, int y) {
        int[][] square = { { x, y }, { x, y + 1 }, { x + 1, y }, { x + 1, y + 1 } };
        if (checkMultiCellBuffer(square, 1)) {
            for (int[] pos : square) {
                InternalBoard[pos[0]][pos[1]] = "a";
            }
            return true;
        }
        return false;
    }
}
