package otherStuff;

import org.nocrala.tools.texttablefmt.*;
import java.util.*;
import java.io.*;

public class wheel {
    public static void main(String[] args) {
        wheel w = new wheel();
        int choice = 0;
        Scanner in = new Scanner(System.in);
        while (choice != -1) {
            System.out.print("Welcome to the Spotify Playlist\n" +
                    "1) Create Playlist\n" +
                    "2) Rename Playlist\n" +
                    "3) Remove Playlist\n" +
                    "4) Show Playlist\n" +
                    "5) Add Song\n" +
                    "6) Edit Song\n" +
                    "7) Remove Song\n" +
                    "8) Sort Playlist\n" +
                    "9) Exit\n" +
                    ">> ");
            String name;
            choice = in.nextInt();
            switch (choice) {
                case 1:
                    
                    break;
            
                default:
                    break;
            }
        }
    }

    private static LinkedList<String> names;
    private static Random rand;
    private static String[] team1;
    private static String[] team2;

    public wheel() {
        rand = new Random();
        team1 = new String[6];
        team2 = new String[6];
    }

    public void addNames(String n) {
        names.add(n);
    }

    public LinkedList<String> getNames() {
        return names;
    }

    public void randomizeTeams() {
        int t1 = 0, t2 = 0;

        while (t2 + t1 != 12) {
            int team = rand.nextInt(2) + 1;
            String name = names.get(t2 + t1);
            if (team == 1) {
                if (t1 < team1.length) {
                    team1[t1++] = name;
                } else {
                    team2[t2++] = name;
                }
            } else {
                if (t2 < team2.length) {
                    team2[t2++] = name;
                } else {
                    team1[t1++] = name;
                }
            }
        }
    }

    public void showTeams() {
        String[] columnNames = {"Team 1", "Team 2"};
        Table t = new Table(2, BorderStyle.UNICODE_BOX, ShownBorders.ALL);

        t.addCell(columnNames[0]);
        t.addCell(columnNames[1]);

        for (int i = 0; i < 6; i++) {
            t.addCell(team1[i]);
            t.addCell(team2[i]);
        }

        System.out.println(t.render());
    }

    public void clearScreen() {
        Scanner in = new Scanner(System.in);
        System.out.println("Press Enter to continue...");
        in.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
        in.close();
    }
}
