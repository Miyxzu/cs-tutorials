
import java.util.concurrent.*;
import java.util.*;
import com.opencsv.*;
import java.io.*;

public class spotifyPlaylist {
    public static void main(String[] args) throws InterruptedException {
        spotifyPlaylist sp = new spotifyPlaylist();
        // int choice = 0;
        // Scanner in = new Scanner(System.in);
        // while (choice != -1) {
        //     System.out.println("Welcome to the Spotify Playlist\n" +
        //     "1) Add a song to the queue\n" +
        //     "2) Remove a song from the queue\n" +
        //     "3) See Next Track\n" +
        //     "4) See Latest Track\n" +
        //     "5) View the queue\n" +
        //     "6) Clear the queue\n" +
        //     "7) Exit");
        // }
        sp.readData("test");

        TimeUnit.SECONDS.sleep(5);

        sp.renamePlaylist("test", "testNew");

        // TimeUnit.SECONDS.sleep(2);

        // sp.readData("testNew");
        
        // TimeUnit.SECONDS.sleep(5);

        // sp.removePlaylist("testNew");

        TimeUnit.SECONDS.sleep(2);

        sp.createPlaylist("dsg");
        sp.createPlaylist("anotherTest");

        TimeUnit.SECONDS.sleep(2);

        sp.addSong("Song1", "Artist1", "Album1", "dsg");
        sp.addSong("Song2", "Artist2", "Album2", "dsg");
        sp.addSong("Song3", "Artist3", "Album3", "anotherTest");
        sp.readData("dsg");
        sp.removePlaylist("anotherTest");

        TimeUnit.SECONDS.sleep(5);
        sp.removePlaylist("dsg");
        sp.removePlaylist("testNew");
    }

    public spotifyPlaylist() throws InterruptedException {
        File dir = new File("./IB/playlistFolder");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File("./IB/playlistFolder/test.csv");
        try {
            FileWriter outputfile = new FileWriter(file);
            CSVWriter writer = new CSVWriter(outputfile);
            String[] header = { "Name", "Artist", "Album" };
            writer.writeNext(header);
            // List<String[]> data = new ArrayList<String[]>();
            // data.add(new String[] { "Song1", "Artist1", "Album1" });
            // data.add(new String[] { "Song2", "Artist20", "Album2" });
            // data.add(new String[] { "Song3", "Artist30", "Album30" });
            // data.add(new String[] { "Song40", "Artist4", "Album40" });
            // data.add(new String[] { "Song50", "Artist50", "Album5" });
            // writer.writeAll(data);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void createPlaylist(String name) {
        try {
            File file = new File("./IB/playlistFolder/" + name + ".csv");
            if(file.createNewFile()){
                FileWriter outputfile = new FileWriter(file);
                CSVWriter writer = new CSVWriter(outputfile);
                String[] header = { "Name", "Artist", "Album" };
                writer.writeNext(header);
                writer.close();
                System.out.println("Playlist Successfully Created: " + name);
            } else {
                System.out.println("Playlist already exists: " + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void renamePlaylist(String oldName, String newName) {
        File fileOld = new File("./IB/playlistFolder/" + oldName + ".csv");
        if (fileOld.exists()) {
            File fileNew = new File("./IB/playlistFolder/" + newName + ".csv");
            fileOld.renameTo(fileNew);
            System.out.println("Playlist Successfully Renamed: " + oldName + " -> " + newName);    
        } else {
            System.out.println("Playlist does not exist: " + oldName);
        }
    }

    public void removePlaylist(String name) {
        try {
            File file = new File("./IB/playlistFolder/" + name + ".csv");
            if(file.exists()){
                if(file.delete()){
                    System.out.println("File Exists -> Path: " + file.getCanonicalPath());
                    System.out.println("Playlist Successfully Removed: " + name);
                } else {
                    System.out.println("File Exists -> Path: " + file.getCanonicalPath());
                    System.out.println("Playlist Failed to Remove: " + name);
                }
            } else {
                System.out.println("Playlist does not exist: " + name);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addSong(String title, String artist, String album, String playlist) {
        File file = new File("./IB/playlistFolder/" + playlist + ".csv");
        try {
            CSVReader reader = new CSVReader(new FileReader(file));
            String[] nextRecord;
            while ((nextRecord = reader.readNext()) != null) {
                if (nextRecord[0].equals(title) && nextRecord[1].equals(artist) && nextRecord[2].equals(album)) {
                    System.out.println("Song already exists");
                    return;
                }
            }
            FileWriter outputfile = new FileWriter(file, true);
            CSVWriter writer = new CSVWriter(outputfile);            
            String[] data = { title, artist, album };
            writer.writeNext(data);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readData(String playlist) {
        try {
            CSVReader csvReader = new CSVReader(new FileReader("./IB/playlistFolder/" + playlist + ".csv"));
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                System.out.printf("%-11s %-11s %-11s%n", nextRecord[0], nextRecord[1], nextRecord[2]);
            }
            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void sortPlaylist() {
        
    }

    public void clearScreen() {
        Scanner in = new Scanner(System.in);
        System.out.println("Press Enter to continue...");
        in.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}