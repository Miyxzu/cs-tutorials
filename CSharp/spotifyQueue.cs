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
                Console.WriteLine("Welcome to Spotify Queue System");
                Console.WriteLine("1) Queue Song");
                Console.WriteLine("2) Dequeue Song");
                Console.WriteLine("3) See Next Track");
                Console.WriteLine("4) Latest Added Song");
                Console.WriteLine("5) See Queue");
                Console.WriteLine("6) Exit");
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
                                break;
                            }
                            else
                            {
                                continue;
                            }
                        }
                        songQueue.Enqueue(song);
                        Console.Clear();
                        break;
                    case 2:
                        foreach (string s in songQueue)
                        {
                            Console.WriteLine(s);
                        }
                        Console.WriteLine("What song do you want to remove?");
                        string removeSong = Console.ReadLine();
                        if (songQueue.Contains(removeSong))
                        {
                            songQueue = new Queue<string>(songQueue.ToArray().Where(val => val != removeSong));
                        }
                        else
                        {
                            Console.WriteLine("Song not in Queue");
                        }
                        Console.Clear();                       
                        break;
                    case 3:
                        Console.WriteLine("Next Song is: " + songQueue.Peek());
                        Console.WriteLine("Press Enter to Continue");
                        Console.ReadLine();
                        Console.Clear();
                        break;
                    case 4:
                        Console.WriteLine("Latest Song is: " + songQueue.ToArray()[songQueue.Count - 1]);
                        Console.WriteLine("Press Enter to Continue");
                        Console.ReadLine();
                        Console.Clear();
                        break;
                    case 5:
                        Console.WriteLine("Queue is: ");
                        foreach (string s in songQueue)
                        {
                            Console.WriteLine(s);
                        }
                        Console.WriteLine("Press Enter to Continue");
                        Console.ReadLine();
                        Console.Clear();
                        break;
                    case 6:
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
    }
}