using System;
using System.Collections.Generic;
namespace CSharp
{
    class spotifyQueue
    {
        static void Main(string[] args)
        {
            Queue<string> songQueue = new Queue<string>();
            int choice = 0;
            while (choice != -1)
            {
                Console.WriteLine("Welcome to the Spotify Queue System");
                Console.WriteLine("1) Queue Song");
                Console.WriteLine("2) Dequeue Song");
                Console.WriteLine("3) See Next Track");
                Console.WriteLine("4) Latest Added Song");
                Console.WriteLine("5) See Queue");
                Console.WriteLine("6) Clear Queue");
                Console.WriteLine("7) Exit");
                choice = Convert.ToInt32(Console.ReadLine());

                switch (choice)
                {
                    case 1:
                        Console.WriteLine("Enter Song Name");
                        string song = Console.ReadLine();
                        foreach (string s in songQueue)
                        {
                            if (s == song)
                            {
                                Console.WriteLine("Song Already in Queue");
                                song = null;
                                clearScreen();
                                break;
                            }
                        }
                        songQueue.Enqueue(song);
                        Console.WriteLine("Song Queued");
                        clearScreen();
                        break;
                    case 2:
                        Console.WriteLine("Queue: ");
                        foreach (string s in songQueue)
                        {
                            Console.WriteLine(s);
                        }
                        Console.WriteLine("What song do you want to remove?");
                        string removeSong = Console.ReadLine();
                        if (songQueue.Contains(removeSong))
                        {
                            songQueue = new Queue<string>(songQueue.ToArray().Where(val => val != removeSong));
                            Console.WriteLine("Song Removed");
                            clearScreen();
                            break;
                        }
                        Console.WriteLine("Song not in Queue");
                        clearScreen();
                        break;
                    case 3:
                        Console.WriteLine("Next Song is: " + songQueue.Peek());
                        clearScreen();
                        break;
                    case 4:
                        Console.WriteLine("Latest Song is: " + songQueue.ToArray()[songQueue.Count - 1]);
                        clearScreen();
                        break;
                    case 5:
                        Console.WriteLine("Queue is: ");
                        foreach (string s in songQueue)
                        {
                            Console.WriteLine(s);
                        }
                        clearScreen();
                        break;
                    case 6:
                        songQueue.Clear();
                        Console.WriteLine("Queue Cleared");
                        clearScreen();
                        break;
                    case 7:
                        choice = -1;
                        Console.Clear();
                        break;
                    default:
                        Console.WriteLine("Invalid Choice");
                        break;
                }
            }
            Console.WriteLine("Thank You for using Spotify Queue System");
        }

        static void clearScreen()
        {
            Console.WriteLine("Press any key to continue");
            Console.ReadKey();
            Console.Clear();
        }
    }
}