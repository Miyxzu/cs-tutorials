import java.util.*;
import com.opencsv.*;
import java.io.*;

public class spotifyPlaylist {
    public static void main(String[] args) {
        spotifyPlaylist sp = new spotifyPlaylist();
        sp.readData();
    }

    File file;
    FileWriter outputfile;
    CSVWriter writer;
    CSVReader reader;

    public spotifyPlaylist() {
        file = new File("./IB/spotifyPlaylist.csv");
        try {
            outputfile = new FileWriter(file);
            writer = new CSVWriter(outputfile);
            String[] header = { "Name", "Artist", "Album" };
            writer.writeNext(header);
            writer.close();
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }
    }

    public void addSong(String title, String artist, String album) {
        try {
            reader = new CSVReader(new FileReader(file));
            String[] nextRecord;
            while ((nextRecord = reader.readNext()) != null) {
                if (nextRecord[0].equals(title) && nextRecord[1].equals(artist) && nextRecord[2].equals(album)) {
                    System.out.println("Song already exists");
                    return;
                }
            }
            outputfile = new FileWriter(file, true);
            writer = new CSVWriter(outputfile);            
            String[] data = { title, artist, album };
            writer.writeNext(data);
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readData() {
        try {
            CSVReader csvReader = new CSVReader(new FileReader("./IB/spotifyPlaylist.csv"));
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                System.out.printf("%-11s %-11s %-11s%n", nextRecord[0], nextRecord[1], nextRecord[2]);
            }
            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
