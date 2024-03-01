import java.util.*;

public class spotifyQueue {
    public static void main(String[] args) {
        Queue<String> songQueue = new LinkedList<String>();
        int choice = 0;
        Scanner sc = new Scanner(System.in);
        while(choice != -1){
            System.out.println("Welcome to the Spotify Queue System\n" +
                            "1) Add a song to the queue\n" +
                            "2) Remove a song from the queue\n" +
                            "3) See Next Track\n" +
                            "4) See Latest Track\n" +
                            "5) View the queue\n" +
                            "6) Clear the queue\n" +
                            "7) Exit");
            String song, lastsong = "";
            Boolean found = false;
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Enter the song name: ");
                    song = sc.next();

                    for (String s : songQueue) {
                        if (s.equals(song)) {
                            System.out.println("Song already in queue");
                            found = true;
                            clearScreen();
                            break;
                        }
                    }

                    if (!found) {
                        songQueue.add(song);
                        lastsong = song;
                        System.out.println("Song added to queue");
                        clearScreen();
                    } else {
                        break;
                    }
                case 2:
                    System.out.println("Queue: ");
                    for (String s : songQueue) {
                        System.out.println(s);
                    }

                    System.out.println("What song would you like to remove?");
                    song = sc.next();

                    for(String s : songQueue){
                        if (s.equals(song)) {
                            songQueue.remove(song);
                            System.out.println("Song removed from queue");
                            found = true;
                            clearScreen();
                            break;
                        }
                    }
                    
                    if (!found) {
                        System.out.println("Song not found in queue");
                        clearScreen();
                        break;
                    } else {
                        break;
                    }
                case 3:
                    System.out.println("Next Track: " + songQueue.peek());
                    clearScreen();
                    break;
                case 4:
                    System.out.println("Latest Track: " + lastsong);
                    clearScreen();
                    break;
                case 5:
                    System.out.println("Queue: " + songQueue);
                    clearScreen();
                    break;
                case 6:
                    songQueue.clear();
                    System.out.println("Queue cleared");
                    clearScreen();
                    break;
                case 7:
                    choice = -1;
                    System.out.print("\033[H\033[2J");
                    System.out.flush();
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
        System.out.println("Thank you for using the Spotify Queue System");
    }

    static void clearScreen() {
        Scanner sc = new Scanner(System.in); // Declare and initialize the Scanner object
        System.out.println("Press Enter to continue");
        sc.nextLine();
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}

