import java.util.Scanner;

public class logical {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter the current temperature: ");
        double temp = in.nextDouble();

        if(temp > 30){
            System.out.println("Feels like hell outside");
        }else if(temp >= 20 && temp <= 30){
            System.out.println("Its warm out, but not burning warm");
        }else if(temp > 10 && temp < 20){
            System.out.println("Its relatively cool I guess you could say");
        }else{
            System.out.println("How are you not cold yet?");
        }
        System.out.println();
        System.out.println();
        System.out.print("The game has paused. Press q or Q to quit or Press c or C to continue: ");
        String response = in.nextLine();

        if(response == "q" || response == "Q"){
            System.out.println("See you next time!");
        }else if(response == "c" || response == "C"){
            System.out.println("You have continued the game");
        }else{
            System.out.println("That is not a valid option");
        }

        in.close();
    }
}
