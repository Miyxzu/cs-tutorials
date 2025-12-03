package assessmentfinal;

import java.util.*;
import java.io.*;

public class Game {

    public static void main(String[] args) {
        Game game = new Game();
        in = new Scanner(System.in);
        String[] playerNames = { "Player1", "Player2" };
        if (!game.startNewGame(playerNames)) {
            System.out.println("Error starting new game.");
            in.close();
            return;
        }

        game.turn();

        in.close();
    }

    private static Scanner in;
    private Player[] players;
    private int currentPlayerIndex;
    private int currentTurn;
    private Timer gameTimer;
    private static final int MAX_TURNS = 30;
    private static final int MAX_TURN_TIME = 120; // in seconds

    public Game() {
        players = new Player[2];
        currentPlayerIndex = 0;
        currentTurn = 1;
    }

    public boolean startNewGame(String[] playerNames) {
        for (int i = 0; i < playerNames.length; i++) {
            players[i] = new Player(playerNames[i]);
            players[i].board.initializeBoard();
        }
        System.out.println("Placing creatures on the board...");

        players[0].setCurrentTurn(true);
        return true;
    }

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

    public boolean loadPreviousGame(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            System.out.println("Save file not found.");
            return false;
        }

        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;

            while ((line = br.readLine()) != null && !line.equals("GAME_STATE")) {
            }

            currentTurn = Integer.parseInt(br.readLine().split("=")[1]);
            currentPlayerIndex = Integer.parseInt(br.readLine().split("=")[1]);
            br.readLine(); // Skip empty line

            for (int i = 0; i < players.length; i++) {
                br.read(); // Skip player header
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
                    String[] cells = br.readLine().split(" ");
                    for (int col = 0; col < 16; col++) {
                        internalBoard[row][col] = cells[col].equals("~") ? null : cells[col];
                    }
                }
                br.readLine(); // Skip empty line

                // Load player board
                br.readLine(); // Skip PLAYER_BOARD header
                boolean[][] playerBoard = new boolean[16][16];
                for (int row = 0; row < 16; row++) {
                    String[] cells = br.readLine().split(" ");
                    for (int col = 0; col < 16; col++) {
                        playerBoard[row][col] = cells[col].equals("1");
                    }
                }

                players[i].overrideCurrentBoards(internalBoard, playerBoard, creaturesLeft);
            }

            for (int i = 0; i < players.length; i++) {
                players[i].setCurrentTurn(i == currentPlayerIndex);
            }

            System.out.println("Game loaded successfully.");
            return true;
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("Error loading game: " + e.getMessage());
            return false;
        }
    }

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

    public void turn() {
        int choice = 0;
        boolean playing = true;
        String filename = "";
        File checkFile;

        turnTimer();

        players[currentPlayerIndex].board.getInternalBoard();
        players[currentPlayerIndex].displayBoard();
        getPlayerList();
        System.out.println("Turn " + currentTurn + ": It's " + players[currentPlayerIndex].getName() + "'s turn.");
        System.out.print(
                "Choose an action:\n" + "1) Explore\n" + "2) Save Current Gamestate\n" + "3) Quit Game\n" + ">> ");

        in = new Scanner(System.in);

        choice = in.nextInt();
        in.nextLine(); // Consume newline

        while (choice != -1) {
            switch (choice) {
            case 1:
                System.out.print("Enter your guess (X, Y): ");
                String coords = in.nextLine();
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
                int x = Integer.parseInt(parts[0]);
                int y = Integer.parseInt(parts[1]);
                players[currentPlayerIndex].Guess(x, y);
                choice = -1;
                break;
            case 2:
                gameTimer.cancel();
                System.out.println("Enter name to save the game.\n" + ">> ");
                filename = in.nextLine();

                checkFile = new File("./SavedGames/", filename + ".txt");
                if (checkFile.exists()) {
                    System.out.println("A save with that name already exists.");
                    System.out.println("Would you like to overwrite it? (y/n)\n" + ">> ");
                    if (!in.nextLine().equalsIgnoreCase("n")) {
                        System.out.println("Enter name to save the game\n" + ">> ");
                        filename = in.nextLine();
                        break;
                    }
                }

                try {
                    saveCurrentGame(filename);
                    System.out.println("Game saved successfully.");
                } catch (Exception e) {
                    System.out.println("Error saving the game: " + e.getMessage());
                }

                turnTimer();
                break;
            case 4:
                System.out.println("What item would you like to use?\n" + ">> ");
            case 3:
                gameTimer.cancel();
                System.out.println("Would you like to save before quitting? (y/n)\n" + ">> ");
                String confirm = in.nextLine();
                if (confirm.equalsIgnoreCase("y")) {
                    System.out.print("Enter filename to save the game: ");
                    filename = in.nextLine();

                    System.out.println("Enter name to save the game.\n" + ">> ");
                    filename = in.nextLine();

                    checkFile = new File("./SavedGames/", filename + ".txt");
                    if (checkFile.exists()) {
                        System.out.println("A save with that name already exists.");
                        System.out.println("Would you like to overwrite it? (y/n)\n" + ">> ");
                        if (!in.nextLine().equalsIgnoreCase("n")) {
                            System.out.println("Enter name to save the game\n" + ">> ");
                            filename = in.nextLine();
                            break;
                        }
                    }

                    try {
                        saveCurrentGame(filename);
                        System.out.println("Game saved successfully.");
                    } catch (Exception e) {
                        System.out.println("Error saving the game: " + e.getMessage());
                        System.out.println("Quitting without saving.");
                    }
                } else {
                    System.out.println("Continuing the game.");
                    turnTimer();
                    break;
                }
                playing = false;
                choice = -1;
                break;
            default:
                System.out.println("Invalid option");
            }
        }
        if (playing) {
            checkForGameFinished();
        }
    }

    /**
     * See https://stackoverflow.com/a/4044793
     */
    private void turnTimer() {
        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Time's up! Your turn is over.");
                checkForGameFinished();
            }
        }, (long) (MAX_TURN_TIME * 1000));
    }

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

    public void getPlayerList() {
        System.out.printf("%-25s%-10s%-8s%-8s\n", "Player", "Current Turn", "Health", "Score");
        for (Player p : players) {
            System.out.println(p);
        }
        System.out.println();
    }

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
