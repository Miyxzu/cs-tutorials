package otherStuff;

import java.util.*;
import java.io.*;

public class codegen {
    private static LinkedList<Integer> code;

    public static void main(String[] args) {
        code = new LinkedList<>();
        Random rand = new Random();
        long startTime = System.currentTimeMillis();

        for (int i = 0; i < 415471; i++) {
            int num = rand.nextInt(100000, 515470);
            if (code.contains(num)) {
                i--;
            } else {
                System.out.println(num);
                code.add(num);
            }
        }

        try {
            FileWriter writer = new FileWriter("C:/Users/exsu/Development/cs-tutorials/Java/src/otherStuff/output.txt");
            for (int num : code) {
            writer.write(num + " ");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }

        System.out.println("Time: " + (System.currentTimeMillis() - startTime) / 1000.0 + " seconds");
    }
}
