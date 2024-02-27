using System;
namespace CSharp
{
    public class spotifyQueue
    {
        public static void mainSpoQ(string[] args)
        {
            int choice = 0;
            while(choice != -1){
                console.WriteLine("Welcome to Spotify Queue System");
                console.WriteLine("1) Queue Song");
                console.WriteLine("2) Dequeue Song");
                console.WriteLine("3) See Next Track");
                console.WriteLine("4) Exit");
                choice = Convert.ToInt32(Console.ReadLine());
            }
        }
    }
}