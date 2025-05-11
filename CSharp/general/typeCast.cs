namespace CSharp;
public class typeCast
{
    public static void Mainx(string[] args)
    {
        double pi = 3.141592653589793238462643383279502884197169399375105820974944592307816406286;
        Console.WriteLine("pi = " + pi);
        int intPi = Convert.ToInt32(pi);
        Console.WriteLine("intPi = " + intPi);

        int blaze = 420;
        Console.WriteLine("blaze   = " + blaze);
        int doubleBlaze = Convert.ToInt32(blaze);
        Console.WriteLine("doubleBlaze = " + doubleBlaze);


    }
}
