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
        creaturesLeft = 0;
        for (String creature : creatures) {
            if (!creature.equals("b") && !creature.equals("j")) {
                creaturesLeft++;
            }
        }
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
        for (int bufferX = -bufferSize; bufferX <= bufferSize; bufferX++) {
            for (int bufferY = -bufferSize; bufferY <= bufferSize; bufferY++) {
                if (bufferX == 0 && bufferY == 0)
                    continue; // Skip the center cell

                int newX = x + bufferX;
                int newY = y + bufferY;

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
     * Checks if all cells for a multi-cell creature (i.e. shark, eel, manta) have valid buffer zones
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
            for (int bufferX = -bufferSize; bufferX <= bufferSize; bufferX++) {
                for (int bufferY = -bufferSize; bufferY <= bufferSize; bufferY++) {
                    int newX = pos[0] + bufferX;
                    int newY = pos[1] + bufferY;

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
     * Placement for Fish (2 cells)
     * @return boolean
     */
    private boolean placeFish(int x, int y) {
        // Try opposite orientation first
        if (lastFishOrientation == null || lastFishOrientation.equals("vertical")) {
            if (placeFishOri(x, y, 0)) {
                lastFishOrientation = "horizontal";
                return true;
            } else if (placeFishOri(x, y, 1)) {
                lastFishOrientation = "vertical";
                return true;
            }
        } else {
            if (placeFishOri(x, y, 1)) {
                lastFishOrientation = "vertical";
                return true;
            } else if (placeFishOri(x, y, 0)) {
                lastFishOrientation = "horizontal";
                return true;
            }
        }
        return false;
    }

    /**
     * Placement for Shark (3 cells)
     * @return boolean
     */
    private boolean placeShark(int x, int y) {
        if (lastSharkOrientation == null || lastSharkOrientation.equals("vertical")) {
            if (placeSharkOri(x, y, 0)) {
                lastSharkOrientation = "horizontal";
                return true;
            } else if (placeSharkOri(x, y, 1)) {
                lastSharkOrientation = "vertical";
                return true;
            }
        } else {
            if (placeSharkOri(x, y, 1)) {
                lastSharkOrientation = "vertical";
                return true;
            } else if (placeSharkOri(x, y, 0)) {
                lastSharkOrientation = "horizontal";
                return true;
            }
        }
        return false;
    }

    /**
     * Determines placement of Fish/Shark based on orientation
     * @param x
     * @param y
     * @param orientation 0 = horizontal, 1 = vertical
     * @return boolean Returns success of placement
     */
    private boolean placeFishOri(int x, int y, int orientation) {
        int[][] hori = { { x, y }, { x, y + 1 } };
        int[][] vert = { { x, y }, { x + 1, y } };

        if (orientation == 0) { // horizontal
            if (checkMultiCellBuffer(hori, 1)) {
                InternalBoard[x][y] = "f";
                InternalBoard[x][y + 1] = "f";
                return true;
            }
        } else { // vertical
            if (checkMultiCellBuffer(vert, 1)) {
                InternalBoard[x][y] = "f";
                InternalBoard[x + 1][y] = "f";
                return true;
            }
        }
        return false;
    }

    private boolean placeSharkOri(int x, int y, int orientation) {
        int[][] hori = { { x, y - 1 }, { x, y }, { x, y + 1 } };
        int[][] vert = { { x - 1, y }, { x, y }, { x + 1, y } };

        if (orientation == 0) { // horizontal
            if (checkMultiCellBuffer(hori, 1)) {
                InternalBoard[x][y - 1] = "s";
                InternalBoard[x][y] = "s";
                InternalBoard[x][y + 1] = "s";
                return true;
            }
        } else { // vertical
            if (checkMultiCellBuffer(vert, 1)) {
                InternalBoard[x - 1][y] = "s";
                InternalBoard[x][y] = "s";
                InternalBoard[x + 1][y] = "s";
                return true;
            }
        }
        return false;
    }

    /**
     * Eel placement - 4 cells zig-zag
     * @param x
     * @param y
     * @return boolean Returns success of placement
     */
    private boolean placeEel(int x, int y) {
        // Try different zig-zag orientations
        // Pattern 1: Right-down-right-down (like stairs going right)
        int[][] rdrd = { { x, y }, { x, y + 1 }, { x + 1, y + 1 }, { x + 1, y + 2 } };
        if (checkMultiCellBuffer(rdrd, 1)) {
            for (int[] pos : rdrd) {
                InternalBoard[pos[0]][pos[1]] = "e";
            }
            return true;
        }

        // Pattern 2: Down-right-down-right (like stairs going down)
        int[][] drdr = { { x, y }, { x + 1, y }, { x + 1, y + 1 }, { x + 2, y + 1 } };
        if (checkMultiCellBuffer(drdr, 1)) {
            for (int[] pos : drdr) {
                InternalBoard[pos[0]][pos[1]] = "e";
            }
            return true;
        }

        // Pattern 3: Left-down-left-down (mirror of pattern 1)
        int[][] ldld = { { x, y }, { x, y - 1 }, { x + 1, y - 1 }, { x + 1, y - 2 } };
        if (checkMultiCellBuffer(ldld, 1)) {
            for (int[] pos : ldld) {
                InternalBoard[pos[0]][pos[1]] = "e";
            }
            return true;
        }

        // Pattern 4: Up-right-up-right (mirror of pattern 2)
        int[][] urur = { { x, y }, { x - 1, y }, { x - 1, y + 1 }, { x - 2, y + 1 } };
        if (checkMultiCellBuffer(urur, 1)) {
            for (int[] pos : urur) {
                InternalBoard[pos[0]][pos[1]] = "e";
            }
            return true;
        }

        return false;
    }

    /**
     * Manta Ray placement - V-shaped (3 cells)
     * @param x
     * @param y
     * @return boolean Returns success of placement
     */
    private boolean placeMantaRay(int x, int y) {
        // pointing down (V)
        int[][] down = { { x, y - 1 }, { x + 1, y }, { x, y + 1 } };
        if (checkMultiCellBuffer(down, 1)) {
            for (int[] pos : down) {
                InternalBoard[pos[0]][pos[1]] = "m";
            }
            return true;
        }

        // pointing up (^)
        int[][] up = { { x, y - 1 }, { x - 1, y }, { x, y + 1 } };
        if (checkMultiCellBuffer(up, 1)) {
            for (int[] pos : up) {
                InternalBoard[pos[0]][pos[1]] = "m";
            }
            return true;
        }

        // pointing right (>)
        int[][] Right = { { x - 1, y }, { x, y + 1 }, { x + 1, y } };
        if (checkMultiCellBuffer(Right, 1)) {
            for (int[] pos : Right) {
                InternalBoard[pos[0]][pos[1]] = "m";
            }
            return true;
        }

        // pointing left (<)
        int[][] Left = { { x - 1, y }, { x, y - 1 }, { x + 1, y } };
        if (checkMultiCellBuffer(Left, 1)) {
            for (int[] pos : Left) {
                InternalBoard[pos[0]][pos[1]] = "m";
            }
            return true;
        }

        return false;
    }

    /**
     * Anenome placement - 4 cells circle-shaped (2x2)
     * @param x
     * @param y
     * @return boolean Returns success of placement
     */
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
