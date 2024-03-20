
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
        CSVReader reader = null;
        try {
            reader = new CSVReader(new FileReader(file));
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
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void removeSong(String title, String artist, String album, String playlist) {
        File file = new File("./IB/playlistFolder/" + playlist + ".csv");
        CSVReader reader = null;
        List<String[]> csvBody = new ArrayList<>();
        try {
            reader = new CSVReader(new FileReader(file));
            String[] nextRecord;
            while ((nextRecord = reader.readNext()) != null) {
                if (nextRecord[0].equals(title) && nextRecord[1].equals(artist) && nextRecord[2].equals(album)) {
                    continue;
                }
                csvBody.add(nextRecord);
            }
            reader.close();

            CSVWriter writer = new CSVWriter(new FileWriter(file));
            writer.writeAll(csvBody);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void editSong(String playlist, String title, String artist, String album, String field, String newValue) {
        File file = new File("./IB/playlistFolder/" + playlist + ".csv");
        CSVReader reader = null;
        List<String[]> csvBody = new ArrayList<>();
        try {
            reader = new CSVReader(new FileReader(file));
            String[] nextRecord;
            while ((nextRecord = reader.readNext()) != null) {
                if (nextRecord[0].equals(title) && nextRecord[1].equals(artist) && nextRecord[2].equals(album)) {
                    switch (field.toLowerCase()) {
                        case "title":
                            nextRecord[0] = newValue;
                            break;
                        case "artist":
                            nextRecord[1] = newValue;
                            break;
                        case "album":
                            nextRecord[2] = newValue;
                            break;
                    }
                }
                csvBody.add(nextRecord);
            }
            reader.close();

            CSVWriter writer = new CSVWriter(new FileWriter(file));
            writer.writeAll(csvBody);
            writer.flush();
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

    public void sortPlaylist(String playlist, String field) {
        File file = new File("./IB/playlistFolder/" + playlist + ".csv");
        CSVReader reader = null;
        List<String[]> csvBody = new ArrayList<>();
        try {
            reader = new CSVReader(new FileReader(file));
            String[] nextRecord;
            while ((nextRecord = reader.readNext()) != null) {
                csvBody.add(nextRecord);
            }
            reader.close();

            switch (field.toLowerCase()) {
                case "title":
                    csvBody.sort(Comparator.comparing(a -> a[0]));
                    break;
                case "artist":
                    csvBody.sort(Comparator.comparing(a -> a[1]));
                    break;
                case "album":
                    csvBody.sort(Comparator.comparing(a -> a[2]));
                    break;
            }

            File sortedFile = new File("./IB/playlistFolder/" + playlist + "Sorted.csv");
            CSVWriter writer = new CSVWriter(new FileWriter(sortedFile));
            writer.writeAll(csvBody);
            writer.flush();
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void clearScreen() {
        Scanner in = new Scanner(System.in);
        System.out.println("Press Enter to continue...");
        in.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
