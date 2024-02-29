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
            choice = sc.nextInt();
            switch(choice){
                case 1:
                    System.out.println("Enter the song name: ");
                    song = sc.next();
                    for (String s : songQueue) {
                        if (s.equals(song)) {
                            System.out.println("Song already in queue");
                            break;
                        }
                    }
                    lastsong = song;
                    songQueue.add(song);
                    System.out.println("Song added to queue");
                    break;
                case 2:
                    System.out.println("Enter the song name: ");
                    song = sc.next();
                    songQueue.remove(song);
                    break;
                case 3:
                    System.out.println("Next Track: " + songQueue.peek());
                    break;
                case 4:
                    System.out.println("Latest Track: " + lastsong);
                    break;
                case 5:
                    System.out.println("Queue: " + songQueue);
                    break;
                case 6:
                    songQueue.clear();
                    break;
                case 7:
                    choice = -1;
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}

