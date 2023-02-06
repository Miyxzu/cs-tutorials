import java.util.Scanner;

public class exception {
    public static void main(String[] args) {
    try{
        Scanner in = new Scanner(System.in);

        System.out.print("Enter an integer number to divide: ");
        var x = in.nextInt();

        System.out.print("Enter an integer number to divide: ");
        var y = in.nextInt();

        var z = x / y;

        System.out.println("result: " + z);
        }
        catch(ArithmeticException ex) {
            System.out.println("you can't divide by 0. (ArithmeticException)");
        }
    }
}
