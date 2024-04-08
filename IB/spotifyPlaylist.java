import java.util.*;
import java.util.stream.*;
import com.opencsv.*;
import java.io.*;
import java.nio.file.*;

public class spotifyPlaylist {
    public static void main(String[] args) throws InterruptedException, IOException {
        spotifyPlaylist sp = new spotifyPlaylist();
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

            String playlist, title, artist, album;
            choice = in.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Playlist Name: ");
                    playlist = in.next();
                    sp.createPlaylist(playlist);
                    sp.clearScreen();
                    break;
                case 2:
                    System.out.println("Playlist: ");
                    playlist = in.next();
                    System.out.println("New name: ");
                    String newName = in.next();
                    sp.renamePlaylist(playlist, newName);
                    sp.clearScreen();
                    break;
                case 3:
                    System.out.println("Playlist to Remove: ");
                    playlist = in.next();
                    sp.removePlaylist(playlist);
                    sp.clearScreen();
                    break;
                case 4:
                    System.out.println("Enter the name of the playlist you want to view: ");
                    System.out.println(sp.listFiles());
                    playlist = in.next();
                    sp.readData(playlist);
                    sp.clearScreen();
                    break;
                case 5:
                    System.out.println("Enter the name of the playlist you want to add a song to: ");
                    playlist = in.next();
                    System.out.println("Enter title: ");
                    title = in.next();
                    System.out.println("Enter artist: ");
                    artist = in.next();
                    System.out.println("Enter album: ");
                    album = in.next();
                    sp.addSong(title, artist, album, playlist);
                    sp.clearScreen();
                    break;
                case 6:
                    System.out.println("Playlist: ");
                    playlist = in.next();
                    System.out.println("Enter title: ");
                    title = in.next();
                    System.out.println("Enter artist: ");
                    artist = in.next();
                    System.out.println("Enter album: ");
                    album = in.next();
                    System.out.println("Field (title, artist, album): ");
                    String field = in.next();
                    System.out.println("New value: ");
                    String newValue = in.next();
                    sp.editSong(playlist, title, artist, album, field, newValue);
                    sp.clearScreen();
                    break;
                case 7:
                    System.out.println("Playlist: ");
                    playlist = in.next();
                    System.out.println("Enter title: ");
                    title = in.next();
                    System.out.println("Enter artist: ");
                    artist = in.next();
                    System.out.println("Enter album: ");
                    album = in.next();
                    sp.removeSong(title, artist, album, playlist);
                    sp.clearScreen();
                    break;
                case 8:
                    System.out.println("Playlist: ");
                    playlist = in.next();
                    System.out.println("Field (title, artist, album): ");
                    String sortField = in.next();
                    sp.sortPlaylist(playlist, sortField);
                    sp.clearScreen();
                    break;
                case 9:
                    choice = -1;
                    break;
                case 10:
                    System.out.println(sp.listFiles());
                    sp.clearScreen();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        System.out.println("Thank you for using the Spotify Playlist System");
        in.close();
    }

    public spotifyPlaylist() throws InterruptedException {
        File dir = new File("./IB/playlistFolder/");
        if (!dir.exists()) {
            dir.mkdir();
        }
        File file = new File("./IB/playlistFolder/default.csv");
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
            if (file.createNewFile()) {
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
            if (file.exists()) {
                if (file.delete()) {
                    System.out.println("Playlist Successfully Removed: " + name);
                } else {
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
        if(!file.exists()){
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
        } else {
            System.out.println("Playlist does not exist");
            return;
        }
    }

    public void removeSong(String title, String artist, String album, String playlist) {
        File file = new File("./IB/playlistFolder/" + playlist + ".csv");
        if (file.exists()) {
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
        } else {
            System.out.println("Playlist does not exist");
            return;
        }
    }

    public void editSong(String playlist, String title, String artist, String album, String field, String newValue) {
        File file = new File("./IB/playlistFolder/" + playlist + ".csv");
        if (file.exists()) {
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
        } else {
            System.out.println("Playlist does not exist");
            return;
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
        File dir = new File("./IB/playlistFolder/sorted/");
        if (!dir.exists()) {
            dir.mkdir();
        }

        try {
            System.out.println("Playlists: ");
            listFiles();
        } catch (Exception e) {
            e.printStackTrace();
        }

        System.out.println();
        File file = new File("./IB/playlistFolder/sorted/" + playlist + ".csv");
        if (file.exists()) {
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
        } else {
            System.out.println("Playlist does not exist");
            return;
        }
    }

    public void clearScreen() {
        Scanner in = new Scanner(System.in);
        System.out.println("Press Enter to continue...");
        in.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public Set<String> listFiles() throws IOException {
        try (Stream<Path> stream = Files.list(Paths.get("./IB/playlistFolder/"))) {
            return stream
                    .filter(file -> !Files.isDirectory(file))
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .collect(Collectors.toSet());
        }
    }

    public List<String[]> listSongs(String playlist) {
        List<String[]> songs = new ArrayList<>();
        try {
            CSVReader csvReader = new CSVReader(new FileReader("./IB/playlistFolderr/" + playlist + ".csv"));
            String[] nextRecord;
            while ((nextRecord = csvReader.readNext()) != null) {
                songs.add(nextRecord);
            }
            csvReader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return songs;
    }
}
