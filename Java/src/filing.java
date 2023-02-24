import java.io.File;

public class filing {
    public static void main(String[] args) {
        File file = new File("text.txt");

        if(file.exists()){
            System.out.println("File does exist ");
        }else{
            System.out.println("File does not exist");
        }
    }
}
