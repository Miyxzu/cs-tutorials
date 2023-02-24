import java.util.InputMismatchException;
import java.util.Scanner;

public class exception {
    public static void main(String[] args) {
    try{
        Scanner in = new Scanner(System.in);

        System.out.print("Enter an Integer number to divide: ");
        var x = in.nextInt();

        System.out.print("Enter an Integer number to divide: ");
        var y = in.nextInt();

        var z = x / y;
        System.out.println();

        System.out.println("result: " + z);
        System.out.println();

        in.close();
        }
        catch(ArithmeticException arithEx) {
            System.out.println("you can't divide by 0. (ArithmeticException)");
        }
        catch(InputMismatchException inputEx){
            System.out.println("it says integer not string. get glasses. (InputMismatchException)");
        }
        finally{
            System.out.println("End of Compilation");
        }
    }
}
