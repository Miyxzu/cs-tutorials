import java.util.Arrays;
import java.util.Scanner;

public class arrays {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        //Creating Arrays via Assigning
        String[] thegents = {"Taerim", "Rishahb", "Connor"};

        thegents[0] = "Jean";

        System.out.println(thegents[2]);

        System.out.println();

        //Creating Arrays via elements
        String[] team = new String[4];

        
        team[0] = "Cam";
        team[1] = "Peep";
        team[2] = "Jefe";
        team[3] = "";

        System.out.print("Enter a new teammate: ");
        team[3] = in.next();

        //Print list of inputed data in array
        System.out.println(Arrays.toString(team));

        in.close();
    }
}
