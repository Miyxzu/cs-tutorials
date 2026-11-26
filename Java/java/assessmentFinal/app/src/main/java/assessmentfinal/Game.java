package assessmentfinal;

import java.util.*;
import java.io.*;

public class Game {
    private static Scanner in;
    private Player[] players;
    private int currentPlayerIndex;
    private int currentTurn;
    private Timer gameTimer;
    private static final int MAX_TURNS = 100;
    private static final int MAX_TURN_TIME = 180; // in seconds

    /**
     * Constructor Sets up initial game state
     */
    public Game() {
        players = new Player[2];
        currentPlayerIndex = 0;
        currentTurn = 1;
    }

    /**
     * Starts a new game with provided player names
     * 
     * @param playerNames Array of player names
     * @return boolean Returns whether game started successfully
     */
    public boolean startNewGame(String[] playerNames) {
        for (int i = 0; i < playerNames.length; i++) {
            players[i] = new Player(playerNames[i]);
            players[i].board.initializeBoard();
        }
        System.out.println("Placing creatures on the board...");

        players[0].setCurrentTurn(true);
        return true;
    }

    /**
     * Saves the current game state to a file
     * 
     * @param filename Name of the file to save the game state
     * @throws IOException If an I/O error occurs
     */
    public void saveCurrentGame(String filename) throws IOException {
        File dir = new File("./SavedGames/");

        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File(dir, filename + ".txt");

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            bw.write("GAME_STATE\n");
            bw.write("CurrentTurn=" + currentTurn + "\n");
            bw.write("CurrentPlayerIndex=" + currentPlayerIndex + "\n");
            bw.write("\n");

            // Save each player's state
            for (int i = 0; i < players.length; i++) {
                bw.write("PLAYER_" + i + "\n");
                bw.write("Name=" + players[i].getName() + "\n");
                bw.write("Health=" + players[i].getHealth() + "\n");
                bw.write("Score=" + players[i].getScore() + "\n");
                bw.write("CreaturesLeft=" + players[i].getCreaturesLeft() + "\n");
                bw.write("\n");

                // Save internal board (actual creature positions)
                bw.write("INTERNAL_BOARD\n");
                for (int row = 0; row < 16; row++) {
                    for (int col = 0; col < 16; col++) {
                        String cell = players[i].board.InternalBoard[row][col];
                        bw.write((cell == null ? "~" : cell) + " ");
                    }
                    bw.write("\n");
                }
                bw.write("\n");

                // Save player board (revealed positions)
                bw.write("PLAYER_BOARD\n");
                for (int row = 0; row < 16; row++) {
                    for (int col = 0; col < 16; col++) {
                        bw.write((players[i].board.PlayerBoard[row][col] ? "1" : "0") + " ");
                    }
                    bw.write("\n");
                }
                bw.write("\n");
            }
        }
    }

    /**
     * Loads a previously saved game state from a file
     * 
     * @param filename Name of the file to load the game state from
     * @return boolean Returns whether the game was loaded successfully
     * @throws IOException If an I/O error occurs
     */
    public boolean loadPreviousGame(String filename) throws IOException {
        File file = new File("./SavedGames/", filename + ".txt");
        if (!file.exists()) {
            System.out.println("Save file not found.");
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            while ((line = br.readLine()) != null && !line.equals("GAME_STATE")) {
            }

            currentTurn = Integer.parseInt(br.readLine().split("=")[1]);
            currentPlayerIndex = Integer.parseInt(br.readLine().split("=")[1]);
            br.readLine(); // Skip empty line

            for (int i = 0; i < players.length; i++) {
                br.readLine(); // Skip player header
                String name = br.readLine().split("=")[1];
                int health = Integer.parseInt(br.readLine().split("=")[1]);
                int score = Integer.parseInt(br.readLine().split("=")[1]);
                int creaturesLeft = Integer.parseInt(br.readLine().split("=")[1]);
                br.readLine(); // Skip empty line

                players[i] = new Player(name);
                players[i].setHealth(health);
                players[i].setScore(score);

                // Load internal board
                br.readLine(); // Skip INTERNAL_BOARD header
                String[][] internalBoard = new String[16][16];
                for (int row = 0; row < 16; row++) {
                    line = br.readLine();
                    if (line == null || line.trim().isEmpty()) {
                        System.out.println("Error: Incomplete internal board data at row " + row);
                        return false;
                    }
                    String[] cells = line.trim().split(" ");
                    if (cells.length != 16) {
                        System.out.println("Error: Expected 16 cells but got " + cells.length + " at row " + row);
                        return false;
                    }
                    for (int col = 0; col < 16; col++) {
                        internalBoard[row][col] = cells[col].equals("~") ? null : cells[col];
                    }
                }
                br.readLine(); // Skip empty line

                // Load player board
                br.readLine(); // Skip PLAYER_BOARD header
                boolean[][] playerBoard = new boolean[16][16];
                for (int row = 0; row < 16; row++) {
                    line = br.readLine();
                    if (line == null || line.trim().isEmpty()) {
                        System.out.println("Error: Incomplete player board data at row " + row);
                        return false;
                    }
                    String[] cells = line.trim().split(" ");
                    if (cells.length != 16) {
                        System.out.println("Error: Expected 16 cells but got " + cells.length + " at row " + row);
                        return false;
                    }
                    for (int col = 0; col < 16; col++) {
                        playerBoard[row][col] = cells[col].equals("1");
                    }
                }

                line = br.readLine(); // Skip empty line

                players[i].overrideCurrentBoards(internalBoard, playerBoard, creaturesLeft);
            }

            for (int i = 0; i < players.length; i++) {
                players[i].setCurrentTurn(i == currentPlayerIndex);
            }

            System.out.println("Game loaded successfully.");
            return true;
        } catch (NumberFormatException e) {
            System.out.println("Error loading game: Invalid number format - " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Error loading game: Missing data in save file - " + e.getMessage());
            e.printStackTrace();
            return false;
        } catch (Exception e) {
            System.out.println("Error loading game: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Lists all saved games in the SavedGames directory
     * 
     * @return boolean Returns whether there are saved games to list
     */
    public boolean getSavedGamesList() {
        File dir = new File("./SavedGames/");

        if (!dir.exists()) {
            return false;
        }

        File[] filesList = dir.listFiles((d, name) -> name.endsWith(".txt"));
        if (filesList == null || filesList.length == 0) {
            return false;
        }

        System.out.println("Saved Games:");
        for (File file : filesList) {
            System.out.println("- " + file.getName());
        }
        return true;
    }

    /**
     * Manages a player's turn Check each switch case for details
     */
    public void turn() {
        int choice = 0;
        boolean playing = true;
        boolean timeExpired = false;
        String filename = "";
        String overwrite = "";
        File checkFile;

        turnTimer();

        // Display current player's board and status
        players[currentPlayerIndex].displayBoard();
        getPlayerList();
        System.out.println("Turn " + currentTurn + ": It's " + players[currentPlayerIndex].getName() + "'s turn.");
        System.out.print(
                "Choose an action:\n" + "1) Explore\n" + "2) Save Current Gamestate\n" + "3) Quit Game\n" + ">> ");

        in = new Scanner(System.in);

        choice = in.nextInt();
        in.nextLine(); // Consume newline
        System.out.println();

        while (choice != -1) {
            switch (choice) {
            case 1: // Guess
                System.out.print("Enter your guess (X, Y): ");
                String coords = "";

                try {
                    coords = in.nextLine();
                } catch (Exception e) {
                    // Timer expired during input
                    gameTimer.cancel();
                    timeExpired = true;
                    break;
                }

                String[] parts;
                if (!coords.contains(", ")) {
                    if (!coords.contains(",")) {
                        System.out.println("Invalid input format.");
                        choice = -1;
                        break;
                    } else {
                        parts = coords.split(",");
                        if (parts.length != 2) {
                            System.out.println("Invalid input format.");
                            choice = -1;
                            break;
                        }
                        coords = parts[0].trim() + ", " + parts[1].trim();
                    }
                } else {
                    parts = coords.split(", ");
                    if (parts.length != 2) {
                        System.out.println("Invalid input format.");
                        choice = -1;
                        break;
                    }
                }

                try {
                    // Accounting for the fact that 2D array is [Y][X]
                    int x = Integer.parseInt(parts[1].trim());
                    int y = Integer.parseInt(parts[0].trim());

                    // Cancel timer before processing guess
                    gameTimer.cancel();

                    players[currentPlayerIndex].Guess(x, y);
                    choice = -1;
                } catch (NumberFormatException e) {
                    System.out.println("Invalid coordinates. Please enter numbers.");
                    choice = -1;
                }

                break;
            case 2: // Save Game
                gameTimer.cancel();
                System.out.print("Enter name to save the game.\n" + ">> ");
                filename = in.nextLine();

                checkFile = new File("./SavedGames/", filename + ".txt");
                if (checkFile.exists()) {
                    System.out.println("A save with that name already exists.");
                    System.out.print("Would you like to overwrite it? (y/n)\n" + ">> ");
                    overwrite = in.nextLine();

                    System.out.println();
                    if (overwrite.equalsIgnoreCase("n")) {
                        System.out.print("Enter new name to save the game\n" + ">> ");
                        filename = in.nextLine();
                        System.out.println();
                    } else {
                        System.out.println("Overwriting existing save...");
                    }
                }

                try {
                    saveCurrentGame(filename);
                    System.out.println("Game saved successfully.");
                } catch (Exception e) {
                    System.out.println("Error saving the game: " + e.getMessage());
                }

                turnTimer();
                System.out.print("\nChoose an action:\n" + "1) Explore\n" + "2) Save Current Gamestate\n"
                        + "3) Quit Game\n" + ">> ");

                choice = in.nextInt();
                in.nextLine(); // Consume newline
                System.out.println();
                break;
            case 3: // Quit Game w/o Saving
                gameTimer.cancel();
                System.out.print("Would you like to save before quitting? (y/n)\n" + ">> ");
                String confirm = in.nextLine();
                if (confirm.equalsIgnoreCase("y")) {
                    System.out.print("Enter name to save the game.\n" + ">> ");
                    filename = in.nextLine();

                    checkFile = new File("./SavedGames/", filename + ".txt");
                    if (checkFile.exists()) {
                        System.out.println("A save with that name already exists.");
                        System.out.print("Would you like to overwrite it? (y/n)\n" + ">> ");
                        overwrite = in.nextLine();

                        System.out.println();
                        if (overwrite.equalsIgnoreCase("n")) {
                            System.out.println("Quitting without saving...");
                            playing = false;
                            choice = -1;
                            clearScreen();
                            break;
                        }
                    }

                    try {
                        saveCurrentGame(filename);
                        System.out.println("Game saved successfully.");
                    } catch (Exception e) {
                        System.out.println("Error saving the game: " + e.getMessage());
                    }
                }

                System.out.println();
                System.out.println("Quitting...");
                playing = false;
                choice = -1;
                clearScreen();
                break;
            case 4: // Idk if i decide to work on this later or just remove it
                System.out.print("What item would you like to use?\n" + ">> ");
                break;
            default:
                System.out.println("Invalid option");
            }
        }
        if (playing && !timeExpired) {
            checkForGameFinished();
        } else if (timeExpired) {
            System.out.println("\nTime has expired for this turn.");
            System.out.println("Your turn has ended.");
            checkForGameFinished();
        }
    }

    /**
     * See https://stackoverflow.com/a/4044793 Creates a timer for the player's turn
     */
    private void turnTimer() {
        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("\n\n=== TIME'S UP! ===");
                System.out.println("Your turn has ended.");
            }
        }, (long) (MAX_TURN_TIME * 1000));
    }

    /**
     * Switches turns between players
     */
    private void switchTurns() {
        for (int i = 0; i < players.length; i++) {
            if (players[i].isCurrentTurn() == true) {
                players[i].setCurrentTurn(false);
            } else {
                players[i].setCurrentTurn(true);
            }
        }
        currentPlayerIndex = (currentPlayerIndex + 1) % players.length;
        if (currentPlayerIndex == 0) {
            currentTurn++;
        }
    }

    /**
     * Checks if the game has finished and declares a winner if so if not, switches
     * turns and continues the game
     */
    private void checkForGameFinished() {
        int highestScore = 0;
        boolean winnerFlag = false;
        String winner = "";

        for (Player p : players) {
            if (!p.isAlive()) {
                System.out.println(p.getName() + " has hit all 4 bombs and is out of the game!");
                // Find the other player who is still alive as the winner
                for (Player other : players) {
                    if (other.isAlive() && other != p) {
                        winner = other.getName();
                        highestScore = other.getScore();
                        winnerFlag = true;
                        break;
                    }
                }
            } else if (p.getCreaturesLeft() == 0) {
                System.out.println(p.getName() + " has found all creatures and won the game!");
                p.setWon(true);
                winnerFlag = true;
                break;
            } else if (currentTurn >= MAX_TURNS) {
                if (p.getScore() > highestScore) {
                    highestScore = p.getScore();
                    winner = p.getName();
                    winnerFlag = true;
                    break;
                } else if (p.getScore() > highestScore && highestScore == 0) {
                    highestScore = p.getScore();
                    winner = p.getName();
                }
            }
        }

        if (winnerFlag) {
            System.out.println("Game Over!");
            if (!winner.equals("")) {
                System.out.println("The winner is " + winner + " with a score of " + highestScore + "!");
            }
            gameTimer.cancel();
            System.exit(0);
        } else {
            switchTurns();
            clearScreen();
            turn();
        }
    }

    /**
     * Displays the list of players with their current status
     */
    public void getPlayerList() {
        System.out.printf("%-20s%-15s%-8s%-8s\n", "Player", "Current Turn", "Health", "Score");
        for (Player p : players) {
            System.out.println(p);
        }
        System.out.println();
    }

    /**
     * Self-explanatory
     */
    private void clearScreen() {
        if (in.hasNextLine()) {
            @SuppressWarnings("unused")
            String leftover = in.nextLine();
        }
        System.out.println("Press Enter to continue...");
        in.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
