import java.util.Scanner;

public class switchstate {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Enter top player name (case sensitive): ");
        String player = in.nextLine();

        switch(player) {
            case "Mrekk": System.out.println("mark");
            break;
            case "Whitecat": System.out.println("the german guy");
            break;
            case "Utami": System.out.println("vaxei with nerves");
            break;
            case "Shigetora": System.out.println("shige");
            break;
            case "Ryuk": System.out.println("ryan kwan");
            break;
            case "Merami": System.out.println("aetrna");
            break;
            case "Sytho": System.out.println("seetho");
            break;
            default: System.out.println("That is not a top player");

            in.close();
        }
    }
}
