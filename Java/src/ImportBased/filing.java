package ImportBased;

import java.io.File;

public class filing {
    public static void main(String[] args) {
        File file = new File("Java/src/ImportBased/text.txt");

        if(file.exists()){
            System.out.println("File does exist ");
            System.out.println(file.getAbsolutePath());
        }else{
            System.out.println("File does not exist");
        }
    }
}
