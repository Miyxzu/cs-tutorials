package ImportBased;

import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.FileNotFoundException;
import java.io.IOException;

public class filingRead {
    public static void main(String[] args) {
        try {
            Scanner in = new Scanner(System.in);

            System.out.print("Enter the path of the file you want to read from: ");
            String path = in.nextLine();

            System.out.println();

            File file = new File(path);
            FileReader read = new FileReader(file);
            int i;
            if (file.exists()) {
                System.out.println("File does exist");
                System.out.println("Notice: Reading from file " + file.getName());
                System.out.println();
                System.out.println("File contents: ");
                while ((i = read.read()) != -1) {
                    System.out.print((char) i);
                }
            } else {
                System.out.println("File does not exist");
            }
            read.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// Path: /Users/66000/cs-tutorials/Java/src/ImportBased/kickback.txt