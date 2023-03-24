using System;
namespace CSharp
{
    public class Program
    {
        public static void Main(string[] args)
        {
            String x = "This is fun!";

            for(int i = 0; i < x.Length; i++)
            {
                Console.Write(x[i] + " ");
            }
        }
    }
}