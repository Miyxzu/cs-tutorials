package assessmentfinal;

import java.util.*;
import java.io.*;

public class Game {
    private Player[] players;
    private int currentPlayerIndex;
    private int currentTurn;

    public boolean loadPreviousGame(String filename) throws IOException {
        File file = new File(filename);
        if (!file.exists()) {
            return false;
        }
        // Logic to read the file and load game state would go here
        return true;
    }

    public void turn() {
        
    }
}
