package assessmentfinal;

import java.util.*;

public class Board {
    private Random rand;
    protected String[][] InternalBoard;
    protected boolean[][] PlayerBoard;
    private int creaturesLeft;
    private String lastFishOrientation = null;
    private String lastSharkOrientation = null;
    private final List<String> creatures = new ArrayList<>(
            Arrays.asList("c", "c", "c", "c", "c", "c", "c", "c", "b", "b", "b", "j", "j", "j", "f", "f", "f", "f", "s", "s", "s"));

    public Board() {
        InternalBoard = new String[16][16];
        PlayerBoard = new boolean[16][16];
        creaturesLeft = creatures.size();
    }

    /**
     * 
     */
    protected void addCreaturesToBoard() {
        rand = new Random();

        System.out.println("Placing creatures on the board...");

        for (int i = 0; i < creaturesLeft; i++) {
            int x = rand.nextInt(12) + 1;
            int y = rand.nextInt(12) + 1;

            if (validPositioning(x, y)) {
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
                }

                if (placed) {
                    creatures.remove(idx);
                } else {
                    i--;
                }
            } else {
                i--;
            }
        }
    }

    /**
     * @return boolean
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

    // Internal methods
    /**
     * @param x
     * @param y
     */
    private boolean validPositioning(int x, int y) {
        if (x < 0 || x >= 16 || y < 0 || y >= 16) {
            return false;
        }
        return InternalBoard[x][y] == null;
    }

    /**
     * @param x
     * @param y
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
     * @param x
     * @param y
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

    /**
     * @param x
     * @param y
     * @return boolean
     */
    private boolean placeFishHori(int x, int y) {
        if (validPositioning(x, y) && validPositioning(x, y + 1)) {
            InternalBoard[x][y] = "f";
            InternalBoard[x][y + 1] = "f";
            return true;
        }
        return false;
    }

    /**
     * @param x
     * @param y
     * @return boolean
     */
    private boolean placeFishVert(int x, int y) {
        if (validPositioning(x, y) && validPositioning(x + 1, y)) {
            InternalBoard[x][y] = "f";
            InternalBoard[x + 1][y] = "f";
            return true;
        }
        return false;
    }

    /**
     * @param x
     * @param y
     * @return boolean
     */
    private boolean placeSharkHori(int x, int y) {
        if (validPositioning(x, y - 1) && validPositioning(x, y) && validPositioning(x, y + 1)) {
            InternalBoard[x][y - 1] = "s";
            InternalBoard[x][y] = "s";
            InternalBoard[x][y + 1] = "s";
            return true;
        }
        return false;
    }

    /**
     * @param x
     * @param y
     * @return boolean
     */
    private boolean placeSharkVert(int x, int y) {
        if (validPositioning(x - 1, y) && validPositioning(x, y) && validPositioning(x + 1, y)) {
            InternalBoard[x - 1][y] = "s";
            InternalBoard[x][y] = "s";
            InternalBoard[x + 1][y] = "s";
            return true;
        }
        return false;
    }
}
