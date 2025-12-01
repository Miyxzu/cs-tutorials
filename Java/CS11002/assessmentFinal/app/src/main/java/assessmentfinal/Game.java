package assessmentfinal;

import java.util.*;
import java.io.*;

public class Game {
    private Player[] players;
    private int currentPlayerIndex;
    private int currentTurn;
    private Timer gameTimer;

    public boolean startNewGame() {
        
    }

    public boolean saveCurrentGame() {
        
    }

    public boolean loadPreviousGame(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            return false;
        }

        BufferedReader reader = new BufferedReader(new FileReader(file));
        // Logic to read the file and load game state would go here
        return true;
    }

    public void turn() {
        gameTimer = new Timer();
        gameTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                
            }
        }, (long)(1.5*60*1000));

    }

    public void checkForGameFinished() {
        for (Player p : players) {
            if (!p.isAlive()) {
                System.out.println(p.getName() + " has hit all 4 bombs and is out of the game!");
            }
        }
    }
}