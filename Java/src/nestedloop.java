import java.util.Scanner;

public class nestedloop {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.print("Enter # of rows: ");
        int row = in.nextInt();
        System.out.print("Enter # of columns: ");
        int column = in.nextInt();
        System.out.print("Enter char to use: ");
        String symbol = in.next();

        for(int i = 1; i <= row; i++){
            System.out.println();
            for(int j = 1; j <= column; j++){
                System.out.println(symbol);
            }
        }

        in.close();
    }
}
