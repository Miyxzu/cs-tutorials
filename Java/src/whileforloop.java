import java.util.Scanner;

public class whileforloop {
    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            String name = "";

            while(name.isEmpty()){
                System.out.print("Enter a name: ");
                name = in.nextLine();
            }

            System.out.println("Hello " + name);
        }
        System.out.println();

        for(int i = 0; i <=10; i++){
            System.out.println(i);
        }
        System.out.println();
        for(int l = 10; l >=0; l--){
           System.out.println(l); 
        }
        System.out.println("Happy new years");
    }
}
